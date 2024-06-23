package org.example.keephealthy02.Api;

import org.example.keephealthy02.domain.XunFeiBigModelAPI;
import org.junit.jupiter.api.Test;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

public class TestApi {
    @Test
    public void get() throws MalformedURLException, URISyntaxException {
        XunFeiBigModelAPI xunFeiBigModelAPI = new XunFeiBigModelAPI();
        xunFeiBigModelAPI.FAQ("晚上吃什么");
    }
}
