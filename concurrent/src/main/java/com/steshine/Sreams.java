package com.steshine;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by skychen on 2019/1/16.
 */
public class Sreams {
    public static void main(String[] args) {
        Map<String,List<String>> map = new HashMap<>();

        map.put("1",Arrays.asList(new String[]{"a","b"}));
        map.put("2",Arrays.asList(new String[]{"a","c"}));
        map.put("3",Arrays.asList(new String[]{"c","b"}));
        map.entrySet().stream().map(entry -> entry.getValue().stream());
    }
}
