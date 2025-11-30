package com.kamus.gimmick.hashmap;

public class GimmickNode {
    public String key;
    public String className;  // CONTOH: com.kamus.gimmick.easteregg.HelloGimmick
    public GimmickNode next;

    public GimmickNode(String key, String className) {
        this.key = key;
        this.className = className;
    }
}
