package by.boiko.crm.service.impl;

import by.boiko.crm.model.GazProm;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Erizo on 09.01.2018.
 */
public class HTMLParserExample1 {

    public static void main(String[] args) throws IOException {

        String fileName = "F:\\shell.txt";
        List<String> lines = Files.readAllLines(Paths.get(fileName));
        List<GazProm> gazProms = new ArrayList<>();
        for (String item : lines) {
            String[] strings = item.split(",");
            String[] name = strings[2].split(" ");
            try {
                gazProms.add(new GazProm(name[2].substring(0, name[2].length()-1).replace(".", ""), strings[1].trim(), strings[0].trim()));
            }catch (Exception e){
                System.out.println(Arrays.toString(strings));
            }

        }
        File fileDir = new File("F:\\shell.csv");
            Writer out = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileDir), "cp1251"));

            for (GazProm d : gazProms) {
                out.write("Шелл");
                out.append(";");
                out.append("Заправка");
                out.append(";");
                out.append(d.getAddress());
                out.append(";");
                out.append(d.getLatitud());
                out.append(";");
                out.append(d.getLongitud());
                out.append(";");
                out.append('\n');
            }

            out.flush();
            out.close();
        System.out.println(lines);

//        Document doc;
//
//        doc = Jsoup.connect("http://neftm.ru/#map").get();
//
//        Elements info = doc.select("div#point-ctl-1");
//        Elements links = doc.getElementsByClass("span");
//        System.out.println(links);

