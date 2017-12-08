package by.boiko.crm.service;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Review;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.Table;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public interface OnlinerService {

    List<Review> getReviews(String decodedUrl);

    ArrayList<Table> getDescription(String url);

    List<String> getImages(String url);

    void save(String skm, String url);

    List getAllUnattachedGoods(int page);

    void delete(int sku);

    void moveGoods(int id);

    List<Onliner> getAllGoods(List<SkuModel> decodedUrl);

    List<SkuModel> loadGoods();

    void saveGoods(String sku, String name) throws UnsupportedEncodingException;

    int getAllCount();
}
