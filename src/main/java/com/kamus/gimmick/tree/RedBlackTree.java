package com.kamus.gimmick.tree;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

import com.kamus.gimmick.MainController;
import com.kamus.gimmick.easteregg.GimmickInterface;
import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

public class RedBlackTree {
    private GimmickNode root;
    private int size;

    // ================ GETTERS ================
    public GimmickNode getRoot() {
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

        return findGimmickNode(root, key);
    }

    public String findGimmickNode(GimmickNode current, String key) {
        if (current == null) {
            return null;
        }

        int comparison = key.toLowerCase().compareTo(current.getKey());

        if (comparison < 0) {
            return findGimmickNode(current.getLeft(), key);
        } else if (comparison > 0) {
            return findGimmickNode(current.getRight(), key);
        } else {
            return current.getValue();
        }
    }

    // ================ LOADER ================
    public boolean loadAllData(String csvName, String keyCol, String valueCol)
            throws IOException, CsvValidationException {
        CSVReaderHeaderAware reader = new CSVReaderHeaderAware(new FileReader(csvName));
        Map<String, String> values;
        int count = 0;

        while ((values = reader.readMap()) != null) {
            String key = values.get(keyCol);
            String value = values.get(valueCol);
            String gimmick = values.get("gimmick");
            if (gimmick == null) {
                gimmick = "";
            }

            if (key != null && value != null) {
                if (insert(key, value, gimmick)) {
                    count++;
                }
            }
        }

        reader.close();

        if (count > 0)
            return true;
        return false;
    }

    protected boolean insert(String key, String value, String className) {
        if (key == null || value == null) {
            return false;
        }

        GimmickNode newGimmickNode = new GimmickNode(key, value, className);

        if (root == null) {
            root = newGimmickNode;
            root.setRed(false); // akar hytam
            size++;
            return true;
        }

        GimmickNode current = root;
        GimmickNode parent = null;

        while (current != null) {
            parent = current;
            int comparison = key.toLowerCase().compareTo(current.getKey());

            if (comparison < 0) {
                current = current.getLeft();
            } else if (comparison > 0) {
                current = current.getRight();
            } else {
                return false;
            }
        }

        newGimmickNode.setParent(parent);
        int comparison = key.toLowerCase().compareTo(parent.getKey());

        if (comparison < 0) {
            parent.setLeft(newGimmickNode);
        } else {
            parent.setRight(newGimmickNode);
        }

        size++;
        balanceRBT(newGimmickNode);
        return true;
    }

    // ================ BALANCE ================
    private void balanceRBT(GimmickNode GimmickNode) {
        GimmickNode.setRed(true);

        while (GimmickNode != null && GimmickNode != root && GimmickNode.getParent().isRed()) {
            if (GimmickNode.getParent() == GimmickNode.getParent().getParent().getLeft()) {
                GimmickNode uncle = GimmickNode.getParent().getParent().getRight();

                if (uncle != null && uncle.isRed()) {
                    GimmickNode.getParent().setRed(false);
                    uncle.setRed(false);
                    GimmickNode.getParent().getParent().setRed(true);
                    GimmickNode = GimmickNode.getParent().getParent();
                } else {
                    if (GimmickNode == GimmickNode.getParent().getRight()) {
                        GimmickNode = GimmickNode.getParent();
                        rotateLeft(GimmickNode);
                    }
                    GimmickNode.getParent().setRed(false);
                    GimmickNode.getParent().getParent().setRed(true);
                    rotateRight(GimmickNode.getParent().getParent());
                }
            } else {
                GimmickNode uncle = GimmickNode.getParent().getParent().getLeft();

                if (uncle != null && uncle.isRed()) {
                    GimmickNode.getParent().setRed(false);
                    uncle.setRed(false);
                    GimmickNode.getParent().getParent().setRed(true);
                    GimmickNode = GimmickNode.getParent().getParent();
                } else {
                    if (GimmickNode == GimmickNode.getParent().getLeft()) {
                        GimmickNode = GimmickNode.getParent();
                        rotateRight(GimmickNode);
                    }
                    GimmickNode.getParent().setRed(false);
                    GimmickNode.getParent().getParent().setRed(true);
                    rotateLeft(GimmickNode.getParent().getParent());
                }
            }
        }

        root.setRed(false);
    }

    private void rotateLeft(GimmickNode x) {
        if (x == null)
            return;

        GimmickNode y = x.getRight();
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

    private void rotateRight(GimmickNode x) {
        if (x == null)
            return;

        GimmickNode y = x.getLeft();
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

    // ================ GIMMICK ================
    public GimmickInterface getGimmick(String key, MainController controller) {
        GimmickNode node = findNode(root, key);
        if (node == null || node.getClassName() == null || node.getClassName().isBlank()) {
            return null;
        }

        try {
            Class<?> cls = Class.forName(node.getClassName());

            if (controller != null) {
                return (GimmickInterface) cls.getConstructor(MainController.class).newInstance(controller);
            }

            return (GimmickInterface) cls.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            System.err.println("Failed to load gimmick: " + node.getClassName());
            return null;
        }
    }

    private GimmickNode findNode(GimmickNode current, String key) {
        if (current == null)
            return null;

        int cmp = key.toLowerCase().compareTo(current.getKey());
        if (cmp < 0)
            return findNode(current.getLeft(), key);
        else if (cmp > 0)
            return findNode(current.getRight(), key);
        else
            return current;
    }

    // ================ DEBUG ================
    public void printTree() {
        if (root == null) {
            System.out.println("Tree is empty");
            return;
        }
        printTreeHelper(root, "", true);
    }

    private void printTreeHelper(GimmickNode GimmickNode, String prefix, boolean isTail) {
        if (GimmickNode == null) {
            return;
        }

        String color = GimmickNode.isRed() ? "\u001B[31m" : "\u001B[37m";
        String reset = "\u001B[0m";

        System.out.println(prefix + (isTail ? "└── " : "├── ") + color + GimmickNode.getKey() + reset);

        if (GimmickNode.getLeft() != null || GimmickNode.getRight() != null) {
            if (GimmickNode.getRight() != null) {
                printTreeHelper(GimmickNode.getRight(), prefix + (isTail ? "    " : "│   "), false);
            } else {
                System.out.println(prefix + (isTail ? "    " : "│   ") + "├── null");
            }

            if (GimmickNode.getLeft() != null) {
                printTreeHelper(GimmickNode.getLeft(), prefix + (isTail ? "    " : "│   "), true);
            } else {
                System.out.println(prefix + (isTail ? "    " : "│   ") + "└── null");
            }
        }
    }

    public void printInOrder(GimmickNode n, boolean showValues) {
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