//        Document doc;
//        try {
//            List<ParserTap> parserTaps = new ArrayList<>();
//            for (int i = 1; i <= 43; i++) {
//                // need http protocol
//                doc = Jsoup.connect("https://azs.tatneft.ru/gps?region_id=0&region_name=%D0%92%D1%8B%D0%B1%D1%80%D0%B0%D1%82%D1%8C+%D1%80%D0%B5%D0%B3%D0%B8%D0%BE%D0%BD&dont_change_region=0&number=&page=" + i).get();
//
//                // get page title
//                String title = doc.title();
//                System.out.println("title : " + title);
//
//                // get all links
//                Elements links = doc.select("tr");
//                for (int j = 6; j < links.size() ; j++) {
//                    Elements td = links.get(j).select("td");
//                    String city = null;
//                    String address = null;
//                    try {
//                        String[] strings = td.get(1).ownText().split(",");
//                        city = strings[0];
//                        if (strings.length == 3) {
//                            address = strings[1] + strings[2];
//                        }
//                        if (strings.length == 4) {
//                            address = strings[1] + strings[2] + strings[3];
//                        }
//                        if (strings.length == 5) {
//                            address = strings[1] + strings[2] + strings[3] + strings[4];
//                        }
//                        if (strings.length == 6) {
//                            address = strings[1] + strings[2] + strings[3] + strings[4] + strings[5];
//                        }
//                        if (strings.length == 7) {
//                            address = strings[1] + strings[2] + strings[3] + strings[4] + strings[5] + strings[6];
//                        }
//
//                        parserTaps.add(new ParserTap(city, address, td.get(2).ownText(), td.get(3).ownText()));
//                    } catch (Exception e) {
//                        city = "empty";
//                        address = "empty";
//                        parserTaps.add(new ParserTap("empty", "empty", "empty", "empty"));
//                    }
//                }
//
//                System.out.println("done");
//            }
//
//
//            File fileDir = new File("D:\\tatneft.csv");
//            Writer out = new BufferedWriter(new OutputStreamWriter(
//                    new FileOutputStream(fileDir), "cp1251"));
//
//            for (ParserTap d : parserTaps) {
//                out.write("Татнефть");
//                out.append(";");
//                out.append("Заправка");
//                out.append(";");
//                out.append(d.getCity()).append(d.getAddress());
//                out.append(";");
//                out.append(d.getLatitud());
//                out.append(";");
//                out.append(d.getLongitud());
//                out.append('\n');
//            }
//
//            out.flush();
//            out.close();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
// https://www.gpnbonus.ru/our_azs/?region_id=139&region_name=%D0%A1%D0%B0%D0%BD%D0%BA%D1%82-%D0%9F%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3%2F%D0%9B%D0%9E&CenterLon=30.315868&CenterLat=59.939095&city=
//        List<String> listGps = new ArrayList<>();
//        List<String> names = new ArrayList<>();
//        List<GazProm> gazProm = new ArrayList<>();
//
//        load("https://www.gpnbonus.ru/our_azs/?region_id=136&region_name=%D0%9C%D0%BE%D1%81%D0%BA%D0%B2%D0%B0%2F%D0%9C%D0%9E&CenterLon=38.929526&CenterLat=55.531728&city=", listGps, names);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=139&region_name=%D0%A1%D0%B0%D0%BD%D0%BA%D1%82-%D0%9F%D0%B5%D1%82%D0%B5%D1%80%D0%B1%D1%83%D1%80%D0%B3%2F%D0%9B%D0%9E&CenterLon=30.315868&CenterLat=59.939095&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=233&region_name=%D0%90%D0%BB%D1%82%D0%B0%D0%B9%D1%81%D0%BA%D0%B8%D0%B9+%D0%BA%D1%80%D0%B0%D0%B9&CenterLon=82.530013&CenterLat=52.129671&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=504900&region_name=%D0%92%D0%BE%D0%BB%D0%BE%D0%B3%D0%BE%D0%B4%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=39.81184&CenterLat=59.20829&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=375309&region_name=%D0%92%D0%BB%D0%B0%D0%B4%D0%B8%D0%BC%D0%B8%D1%80%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=40.679166&CenterLat=56.013588&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=234&region_name=%D0%98%D0%B2%D0%B0%D0%BD%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=41.554071&CenterLat=56.989772&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=7271989&region_name=%D0%98%D1%80%D0%BA%D1%83%D1%82%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=106.363305&CenterLat=57.100294&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=133&region_name=%D0%9A%D0%B0%D0%BB%D1%83%D0%B6%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=35.445185&CenterLat=54.371800&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=16625093&region_name=%D0%9A%D0%B0%D0%BB%D0%B8%D0%BD%D0%B8%D0%BD%D0%B3%D1%80%D0%B0%D0%B4%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=20.50000&CenterLat=54.71667&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=134&region_name=%D0%9A%D0%B5%D0%BC%D0%B5%D1%80%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=87.207361&CenterLat=54.779047&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=8860028&region_name=%D0%9A%D0%B8%D1%80%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=50.097125&CenterLat=58.548222&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=2929242&region_name=%D0%9A%D0%BE%D1%81%D1%82%D1%80%D0%BE%D0%BC%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=43.788495&CenterLat=58.456003&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=1236&region_name=%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D0%B4%D0%B0%D1%80%D1%81%D0%BA%D0%B8%D0%B9+%D0%BA%D1%80%D0%B0%D0%B9&CenterLon=39.130793&CenterLat=44.336757&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=135&region_name=%D0%9A%D1%80%D0%B0%D1%81%D0%BD%D0%BE%D1%8F%D1%80%D1%81%D0%BA%D0%B8%D0%B9+%D0%BA%D1%80%D0%B0%D0%B9&CenterLon=92.97174&CenterLat=55.99584&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=584142&region_name=%D0%9A%D1%83%D1%80%D0%B3%D0%B0%D0%BD%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=64.809405&CenterLat=55.448347&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=235&region_name=%D0%9D%D0%B8%D0%B6%D0%B5%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=44.611891&CenterLat=56.312764&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=236&region_name=%D0%9D%D0%BE%D0%B2%D0%B3%D0%BE%D1%80%D0%BE%D0%B4%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=32.490222&CenterLat=58.307715&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=137&region_name=%D0%9D%D0%BE%D0%B2%D0%BE%D1%81%D0%B8%D0%B1%D0%B8%D1%80%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=79.770236&CenterLat=55.276272&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=138&region_name=%D0%9E%D0%BC%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=73.344416&CenterLat=56.103472&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=907963&region_name=%D0%9F%D0%B5%D1%80%D0%BC%D1%81%D0%BA%D0%B8%D0%B9+%D0%BA%D1%80%D0%B0%D0%B9&CenterLon=56.225679&CenterLat=59.117698&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=7840276&region_name=%D0%9F%D0%B5%D0%BD%D0%B7%D0%B5%D0%BD%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=44.634151&CenterLat=53.1824&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=237&region_name=%D0%9F%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=29.236911&CenterLat=57.236486&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=11748599&region_name=%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0+%D0%90%D0%BB%D1%82%D0%B0%D0%B9&CenterLon=85.88333&CenterLat=52.00000&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=34014&region_name=%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0+%D0%9A%D0%B0%D1%80%D0%B5%D0%BB%D0%B8%D1%8F&CenterLon=30.64124&CenterLat=62.1673&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=6752639&region_name=%D0%A0%D0%B5%D1%81%D0%BF%D1%83%D0%B1%D0%BB%D0%B8%D0%BA%D0%B0+%D0%A5%D0%B0%D0%BA%D0%B0%D1%81%D0%B8%D1%8F&CenterLon=89.897078&CenterLat=53.386357&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=34013&region_name=%D0%A0%D1%8F%D0%B7%D0%B0%D0%BD%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=40.625240&CenterLat=54.333363&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=4389479&region_name=%D0%A1%D0%B0%D0%BC%D0%B0%D1%80%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=50.463301&CenterLat=53.27635&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=132&region_name=%D0%A1%D0%B2%D0%B5%D1%80%D0%B4%D0%BB%D0%BE%D0%B2%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=61.530761&CenterLat=58.586755&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=140&region_name=%D0%A1%D0%BC%D0%BE%D0%BB%D0%B5%D0%BD%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=32.998543&CenterLat=54.956192&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=141&region_name=%D0%A2%D0%B2%D0%B5%D1%80%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=34.706195&CenterLat=57.093033&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=9588076&region_name=%D0%A2%D1%83%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=37.575693&CenterLat=53.888064&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=142&region_name=%D0%A2%D0%BE%D0%BC%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=85.06325&CenterLat=56.51752&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=143&region_name=%D0%A2%D1%8E%D0%BC%D0%B5%D0%BD%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=67.39452&CenterLat=56.80244&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=584143&region_name=%D0%A5%D0%9C%D0%90%D0%9E+-+%D0%AE%D0%B3%D1%80%D0%B0&CenterLon=65.897508&CenterLat=61.588912&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=144&region_name=%D0%A7%D0%B5%D0%BB%D1%8F%D0%B1%D0%B8%D0%BD%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=60.395641&CenterLat=54.446199&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=588981&region_name=%D0%AF%D0%BC%D0%B0%D0%BB%D0%BE-%D0%9D%D0%B5%D0%BD%D0%B5%D1%86%D0%BA%D0%B8%D0%B9+%D0%90%D0%9E&CenterLon=80.005397&CenterLat=66.086435&city=", listGps, names);
//        System.out.println(1);
//        load("https://www.gpnbonus.ru/our_azs/?region_id=145&region_name=%D0%AF%D1%80%D0%BE%D1%81%D0%BB%D0%B0%D0%B2%D1%81%D0%BA%D0%B0%D1%8F+%D0%BE%D0%B1%D0%BB%D0%B0%D1%81%D1%82%D1%8C&CenterLon=39.105138&CenterLat=57.817361&city=", listGps, names);
//        System.out.println(1);
//
//        for (int i = 0; i < listGps.size(); i++) {
//            String[] strings = listGps.get(i).split(" ");
//            gazProm.add(new GazProm(names.get(i).replace(",", " "), strings[1], strings[2]));
//        }
//
//        File fileDir = new File("D:\\gaz_prom.csv");
//        Writer out = new BufferedWriter(new OutputStreamWriter(
//                new FileOutputStream(fileDir), "cp1251"));
//
//        for (GazProm d : gazProm) {
//
//            String[] strings = d.getAddress().split(" ");
//            out.write("Газпромнефть");
//            out.append(";");
//            out.append("Заправка");
//            out.append(";");
//            out.append(strings[0]);
//            out.append(";");
//            StringBuilder stringBuilder = new StringBuilder();
//            for (int i = 2; i < strings.length; i++) {
//                if (i == strings.length) {
//                    stringBuilder.append(strings[i]);
//                }else {
//                    stringBuilder.append(strings[i]).append(" ");
//                }
//            }
//            String str = String.valueOf(stringBuilder);
//            String item = str.trim();
//            String string = item.substring(0, item.length());
//            out.append(string);
//            out.append(";");
//            out.append(d.getLatitud());
//            out.append(";");
//            out.append(d.getLongitud());
//            out.append(";");
//            out.append('\n');
//
//        }
//        out.flush();
//        out.close();
//
//    }
//
//    private static void load(String url, List<String> listGps, List<String> names) throws IOException {
//        Document doc = Jsoup.connect(url).get();
//
//        Elements links = doc.select("nobr");
//        Elements nameDiv = doc.getElementsByClass("DinProMedium fs18 clr6");
//
//        for (Element item : nameDiv) {
//            String nb = item.text();
//            names.add(nb);
//        }
//
//        for (int i = 0; i < links.size(); i += 2) {
//            String nb = links.get(i).text();
//            String[] strings = nb.split(":");
//            String gps = strings[1];
//            listGps.add(gps);
//        }
    }

}
