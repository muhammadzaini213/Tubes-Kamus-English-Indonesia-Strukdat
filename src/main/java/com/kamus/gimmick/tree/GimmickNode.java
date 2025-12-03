package com.kamus.gimmick.tree;

import com.kamus.gimmick.easteregg.GimmickInterface;

public class GimmickNode {

    private String key;
    private String value;
    private GimmickInterface className;
    private GimmickNode left;
    private GimmickNode right;
    private GimmickNode parent;

    private boolean isRed = true;
    
    public GimmickNode(String key, String value, GimmickInterface className) {
        this.key = key.toLowerCase();
        this.value = value;
        this.className = className;
    }

    // ===== GETTERS =====
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public GimmickInterface getClassName() {
        return className;
    }

    public GimmickNode getLeft() {
        return left;
    }

    public GimmickNode getRight() {
        return right;
    }

    public GimmickNode getParent() {
        return parent;
    }

    public boolean isRed() {
        return isRed;
    }

    // ===== SETTERS =====
    public void setKey(String key) {
        this.key = key.toLowerCase();
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLeft(GimmickNode left) {
        this.left = left;
    }

    public void setRight(GimmickNode right) {
        this.right = right;
    }

    public void setParent(GimmickNode parent) {
        this.parent = parent;
    }

    public void setRed(boolean red) {
        isRed = red;
    }
}