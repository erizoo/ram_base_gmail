package by.boiko.crm.controller;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.SkuModel;
import by.boiko.crm.model.Table;
import by.boiko.crm.service.OnlinerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Controller
public class OnlinerController {

    @Autowired
    private OnlinerService onlinerService;

    @RequestMapping(value = "/onliner")
    public ModelAndView toPageOnliner() {
        return new ModelAndView("onliner");
    }

    @RequestMapping(value = "/bind/{sku}/{url}")
    public String bindOnliner(@PathVariable(value = "sku") String sku, @PathVariable(value = "url") String url) {
        String decodedUrl = new String(Base64.getDecoder().decode(url));
        SkuModel skuModel = new SkuModel();
        skuModel.setSku(sku);
        skuModel.setUrl(decodedUrl);
        onlinerService.save(skuModel);
        return "redirect:/onliner";

    }

    @ResponseBody
    @RequestMapping(value = "/search/{name}")
    public List<String> searchName(@PathVariable(value = "name") String name) {
       return onlinerService.getNames(name);
    }


    @ResponseBody
    @RequestMapping(value = "/description")
    public ArrayList<Table> getDescription() {
        return onlinerService.getDescription();
    }
}
