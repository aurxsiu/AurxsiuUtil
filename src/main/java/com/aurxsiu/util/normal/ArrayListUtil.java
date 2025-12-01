package com.aurxsiu.util.normal;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ArrayListUtil {
    @SafeVarargs
    public static  <T> ArrayList<T> getArrayList(T... values) {
        ArrayList<T> result = new ArrayList<>();
        Collections.addAll(result, values);
        return result;
    }

}
