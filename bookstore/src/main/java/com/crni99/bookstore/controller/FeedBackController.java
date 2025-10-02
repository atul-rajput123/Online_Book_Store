package com.crni99.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crni99.bookstore.model.FeedBack;
import com.crni99.bookstore.service.FeedBackService;

@Controller
@RequestMapping("/feedback")
public class FeedBackController {

    @Autowired
    FeedBackService feedBackService;

    @GetMapping(value = { "", "/" })
    public String showFeedBack(Model model) {
        List<FeedBack> list = feedBackService.showFeedBack();
        model.addAttribute("feedbacks", list);
        return "showFeedBack";
    }

    @GetMapping("/feedback")
    public String feedBack() {
        return "feedback";
    }

    @PostMapping("/submit")
    public String feedBack(@ModelAttribute FeedBack feedBack, Model model) {

        feedBackService.feedBackService(feedBack);
        model.addAttribute("successMessage", "The order is confirmed, check your email.");
        return "thankyou";
    }

}
