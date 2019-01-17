package by.boiko.crm.controller;

import by.boiko.crm.model.pojo.BotOrders;
import by.boiko.crm.service.BotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BotController {

    @Autowired
    private BotService botService;

    @GetMapping(value = "/bot")
    public ModelAndView getOrders() {
        ModelAndView mv = new ModelAndView("bot");
        mv.addObject("orders", botService.getAll());
        return mv;
    }

    @PostMapping(value = "/create")
    @ResponseBody
    public String createOrder(@RequestBody BotOrders botOrders) {
        botService.save(botOrders);
        return "redirect:/bot";
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteOrder(@PathVariable("id") Long id) {
        botService.deleteItem(id);
        return "redirect:/bot";
    }
}
