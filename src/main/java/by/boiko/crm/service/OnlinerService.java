package by.boiko.crm.service;

import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Review;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.Table;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public interface OnlinerService {

    List<Review> getReviews(String decodedUrl, WebDriver driver) throws URISyntaxException, IOException;

    ArrayList<Table> getDescription(String url, WebDriver driver);

    List<String> getImages(String url, WebDriver driver);

    void saveImagesToDisk(List<SkuModel> skuModelsList) throws IOException;

    void save(String skm, String url);

    List getAllUnattachedGoods(int page);

    void delete(int sku);

    void moveGoods(int id);

    List<Onliner> getAllGoods(List<SkuModel> decodedUrl) throws URISyntaxException, IOException;

    List<SkuModel> loadGoods();

    void saveGoods(String sku, String name) throws UnsupportedEncodingException;

    int getAllCount();

    void writeToFile(String str);

    void saveToDb();

    void equalsToDb();

    void test() throws IOException;
}
