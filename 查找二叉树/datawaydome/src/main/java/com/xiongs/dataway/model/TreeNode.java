package com.xiongs.dataway.model;

/**
 * @version v1.0
 * @description:
 * @author: xiong_s on 2020/5/14 17:33
 */
public class TreeNode {
    private int value;
    public int childrenNum = 0;
    private TreeNode leftNode;
    private TreeNode rightNode;

    public TreeNode(int value) {
        this.value = value;
    }

    public void add2Right(int v) {
        if (this.rightNode == null) {
            this.rightNode = new TreeNode(v);
        } else {
            if (v > this.rightNode.getValue()) {
                this.rightNode.add2Right(v);
            } else if (v == this.rightNode.getValue()) {
                throw new RuntimeException("value empty exception");
            } else {
                this.rightNode.add2Left(v);
            }
        }
    }

    public void add2Left(int v) {
        if (this.leftNode == null) {
            this.leftNode = new TreeNode(v);
        } else {
            if (v > this.leftNode.getValue()) {
                this.leftNode.add2Right(v);
            } else if (v == this.leftNode.getValue()) {
                throw new RuntimeException("value empty exception");
            } else {
                this.leftNode.add2Left(v);
            }
        }
    }


    public int getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(int childrenNum) {
        this.childrenNum = childrenNum;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(TreeNode leftNode) {
        this.leftNode = leftNode;
    }

    public TreeNode getRightNode() {
        return rightNode;
    }

    public void setRightNode(TreeNode rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TreeNode)) {
            return false;
        }
        TreeNode o = (TreeNode) obj;
        return this.getValue() == o.getValue();
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "value=" + value +
                ", childrenNum=" + childrenNum +
                ", leftNode=" + leftNode +
                ", rightNode=" + rightNode +
                '}';
    }

    public TreeNode getOnlyChildren() {
        if (this.getLeftNode() != null && this.getRightNode() != null) {
            throw new RuntimeException("Not Only Children Exception");
        }
        if (this.getLeftNode() == null && this.getRightNode() == null){
            throw new RuntimeException("Null Children Exception");
        }
        return this.getLeftNode() == null ? this.getRightNode() : this.getLeftNode();
    }

    public TreeNode getMostLeftNode() {
        if (this.getLeftNode()==null){
            return this;
        }else {
            return this.getLeftNode().getMostLeftNode();
        }
    }
    public TreeNode getMostRightNode() {
        if (this.getRightNode()==null){
            return this;
        }else {
            return this.getRightNode().getMostRightNode();
        }
    }
}
