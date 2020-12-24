package com.example.demospringamqpscheduledconsumer.api;

import com.example.demospringamqpscheduledconsumer.business.PostService;
import com.example.demospringamqpscheduledconsumer.dto.PostDto;
import com.example.demospringamqpscheduledconsumer.model.Post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/post")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("")
    public Post createPost(@RequestBody PostDto postDto){
        return postService.create(postDto.getContent());
    }
}
