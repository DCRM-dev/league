package com.example.league.solution1;

import java.util.Comparator;

public class CustomComparator implements Comparator<String> {

    @Override
    public int compare(String o1, String o2) {
        return o1.toLowerCase().compareTo(o2.toLowerCase());
    }
}
