package by.boiko.crm.service.impl;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Erizo on 20.12.2017.
 */
public class JsonToFile {

    public static void main(String[] args) {
        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("D:\\test.json"));

            JSONArray jsonArray = (JSONArray) obj;


            for (Object items : jsonArray) {
                JSONObject jsonObject = (JSONObject) items;
                JSONArray images = (JSONArray) jsonObject.get("images");
                String sku = (String) jsonObject.get("sku");
                System.out.println(images);
                File folders = new File("D:\\onliner\\" + sku);
                folders.mkdir();
                for (Object url : images) {
                    Thread.sleep(1000);
                    if(images.contains("png")){
                        BufferedImage image = null;
                        try {
                            URL urlImages = new URL((String) url);
                            String str = String.valueOf(urlImages);
                            System.out.println(url);
                            image = ImageIO.read(urlImages);
                            String[] nameImage = str.split("/");
                            ImageIO.write(image, "png", new File("D:\\onliner_images\\" + sku + "\\" + nameImage[6] + ".png"));
                        }catch (Exception e){
                            System.out.println(e.getMessage());
                        }
                    } else {
                        System.out.println("Не png");
                    }
                }
            }
//
        } catch (IOException | ParseException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
