package com.example.demospringamqpscheduledconsumer.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Post {
    private String id;
    private String content;
}
