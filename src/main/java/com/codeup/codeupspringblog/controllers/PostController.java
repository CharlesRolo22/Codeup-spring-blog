package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    @ResponseBody
    public String viewPosts() {
        return "This is the posts index page";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String singlePost(@PathVariable long id) {
        return "Viewing post with the id of: " + id;
    }


    @GetMapping("/posts/create")
    @ResponseBody
    public String showPostForm() {
        return "Viewing form to create a new post!";
    }


    @PostMapping("/posts/create")
//    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String submitNewPost() {
        return "submitting new post";
    }
}