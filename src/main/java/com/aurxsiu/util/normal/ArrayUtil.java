package com.aurxsiu.util.normal;

import java.util.Arrays;

public class ArrayUtil<T> {
    /**
     * 创建一个新的数组,将first插入到数组的最前面
     * */
    @SafeVarargs
    public static <T> T[] prepend(T first, T... rest) {
        T[] result = Arrays.copyOf(rest, rest.length + 1);
        System.arraycopy(result, 0, result, 1, rest.length);
        result[0] = first;
        return result;
    }
}
