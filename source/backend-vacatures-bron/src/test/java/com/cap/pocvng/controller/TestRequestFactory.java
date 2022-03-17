package com.cap.pocvng.controller;


import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class TestRequestFactory {

    public static MockHttpServletRequestBuilder getWithHeaders(String url) {
        return MockMvcRequestBuilders.get(url)
                .header("X-VUM-berichtVersie", 1)
                .header("X-VUM-fromParty", 1)
                .header("X-VUM-toParty", 123456789)
                .header("X-VUM-viaParty", 1)
                .header("X-VUM-SUWIparty", true);
    }

    public static MockHttpServletRequestBuilder postWithHeaders(String url) {
        return MockMvcRequestBuilders.post(url)
                .header("X-VUM-berichtVersie", 1)
                .header("X-VUM-fromParty", 1)
                .header("X-VUM-toParty", 123456789)
                .header("X-VUM-viaParty", 1)
                .header("X-VUM-SUWIparty", true);
    }

}
