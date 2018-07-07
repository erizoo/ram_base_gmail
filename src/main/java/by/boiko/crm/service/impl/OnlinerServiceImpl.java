package by.boiko.crm.service.impl;

import by.boiko.crm.dao.OnlinerDao;
import by.boiko.crm.model.Onliner;
import by.boiko.crm.model.Review;
import by.boiko.crm.model.Table;
import by.boiko.crm.model.pojo.PendingGoods;
import by.boiko.crm.model.pojo.SkuModel;
import by.boiko.crm.model.pojo.UnattachedGoods;
import by.boiko.crm.service.OnlinerService;
import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.cloudinary.json.JSONArray;
import org.cloudinary.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class OnlinerServiceImpl implements OnlinerService {

    @Autowired
    private OnlinerDao onlinerDao;

    public static void main(String[] args) throws IOException {

    }

    @Override
    public List<Review> getReviews(String decodedUrl, WebDriver driver) throws URISyntaxException, IOException {


        List<Review> listReviews = new ArrayList<>();
        List<String> listStarsForReviews = new ArrayList<>();
        List<String> listTextForReviews = new ArrayList<>();
        List<String> listTextForPlusReviews = new ArrayList<>();
        List<String> listTextForMinusReviews = new ArrayList<>();
        String stars = null;

        String[] urls = decodedUrl.split("/");


        try {
            URL oracle = new URL("https://catalog.api.onliner.by/products/" + urls[urls.length - 1]);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null)
                sb.append(inputLine);
            in.close();
            String str = String.valueOf(sb);
            JSONObject json = new JSONObject(str);
            JSONObject reviews = json.getJSONObject("reviews");
            if (reviews.getInt("count") > 0) {
                driver.navigate().to(decodedUrl + "/reviews?region=minsk");
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
                for (WebElement str1 : listReviewsText) {
                    String name = str1.getText();
                    listTextForReviews.add(name);
                }
                for (WebElement str2 : listPlusReviewsText) {
                    String name = str2.getText();
                    listTextForPlusReviews.add(name);
                }
                for (WebElement str3 : listMinusReviewsText) {
                    String name = str3.getText();
                    listTextForMinusReviews.add(name);
                }
                for (int i = 0; i <= listStarsForReviews.size() - 1; i++) {
                    listReviews.add(new Review(listStarsForReviews.get(i), listTextForReviews.get(i), listTextForPlusReviews.get(i), listTextForMinusReviews.get(i)));
                }
                System.out.println("Больше нуля");
                System.out.println(listReviews);
            } else {
                listReviews.add(new Review("empty", "empty", "empty", "empty"));
                System.out.println("Равно нулю");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return listReviews;
    }

    @Override
    public ArrayList<Table> getDescription(String url, WebDriver driver) {

        driver.navigate().to(url);
        ArrayList<Table> listTable = new ArrayList<>();
        List<Table.TypeTrTable> listTableTr = null;


        try {
            List<WebElement> listReviewsText = driver.findElements(By.cssSelector("table.product-specs__table"));
            ArrayList<String> listCategories = new ArrayList<>();
            int i = 0;
            String picture = null;
            int countTdElements = 0;
            for (WebElement list : listReviewsText) {
                List<WebElement> TBodyCollection = list.findElements(By.tagName("tbody"));
                for (WebElement tbody : TBodyCollection) {
                    String[] tbodyItem = tbody.getText().split("\\n");
                    listTableTr = new ArrayList<>();
                    if (tbodyItem[1].contains("Описание")) {
                        listCategories.add(tbodyItem[0]);
                        listTableTr.add(new Table.TypeTrTable("Описание", tbodyItem[2]));
                        listTable.add(new Table(listCategories.get(i), listTableTr));
                    } else {
                        listCategories.add(tbodyItem[0]);
                        List<WebElement> TRCollection = tbody.findElements(By.tagName("tr"));
                        for (WebElement tr : TRCollection) {
                            List<WebElement> webElementList = tr.findElements(By.tagName("td"));
                            countTdElements = webElementList.size();
                            if (webElementList.size() == 1) {
                                System.out.println(webElementList.get(0).getText());
                            } else {
                                String nameParam = webElementList.get(0).getText();
                                List<WebElement> valueParam = webElementList.get(1).findElements(By.tagName("span"));
                                if (valueParam.size() == 1) {
                                    String valuStr = null;
                                    if (valueParam.get(0).getText().equals("")) {
                                        String strw = valueParam.get(0).getAttribute("class");
                                        if (strw.equals("i-tip")) {
                                            picture = "Есть";
                                        } else {
                                            picture = "Нет";
                                        }
                                        listTableTr.add(new Table.TypeTrTable(nameParam, picture));
                                    } else {
                                        valuStr = valueParam.get(0).getText();
                                        listTableTr.add(new Table.TypeTrTable(nameParam, valuStr));
                                    }
                                } else {
                                    listTableTr.add(new Table.TypeTrTable(nameParam, "Есть" + "," + " " + valueParam.get(1).getText()));
                                }
                            }
                        }
                        if (listCategories.get(0).contains("Общая информация") && countTdElements == 1) {
                            listTable.add(new Table(listCategories.get(i + 1), listTableTr));
                            i++;
                        } else {
                            listTable.add(new Table(listCategories.get(i), listTableTr));
                            i++;
                        }

                    }
                }

            }
        } catch (Exception e) {
            listTable.add(new Table("null", listTableTr));
        }

        return listTable;
    }

    public List<String> getImages(String url, WebDriver driver) {
        driver.navigate().to(url);
        List<String> stringList = new ArrayList<>();
        try {
            List<WebElement> listImages = driver.findElements(By.xpath("//div[@class='product-gallery__shaft']"));
            for (WebElement list : listImages) {
                List<WebElement> link = list.findElements(By.className("product-gallery__thumb"));
                for (WebElement listImag : link) {
                    String linkImage = listImag.getAttribute("data-original");
                    stringList.add(linkImage);
                }
            }
        } catch (Exception e) {
            stringList.add("null");
        }
        return stringList;
    }

    @Override
    public void save(String sku, String url) {
        String decodedUrl = new String(Base64.getDecoder().decode(url));
        SkuModel skuModel = new SkuModel();
        skuModel.setSku(sku);
        skuModel.setUrl(decodedUrl);
//        onlinerDao.save(skuModel);
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
    public void moveGoods(String id, String url) {
        onlinerDao.moveGoods(id,url);
    }


    @Override
    public List<Onliner> getAllGoods(List<SkuModel> skuModelList) throws IOException {
        Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\resultParsing.txt"), "utf-8"));
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\phantomjs\\bin\\phantomjs.exe");
        WebDriver driver = new PhantomJSDriver(caps);
        List<PendingGoods> pendingGoods = onlinerDao.getCheckGoods();
        List<Onliner> onlinerList = new ArrayList<>();
        for (PendingGoods itemList : pendingGoods) {
            String shortDescription;
            try {
                shortDescription = getShortDescription(itemList.getName(), driver);
                System.out.println(shortDescription);
            } catch (Exception e) {
                shortDescription = "nope";
                System.out.println(shortDescription);
            }
            System.out.println(itemList.getSku() + " " + itemList.getName());
            ArrayList<Table> description = getDescription(itemList.getName(), driver);
            System.out.println(description);
            writer.write(new Gson().toJson(new Onliner(itemList.getSku(), shortDescription, description)));
            writer.write(",");
            writer.flush();
        }
        driver.close();
        return onlinerList;
    }

    private String getShortDescription(String url, WebDriver driver) {
        driver.navigate().to(url);
        WebElement webElement = driver.findElement(By.xpath("//div[@class='offers-description__specs']"));
        return webElement.getText();
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

    @Override
    public void writeToFile(String str) {
        String decodedUrl = new String(Base64.getDecoder().decode(str));
        try {
            String result = URLDecoder.decode(decodedUrl, "UTF-8");
            FileUtils.writeStringToFile(new File("test.json"), result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void saveToDb() {
        String fileName = "D://lines.txt";
        List<String> stringList = new ArrayList<>();
        List<String> skuList = new ArrayList<>();
        List<String> nameList = new ArrayList<>();
        //read file into stream, try-with-resources
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stringList = stream.collect(Collectors.toList());
            for (String items : stringList) {
                String[] strings = items.split(",");
                skuList.add(strings[0]);
                nameList.add(strings[1]);
            }
            for (int i = 0; i < nameList.size() - 1; i++) {
                onlinerDao.saveGoods(skuList.get(i), nameList.get(i));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void equalsToDb() {
        List<SkuModel> skuModels = new ArrayList<>();
        List<Integer> deleteGoods = new ArrayList<>();
        List<UnattachedGoods> loadAllUnattachedGoods = onlinerDao.loadAllUnattachedGoods();
        for (UnattachedGoods unattachedGoods : loadAllUnattachedGoods) {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Integer id = unattachedGoods.getId();
            String name = unattachedGoods.getName().replaceAll(" ", "%20");
            String sku = unattachedGoods.getSku();
            try {
                URL oracle = new URL("https://catalog.api.onliner.by/search/products?query=" + name);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(oracle.openStream()));

                String inputLine;
                StringBuilder sb = new StringBuilder();
                while ((inputLine = in.readLine()) != null)
                    sb.append(inputLine);
                in.close();
                String str = String.valueOf(sb);
                JSONObject json = new JSONObject(str);
                JSONArray products = json.getJSONArray("products");
                if (products.length() == 1) {
                    JSONObject url = products.getJSONObject(0);
                    String strUrl = url.getString("html_url");
                    System.out.println(strUrl);
                    String urlStr = url.getString("url");
                    deleteGoods.add(id);
                    skuModels.add(new SkuModel(sku, name, strUrl, urlStr));

                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        for (Integer idItems : deleteGoods) {
            onlinerDao.delete(idItems);
        }

        for (SkuModel items : skuModels) {
            SkuModel skuModel = new SkuModel();
            skuModel.setSku(items.getSku());
            skuModel.setName(items.getName());
            skuModel.setUrl(items.getUrl());
//            onlinerDao.save(skuModel);
        }
    }

    @Override
    public List<Table> test() throws IOException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\phantomjs\\bin\\phantomjs.exe");
        WebDriver driver = new PhantomJSDriver(caps);

        driver.navigate().to("https://catalog.onliner.by/notebook/hp/1za81ea");
        ArrayList<Table> listTable = new ArrayList<>();
        List<Table.TypeTrTable> listTableTr = null;

        try {
            List<WebElement> listReviewsText = driver.findElements(By.cssSelector("table.product-specs__table"));
            ArrayList<String> listCategories = new ArrayList<>();
            int i = 0;
            String picture = null;
            int countTdElements = 0;
            for (WebElement list : listReviewsText) {
                List<WebElement> TBodyCollection = list.findElements(By.tagName("tbody"));
                for (WebElement tbody : TBodyCollection) {
                    String[] tbodyItem = tbody.getText().split("\\n");
                    listTableTr = new ArrayList<>();
                    if (tbodyItem[1].contains("Описание")) {
                        listCategories.add(tbodyItem[0]);
                        listTableTr.add(new Table.TypeTrTable("Описание", tbodyItem[2]));
                        listTable.add(new Table(listCategories.get(i), listTableTr));
                    } else {
                        listCategories.add(tbodyItem[0]);
                        List<WebElement> TRCollection = tbody.findElements(By.tagName("tr"));
                        for (WebElement tr : TRCollection) {
                            List<WebElement> webElementList = tr.findElements(By.tagName("td"));
                            countTdElements = webElementList.size();
                            if (webElementList.size() == 1) {
                                System.out.println(webElementList.get(0).getText());
                            } else {
                                String nameParam = webElementList.get(0).getText();
                                List<WebElement> valueParam = webElementList.get(1).findElements(By.tagName("span"));
                                if (valueParam.size() == 1) {
                                    String valuStr = null;
                                    if (valueParam.get(0).getText().equals("")) {
                                        String strw = valueParam.get(0).getAttribute("class");
                                        if (strw.equals("i-tip")) {
                                            picture = "Есть";
                                        } else {
                                            picture = "Нет";
                                        }
                                        listTableTr.add(new Table.TypeTrTable(nameParam, picture));
                                    } else {
                                        valuStr = valueParam.get(0).getText();
                                        listTableTr.add(new Table.TypeTrTable(nameParam, valuStr));
                                    }
                                } else {
                                    listTableTr.add(new Table.TypeTrTable(nameParam, "Есть" + "," + " " + valueParam.get(1).getText()));
                                }
                            }
                        }
                        if (listCategories.get(0).contains("Общая информация") && countTdElements == 1) {
                            listTable.add(new Table(listCategories.get(i + 1), listTableTr));
                            i++;
                        } else {
                            listTable.add(new Table(listCategories.get(i), listTableTr));
                            i++;
                        }

                    }
                }

            }
        } catch (Exception e) {
            listTable.add(new Table("null", listTableTr));
        }

        driver.close();
        return listTable;
    }

    @Override
    public Onliner getGoods(String url, String sku) {
        Writer writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("D:\\test.txt"), "utf-8"));
        } catch (UnsupportedEncodingException | FileNotFoundException e) {
            e.printStackTrace();
        }
        String decodedUrl = new String(Base64.getDecoder().decode(url));

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "C:\\phantomjs\\bin\\phantomjs.exe");
        WebDriver driver = new PhantomJSDriver(caps);

        String shortDescription = getShortDescription(decodedUrl, driver);
        System.out.println(shortDescription);
//            List<Review> reviews = getReviews(itemList.getUrl(), driver);
//            System.out.println(reviews);
        ArrayList<Table> description = getDescription(decodedUrl, driver);
        try {
            LocalDateTime currentTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formatDateTime = currentTime.format(formatter);
            writer.write("<!-- boiko-" + formatDateTime + "-->");
            writer.write("\n");
            writer.write("<table>");
            for (Table item : description) {
                writer.write("<tr>\n" +
                        "<td colspan=\"2\" class=\"param-block\"><b>" + item.getCategory() + "</b></td>\n" +
                        "</tr>");
                for (Table.TypeTrTable items: item.getListRow()) {
                    writer.write("<tr>\n" +
                            "<td class=\"param-name\">" + items.getParameter() + "</td>\n" +
                            "<td>" + items.getValue() + "</td>\n" +
                            "</tr>");
                }

            }
            writer.write("</table>");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Onliner onliner = new Onliner(description);
        driver.close();
        return onliner;
    }


    @Override
    public void getCheckGood() throws FileNotFoundException, UnsupportedEncodingException {
        List<PendingGoods> list = onlinerDao.getCheckGoods();
        Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("D:\\pendingGoods.txt"), "utf-8"));
        for (PendingGoods items : list) {
            System.out.println(items.getName());
            try {
                writer.write(items.getSku());
                writer.write("\n");
                writer.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


//
//        // overlay settings
//        File input = new File("D://duke.jpeg");
//        File overlay = new File("D://logo.png");
//        File output = new File("D://duke-image-watermarked.png");
//
//        // adding text as overlay to an image
//        addImageWatermark(overlay, "png", input, output);
//    }
//
//    private static void addImageWatermark(File watermark, String type, File source, File destination) throws IOException {
//        BufferedImage image = ImageIO.read(source);
//        BufferedImage overlay = resize(ImageIO.read(watermark), 70, 220);
//
//        // determine image type and handle correct transparency
//        int imageType = "png".equalsIgnoreCase(type) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB;
//        BufferedImage watermarked = new BufferedImage(image.getWidth(), image.getHeight(), imageType);
//
//        // initializes necessary graphic properties
//        Graphics2D w = (Graphics2D) watermarked.getGraphics();
//        w.drawImage(image, 0, 0, null);
//        AlphaComposite alphaChannel = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f);
//        w.setComposite(alphaChannel);
//
//        // calculates the coordinate where the String is painted
//        int centerX = image.getWidth() - 200;
//        int centerY = image.getHeight() - 80;
//
//        // add text watermark to the image
//        w.drawImage(overlay, centerX, centerY, null);
//        ImageIO.write(watermarked, type, destination);
//        w.dispose();
//    }
//
//    private static BufferedImage resize(BufferedImage img, int height, int width) {
//        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
//        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
//        Graphics2D g2d = resized.createGraphics();
//        g2d.drawImage(tmp, 0, 0, null);
//        g2d.dispose();
//        return resized;

//        List<String> stringList = new ArrayList<>();
//        stringList.add("123534");
//        stringList.add("342342");
//        stringList.add("123325");
//        DesiredCapabilities caps = new DesiredCapabilities();
//        caps.setJavascriptEnabled(true);
//        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
//                "D:\\phantomjs\\bin\\phantomjs.exe");
//
//        WebDriver driver = new PhantomJSDriver(caps);
//
//        for (String str : stringList) {
//            File folders = new File("D:\\onliner_images\\" + str);
//            folders.mkdir();
//
//
//            driver.navigate().to("https://catalog.onliner.by/microphones/lewitt/lct240");
//            List<String> stringArrayList = new ArrayList<>();
//
//            List<WebElement> listImages = driver.findElements(By.xpath("//div[@class='product-gallery__shaft']"));
//            for (WebElement list : listImages) {
//                List<WebElement> link = list.findElements(By.className("product-gallery__thumb"));
//                for (WebElement listImag : link) {
//                    String linkImage = listImag.getAttribute("data-original");
//                    stringArrayList.add(linkImage);
//                }
//            }
//
//            for (String images : stringArrayList) {
//                URL url = new URL(images);
//                InputStream in = new BufferedInputStream(url.openStream());
//                OutputStream out = new BufferedOutputStream(new FileOutputStream("D:\\onliner_images\\" + str + "\\" + "Image-Porkeri_001.jpg"));
//                out.write(in.read());
//                in.close();
//                out.close();
//            }
//
//        }

//        String fileName = "test.txt";
//        try {
//            PrintWriter out = new PrintWriter(new FileWriter(folder.toString() + "/" + fileName));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


    public void saveImagesToDisk(List<SkuModel> skuModelsList) throws IOException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setJavascriptEnabled(true);
        caps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,
                "D:\\phantomjs\\bin\\phantomjs.exe");

        WebDriver driverr = new PhantomJSDriver(caps);

        List<String> stringList = new ArrayList<>();
        stringList.add("123534");


        for (SkuModel str : skuModelsList) {
            File folders = new File("D:\\onliner_images\\" + str.getSku());
            folders.mkdir();

            driverr.navigate().to(str.getUrl());
            List<String> stringListArray = new ArrayList<>();

            List<WebElement> listImages = driverr.findElements(By.xpath("//div[@class='product-gallery__shaft']"));
            for (WebElement list : listImages) {
                List<WebElement> link = list.findElements(By.className("product-gallery__thumb"));
                for (WebElement listImag : link) {
                    String linkImage = listImag.getAttribute("data-original");
                    if (linkImage.trim().length() != 0) {
                        stringListArray.add(linkImage);
                    }
                }
            }
            for (String images : stringListArray) {
                BufferedImage image = null;
                URL urlImages = new URL(images);
                System.out.println(images);
                image = ImageIO.read(urlImages);
                String[] nameImage = images.split("/");
                ImageIO.write(image, "jpg", new File("D:\\onliner_images\\" + str.getSku() + "\\" + nameImage[5] + ".jpg"));
            }
        }
    }
}



