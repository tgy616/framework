package com.tgy.idempotent;

import org.springframework.web.servlet.function.ServerResponse;

import javax.servlet.http.HttpServletRequest;

/**
 * @author DragonSwimDiving
 * @program idempotent
 * @Date 2020-05-14 18:14
 **/
public interface TokenService {

    ServerResponse createToken();

    void checkToken(HttpServletRequest request);
}
