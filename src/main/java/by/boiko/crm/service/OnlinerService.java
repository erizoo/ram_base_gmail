package by.boiko.crm.service;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.Table;
import by.boiko.crm.model.pojo.UnattachedGoods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface OnlinerService {

    List<Onliner> getReviews(String decodedUrl);

    ArrayList<Table> getDescription();

    List<String> getImages();

    List<String> getNames(String name);

    void save(SkuModel skuModel);

    List<UnattachedGoods> getAllGoods();

    void delete(String sku);

    UnattachedGoods findBySky(String sku);
}
