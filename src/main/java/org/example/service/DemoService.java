package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {
    public record Greeting(long id, String content) {

    }
}
