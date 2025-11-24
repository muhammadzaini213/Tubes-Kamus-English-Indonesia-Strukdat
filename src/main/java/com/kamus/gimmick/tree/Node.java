package com.kamus.gimmick.tree;

public class Node {

    private String key;     // kata sumber
    private String value;   // terjemahan

    private Node left;
    private Node right;
    private Node parent;

    private boolean isRed = true; // new node always red

    public Node(String key, String value) {
        this.key = key.toLowerCase();  // optional normalization
        this.value = value;
    }

    // ===== GETTERS =====
    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
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

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setRed(boolean red) {
        isRed = red;
    }
}
