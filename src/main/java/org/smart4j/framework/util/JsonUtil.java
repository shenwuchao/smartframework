package org.smart4j.framework.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * json处理工具类
 *
 * @author wangc 2018/6/13
 * @version 1.0.0
 * @since jdk1.8
 */
public final class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    /**
     * 转换为json字符串
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String toJson (T obj) {
        String json = null;
        try {
            json = OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            LOGGER.error("convert POJO to JSON failure", e);
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将json转为pojo
     *
     * @param json
     * @param cls
     * @return
     */
    public static <T> T fromJson (String json, Class<T> cls) {
        T pojo = null;
        try {
            pojo = OBJECT_MAPPER.readValue(json, cls);
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("convert JSON to POJO failure", e);
            throw new RuntimeException(e);
        }
        return pojo;
    }

}
