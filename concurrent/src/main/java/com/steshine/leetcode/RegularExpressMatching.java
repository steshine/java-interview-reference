package com.steshine.leetcode;

/**
 * Created by skychen on 2018/11/13.
 * Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
 */
public class RegularExpressMatching {

    public boolean isMatch(String s, String p) {
        boolean isMatch = false;
        // special situation
        if (isNull(s) || isNull(p)) {
            return false;
        }
        // argument p not contain express .make entire match
        if (!p.contains(".") && !p.contains("*")) {
            return s.equals(p);
        }
        // if contains * then split with *
        if (p.contains("*")) {
            String segments[] = p.split("\\*");
            int trip = 1;
            if(segments.length > 1){
                trip = segments.length - 1;
            }
            for (int i = 0; i < trip; i++) {
                if (!segments[i].contains(".")) {
                    isMatch = isMatch | s.contains(segments[i].concat(segments[i]));
                } else {
                    isMatch = isMatch | characterMatch(s, segments[i].concat(segments[i])); // repeat once
                }
            }
        }else {
            isMatch = characterMatch(s, p);
        }
        return isMatch;
    }

    private boolean characterMatch(String s, String segment) {
        if (!segment.contains(".")) {
            return s.equals(segment);
        }
        if (s.length() > segment.length()) {
            return false;
        }
        char[] chars = s.toCharArray();
        int length = chars.length;
        int counter = 0;
        for (int i = 0; i < length; i++) {
            if (chars[i] == segment.charAt(i) || segment.charAt(i) == '.') {
                counter++;
            }
        }
        return counter == length;
    }

    private boolean isNull(String s) {
        return s == null || s == "";
    }

    public static void main(String[] args) {
        System.out.println(new RegularExpressMatching().isMatch("aa", "a*"));
    }
}
