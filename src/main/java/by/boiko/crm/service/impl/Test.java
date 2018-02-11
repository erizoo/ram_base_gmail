package by.boiko.crm.service.impl;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by Erizo on 29.01.2018.
 */
public class Test {


    public static void main(String[] args) {
        List<String[]> list = new ArrayList<>();
        String fileName = "F://market2.txt";
        String fileNameTest = "F://test2.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            List<String> result = stream.collect(Collectors.toList());
            for (String items : result) {
                String[] strings = items.split(";");
                if (strings.length == 3) {
                    list.add(strings);
                }
            }
            List<String[]> resultForMarket = new ArrayList<>();
            Stream<String> streamtwo = Files.lines(Paths.get(fileNameTest));
            List<String> resultDB = streamtwo.collect(Collectors.toList());
            for (String[] items : list) {
                for (String string : resultDB) {
                    if (items[0].equals(string.replaceAll("\"", ""))) {
                        System.out.println(string.replaceAll("\"", ""));
                        resultForMarket.add(items);
                    }
                }

            }
            PrintWriter pw = new PrintWriter(new FileWriter("out.txt"));

            for (String[] items : resultForMarket) {
                String sku = items[0];
                String url = items[2];
                String resultMar = sku + ";" + url;
                pw.write(resultMar);
                pw.write("\n");
            }
            pw.close();
            System.out.println("dsgsdg");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
