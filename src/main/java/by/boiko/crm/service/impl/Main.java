package by.boiko.crm.service.impl;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] argv) throws IOException {

        String fileName = "D:\\result.txt";
        BufferedImage image = null;
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> result = stream.collect(Collectors.toList());
        for (String items : result) {
            String[] strings = items.split(";");
            Files.createDirectories(Paths.get("D:\\market\\" + strings[0]));
//            new File("D:\\market\\" + strings[0]).mkdir();
            URL url = new URL(strings[1]);
            image = ImageIO.read(url);
            System.out.println(url);
            ImageIO.write(image, "jpg", new File("D:\\market\\" + strings[0] + "\\" + "0.jpg"));
        }

    }
}
