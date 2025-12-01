package com.aurxsiu.util.normal;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static interface Action{
        public String act(String str);
    }
    //todo: 从chengxusheji中获取实际开发组件
    public static String parse(String template, Map<String, Action> variables) {
        // 正则表达式匹配所有 ${key}
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
        Matcher matcher = pattern.matcher(template);

        StringBuilder result = new StringBuilder();

        // 查找所有占位符并替换
        while (matcher.find()) {
            String key = matcher.group(1);  // 获取占位符中的键名
            String value = variables.getOrDefault(key, (str)->"").act(template);  // 获取对应的值，默认为空字符串
            matcher.appendReplacement(result, value);  // 用实际值替换占位符
        }

        matcher.appendTail(result);  // 将剩余部分追加到结果中

        return result.toString();
    }


}
