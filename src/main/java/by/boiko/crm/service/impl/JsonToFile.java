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
//
//    public static void main(String[] args) throws IOException {
//        BufferedImage image = null;
//        URL urlImages = new URL( "https://content2.onliner.by/catalog/device/large/3399b15b0627a4f5bb588a65028d15fe.png");
//        String str = String.valueOf(urlImages);
//        if (str.contains("png")) {
//            System.out.println(urlImages);
//            image = ImageIO.read(urlImages);
//            String[] nameImage = str.split("/");
//            String name = nameImage[6].substring(0, nameImage[6].length() - 5);
//            int width = image.getWidth();
//            int height = image.getHeight();
//            BufferedImage croppedImage = image.getSubimage(0, 0, width, height-30);
//            ImageIO.write(croppedImage, "png", new File("D:\\" + name + ".png"));
//
//        } else {
//            System.out.println("те пнг");
//        }
//
//    }


    public static void main(String[] args) throws ParseException, InterruptedException, IOException {
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
                    BufferedImage image = null;
                    try {
                        URL urlImages = new URL((String) url);
                        String str = String.valueOf(urlImages);
                        if (str.contains("png")) {
                            System.out.println(sku);
                            System.out.println(url);
                            image = ImageIO.read(urlImages);
                            String[] nameImage = str.split("/");
                            String name = nameImage[6].substring(0, nameImage[6].length() - 5);
                            int width = image.getWidth();
                            int height = image.getHeight();
                            BufferedImage croppedImage = null;
                            if (height > width * 2) {
                                croppedImage = image.getSubimage(0, 0, width - 30, height - 30);
                            } else {
                                croppedImage = image.getSubimage(0, 0, width, height - 30);
                            }
                            ImageIO.write(croppedImage, "png", new File("D:\\onliner\\" + sku + "\\" + name + ".png"));
                        } else {
                            System.out.println(url);
                            image = ImageIO.read(urlImages);
                            String[] nameImage = str.split("/");
                            String name = nameImage[6].substring(0, nameImage[6].length() - 5);
                            int width = image.getWidth();
                            int height = image.getHeight();
                            BufferedImage croppedImage = null;
                            if (height > width * 2) {
                                croppedImage = image.getSubimage(0, 0, width - 30, height);
                            } else {
                                croppedImage = image.getSubimage(0, 0, width, height - 30);
                            }
                            ImageIO.write(croppedImage, "jpg", new File("D:\\onliner\\" + sku + "\\" + name + ".jpg"));
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }


            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
