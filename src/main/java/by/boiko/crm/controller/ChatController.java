package by.boiko.crm.controller;

import by.boiko.crm.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.ws.rs.POST;

@Controller
public class ChatController {


    @Autowired
    private BotService botService;

    @PostMapping(value = "/chat/{message}")
    public String bindOnliner(@PathVariable(value = "message") String message) {
        botService.getMessage(message);
        return "redirect:/onliner";
    }
}
