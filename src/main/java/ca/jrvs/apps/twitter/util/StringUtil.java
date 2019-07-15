package ca.jrvs.apps.twitter.util;

import java.util.Arrays;

public class StringUtil {

    public static boolean isEmpty(String s) {
        return s == null || s.isEmpty();
    }


    public static boolean isEmpty(String... s) {
        return Arrays.stream(s).anyMatch(str -> str == null || str.isEmpty());

    }
}