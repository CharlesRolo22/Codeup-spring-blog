package controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public String landingPage() {
        return "This is the landing page!";
    }

    @Controller
    public class MathController {

        @GetMapping("/add/{num1}/and/{num2}")
        @ResponseBody
        public int addNumbers(@PathVariable("num1") int num1, @PathVariable("num2") int num2) {
            return num1 + num2;
        }

        @GetMapping("/subtract/{num1}/from/{num2}")
        @ResponseBody
        public int subtractNumbers(@PathVariable("num1") int num1, @PathVariable("num2") int num2) {
            return num2 - num1;
        }

        @GetMapping("/multiply/{num1}/and/{num2}")
        @ResponseBody
        public int multiplyNumbers(@PathVariable("num1") int num1, @PathVariable("num2") int num2) {
            return num1 * num2;
        }

        @GetMapping("/divide/{num1}/by/{num2}")
        @ResponseBody
        public int divideNumbers(@PathVariable("num1") int num1, @PathVariable("num2") int num2) {
            return num1 / num2;
        }
    }

    @Controller
    public class PostController {

        @GetMapping("/posts")
        @ResponseBody
        public String postsIndexPage() {
            return "Posts index page";
        }

        @GetMapping("/posts/{id}")
        @ResponseBody
        public String viewPost(@PathVariable("id") int id) {
            return "Viewing post with ID: " + id;
        }

        @GetMapping("/posts/create")
        @ResponseBody
        public String createPostForm() {
            return "Viewing the form for creating a post";
        }

        @ResponseBody
        public String createPost() {
            // Logic to create a new post
            return "Creating a new post";
        }
    }

}