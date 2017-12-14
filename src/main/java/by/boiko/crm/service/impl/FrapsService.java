package by.boiko.crm.service.impl;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FrapsService {

    public static void main(String[] args) throws IOException {
        String excelFilePath = "D:\\text.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet firstSheet = workbook.getSheetAt(0);

        List<String> stringList = new ArrayList<>();


        for (Row nextRow : firstSheet) {
            Iterator<Cell> cellIterator = nextRow.cellIterator();

            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                String str = cell.getStringCellValue();

                String[] items =  str.split(",");
                stringList.add(items[1].trim());
//                stringList.add(items[1].substring(4, items[1].length()-1));
            }
        }
        List<Double> integerList = new ArrayList<>();
        for (int i = 1; i <=stringList.size() - 1; i++){
            integerList.add((Double.parseDouble(stringList.get(i+1)) - Double.parseDouble(stringList.get(i))));
        }
        System.out.println(integerList);

        workbook.close();
        inputStream.close();
    }

}
