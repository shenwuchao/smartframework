package org.smart4j.framework.bean;

import org.smart4j.framework.util.CastUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数对象
 *
 * @author wangc 2018/6/13
 * @version 1.0.0
 * @since jdk1.8
 */
public class Parameter {

    private Map<String, Object> paraMap = new HashMap<>();

    public Parameter(Map<String, Object> paraMap) {
        this.paraMap = paraMap;
    }

    /**
     * 根据参数名称获取long类型参数值
     *
     * @param name
     * @return
     */
    public long getLong (String name) {
        return CastUtil.castLong(paraMap.get(name));
    }

    /**
     * 根据参数名称获取int类型参数值
     *
     * @param name
     * @return
     */
    public int getInt (String name) {
        return CastUtil.castInt(paraMap.get(name));
    }

    /**
     * 根据参数名获取String类型参数值
     *
     * @param name
     * @return
     */
    public String getString (String name) {
        return CastUtil.castString(paraMap.get(name));
    }

    /**
     * 根据参数名获取double类型参数值
     *
     * @param name
     * @return
     */
    public double getDouble (String name) {
        return CastUtil.castDouble(paraMap.get(name));
    }

    /**
     * 根据参数名获取boolean类型参数值
     *
     * @param name
     * @return
     */
    public boolean getBoolean (String name) {
        return CastUtil.castBoolean(paraMap.get(name));
    }

    /**
     * 获取所有参数
     *
     * @return
     */
    public Map<String, Object> getAllParameters () {
        return paraMap;
    }
}
