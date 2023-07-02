package com.github.ilRoute.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

/**
 * json工具类
 * @author lwx
 */
public class JsonUtils {

    public static  <V> List<V> json2List(String jsonStr,TypeReference<List<V>> typeReference) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonStr, typeReference);
    }
}
