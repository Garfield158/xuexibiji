package com.xiongs.dataway.model;

import java.util.List;

/**
 * @version v1.0
 * @description:
 * @author: xiong_s on 2020/5/14 17:37
 */
public class HashBinaryTree<T> {
    private HashTreeNode<T> root = null;

    public HashBinaryTree() {
    }

    //构建
    public HashBinaryTree<T> build(List<T> list) {
        for (T t : list) {
            if (root == null) {
                root = new HashTreeNode<>(t);
                continue;
            }
            put(t);
        }
        return this;
    }

    //查询
    public HashTreeNode<T> select(T v) {
        return select(root, v);
    }

    //新增
    public boolean put(T value) {
        if (root == null) {
            root = new HashTreeNode<>(value);
            return true;
        }
        if (value.hashCode() > root.getValue().hashCode()) {
            return add2Right(root, value);
        } else if (value == root.getValue()) {
            throw new RuntimeException("value empty exception");
        } else {
            return add2Left(root, value);
        }
    }

    //删除
    public void deleted(T value) {
        deleted(root, value);
    }

    private void deleted(HashTreeNode<T> node, T value) {
        if (value == node.getValue()) {
            //del
            HashTreeNode<T> parentNode = getParentNode(node);
            if (parentNode == null) {
                root = null;
                return;
            }

            if (node.childrenNum == 0) {
                if (node.equals(parentNode.getLeftNode())) {
                    parentNode.setLeftNode(null);
                } else {
                    parentNode.setRightNode(null);
                }

            } else if (node.childrenNum == 1) {
                HashTreeNode<T> childrenNode = node.getOnlyChildren();
                if (node.equals(parentNode.getLeftNode())) {
                    parentNode.setLeftNode(childrenNode);
                } else {
                    parentNode.setRightNode(childrenNode);
                }
            } else {//有两种方案，1.把右子树往上提，左子树作为上提后的的最左子树、2.把左子树往上提，右子树作为上提后的最右子树
                HashTreeNode<T> newTreeNode = node.getRightNode();
                HashTreeNode<T> mostLeftNode = newTreeNode.getMostLeftNode();
                mostLeftNode.setLeftNode(node.getLeftNode());
                if (node.equals(parentNode.getLeftNode())) {
                    parentNode.setLeftNode(newTreeNode);
                } else {
                    parentNode.setRightNode(newTreeNode);
                }
            }

        } else if (value.hashCode() > node.getValue().hashCode()) {
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


    public HashTreeNode<T> getParentNode(HashTreeNode<T> node) {
        if (node == null) {
            throw new RuntimeException("NullPointerException at " + this.getClass().getName());
        }
        return getParentNode(root, node);
    }

    private HashTreeNode<T> getParentNode(HashTreeNode<T> root, HashTreeNode<T> node) {
        if (node.equals(root.getRightNode()) || node.equals(root.getLeftNode())) {
            return root;
        }
        if (node.getValue().hashCode() > root.getValue().hashCode()) {
            return getParentNode(root.getRightNode(), node);
        } else {
            return getParentNode(root.getLeftNode(), node);
        }
    }


    private boolean add2Right(HashTreeNode<T> node, T v) {
        if (node.getRightNode() == null) {
            node.setRightNode(new HashTreeNode<T>(v));
            node.childrenNum++;
            return true;
        }
        if (v.hashCode() > node.getRightNode().getValue().hashCode()) {
            return add2Right(node.getRightNode(), v);
        } else if (v == node.getRightNode().getValue()) {
            throw new RuntimeException("value empty exception");
        } else {
            return add2Left(node.getRightNode(), v);
        }
    }

    private boolean add2Left(HashTreeNode<T> node, T v) {
        if (node.getLeftNode() == null) {
            node.setLeftNode(new HashTreeNode<>(v));
            node.childrenNum++;
            return true;
        }
        if (v.hashCode() > node.getLeftNode().getValue().hashCode()) {
            return add2Right(node.getLeftNode(), v);
        } else if (v == node.getLeftNode().getValue()) {
            throw new RuntimeException("value empty exception");
        } else {
            return add2Left(node.getLeftNode(), v);
        }
    }


    private HashTreeNode<T> select(HashTreeNode<T> node, T v) {
        if (node == null) {
            throw new RuntimeException("value non-existent Exception");
        }
        if (v.hashCode() > node.getValue().hashCode()) {
            return select(node.getRightNode(), v);
        } else if (v == node.getValue()) {
            return node;
        } else {
            return select(node.getLeftNode(), v);
        }
    }


    public boolean check() {
        return check(root);
    }

    private boolean check(HashTreeNode<T> root) {
        if (root.getRightNode() != null) {
            if (root.getValue().hashCode() < root.getRightNode().getValue().hashCode()) {
                return check(root.getRightNode());
            } else {
                return false;
            }
        }
        if (root.getLeftNode() != null) {
            if (root.getValue().hashCode() > root.getLeftNode().getValue().hashCode()) {
                return check(root.getLeftNode());
            } else {
                return false;
            }
        }
        return true;
    }

    public HashTreeNode<T> getRoot() {
        return root;
    }


    public void setRoot(HashTreeNode<T> root) {
        this.root = root;
    }
}
