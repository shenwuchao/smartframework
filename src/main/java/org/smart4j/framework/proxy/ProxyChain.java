package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * 代理链
 *
 * @author wangc
 * @version 1.0.0
 * @since jdk1.8
 */
public class ProxyChain {

    private final Class<?> targetClass;
    private final Object targetObject;
    private final Method targetMethod;
    private final MethodProxy methodProxy;
    private final Object[] parameters;

    private List<Proxy> proxies;
    private int proxyIndex;

    public ProxyChain (Class<?> targetClass,
                       Object targetObject,
                       Method targetMethod,
                       MethodProxy methodProxy,
                       Object[] parameters,
                       List<Proxy> proxies) {
        this.targetClass = targetClass;
        this.targetObject = targetObject;
        this.targetMethod = targetMethod;
        this.methodProxy = methodProxy;
        this.parameters = parameters;
        this.proxies = proxies;
    }

    /**
     * 执行代理链
     *
     * @return
     * @throws Throwable
     */
    public Object doProxyChain () throws Throwable {
        Object result = null;
        if (proxyIndex < proxies.size()) {
            result = proxies.get(proxyIndex++).doProxy(this);
        } else {
            result = methodProxy.invokeSuper(targetObject, parameters);
        }
        return result;
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public Object getTargetObject() {
        return targetObject;
    }

    public Method getTargetMethod() {
        return targetMethod;
    }

    public MethodProxy getMethodProxy() {
        return methodProxy;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public List<Proxy> getProxies() {
        return proxies;
    }
}
