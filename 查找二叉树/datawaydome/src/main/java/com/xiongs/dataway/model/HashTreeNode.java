package com.xiongs.dataway.model;

/**
 * @version v1.0
 * @description:
 * @author: xiong_s on 2020/5/14 17:33
 */
public class HashTreeNode<T> {
    private T value;
    public int childrenNum = 0;
    private HashTreeNode<T> leftNode;
    private HashTreeNode<T> rightNode;

    public HashTreeNode(T value) {
        this.value = value;
    }




    public int getChildrenNum() {
        return childrenNum;
    }

    public void setChildrenNum(int childrenNum) {
        this.childrenNum = childrenNum;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public HashTreeNode<T> getLeftNode() {
        return leftNode;
    }

    public void setLeftNode(HashTreeNode<T> leftNode) {
        this.leftNode = leftNode;
    }

    public HashTreeNode<T> getRightNode() {
        return rightNode;
    }

    public void setRightNode(HashTreeNode<T> rightNode) {
        this.rightNode = rightNode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof HashTreeNode)) {
            return false;
        }
        HashTreeNode<T> o = (HashTreeNode<T>) obj;
        return this.getValue().hashCode() == o.getValue().hashCode();
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

    public HashTreeNode<T> getOnlyChildren() {
        if (this.getLeftNode() != null && this.getRightNode() != null) {
            throw new RuntimeException("Not Only Children Exception");
        }
        if (this.getLeftNode() == null && this.getRightNode() == null){
            throw new RuntimeException("Null Children Exception");
        }
        return this.getLeftNode() == null ? this.getRightNode() : this.getLeftNode();
    }

    public HashTreeNode<T> getMostLeftNode() {
        if (this.getLeftNode()==null){
            return this;
        }else {
            return this.getLeftNode().getMostLeftNode();
        }
    }
    public HashTreeNode<T> getMostRightNode() {
        if (this.getRightNode()==null){
            return this;
        }else {
            return this.getRightNode().getMostRightNode();
        }
    }
}
