package com.mystepup.homework;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

class BasicUtilsTest {

    private final Random random = new Random();

    // Критерий 1.3: строки до и после каждого теста
    @BeforeEach
    void beforeEach() {
        System.out.println("========================Test method start");
    }

    @AfterEach
    void afterEach() {
        System.out.println("Test method end\n" +
                "========================");
    }

    // ==================== @Test (4 метода) ====================

    @Test
    void isEvenRandom() {
        int n = random.nextInt(100) + 1; // 1..100
        boolean expected = n % 2 == 0;
        boolean actual = BasicUtils.isEven(n);
        System.out.printf("isEven(%d): ожидалось %s, получено %s%n", n, expected, actual);
        System.out.println(expected == actual ? "TEST PASSED" : "TEST FAILED");
    }

    @Test
    void isPositiveRandom() {
        int n = random.nextInt(-50, 51); // -50..50
        boolean expected = n >= 0;
        boolean actual = BasicUtils.isPositive(n);
        System.out.printf("isPositive(%d): ожидалось %s, получено %s%n", n, expected, actual);
        System.out.println(expected == actual ? "TEST PASSED" : "TEST FAILED");
    }

    @Test
    void sumToNRandom() {
        int n = random.nextInt(1, 51); // 1..50
        int expected = n * (n + 1) / 2; // эталон по формуле арифметической прогрессии
        int actual = BasicUtils.sumToN(n);
        System.out.printf("sumToN(%d): ожидалось %d, получено %d%n", n, expected, actual);
        System.out.println(expected == actual ? "TEST PASSED" : "TEST FAILED");
    }

    @Test
    void findMaxRandom() {
        int[] arr = random.ints(10, -100, 101).toArray(); // 10 чисел -100..100
        int expected = Arrays.stream(arr).max().orElseThrow();
        int actual = BasicUtils.findMax(arr);
        System.out.printf("findMax(%s): ожидалось %d, получено %d%n", Arrays.toString(arr), expected, actual);
        System.out.println(expected == actual ? "TEST PASSED" : "TEST FAILED");
    }

    // ==================== @RepeatedTest (4 метода) ====================

    @RepeatedTest(20)
    void checkAccessRepeated() {
        int age = random.nextInt(100); // 0..99
        String expected = age > 18 ? "Allowed" : "Denied";
        String actual = BasicUtils.checkAccess(age);
        System.out.printf("checkAccess(%d): ожидалось %s, получено %s%n", age, expected, actual);
        System.out.println(expected.equals(actual) ? "TEST PASSED" : "TEST FAILED");
    }

    @RepeatedTest(10)
    void blastOffRepeated() {
        int start = random.nextInt(2, 11); // 2..10
        String actual = BasicUtils.blastOff(start);
        boolean correct = actual.contains("1") && actual.contains("Поехали!");
        System.out.printf("blastOff(%d): результат «%s», корректно: %s%n", start, actual, correct);
        System.out.println(correct ? "TEST PASSED" : "TEST FAILED");
    }

    @RepeatedTest(10)
    void hasBugRepeated() {
        String[] messages = new String[5];
        boolean shouldContainBug = random.nextBoolean();
        for (int i = 0; i < messages.length; i++) {
            messages[i] = random.nextBoolean() ? "OK" : "Info";
        }
        if (shouldContainBug) {
            messages[random.nextInt(5)] = random.nextBoolean() ? "Bug" : "bug"; // регистр не важен
        }
        boolean actual = BasicUtils.hasBug(messages);
        System.out.printf("hasBug(%s): ожидалось %s, получено %s%n", Arrays.toString(messages), shouldContainBug, actual);
        System.out.println(shouldContainBug == actual ? "TEST PASSED" : "TEST FAILED");
    }

    @RepeatedTest(10)
    void reverseRepeated() {
        String[] arr = random.ints(6, 0, 100).mapToObj(String::valueOf).toArray(String[]::new);
        String[] actual = BasicUtils.reverse(arr);
        boolean correct = actual != null
                && actual.length == arr.length
                && actual[0].equals(arr[arr.length - 1])
                && actual[actual.length - 1].equals(arr[0]);
        System.out.printf("reverse(%s): результат %s, корректно: %s%n", Arrays.toString(arr), Arrays.toString(actual), correct);
        System.out.println(correct ? "TEST PASSED" : "TEST FAILED");
    }

    // ==================== @ParameterizedTest (4 метода) ====================
    // Данные из CSV-файлов: grades.csv, ranges.csv, averages.csv (src/test/resources)

    @ParameterizedTest
    @CsvFileSource(resources = "/grades.csv", numLinesToSkip = 1)
    void getGradeParameterized(int score, String expected) {
        String actual = BasicUtils.getGrade(score);
        System.out.printf("getGrade(%d): ожидалось %s, получено %s%n", score, expected, actual);
        System.out.println(expected.equals(actual) ? "TEST PASSED" : "TEST FAILED");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ranges.csv", numLinesToSkip = 1)
    void getEvenInRangeParameterized(int start, int end) {
        String actual = BasicUtils.getEvenInRange(start, end);
        StringBuilder expectedBuilder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                expectedBuilder.append(i).append(" ");
            }
        }
        String expected = expectedBuilder.toString().trim();
        boolean correct = actual.trim().equals(expected);
        System.out.printf("getEvenInRange(%d, %d): ожидалось «%s», получено «%s»%n", start, end, expected, actual);
        System.out.println(correct ? "TEST PASSED" : "TEST FAILED");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/averages.csv", numLinesToSkip = 1, delimiter = ';')
    void calcAverageParameterized(String numbers) {
        List<Integer> list = new ArrayList<>();
        for (String s : numbers.split(",")) {
            list.add(Integer.parseInt(s.trim()));
        }
        long sum = 0;
        for (int v : list) {
            sum += v;
        }
        double expected = (double) sum / list.size();
        double actual = BasicUtils.calcAverage(list);
        boolean correct = Math.abs(expected - actual) < 0.001; // сравнение double с допуском
        System.out.printf("calcAverage(%s): ожидалось %.2f, получено %.2f%n", list, expected, actual);
        System.out.println(correct ? "TEST PASSED" : "TEST FAILED");
    }

    // Данные из @CsvSource: имена разделены точкой с запятой, чтобы не конфликтовать с разделителем CSV
    @ParameterizedTest
    @CsvSource({
            "Alice;Bob;Carol;Alice;Dave, Alice",
            "Bob;Carol;Dave, Alice",
            "Alice;Alice;Alice, Alice",
            "Bob;Bob, Bob",
            "Alice;Carol, Dave"
    })
    void removeSpecificNameParameterized(String namesRaw, String nameToRemove) {
        List<String> list = new ArrayList<>(Arrays.asList(namesRaw.split(";")));
        long expectedRemoved = list.stream().filter(s -> s.equals(nameToRemove)).count();
        List<String> actual = BasicUtils.removeSpecificName(list, nameToRemove);
        boolean correct = actual != null
                && actual.stream().noneMatch(s -> s.equals(nameToRemove))
                && actual.size() == list.size() - expectedRemoved;
        System.out.printf("removeSpecificName(%s, «%s»): осталось %s%n", list, nameToRemove, actual);
        System.out.println(correct ? "TEST PASSED" : "TEST FAILED");
    }
}
