package com.kamus.gimmick.tree;

import com.kamus.gimmick.dictionary.DictionaryInterface;

public class RedBlackTree {
    private Node root;
    private int size;

    // ================ GETTERS ================
    public Node getRoot() {
        return root;
    }


    public int getSize() {
        return size;
    }


    // ================ SEARCH ================
    public String find(String key) {
        if (key == null) {
            return null;
        }
        if (root == null) {
            return null;
        }

        return findNode(root, key);
    }


    public String findNode(Node current, String key) {
        if (current == null) {
            return null;
        }

        int comparison = key.toLowerCase().compareTo(current.getKey());

        if (comparison < 0) {
            return findNode(current.getLeft(), key);
        } else if (comparison > 0) {
            return findNode(current.getRight(), key);
        } else {
            return current.getValue();
        }
    }


    // ================ LOADER ================
    public boolean loadAllData(String csvName) {
        try {
            if (this instanceof DictionaryInterface) {
                int loadedCount = ((DictionaryInterface) this).loadFromCSV(csvName);
                return loadedCount > 0;
            }
            return false;
        } catch (Exception e) {
            System.err.println("Error loading data from CSV: " + e.getMessage());
            return false;
        }
    }


    protected boolean insert(String key, String value) {
        if (key == null || value == null) {
            return false;
        }

        Node newNode = new Node(key, value);

        if (root == null) {
            root = newNode;
            root.setRed(false); // akar hytam
            size++;
            return true;
        }

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            int comparison = key.toLowerCase().compareTo(current.getKey());

            if (comparison < 0) {
                current = current.getLeft();
            } else if (comparison > 0) {
                current = current.getRight();
            } else {
                // duplicate key
                return false;
            }
        }

        // Insert
        newNode.setParent(parent);
        int comparison = key.toLowerCase().compareTo(parent.getKey());

        if (comparison < 0) {
            parent.setLeft(newNode);
        } else {
            parent.setRight(newNode);
        }

        size++;
        balanceRBT(newNode);
        return true;
    }


    // ================ BALANCE ================
    private void balanceRBT(Node node) {
        // New nodes are always red
        node.setRed(true);

        while (node != null && node != root && node.getParent().isRed()) {
            if (node.getParent() == node.getParent().getParent().getLeft()) {
                Node uncle = node.getParent().getParent().getRight();

                if (uncle != null && uncle.isRed()) {
                    // Case 1: Uncle is red - recolor
                    node.getParent().setRed(false);
                    uncle.setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    // Uncle is black
                    if (node == node.getParent().getRight()) {
                        // Case 2: Node is right child - left rotate
                        node = node.getParent();
                        rotateLeft(node);
                    }
                    // Case 3: Node is left child - right rotate
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    rotateRight(node.getParent().getParent());
                }
            } else {
                Node uncle = node.getParent().getParent().getLeft();

                if (uncle != null && uncle.isRed()) {
                    // Case 1: Uncle is red - recolor
                    node.getParent().setRed(false);
                    uncle.setRed(false);
                    node.getParent().getParent().setRed(true);
                    node = node.getParent().getParent();
                } else {
                    // Uncle is black
                    if (node == node.getParent().getLeft()) {
                        // Case 2: Node is left child - right rotate
                        node = node.getParent();
                        rotateRight(node);
                    }
                    // Case 3: Node is right child - left rotate
                    node.getParent().setRed(false);
                    node.getParent().getParent().setRed(true);
                    rotateLeft(node.getParent().getParent());
                }
            }
        }

        // Root must always be black
        root.setRed(false);
    }


    private void rotateLeft(Node x) {
        if (x == null) return;

        Node y = x.getRight();
        x.setRight(y.getLeft());

        if (y.getLeft() != null) {
            y.getLeft().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }

        y.setLeft(x);
        x.setParent(y);
    }


    private void rotateRight(Node x) {
        if (x == null) return;

        Node y = x.getLeft();
        x.setLeft(y.getRight());

        if (y.getRight() != null) {
            y.getRight().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getRight()) {
            x.getParent().setRight(y);
        } else {
            x.getParent().setLeft(y);
        }

        y.setRight(x);
        x.setParent(y);
    }


    // ================ DEBUG ================
    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        printTreeHelper(root, "", true);
    }


    private void printTreeHelper(Node node, String prefix, boolean isTail) {
        if (node == null) {
            return;
        }

        // Print current node
        String color = node.isRed() ? "\u001B[31m" : "\u001B[37m"; // Red or White (black won't show in dark terminal)
        String reset = "\u001B[0m";

        System.out.println(prefix + (isTail ? "└── " : "├── ") + color + node.getKey() + reset);

        // Print children
        if (node.getLeft() != null || node.getRight() != null) {
            if (node.getRight() != null) {
                printTreeHelper(node.getRight(), prefix + (isTail ? "    " : "│   "), false);
            } else {
                System.out.println(prefix + (isTail ? "    " : "│   ") + "├── null");
            }

            if (node.getLeft() != null) {
                printTreeHelper(node.getLeft(), prefix + (isTail ? "    " : "│   "), true);
            } else {
                System.out.println(prefix + (isTail ? "    " : "│   ") + "└── null");
            }
        }
    }


    public void printInOrder(Node n, boolean showValues) {
        if (n == null) {
            return;
        }

        printInOrder(n.getLeft(), showValues);

        if (showValues) {
            System.out.println(n.getKey() + " : " + n.getValue());
        } else {
            System.out.println(n.getKey());
        }

        printInOrder(n.getRight(), showValues);
    }

}
