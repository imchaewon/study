package com.example.java_.generic.t8;

import java.util.ArrayList;

import lombok.ToString;

@ToString
class AnimalList<T> {

    ArrayList<T> al = new ArrayList<T>();

    void add(T animal) { al.add(animal); }

    T get(int index) { return al.get(index); }

    boolean remove(T animal) { return al.remove(animal); }

    int size() { return al.size(); }

}
