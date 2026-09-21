package com.mystepup.homework;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class BasicUtilsTest {

    private final Random random = new Random();

    // Вспомогательный метод для вывода разделителей
    private void printSeparators() {
        System.out.println("========================");
    }

    // --- Тест 1: isEven (1 раз, случайное число 1-100) ---
    @Test
    void testIsEven() {
        printSeparators();
        System.out.println("Test method start");

        int number = random.nextInt(100) + 1; // от 1 до 100
        boolean result = BasicUtils.isEven(number);

        // Простая проверка логики (опционально, чтобы тест не был пустым)
        assertEquals(number % 2 == 0, result);

        System.out.println("Test method end");
        printSeparators();
    }

    // --- Тест 2: checkAccess (20 раз, случайные числа 0-99) ---
    @Test
    void testCheckAccessLoop() {
        printSeparators();
        System.out.println("Test method start");

        for (int i = 0; i < 20; i++) {
            int age = random.nextInt(100); // от 0 до 99
            String result = BasicUtils.checkAccess(age);

            // Логическая проверка
            if (age > 18) {
                assertEquals("Allow", result);
            } else {
                assertEquals("Denied", result);
            }
        }

        System.out.println("Test method end");
        printSeparators();
    }

    // --- Тест 3: getGrade (Параметризованный тест) ---

    // Этот метод генерирует список тестовых данных (массив случайных чисел)
    // JUnit будет вызывать этот метод, чтобы получить данные для каждого прогона теста
    private static List<Integer> generateRandomScores() {
        List<Integer> scores = new ArrayList<>();
        Random r = new Random();
        // Генерируем, например, 10 случайных чисел для теста
        for (int i = 0; i < 10; i++) {
            scores.add(r.nextInt(101)); // от 0 до 100
        }
        return scores;
    }

    @ParameterizedTest
    @MethodSource("generateRandomScores")
    void testGetGrade(int score) {
        // Для параметризованных тестов разделители выводятся для КАЖДОГО числа отдельно
        printSeparators();
        System.out.println("Test method start");

        String grade = BasicUtils.getGrade(score);

        // Здесь можно добавить проверки на соответствие диапазонам, если нужно
        // Например: если score > 100, то grade должен быть "Error!"

        System.out.println("Test method end");
        printSeparators();
    }
}