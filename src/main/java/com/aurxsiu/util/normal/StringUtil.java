package com.aurxsiu.util.normal;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
    public static interface Action {
        public String act(String str, String param) throws Exception;
    }

    public static interface GetAction {
        public static class ReturnValue {
            public final Action action;
            public final String param;

            public ReturnValue(Action action, String param) {
                this.action = action;
                this.param = param;
            }
        }

        public ReturnValue getAction(String key);
    }

    public static String parse(String template, GetAction getActionName) throws Exception {
        // 正则表达式匹配所有 ${key}
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
        Matcher matcher = pattern.matcher(template);

        StringBuffer result = new StringBuffer();

        // 查找所有占位符并替换
        while (matcher.find()) {
            String key = matcher.group(1);  // 获取占位符中的键名

            GetAction.ReturnValue action_struct = getActionName.getAction(key);

            String act_result = action_struct.action.act(template, action_struct.param);

            matcher.appendReplacement(result, act_result);  // 用实际值替换占位符
        }

        matcher.appendTail(result);  // 将剩余部分追加到结果中

        return result.toString();
    }


}
