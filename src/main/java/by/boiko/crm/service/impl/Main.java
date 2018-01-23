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
        String[] sizeMatrix = sc.nextLine().split(" ");
        String[] elementPosition = sc.nextLine().split(" ");
        int p = sc.nextInt();
        int countIntMatrix = Integer.parseInt(sizeMatrix[0]);
        int sizeIntMatrix = Integer.parseInt(sizeMatrix[1]);
        List<List<Integer>> listRow = new ArrayList<>();
        String[] strings = null;
        for (int i = 0; i < countIntMatrix; i++) {
            sc.nextLine();
            List<Integer> list = new ArrayList<>();
            for (int j = 0; j < sizeIntMatrix * 2; j++) {
                list.add(sc.nextInt());
            }
            listRow.add(list);
        }

        List<List<Integer>> columsMatrix = new ArrayList<>();
        for (List<Integer> item : listRow) {
            List<Integer> listColumns = new ArrayList<>();
            for (int i = 0; i < item.size(); i++) {
                if (i % 2 == 0 ){
                    listColumns.add(item.get(i));
                }
            }for (int i = 1; i < item.size(); i++) {
                if (i % 2 != 0 ){
                    listColumns.add(item.get(i));
                }
            }
            columsMatrix.add(listColumns);
        }




        pw = new PrintWriter(new File("output.txt"));
        pw.close();
    }
}