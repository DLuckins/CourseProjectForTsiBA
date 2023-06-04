package com.example.bapp;

import java.util.Map;
import java.util.stream.Collectors;

public class Encoding {
    public static String encode(String text){
       return text.chars().mapToObj(ch -> (char) ch+10).collect(StringBuilder :: new,
                StringBuilder :: appendCodePoint,
                StringBuilder :: append ).toString();
    }
    public static String decode(String text){
        return text.chars().mapToObj(ch -> (char) ch-10).collect(StringBuilder :: new,
                StringBuilder :: appendCodePoint,
                StringBuilder :: append ).toString();
    }
}
