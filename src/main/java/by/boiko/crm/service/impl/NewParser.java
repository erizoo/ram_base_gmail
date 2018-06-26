package by.boiko.crm.service.impl;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NewParser {

    public static void main(String[] args) throws IOException {
        compare();
    }

    private static void compare() throws IOException {
        String fileNameOne = "pricesResult.txt";
        String fileNameTwo = "lines.txt";
        Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("allResult.txt"), "utf-8"));
        Stream<String> streamFirst = Files.lines(Paths.get(fileNameOne));
        Stream<String> streamSecond = Files.lines(Paths.get(fileNameTwo));
        List<String> resultFirst = streamFirst.collect(Collectors.toList());
        List<String> resultSecond = streamSecond.collect(Collectors.toList());
        for (String items : resultFirst) {
            String[] strings = items.split(";");
            for (String itemsTwo : resultSecond){
                String[] stringsTwo = itemsTwo.split(",");
                System.out.println(strings[0] + ";" + stringsTwo[0]);
                if (strings[0].equals(stringsTwo[0])){
                    System.out.println(strings[0] + ";" + stringsTwo[0]);
                    writer.write(strings[0] + ";" + strings[1]);
                    writer.write("\n");
                    writer.flush();
                }
            }
        }
    }

    public void fromCsvToTxt() throws IOException {
        List<NewParserModel> list = new ArrayList<>();
        String fileName = "prices.txt";
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> result = stream.collect(Collectors.toList());
        for (String items : result) {
            String[] strings = items.split(";");
            list.add(new NewParserModel(strings[0], strings[2]));
        }
        Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("pricesResult.txt"), "utf-8"));
        for (NewParserModel items : list) {
            writer.write(items.getSku() + ";" + items.getUrl());
            writer.write("\n");
            writer.flush();
        }
    }
}
