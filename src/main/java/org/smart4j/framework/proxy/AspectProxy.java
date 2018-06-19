package org.smart4j.framework.proxy;

import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * 切面代理（基类）
 * @author wangc
 * @version 1.0.0
 * @since jdk1.8
 */
public abstract class AspectProxy implements Proxy {

    private static final Logger LOGGER = LoggerFactory.getLogger(AspectProxy.class);

    @Override
    public Object doProxy(ProxyChain proxyChain) throws Throwable {
        Object result = null;
        Class<?> targetClass = proxyChain.getTargetClass();
        Method targetMethod = proxyChain.getTargetMethod();
        Object[] parameters = proxyChain.getParameters();

        begin();
        try {
            if (intercept(targetClass, targetMethod, parameters)) {
                before(targetClass, targetMethod, parameters);
                result = proxyChain.doProxyChain();
                after(targetClass, targetMethod, parameters);
            } else {
                result = proxyChain.doProxyChain();
            }
        } catch (Exception e) {
            LOGGER.error("proxy failure", e);
            error(targetClass, targetMethod, parameters, e);
            throw e;
        } finally {
            end();
        }
        return result;
    }

    public void begin () {

    }
    public void before (Class<?> cls, Method method, Object[] parameters)
            throws Throwable {}

    public void after (Class<?> cls, Method method, Object[] parameters)
            throws Throwable {}

    public void end () {}

    public boolean intercept (Class<?> cls, Method method, Object[] parameters)
            throws Throwable {
        return true;
    }

    public void error (Class<?> cls, Method method, Object[] parameters, Throwable e) {}

}
