package by.boiko.crm.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] argv) throws IOException {
        new Main().run();
    }

    PrintWriter pw;
    Scanner sc;

    public void run() throws IOException {
        sc = new Scanner(new File("input.txt"));
        int numberPosition = Integer.parseInt(sc.nextLine());
        int passArray[] = new int[100000];
        for (int i = 1; i < passArray.length; i++) {
            passArray[i] = i;
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i < passArray.length; i++) {
            if (i <= 9) {
                list.add(passArray[i]);
            }
            if (i > 9 && i < 100) {
                int j = passArray[i] / 10;
                int m = passArray[i] % 10;
                if (j != m) {
                    list.add(passArray[i]);
                }
            }
            if (i > 99 && i < 1000) {
                int j = passArray[i] / 100;
                int m = passArray[i] % 100;
                int k = m / 10;
                m = m % 10;
                if (j != m && j != k && m != k) {
                    list.add(passArray[i]);
                }
            }
            if (i > 999 && i < 10000) {
                int j = passArray[i] / 1000;
                int m = passArray[i] % 1000;
                int k = m / 100;
                m = m % 100;
                int b = m / 10;
                m = m % 10;
                if (j != m && j != k && j != b && m != k && m != b && k != b) {
                    list.add(passArray[i]);
                }
            }
            if (i > 9999 && i < 100000) {
                int j = passArray[i] / 10000;
                int m = passArray[i] % 10000;
                int k = m / 1000;
                m = m % 1000;
                int b = m / 100;
                m = m % 100;
                int h = m / 10;
                m = m % 10;
                if (j!=m && j!=k && j!=b && m!=k && m!=b && k!=b && h!=m && h!=k && h!=j && h!=b) {
                    list.add(passArray[i]);
                }
            }

        }
        pw = new PrintWriter(new File("output.txt"));
        pw.print(list.get(numberPosition - 1));
        pw.close();
    }
}

//        PrintWriter pw;
//    Scanner sc;
//    public void run() throws IOException{
//        sc = new Scanner(new File("input.txt"));
//        char[] string = sc.nextLine().toCharArray();
//        char[] str = sc.nextLine().toCharArray();
//        int j = 0;
//        pw = new PrintWriter(new File("output.txt"));
//        if (string.length == 0 || str.length == 0){
//            pw.print("NO");
//        }
//        if (string.length == 0 && str.length == 0){
//            pw.print("NO");
//        }
//        if (str.length > string.length){
//            for (char aStr : str) {
//                if (string[j] == aStr) {
//                    j++;
//                    if (j > string.length-1){
//                        break;
//                    }
//                }
//            }
//        }else {
//            for (char aStr : string) {
//                if (str[j] == aStr) {
//                    j++;
//                    if (j > str.length-1){
//                        break;
//                    }
//                }
//            }
//        }
//        if (j == string.length){
//            pw.print("YES");
//        } else {
//            pw.print("NO");
//        }
//        pw.close();

//
//    PrintWriter pw;
//    Scanner sc;
//    List<Integer> listPlusPosition = new ArrayList<>();
//    List<Integer> listMinusPosition = new ArrayList<>();
//    List<Integer> countPlus = new ArrayList<>();
//    List<Integer> countMinus = new ArrayList<>();
//    int resultPlus = 0;
//    int resultMinus = 0;
//
//    public void run() throws IOException {
//        sc = new Scanner(new File("input.txt"));
//        char[] string = sc.nextLine().toCharArray();
//        String[] numbers = sc.nextLine().split(" ");
//        for (int i = 0; i < numbers.length; i++) {
//            int a = Integer.parseInt(numbers[i]);
//            if (a > 0) {
//                countPlus.add(a);
//                listPlusPosition.add(i + 1);
//            } else {
//                countMinus.add(a);
//                listMinusPosition.add(i + 1);
//            }
//        }
//
//        for (Integer item : countPlus) {
//            resultPlus = resultPlus + item;
//        }
//        for (Integer item : countMinus) {
//            resultMinus = resultMinus + Math.abs(item);
//        }
//        pw = new PrintWriter(new File("output.txt"));
//        if (resultPlus > resultMinus){
//            pw.print(countPlus.size());
//            pw.print("\n");
//            for (Integer items : listPlusPosition) {
//                String str = String.valueOf(items);
//                pw.append(str).append(" ");
//            }
//        } else {
//            pw.print(countMinus.size());
//            pw.print("\n");
//            for (Integer items : listMinusPosition) {
//                String str = String.valueOf(items);
//                pw.append(str).append(" ");
//            }
//        }
//        pw.close();