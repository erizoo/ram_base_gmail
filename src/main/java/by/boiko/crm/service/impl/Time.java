package by.boiko.crm.service.impl;

import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Class for calculating the maximum number of people.
 */
public class Time {

    private static List<Long> durationList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        String fileName = "index.txt";
        Stream<String> stream = Files.lines(Paths.get(fileName));
        List<String> result = stream.collect(Collectors.toList());
        List<Integer> amount = new ArrayList<>();

        for (String items : result) {
            LocalTime firstTime = getFirstTime(items);
            LocalTime secondTime = getSecondTime(items);
            int count = 0;
            for (String item : result) {
                LocalTime firstTimeCheck = getFirstTime(item);
                LocalTime secondTimeCheck = getSecondTime(item);
                Duration duration = Duration.between(firstTime, secondTime);
                for (int i = 0; i <= duration.toMinutes(); i++) {
                    LocalTime time = firstTime.plusMinutes(i);
                    if (isBetween(time, firstTimeCheck, secondTimeCheck)) {
                        count++;
                        break;
                    }
                }
            }
            amount.add(count);
            count = 0;
        }
        System.out.println("count: " + Collections.max(amount));
    }


    private static LocalTime getFirstTime(String items) {
        String[] str = items.split(" ");
        String[] firstTime = str[0].split(":");
        int firstHour = Integer.parseInt(firstTime[0]);
        int firstMinute = Integer.parseInt(firstTime[1]);
        return LocalTime.of(firstHour, firstMinute);
    }


    private static LocalTime getSecondTime(String items) {
        String[] str = items.split(" ");
        String[] secondTime = str[1].split(":");
        int secondHour = Integer.parseInt(secondTime[0]);
        int secondMinute = Integer.parseInt(secondTime[1]);
        return LocalTime.of(secondHour, secondMinute);
    }

    /**
     * Method for finding the maximum durationю
     *
     * @param result
     * @return item number in the ArrayList.
     */
    private static int numberElementForMaxDuration(List<String> result) {
        for (String items : result) {
            String[] str = items.split(" ");
            String[] srtFirst = str[0].split(":");
            int firstHour = Integer.parseInt(srtFirst[0]);
            int firstMinute = Integer.parseInt(srtFirst[1]);
            String[] srtSecond = str[1].split(":");
            int secondHour = Integer.parseInt(srtSecond[0]);
            int secondMinute = Integer.parseInt(srtSecond[1]);

            LocalDateTime from = LocalDateTime.of(2014, Month.JULY, 9, firstHour, firstMinute);
            LocalDateTime to = LocalDateTime.of(2014, Month.JULY, 9, secondHour, secondMinute);

            Duration duration = Duration.between(from, to);
            System.out.println("duration: " + duration.toMinutes());
            durationList.add(duration.toMinutes());
        }
        return durationList.indexOf(Collections.max(durationList));
    }

    private static boolean isBetween(LocalTime candidate, LocalTime start, LocalTime end) {
        if (candidate.equals(end)){
            return false;
        } else {
            return !candidate.isBefore(start) && !candidate.isAfter(end);
        }
    }

    @Test
    public void testMultiply() {
        List<String> result = new ArrayList<>();
        result.add("10:20 18:57");
        result.add("10:20 18:58");
        result.add("10:20 18:56");
        // Проверяемый метод
        assertEquals(1, numberElementForMaxDuration(result));
    }

//    @Test
//    public void testGetFirstTime() {
//        List<String> result = new ArrayList<>();
//        result.add("10:20 18:57");
//        result.add("10:20 18:58");
//        result.add("10:20 18:56");
//        // Проверяемый метод
//        assertEquals(LocalTime.of(10, 20), getFirstTime(result, 1));
//    }
//
//    @Test
//    public void testGetSecondTime() {
//        List<String> result = new ArrayList<>();
//        result.add("10:20 18:57");
//        result.add("10:20 18:58");
//        result.add("10:20 18:56");
//        // Проверяемый метод
//        assertEquals(LocalTime.of(18, 58), getSecondTime(result, 1));
//    }

    @Test
    public void testBetweenDate() {
        LocalTime candidate = LocalTime.of(15, 30);
        LocalTime start = LocalTime.of(14, 0);
        LocalTime end = LocalTime.of(16, 20);
        // Проверяемый метод
        assertTrue(isBetween(candidate, start, end));
    }

}
