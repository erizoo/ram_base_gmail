package by.boiko.crm.service;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Review;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.Table;
import by.boiko.crm.model.pojo.UnattachedGoods;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public interface OnlinerService {

    List<Review> getReviews(String decodedUrl);

    ArrayList<Table> getDescription(String url);

    List<String> getImages(String url);

    void save(SkuModel skuModel);

    List<UnattachedGoods> getAllUnattachedGoods();

    void delete(int sku);

    void moveGoods(int id);

    List<Onliner> getAllGoods(List<SkuModel> decodedUrl);

    List<SkuModel> loadGoods();
}
