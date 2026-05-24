package com.example.demo;

public class Board {
    private final int id;
    private final String name;

    public Board(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}

