package by.boiko.crm.controller;


import by.boiko.crm.model.Category;
import by.boiko.crm.model.Email;
import by.boiko.crm.model.Order;
import by.boiko.crm.model.Parser;
import by.boiko.crm.service.UserService;
import by.boiko.crm.service.YandexMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * The controller determines methods for access to User service.
 */

@Controller
public class UserController {

    @Autowired
    private YandexMailService yandexMailService;

    @Autowired
    private UserService userService;


    @RequestMapping(value = "/")
    public ModelAndView getIndexPage() {
        return new ModelAndView("index");
    }

//    @RequestMapping(value = "/start")
//    public String downloadFile() throws IOException {
//        userService.downloadFile();
//        return "redirect:/categories";
//    }
//
//    @RequestMapping(value = "/categories")
//    public ModelAndView getAllCategories() throws IOException {
//        ModelAndView mv = new ModelAndView("listCat");
//        mv.addObject("counts", userService.getCount());
//        mv.addObject("categories", userService.getAll());
//        return mv;
//    }
//
//    @ResponseBody
//    @RequestMapping(value = "/categories_json/{page}")
//    public List<Category> getAllCategoriesJson(@PathVariable(value = "page") int page) throws IOException {
//        return userService.getAllFromPage(page);
//    }
//
//    @RequestMapping(value = "/user/{number}")
//    public ModelAndView getAllUsersForNumber(@PathVariable(value = "number") int number) throws IOException {
//        ModelAndView mv = new ModelAndView("list");
//        mv.addObject("users", userService.getTop(number));
//        return mv;
//    }

    @GetMapping(value = "/email")
    @ResponseBody
    public List<Parser> getAllEmails() throws MessagingException, IOException {
        return yandexMailService.check();
    }

}
