package com.xiongs.dataway.model;

/**
 * @version v1.0
 * @description:
 * @author: xiong_s on 2020/5/14 17:37
 */
public class BinaryTree {
    private TreeNode root = null;

    public BinaryTree() {
    }

    //构建
    public BinaryTree build(int[] list) {
        //遍历源数据，新增根节点或调用新增方法插入
        for (int t : list) {
            if (root == null) {
                root = new TreeNode(t);
                continue;
            }
            put(t);
        }
        return this;
    }

    //查询
    public TreeNode select(int v) {
        //从跟节点开始查询
        return select(root, v);
    }

    private TreeNode select(TreeNode node, int v) {
        if (node == null) {
            throw new RuntimeException("value non-existent Exception");
        }
        //根据查询值和当前节点值对比，相同则返回，否则继续递归
        if (v > node.getValue()) {
            return select(node.getRightNode(), v);
        } else if (v == node.getValue()) {
            return node;
        } else {
            return select(node.getLeftNode(), v);
        }
    }

    //新增节点
    public boolean put(int value) {
        //判断跟节点是否为空，空则新增并返回成功
        if (root == null){
            root = new TreeNode(value);
            return true;
        }
        //从根节点递归添加
        if (value > root.getValue()) {
            return add2Right(root, value);
        } else if (value == root.getValue()) {
            throw new RuntimeException("value empty exception");
        } else {
            return add2Left(root, value);
        }
    }

    //删除
    public void deleted(int value) {
        //从根节点开始排查
        deleted(root, value);
    }

    private void deleted(TreeNode node, int value) {
        //当前节点值与删除值相同则执行删除操作
        if (value == node.getValue()) {
            //del
            TreeNode parentNode = getParentNode(node);
            if (parentNode == null) {
                root = null;
                return;
            }

            //判断子节点数量。0的话直接删除
            if (node.childrenNum == 0) {
                if (node.equals(parentNode.getLeftNode())) {
                    parentNode.setLeftNode(null);
                } else {
                    parentNode.setRightNode(null);
                }
                //子节点数量为1时直接删除将本节点的父节点指向本节点的子节点
            } else if (node.childrenNum == 1) {
                TreeNode childrenNode = node.getOnlyChildren();
                //判断本节点是父节点的左子节点还是右子节点
                if (node.equals(parentNode.getLeftNode())) {
                    parentNode.setLeftNode(childrenNode);
                } else {
                    parentNode.setRightNode(childrenNode);
                }
            }else {//有两种方案，1.把右子树往上提，左子树作为上提后的的最左子树、2.把左子树往上提，右子树作为上提后的最右子树
                TreeNode newTreeNode = node.getRightNode();
                TreeNode mostLeftNode = newTreeNode.getMostLeftNode();
                mostLeftNode.setLeftNode(node.getLeftNode());
                if (node.equals(parentNode.getLeftNode())) {
                    parentNode.setLeftNode(newTreeNode);
                } else {
                    parentNode.setRightNode(newTreeNode);
                }
            }

        } else if (value > node.getValue()) {
            if (node.getRightNode() != null) {
                deleted(node.getRightNode(), value);
            } else {
                throw new RuntimeException("value empty exception");
            }
        } else {
            if (node.getLeftNode() != null) {
                deleted(node.getLeftNode(), value);
            } else {
                throw new RuntimeException("value empty exception");
            }
        }
    }


    public TreeNode getParentNode(TreeNode node) {
        if (node == null) {
            throw new RuntimeException("NullPointerException at " + this.getClass().getName());
        }
        //从根节点开始遍历
        return getParentNode(root, node);
    }
    //第一个参数为树节点，第二参数为查询节点
    private TreeNode getParentNode(TreeNode root, TreeNode node) {
        //当前节点的左右子节点存在与查询节点相同，则返回当前节点。此处的equals需要重写。不重写的话应该用值进行比较
        if (node.equals(root.getRightNode()) || node.equals(root.getLeftNode())) {
            return root;
        }
        //根据节点值得比较进行不同方向的递归
        if (node.getValue() > root.getValue()) {
            return getParentNode(root.getRightNode(), node);
        } else {
            return getParentNode(root.getLeftNode(), node);
        }
    }

    //往传入节点的左边添加节点
    private boolean add2Right(TreeNode node, int v) {
        //传入节点没有右节点则添加节点
        if (node.getRightNode() == null) {
            node.setRightNode(new TreeNode(v));
            node.childrenNum++;
            return true;
        }
        //与传入节点的右节点比较，相等抛出异常，大于则递归往右添加，小于则往左添加
        if (v > node.getRightNode().getValue()) {
            return add2Right(node.getRightNode(), v);
        } else if (v == node.getRightNode().getValue()) {
            throw new RuntimeException("value empty exception");
        } else {
            return add2Left(node.getRightNode(), v);
        }
    }
    //往传入节点的左边添加节点
    private boolean add2Left(TreeNode node, int v) {
        //传入节点没有左节点则添加节点
        if (node.getLeftNode() == null) {
            node.setLeftNode(new TreeNode(v));
            node.childrenNum++;
            return true;
        }
        //与传入节点的左节点比较，相等抛出异常，大于则递归往右添加，小于则往左添加
        if (v > node.getLeftNode().getValue()) {
            return add2Right(node.getLeftNode(), v);
        } else if (v == node.getLeftNode().getValue()) {
            throw new RuntimeException("value empty exception");
        } else {
            return add2Left(node.getLeftNode(), v);
        }
    }


    public boolean check(){
        return check(root);
    }

    private boolean check(TreeNode root) {
        if (root.getRightNode()!=null){
            if (root.getValue()<root.getRightNode().getValue()){
                return check(root.getRightNode());
            }else {
                return false;
            }
        }
        if (root.getLeftNode()!=null){
            if (root.getValue()>root.getLeftNode().getValue()){
                return check(root.getLeftNode());
            }else {
                return false;
            }
        }
        return true;
    }

    public TreeNode getRoot() {
        return root;
    }


    public void setRoot(TreeNode root) {
        this.root = root;
    }
}
