package com.bloknoma.authserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${local.server.port}")
    private int port;

    private String baseUrl(String path) {
        return "http://localhost:" + port + path;
    }

    @Test
    public void homePageProtected() {
        ResponseEntity<String> response = new TestRestTemplate().getForEntity(baseUrl("/auth/"), String.class);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
    }

/*
    @Test
    public void authorizationRedirects() {
        ResponseEntity<String> response = new TestRestTemplate().getForEntity(baseUrl("/auth/oauth/authorize"), String.class);
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        String location = response.getHeaders().getFirst("Location");
        assertTrue("Wrong header: " + location, location.startsWith(baseUrl("/auth/login")));
    }
*/

/*
    @Test
    public void loginSucceeds() {
        ResponseEntity<String> response = new TestRestTemplate().getForEntity(baseUrl("/auth/login"), String.class);
        String csrf = getCsrf(response.getBody());

        MultiValueMap<String, String> form = new LinkedMultiValueMap<String, String>();
        form.set("username", "user");
        form.set("password", "password");
        form.set("_csrf", csrf);
        HttpHeaders headers = new HttpHeaders();
        headers.put("COOKIE", response.getHeaders().get("Set-Cookie"));

        RequestEntity<MultiValueMap<String, String>> request = new RequestEntity<MultiValueMap<String, String>>(
                form, headers, HttpMethod.POST, URI.create(baseUrl("/auth/login")));
        ResponseEntity<Void> location = new TestRestTemplate().exchange(request, Void.class);
        assertEquals(baseUrl("/auth/"), location.getHeaders().getFirst("Location"));
    }

    private String getCsrf(String soup) {
        Matcher matcher = Pattern.compile("(?s).*name=\"_csrf\".*?value=\"([^\"]+).*").matcher(soup);
        if (matcher.matches()) {
            return matcher.group(1);
        }
        return null;
    }
*/

}
