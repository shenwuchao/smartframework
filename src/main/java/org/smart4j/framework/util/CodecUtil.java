package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 编码与解码工具类
 *
 * @author wangc 2018/6/13
 * @version 1.0.0
 * @since jdk1.8
 */
public final class CodecUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 对url进行编码
     *
     * @param source
     * @return
     */
    public static String encodeURL (String source) {
        String target = null;
        try {
            target = URLEncoder.encode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("encode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }

    /**
     * 对url进行解码
     *
     * @param source
     * @return
     */
    public static String decodeURL (String source) {
        String target = null;
        try {
            target = URLDecoder.decode(source, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            LOGGER.error("decode url failure", e);
            throw new RuntimeException(e);
        }
        return target;
    }
}
