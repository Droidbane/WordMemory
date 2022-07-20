package com.droidbane.wordmemory.database;

public class Words {
    private int id;
    private String word1;
    private String word2;

    public Words() {
    }

    public Words(int id, String key, String value) {
        this.id = id;
        this.word1 = key;
        this.word2 = value;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord1() {
        return word1;
    }

    public void setWord1(String word1) {
        this.word1 = word1;
    }

    public String getWord2() {
        return word2;
    }

    public void setWord2(String word2) {
        this.word2 = word2;
    }
}
