package by.boiko.crm.controller;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Table;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.pojo.UnattachedGoods;
import by.boiko.crm.service.MarketService;
import by.boiko.crm.service.OnlinerService;
import by.boiko.crm.service.impl.MarketParserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Base64;
import java.util.List;

import static org.apache.commons.io.FileUtils.getFile;

/**
 * Controller for parsing if Onliner.by.
 */
@Controller
public class OnlinerController {

    @Autowired
    private final OnlinerService onlinerService;

    @Autowired
    public OnlinerController(OnlinerService onlinerService) {
        this.onlinerService = onlinerService;
    }

    @ResponseBody
    @GetMapping(value = "/getGoods")
    public List<SkuModel> getGoods() {
        return onlinerService.loadGoods();
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
        ModelAndView mv = new ModelAndView("onliner");
        mv.addObject("counts", onlinerService.getAllCount());
        return mv;
    }


//    public String writeFile(@RequestParam(value = "value") String value) {
//        onlinerService.writeToFile(value);
//        return "redirect:/listCategory";
//    }

    /**
     * Get all unattached goods.
     *
     * @return JSON
     */
    @ResponseBody
    @GetMapping(value = "/goods/{page}")
    public List<UnattachedGoods> getAllUnattachedGoods(@PathVariable(value = "page") int page) {
        return onlinerService.getAllUnattachedGoods(page);
    }

    @GetMapping(value = "/save_goods")
    public String saveGoods() {
        onlinerService.saveToDb();
        return "redirect:/onliner";
    }

    @GetMapping(value = "/get_check_good")
    public String getCheckGood() throws FileNotFoundException, UnsupportedEncodingException {
        onlinerService.getCheckGood();
        return "redirect:/onliner";
    }

    @GetMapping(value = "/equals")
    public String equalsGoods() throws URISyntaxException, IOException {
        onlinerService.equalsToDb();
        return "redirect:/onliner";
    }

    /**
     * Get all the parameters of the attached products.
     *
     * @return JSON
     */
    @ResponseBody
    @GetMapping(value = "/all_goods")
    public List<Onliner> getAllGoods() throws URISyntaxException, IOException {
        List<SkuModel> skuModelsList = onlinerService.loadGoods();
        return onlinerService.getAllGoods(skuModelsList);
    }

    @ResponseBody
    @GetMapping(value = "/all_goods/{url}/{sku}")
    public Onliner getGoodsFast(@PathVariable(value = "url") String url, @PathVariable(value = "sku") String sku) {
        return onlinerService.getGoods(url, sku);
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

    @PostMapping(value = "/goods/{sku}/{name}")
    public String getGoods(@PathVariable(value = "sku") String sku, @PathVariable(value = "name") String name) throws UnsupportedEncodingException {
        onlinerService.saveGoods(sku, name);
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
    @PostMapping(value = "/move_goods/{id}/{url}")
    public String moveGoods(@PathVariable(value = "id") String id, @PathVariable(value = "url") String url) {
        String decoded = new String(Base64.getDecoder().decode(url));
        onlinerService.moveGoods(id, decoded);
        return "redirect:/onliner";
    }


    @ResponseBody
    @GetMapping(value = "/test")
    public List<Table> test() throws IOException {
        return onlinerService.test();
    }

    @GetMapping(value = "/start")
    public String startParser() throws IOException, InterruptedException {
        return "redirect:/onliner";
    }

}
