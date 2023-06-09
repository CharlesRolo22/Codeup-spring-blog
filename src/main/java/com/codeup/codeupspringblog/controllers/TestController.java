package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Park;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.ParkRepository;
import com.codeup.codeupspringblog.repositories.StateRepository;
import com.codeup.codeupspringblog.services.EmailService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class TestController {

    private final ParkRepository parksDao;
    private final StateRepository statesDao;
    private final EmailService emailService;
    public TestController(ParkRepository parksDao, StateRepository statesDao, EmailService emailService) {
        this.parksDao = parksDao;
        this.statesDao = statesDao;
        this.emailService = emailService;
    }

    // Get Request Mapping for /test
    @GetMapping("/test")
    // Set Response content type to text/html
    @ResponseBody
    // define method that will return text/html
    public String test() {
        return "Hello Kotlin!";
    }


    // /parks
    @GetMapping("/parks/{park}/{message}")
    @ResponseBody
    public String parks(@PathVariable String park, @PathVariable String message) {
        return "<h1>Welcome to " + park + "!</h1><p>" + message + "</p>";
    }

    @GetMapping("/parks")
    public String parks(Model model) {

        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(loggedInUser.getEmail());
        System.out.println(loggedInUser.getId());
        System.out.println(loggedInUser.getUsername());

        List<Park> parks = parksDao.findAll();
        model.addAttribute("parks", parks);


        return "parks/index";
    }

    @GetMapping("/park")
    public String park(Model model) {
        Park byName = parksDao.findByName("Big Bend National Park");
        model.addAttribute("park", byName);
        return "parks/show";
    }

    @GetMapping("/parks/create")
    public String showParksForm(Model model) {

        model.addAttribute("states", statesDao.findAll());

        model.addAttribute("park", new Park());
        return "parks/create";
    }


    // EDIT HINT
//    @GetMapping("/parks/{id}/edit")
    // 1. Find the Park using the PathVariable
    // 2. Use the Model to send the Park to the form.
    // 3. return the template for the edit.html


    @PostMapping("/parks/create")
    // Using the @ModelAttribute annotation to receive the Park object being submitted from our form
    public String submitNewPark(@ModelAttribute Park park) {
        // Spring Recommended syntax for .findById() method.
//        if(statesDao.findById(stateId).isPresent()) {
//            State state = statesDao.findById(stateId).get();
//            Park newPark = new Park(name, state);
//            parksDao.save(newPark);
//
//        }


        // Save the park and it's values into our database.
        parksDao.save(park);

//        emailService.prepareAndSend(park, "New Park has been founded!", "A new park has been founded by the name of: ");

        return "redirect:/parks";
    }

    @RequestMapping(path = "/increment/{number}", method = RequestMethod.GET)
    @ResponseBody
    public String addOne(@PathVariable int number) {
        return number + " plus one is " + (number + 1) + "!";
    }


    @GetMapping("/join")
    public String showJoinForm() {
        return "join";
    }

    @PostMapping("/join")
    public String submitCohortForm(@RequestParam String cohort, Model model) {
        model.addAttribute("cohort", cohort);
        return "join";
    }

    @GetMapping("/randomStudent")
    public String showStudent() {
        return "randomStudent";

    }

}