package org.smart4j.framework;

import org.apache.commons.lang3.StringUtils;
import org.smart4j.framework.bean.*;
import org.smart4j.framework.exception.NotFoundBeanInstanceException;
import org.smart4j.framework.helper.BeanHelper;
import org.smart4j.framework.helper.ConfigHelper;
import org.smart4j.framework.helper.ControllerHelper;
import org.smart4j.framework.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 请求转发器
 * @author wangc 2018/6/13
 * @version 1.0.0
 * @since jdk1.8
 */
@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
public final class DispatcherServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        HelperLoader.init();
        ServletContext servletContext = getServletContext();
        ServletRegistration jspRegistration = servletContext
                .getServletRegistration("jsp");
        jspRegistration.addMapping(ConfigHelper.getAppJspPath() + "*");
        ServletRegistration defaultRegistration = servletContext.getServletRegistration("default");
        defaultRegistration.addMapping(ConfigHelper.getAppAssetPath() + "*");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();
        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);
        if (handler != null) {
            Class<?> controllerClass = handler.getControllerClass();
            try {
                Object controller = BeanHelper.getBeanInstance(controllerClass);
                Map<String, Object> paraMap = new HashMap<>();
                Enumeration<String> paraNames = req.getParameterNames();
                while (paraNames.hasMoreElements()) {
                    String name = paraNames.nextElement();
                    String value = req.getParameter(name);
                    paraMap.put(name, value);
                }
                String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));
                if (StringUtil.isNotEmpty(body)) {
                    String[] params = StringUtils.split(body, "&");
                    if (ArrayUtil.isNotEmpty(params)) {
                        for (String param : params) {
                            String[] array = StringUtils.split(param, "=");
                            if (ArrayUtil.isNotEmpty(array)
                                    && array.length == 2) {
                                paraMap.put(array[0], array[1]);
                            }
                        }
                    }
                }
                Parameter parameter = new Parameter(paraMap);
                Method method = handler.getActionMethod();
                Object result = ReflectionUtil.invokeMethod(controller, method, parameter);
                String retClassName = method.getReturnType().getSimpleName();
                if ("View".equals(retClassName)) {
                    View view = (View)result;
                    String path = view.getPath();
                    if (path.startsWith("/")) {
                        resp.sendRedirect(req.getContextPath() + path);
                    } else {
                        Map<String, Object> model = view.getModel();
                        Iterator<Map.Entry<String, Object>> entries = model.entrySet().iterator();
                        while (entries.hasNext()) {
                            Map.Entry<String, Object> entry = entries.next();
                            req.setAttribute(entry.getKey(), entry.getValue());
                        }
                        req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path)
                                .forward(req, resp);

                    }
                }
                if ("Data".equals(retClassName)) {
                    Data data = (Data)result;
                    Object model = data.getModel();
                    if (model != null) {
                        String json = JsonUtil.toJson(model);
                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        try (PrintWriter printWriter = resp.getWriter();) {
                            printWriter.write(json);
                            printWriter.flush();
                        }
                    }
                }
            } catch (NotFoundBeanInstanceException e) {
                e.printStackTrace();
            }
        }
    }
}
