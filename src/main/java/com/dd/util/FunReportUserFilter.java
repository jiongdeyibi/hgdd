package com.dd.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class FunReportUserFilter implements Filter {
    protected final Logger logger = LogManager.getLogger(this.getClass());

    private String excludedPages;

    private String[] excludedPageArray;

    @Override
    public void init(FilterConfig cfg) throws ServletException {
        excludedPages = cfg.getInitParameter("excludedPages");
        if (excludedPages != null && !"".equals(excludedPages)) {
            excludedPageArray = excludedPages.split(",");
        }
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        String requestPath = request.getRequestURI();

        //请求如果是根节点获取index.html则直接通过
        if ("/".equals(requestPath)) {
            logger.info("ROOT path: [" + request.getRequestURI() + "]");
            chain.doFilter(req, res);
            return;
        }
        //获得第二个/ 的序号
        int secondIndex = requestPath.indexOf("/", requestPath.indexOf("/") + 1);
        //获取requestURI的第一截内容
        String firstPath = secondIndex == -1 ? requestPath : requestPath.substring(0, secondIndex);
        //判断第一截访问地址是否属于不用过滤的地址
        for (String page : excludedPageArray) {
            if (page.equals(firstPath)) {
                logger.info("excludedPage: [" + request.getRequestURI() + "]");
                chain.doFilter(req, res);
                return;
            }
        }
        String sessionUser = (String) request.getSession().getAttribute("user");

        //如果Session中的user为access_denied或null时，不做任何处理直接返回
        if ("access_denied".equals(sessionUser)) {
            logger.info("Filter    access_denied sessionUser: [" + sessionUser + "]");
            return;
        } else if (null == sessionUser) {
            logger.info("Filter    null sessionUser: [null]");
            return;
        } else {
            //其他情况则放过
            logger.info("Filter    success sessionUser: [" + sessionUser + "]");
            chain.doFilter(req, res);
            return;
        }
    }

    @Override
    public void destroy() {
    }
}
