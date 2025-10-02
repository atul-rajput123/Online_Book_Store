package com.crni99.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.crni99.bookstore.service.ChatBotService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
public class ChatbotController {

    @Autowired
    ChatBotService chatBotService;

    String botAnswer = "";
    String userQuestion;
    Map<String, String> currentMenu = new LinkedHashMap<>();
    boolean flag = false;

    @GetMapping("/chatbot")
    public String showChat(@RequestParam(value = "menu", required = false) String menu,
            @RequestParam(value = "type", required = false) String type, Model model) {

        currentMenu.clear();
        userQuestion = menu;

        if (type != null) {
            userQuestion = type;

            String digitsOnly = type.replaceAll("\\D+", "");
            Map<String, Object> fatch = new LinkedHashMap<>();

            if ((!digitsOnly.isEmpty())) {
                int number = Integer.parseInt(digitsOnly);
                fatch = chatBotService.findById(number);
                if (fatch.size() == 0) {
                    botAnswer = "Order Id is not Found";
                } else if (flag) {
                    botAnswer = fatch.toString();
                } else {
                    botAnswer = fatch.toString() + "<br><br>Confirm cancellation";
                }

            } else {
                defaultMenu();
            }

        } else {
            defaultMenu();
        }

        if (menu != null && type == null) {
            switch (menu) {
                case "Place Order":
                    botAnswer = "Help with placing a new order:<br><br>"
                            + "<a href='/'>Browse books,</a><br><br>"
                            + "Add to cart,<br><br>"
                            + "<a href='/cart'>View cart,</a><br><br>"
                            + "Select payment method,<br><br>"
                            + "Confirm order,<br><br>"
                            + "Provide delivery address.";
                    break;

                case "Cancel Order":
                    botAnswer = "Enter your Order ID";
                    break;

                case "Order Status":
                    botAnswer = "Enter your Order ID";
                    flag = true;
                    break;

                default:
                    List<Map<String, Object>> data = chatBotService.findMenu(menu);
                    for (Map<String, Object> row : data) {
                        String key = (String) row.get("menu");
                        String value = (String) row.get("ans");
                        currentMenu.put(key, value);
                        botAnswer = value;
                    }
                    break;
            }
        } else if (type == null) {

            defaultMenu();
        }

        model.addAttribute("currentMenu", currentMenu);
        model.addAttribute("userQuestion", userQuestion);
        model.addAttribute("botAnswer", botAnswer);

        return "help";
    }

    private void defaultMenu() {

        botAnswer = "Hello! 👋 I’m your Bookstore Assistant. How can I help you today? Select Into Menu";
        List<Map<String, Object>> data = chatBotService.findMenu("chatbot"); // main menu
        for (Map<String, Object> row : data) {
            String key = (String) row.get("menu");
            String value = (String) row.get("ans");
            currentMenu.put(key, value);
        }

    }

}
