package com.kamus.gimmick.hashmap;

import java.lang.reflect.Constructor;
import com.kamus.gimmick.MainController;

public class GimmickHashMap {

    private GimmickNode[] buckets;
    private int capacity = 16;
    private int size = 0;

    public GimmickHashMap() {
        buckets = new GimmickNode[capacity];
    }

    private int hash(String key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    // ================= GETTER =================
    public GimmickInterface get(String key) {
        return get(key, null);
    }

    public GimmickInterface get(String key, MainController controller) {
        int index = hash(key);
        GimmickNode head = buckets[index];

        while (head != null) {
            if (head.key.equals(key)) {

                if (head.className == null || head.className.isBlank()) {
                    return null;
                }

                try {
                    Class<?> cls = Class.forName(head.className);

                    if (controller != null) {
                        Constructor<?> ctor = cls.getConstructor(MainController.class);
                        return (GimmickInterface) ctor.newInstance(controller);
                    }

                    return (GimmickInterface) cls.getDeclaredConstructor().newInstance();

                } catch (Exception e) {
                    System.out.println("Gimmick load failed: " + head.className);
                    return null;
                }
            }
            head = head.next;
        }

        return null;
    }

    public String getValue(String key) {
        int index = hash(key);
        GimmickNode head = buckets[index];

        while (head != null) {
            if (head.key.equals(key)) {
                return head.className;
            }
            head = head.next;
        }
        return null;
    }

    // ================= SETTERS =================
    public void putValue(String key, String value) {
        put(key, value);
    }

    public void put(String key, String className) {
        int index = hash(key);
        GimmickNode head = buckets[index];

        while (head != null) {
            if (head.key.equals(key)) {
                head.className = className;
                return;
            }
            head = head.next;
        }

        GimmickNode node = new GimmickNode(key, className);
        node.next = buckets[index];
        buckets[index] = node;
        size++;

        if ((double) size / capacity > 0.75)
            resize();
    }

    // ================= RESIZER =================
    private void resize() {
        capacity *= 2;
        GimmickNode[] old = buckets;
        buckets = new GimmickNode[capacity];
        size = 0;

        for (GimmickNode head : old) {
            while (head != null) {
                put(head.key, head.className);
                head = head.next;
            }
        }
    }

    public int size() {
        return size;
    }
}
