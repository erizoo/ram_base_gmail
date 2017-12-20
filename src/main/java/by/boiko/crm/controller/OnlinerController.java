package by.boiko.crm.controller;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.pojo.UnattachedGoods;
import by.boiko.crm.service.OnlinerService;
import io.github.bonigarcia.wdm.PhantomJsDriverManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.HttpResource;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import static org.apache.commons.io.FileUtils.getFile;

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

    /**
     * Get all the parameters of the attached products.
     *
     * @return JSON
     */
//    @ResponseBody
//    @GetMapping(value = "/all_goods")
//    public List<Onliner> getAllGoods() throws URISyntaxException, IOException {
//        List<SkuModel> skuModelsList = onlinerService.loadGoods();
//        return onlinerService.getAllGoods(skuModelsList);
//    }
    @GetMapping(value = "/all_goods")
    public @ResponseBody HttpEntity<byte[]> downloadB() throws IOException, URISyntaxException {

        List<SkuModel> skuModelsList = onlinerService.loadGoods();
        onlinerService.getAllGoods(skuModelsList);
        File file = getFile();
        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition", "inline; filename=" + file.getName());
        header.setContentLength(document.length);

        return new HttpEntity<byte[]>(document, header);
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
    @GetMapping(value = "/move_goods/{id}")
    public String moveGoods(@PathVariable(value = "id") int id) {
        onlinerService.moveGoods(id);
        return "redirect:/onliner";
    }

}
