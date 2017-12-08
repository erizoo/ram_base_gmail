package by.boiko.crm.service.impl;

import by.boiko.crm.dao.OnlinerDao;
import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Review;
import by.boiko.crm.model.Table;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.pojo.UnattachedGoods;
import by.boiko.crm.service.OnlinerService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
@Transactional
public class OnlinerServiceImpl implements OnlinerService {

    @Autowired
    private OnlinerDao onlinerDao;

    @Override
    public List<Review> getReviews(String decodedUrl) {
        WebDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\phantomjs\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);
        driver.navigate().to(decodedUrl + "/reviews");
        List<Review> listReviews = new ArrayList<>();
        List<String> listStarsForReviews = new ArrayList<>();
        List<String> listTextForReviews = new ArrayList<>();
        List<String> listTextForPlusReviews = new ArrayList<>();
        List<String> listTextForMinusReviews = new ArrayList<>();
        String stars = null;

        List<WebElement> list = driver.findElements(By.xpath("//div[@class='rev-rating']/img[1]"));
        List<WebElement> listReviewsText = driver.findElements(By.className("rev-content"));
        List<WebElement> listPlusReviewsText = driver.findElements(By.xpath("//div[@class='revpc pros']"));
        List<WebElement> listMinusReviewsText = driver.findElements(By.xpath("//div[@class='revpc cons']"));
        for (WebElement element : list) {
            String link = element.getAttribute("src");
            System.out.println(element.getTagName() + "=" + link + ", " + element.getText());
            if (link.contains("_4")) {
                stars = "4";
            }
            if (link.contains("_5")) {
                stars = "5";
            }
            if (link.contains("_3")) {
                stars = "3";
            }
            if (link.contains("_2")) {
                stars = "2";
            }
            if (link.contains("_1")) {
                stars = "1";
            }
            listStarsForReviews.add(stars);
        }
        for (WebElement str : listReviewsText) {
            String name = str.getText();
            listTextForReviews.add(name);
        }
        for (WebElement str : listPlusReviewsText) {
            String name = str.getText();
            listTextForPlusReviews.add(name);
        }
        for (WebElement str : listMinusReviewsText) {
            String name = str.getText();
            listTextForMinusReviews.add(name);
        }
        for (int i = 0; i <= listStarsForReviews.size() - 1; i++) {
            listReviews.add(new Review(listStarsForReviews.get(i), listTextForReviews.get(i), listTextForPlusReviews.get(i), listTextForMinusReviews.get(i)));
        }

        driver.close();
        return listReviews;
    }

    @Override
    public ArrayList<Table> getDescription(String url) {
        WebDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\phantomjs\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);
        driver.navigate().to(url);

        List<WebElement> listReviewsText = driver.findElements(By.cssSelector("table.product-specs__table"));
        ArrayList<String> listCategories = new ArrayList<>();
        ArrayList<Table> listTable = new ArrayList<>();
        List<Table.TypeTrTable> listTableTr = null;
        int i = 0;
        for (WebElement list : listReviewsText) {
            List<WebElement> TBodyCollection = list.findElements(By.tagName("tbody"));
            for (WebElement tbody : TBodyCollection) {
                String[] tbodyItem = tbody.getText().split("\\n");
                listCategories.add(tbodyItem[0]);
                List<WebElement> TRCollection = tbody.findElements(By.tagName("tr"));
                listTableTr = new ArrayList<>();
                for (WebElement tr : TRCollection) {
                    if (tr.getText().contains("\n")) {
                        String[] item = tr.getText().split("\n");
                        ArrayList<String> listParameters = new ArrayList<>();
                        ArrayList<String> listValues = new ArrayList<>();
                        listParameters.add(item[0]);
                        listValues.add(item[1]);
                        listTableTr.add(new Table.TypeTrTable(listParameters.get(0), listValues.get(0)));
                    }
                }
                listTable.add(new Table(listCategories.get(i), listTableTr));
                i++;
            }

        }
        return listTable;
    }

    public List<String> getImages(String url) {
        WebDriver driver;
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\phantomjs\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        driver = new PhantomJSDriver(caps);
        driver.navigate().to(url);

        List<String> stringList = new ArrayList<>();
        List<WebElement> listImages = driver.findElements(By.xpath("//div[@class='product-gallery__shaft']"));
        for (WebElement list : listImages) {
            List<WebElement> link = list.findElements(By.className("product-gallery__thumb"));
            for (WebElement listImag : link) {
                String linkImage = listImag.getAttribute("data-original");
                stringList.add(linkImage);
            }
        }
        driver.close();
        return stringList;
    }

    @Override
    public void save(String sku, String url) {
        String decodedUrl = new String(Base64.getDecoder().decode(url));
        SkuModel skuModel = new SkuModel();
        skuModel.setSku(sku);
        skuModel.setUrl(decodedUrl);
        onlinerDao.save(skuModel);
    }

    @Override
    public List<UnattachedGoods> getAllUnattachedGoods(int page) {
        return onlinerDao.loadAllGoods(page);
    }

    @Override
    public void delete(int id) {
        onlinerDao.delete(id);
    }

    @Override
    public void moveGoods(int id) {
        onlinerDao.moveGoods(id);
    }

    @Override
    public List<Onliner> getAllGoods(List<SkuModel> skuModelList) {
        List<Onliner> onlinerList = new ArrayList<>();
        for (SkuModel itemList : skuModelList) {
            onlinerList.add(new Onliner(getReviews(itemList.getUrl()), getDescription(itemList.getUrl()), getImages(itemList.getUrl())));

        }
        return onlinerList;
    }

    @Override
    public List<SkuModel> loadGoods() {
        return onlinerDao.loadGoods();
    }

    @Override
    public void saveGoods(String sku, String name) throws UnsupportedEncodingException {
        String decodedUrl = new String(Base64.getDecoder().decode(name));
        String result = URLDecoder.decode(decodedUrl, "UTF-8");
        onlinerDao.saveGoods(sku, result);
    }

    @Override
    public int getAllCount() {
        return onlinerDao.getAllCount();
    }
}
