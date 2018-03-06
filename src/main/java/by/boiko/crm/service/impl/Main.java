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

        String fileName = "D:\\бойко.txt";
        BufferedImage image = null;
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> result = stream.collect(Collectors.toList());
        int count = 0;
        for (String items : result) {
            Files.createDirectories(Paths.get("D:\\foto\\" + items));
            count++;
            if (count == 1500){
                break;
            }
        }

    }
}
