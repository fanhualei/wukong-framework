package com.wukong.examples.controller;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.Assert;
import org.springframework.util.Base64Utils;

import java.io.IOException;
import java.nio.charset.Charset;

public class BasicJwtInterceptor implements ClientHttpRequestInterceptor {

    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final String token;

    /**
     * Create a new interceptor which adds a BASIC authorization header
     * for the given username and password.
     * @param token the username to use
     */
    public BasicJwtInterceptor(String token) {
        Assert.hasLength(token, "token must not be empty");
        this.token = token;
    }


    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body,
                                        ClientHttpRequestExecution execution) throws IOException {
        request.getHeaders().add("Authorization", token);
        return execution.execute(request, body);
    }

}