package com.diyiliu.support.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.SessionException;
import org.apache.shiro.session.mgt.SessionKey;
import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Description: CustomerSessionManager
 * Author: DIYILIU
 * Update: 2019-05-23 17:18
 */

@Slf4j
public class CustomerSessionManager extends DefaultWebSessionManager {
    private static final String AUTHORIZATION = "Authorization";


    @Override
    public Session getSession(SessionKey key) throws SessionException {
        return super.getSession(key);
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        String token = WebUtils.toHttp(request).getHeader(AUTHORIZATION);

        if (StringUtils.isNotEmpty(token)){
            // 设置当前session状态
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, token);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TRUE);

            return token;
        }

        return super.getSessionId(request, response);
    }
}
