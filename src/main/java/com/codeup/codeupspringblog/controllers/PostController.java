package com.codeup.codeupspringblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController<Post> {

    @GetMapping("/post")
    @ResponseBody
    public String viewPosts(Model model) {
        List<Post> posts = new ArrayList<>();
        // Add example posts
        Post post1 = new Post("First Post", "This is the first post.");
        Post post2 = new Post("Second Post", "This is the second post.");
        posts.add(post1);
        posts.add(post2);
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String singlePost(@PathVariable long id, Model model) {
        Post post = new Post("Post Title", "Post Body");
        model.addAttribute("post", post);
        return "posts/show";
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