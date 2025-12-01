package com.aurxsiu.util.normal;

import com.aurxsiu.util.file.FileHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 注意,被read或者write的类最好直接public或者getter,setter
 * */
public class JsonUtil {
    public static ObjectMapper mapper = new ObjectMapper();

    public static <T> T readValue(String content, Class<T> valueType) throws JsonProcessingException, JsonMappingException {
        return mapper.readValue(content, valueType);
    }

    public static String write(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }
}
