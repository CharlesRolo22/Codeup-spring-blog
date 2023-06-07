package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class RollDiceController {

    @GetMapping("/index")
    public String landingPage() {
        return "index";
    }

    @GetMapping("/roll-dice")
    public String rollDicePage() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDice(@PathVariable int guess, Model model) {
        List<Integer> rolls = new ArrayList<>();
        Random random = new Random();

        // Perform the dice rolls
        for (int i = 0; i < 3; i++) {
            int roll = random.nextInt(6) + 1;
            rolls.add(roll);
        }

        int matchCount = (int) rolls.stream().filter(roll -> roll == guess).count();
        boolean guessedCorrectly = matchCount > 0;

        model.addAttribute("rolls", rolls);
        model.addAttribute("guess", guess);
        model.addAttribute("guessedCorrectly", guessedCorrectly);
        model.addAttribute("matchCount", matchCount);

        return "roll-dice-result";
    }
}

