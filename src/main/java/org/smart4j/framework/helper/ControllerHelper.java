package org.smart4j.framework.helper;

import org.apache.commons.lang3.ArrayUtils;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.bean.Handler;
import org.smart4j.framework.bean.Request;
import org.smart4j.framework.util.ArrayUtil;
import org.smart4j.framework.util.CollectionUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 控制器助手类
 * @author wangc 2018/6/12
 * @version 1.0.0
 * @since jdk1.8
 */
public final class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<>();

    static {
        Set<Class<?>> controllerClassSet = ClassHelper.getControllerClassSet();
        if (CollectionUtil.isNotEmpty(controllerClassSet)) {
            for (Class<?> cls : controllerClassSet) {
                Method [] controllerMethods = cls.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(controllerMethods)) {
                    for (Method controllerMethod : controllerMethods) {
                        if (controllerMethod.isAnnotationPresent(Action.class)) {
                            Action action = controllerMethod.getAnnotation(Action.class);
                            String mapping = action.value();
                            if (mapping.matches("\\w+:/\\w+")) {
                                String[] mappingArr = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(mappingArr)
                                        && mappingArr.length == 2) {
                                    String requestMethod = mappingArr[0];
                                    String requestPath = mappingArr[1];
                                    Request request = new Request(requestMethod, requestPath);
                                    Handler handler = new Handler(cls, controllerMethod);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 获取Handler
     *
     * @param requestMethod
     * @param requestPath
     * @return
     */
    public static Handler getHandler (String requestMethod, String requestPath) {
        Request request = new Request(requestMethod, requestPath);
        return ACTION_MAP.get(request);
    }
}
