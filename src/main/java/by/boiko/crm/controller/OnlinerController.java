package by.boiko.crm.controller;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Table;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.pojo.UnattachedGoods;
import by.boiko.crm.service.OnlinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

/**
 * Controller for parsing if Onliner.by.
 */
@Controller
public class OnlinerController {

    private final OnlinerService onlinerService;

    @Autowired
    public OnlinerController(OnlinerService onlinerService) {
        this.onlinerService = onlinerService;
    }



    /**
     * Move to page onliner.jsp.
     *
     * @return jsp page
     */
    @GetMapping(value = "/getData")
    public ModelAndView toPageCategory() {
        return new ModelAndView("loadCategory");
    }

    /**
     * Move to page onliner.jsp.
     *
     * @return jsp page
     */
    @GetMapping(value = "/onliner")
    public ModelAndView toPageOnliner() {
        return new ModelAndView("onliner");
    }

    /**
     * Get all unattached goods.
     *
     * @return JSON
     */
    @ResponseBody
    @GetMapping(value = "/goods")
    public List<UnattachedGoods> getAllUnattachedGoods() {
        return onlinerService.getAllUnattachedGoods();
    }

    /**
     * Get all the parameters of the attached products.
     *
     * @return JSON
     */
    @ResponseBody
    @GetMapping(value = "/all_goods")
    public List<Onliner> getAllGoods() {
        List<SkuModel> skuModelsList = onlinerService.loadGoods();
        return onlinerService.getAllGoods(skuModelsList);
    }

    /**
     * Save to database goods for sku and url.
     *
     * @param sku id goods,
     * @param url url goods
     * @return redirect page onliner.jsp
     */
    @PostMapping(value = "/bind/{sku}/{url}")
    public String bindOnliner(@PathVariable(value = "sku") String sku, @PathVariable(value = "url") String url) {
        onlinerService.save(sku, url);
        return "redirect:/onliner";
    }

    /**
     * Delete goods from database for id.
     *
     * @param id id goods
     * @return redirect page onliner.jsp
     */
    @PostMapping(value = "/delete_goods/{id}")
    public String deleteGoods(@PathVariable(value = "id") int id) {
        onlinerService.delete(id);
        return "redirect:/onliner";
    }

    /**
     * Change goods status from "unattached" to "attached".
     *
     * @param id
     * @return redirect page onliner.jsp
     */
    @GetMapping(value = "/move_goods/{id}")
    public String moveGoods(@PathVariable(value = "id") int id) {
        onlinerService.moveGoods(id);
        return "redirect:/onliner";
    }

}
