package com.mystepup.homework;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@Tag("unit")          // Этот тест — юнит-тест (изолированный)
@Tag("basic-utils")   // Категория: проверяем BasicUtils
@Tag("regression") // Входит в регресс
class BasicUtilsTest {

    private final Random random = new Random();

    // Критерий 1.3: строки до и после каждого теста
    @BeforeEach
    void beforeEach(TestInfo testInfo) {
        System.out.printf("========================Test method %s start%n", testInfo.getDisplayName());
    }

    @AfterEach
    void afterEach(TestInfo testInfo) {
        System.out.printf("Test method %s end ========================%n", testInfo.getDisplayName() );
    }

    // ==================== @Test (4 метода) ====================
    @Test
    @DisplayName("Проверка чётности случайного числа (1–100)")
    @Tag("logic")      // Тестирует логику метода
    @Tag("random")     // Использует рандомные данные
    void isEvenRandomTest(TestInfo testInfo) {
        int number = random.nextInt(100) + 1; // 1..100
        boolean expectedResult = number % 2 == 0;
        boolean actualResult = BasicUtils.isEven(number);

        assertThat(actualResult)
                .as("%s isEven(%d): ожидалось %s, а получили %s",
                        testInfo.getDisplayName(), number, expectedResult, actualResult)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Проверка положительности случайного числа (-50–50)")
    @Tag("boundary")    // Граничные значения (отрицательные, ноль)
    @Tag("random")
    void isPositiveRandomTest(TestInfo testInfo) {
        int number = random.nextInt(-50, 51); // -50..50
        boolean expectedResult = number >= 0;
        boolean actualResult = BasicUtils.isPositive(number);

        assertThat(actualResult)
                .as("%s isPositive(%d): ожидалось %s, а получили %s",
                        testInfo.getDisplayName(), number, expectedResult, actualResult)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Проверка суммы чисел от 1 до N (формула прогрессии)")
    @Tag("math")        // Математическая логика
    @Tag("algorithm")   // Проверка алгоритма
    void sumToNRandomTest(TestInfo testInfo) {
        int number = random.nextInt(1, 51); // 1..50
        int expectedResult = number * (number + 1) / 2; // эталон по формуле арифметической прогрессии
        int actualResult = BasicUtils.sumToN(number);

        assertThat(actualResult)
                .as("%s sumToN(%d): ожидалось %d, получено %d",
                        testInfo.getDisplayName(), number, expectedResult, actualResult)
                .isEqualTo(expectedResult);
    }

    @Test
    @DisplayName("Поиск максимума в массиве случайных чисел")
    @Tag("array")       // Работа с массивами
    @Tag("edge-case")   // Может затронуть граничные случаи (все отрицательные и т.п.)
    void findMaxRandomTest(TestInfo testInfo) {
        int[] arr = random.ints(10, -100, 101).toArray(); // 10 чисел -100..100
        int expectedResult = Arrays.stream(arr).max().orElseThrow();
        int actualResult = BasicUtils.findMax(arr);

        assertThat(actualResult)
                .as("%s findMax(%s): ожидалось %d, получено %d",
                        testInfo.getDisplayName(), Arrays.toString(arr), expectedResult, actualResult)
                .isEqualTo(expectedResult);
    }

    // ==================== @RepeatedTest (4 метода) ====================

    @RepeatedTest(20)
    @DisplayName("Проверка доступа (checkAccess) для случайного возраста")
    @Tag("auth")        // Логика доступа/авторизации
    @Tag("repeated")    // Многократный прогон
    void checkAccessRepeatedTest(TestInfo testInfo) {
        int age = random.nextInt(100); // 0..99
        String expectedResult = age > 18 ? "Allowed" : "Denied";
        String actualResult = BasicUtils.checkAccess(age);

        assertThat(actualResult)
                .as("%s checkAccess(%d): ожидалось %s, получено %s",
                        testInfo.getDisplayName(), age, expectedResult, actualResult)
                .isEqualTo(expectedResult);
    }

    @RepeatedTest(10)
    @DisplayName("Проверка отсчёта и фразы «Поехали!» (blastOff)")
    @Tag("string-format") // Работа со строками/форматированием
    @Tag("repeated")
    void blastOffRepeatedTest(TestInfo testInfo) {
        int start = random.nextInt(2, 11); // 2..10
        String actualResult = BasicUtils.blastOff(start);

        // Логика проверки: должны быть "1" и фраза "Поехали!"
        assertThat(actualResult)
                .as("%s blastOff(%d)",
                        testInfo.getDisplayName(), start)
                .contains("1")
                .contains("Поехали!");                  // Проверка 2
    }

    @RepeatedTest(10)
    @DisplayName("Проверка наличия бага в массиве сообщений (hasBug)")
    @Tag("search")      // Поиск элемента в коллекции
    @Tag("repeated")
    void hasBugRepeatedTest(TestInfo testInfo) {
        // Объявляем пустой массив messages с размером 5
        String[] messages = new String[5];
        // Определяем должен ли быть баг
        boolean shouldContainBug = random.nextBoolean();

        // Рандомно наполняем массив словами OK и Info
        for (int i = 0; i < messages.length; i++) {
            messages[i] = random.nextBoolean() ? "OK" : "Info";
        }
        // если должен быть баг - добавляем
        if (shouldContainBug) {
            messages[random.nextInt(5)] = random.nextBoolean() ? "Bug" : "bug";
        }

        boolean actualResult = BasicUtils.hasBug(messages);

        assertThat(actualResult)
                .as("%s hasBug(%s): ожидалось %s",
                        testInfo.getDisplayName(), Arrays.toString(messages), shouldContainBug)
                .isEqualTo(shouldContainBug);
    }

    @RepeatedTest(10)
    @DisplayName("Проверка разворота массива строк (reverse)")
    @Tag("array-manipulation") // Манипуляции с массивами
    @Tag("repeated")
    void reverseRepeatedTest(TestInfo testInfo) {
        // Генерируем массив из 6 случайных чисел (0–99) и сразу превращаем в String[]
        String[] arr = random.ints(6, 0, 100)
                .mapToObj(String::valueOf)
                .toArray(String[]::new);

        String[] actualResult = BasicUtils.reverse(arr);

        // Проверяем, что результат не null и длина совпадает
        assertThat(actualResult)
                .as("%s reverse(%s)",
                        testInfo.getDisplayName(), Arrays.toString(arr))
                .isNotNull();

        assertThat(actualResult.length)
                .as("%s reverse length check",
                        testInfo.getDisplayName())
                .isEqualTo(arr.length);

        // Проверяем, что массив развёрнут полностью
        // Создаем эталонный список: берём оригинал, превращаем в список и разворачиваем его
        List<String> expectedReversed = new ArrayList<>(Arrays.asList(arr));
        Collections.reverse(expectedReversed);

        assertThat(actualResult)
                .as("%s reverse content check",
                        testInfo.getDisplayName())
                .containsExactlyElementsOf(expectedReversed);
    }

    // ==================== @ParameterizedTest (4 метода) ====================
    // Данные из CSV-файлов: grades.csv, ranges.csv, averages.csv (src/test/resources)

    @ParameterizedTest
    @CsvFileSource(resources = "/grades.csv", numLinesToSkip = 1)
    @DisplayName("Проверка оценки по баллам (getGrade) из CSV")
    @Tag("csv-data")    // Использует внешние данные (CSV)
    @Tag("validation")  // Валидация входных данных
    void getGradeParameterizedTest(int score, String expectedResult, TestInfo testInfo) {
        String actualResult = BasicUtils.getGrade(score);

        assertThat(actualResult)
                .as("%s getGrade(%d): ожидалось '%s', а получено '%s'",
                        testInfo.getDisplayName(), score, expectedResult, actualResult)
                .isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/ranges.csv", numLinesToSkip = 1)
    @DisplayName("Проверка получения чётных чисел в диапазоне (getEvenInRange) из CSV")
    @Tag("csv-data")
    @Tag("range-logic") // Логика работы с диапазонами
    void getEvenInRangeParameterizedTest(int start, int end, TestInfo testInfo) {
        String actualResult = BasicUtils.getEvenInRange(start, end);


        StringBuilder expectedBuilder = new StringBuilder();
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                expectedBuilder.append(i).append(" ");
            }
        }
        String expectedResult = expectedBuilder.toString().trim();

        assertThat(actualResult.trim())
                .as("%s getEvenInRange(%d, %d): ожидалось '%s'",
                        testInfo.getDisplayName(), start, end, expectedResult)
                .isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/averages.csv", numLinesToSkip = 1, delimiter = ';')
    @DisplayName("Проверка расчёта среднего значения (calcAverage) из CSV")
    @Tag("csv-data")
    @Tag("statistic")   // Статистические вычисления
    void calcAverageParameterizedTest(String numbers, TestInfo testInfo) {
        List<Integer> list = new ArrayList<>();

        for (String s : numbers.split(",")) {
            list.add(Integer.parseInt(s.trim()));
        }

        if (list.isEmpty()) {
            return; // Защита от деления на ноль
        }

        long sum = 0;
        for (int number : list) {
            sum += number;
        }

        double expectedResult = (double) sum / list.size();
        double actualResult = BasicUtils.calcAverage(list);
        double delta = 0.001; // допуск при получении среднего значения


        assertThat(actualResult)
                .as("%s calcAverage(%s): ожидалось %.2f",
                        testInfo.getDisplayName(), list, expectedResult)
                .isCloseTo(expectedResult, Offset.offset(delta));
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
    @DisplayName("Проверка удаления конкретного имени из списка (removeSpecificName)")
    @Tag("collection")  // Работа с коллекциями (List)
    @Tag("mutation")    // Метод изменяет/возвращает новую коллекцию
    void removeSpecificNameParameterizedTest(String namesRaw, String nameToRemove, TestInfo testInfo) {
        List<String> list = new ArrayList<>(Arrays.asList(namesRaw.split(";")));
        long expectedCount = list.stream().filter(s -> s.equals(nameToRemove)).count();
        int expectedSize = list.size() - (int) expectedCount;

        List<String> actualResult = BasicUtils.removeSpecificName(list, nameToRemove);

        // AssertJ проверки
        assertThat(actualResult)
                .as("%s removeSpecificName(%s, '%s')",
                        testInfo.getDisplayName(), list, nameToRemove)
                .isNotNull();

        assertThat(actualResult)
                .as("%s проверка отсутствия элемента",
                        testInfo.getDisplayName())
                .doesNotContain(nameToRemove);

        assertThat(actualResult)
                .as("%s проверка размера коллекции",
                        testInfo.getDisplayName())
                .hasSize(expectedSize);
    }

    @Test
    @DisplayName("Проверка наличия слова в предложении (hasWordInSentence) — позитивный кейс")
    @Tag("regex-logic") // Метод скорее всего использует regex или split
    @Tag("positive-case")
    void hasWordInSentencePositiveTest(TestInfo testInfo) {
        String sentence = "Today is a good day!";
        String word = "good";
        boolean actualResult = BasicUtils.hasWordInSentence(sentence, word);

        assertThat(actualResult)
                .as("%s Предложение должно содержать слово '%s'",
                        testInfo.getDisplayName(), word)
                .isTrue();
    }

    // АТ провален из-за реализации метода
    @Test
    @DisplayName("Проверка поиска последнего самого длинного слова (getLastLongestWord)")
    @Tag("string-analysis") // Анализ строк
    @Tag("edge-case")
    void getLastLongestWordTest(TestInfo testInfo) {
        String[] words = {"One", "Two", "Three-1", "Four", "Three-2"};
        String actualResult = BasicUtils.getLastLongestWord(words);
        String expectedResult = "Three-2";

        assertThat(actualResult)
                .as("%s Ожидалось '%s'",
                        testInfo.getDisplayName(), expectedResult)
                .isEqualTo(expectedResult);
    }

    // еще один проваленный АТ, но уже из-за реализации теста
    @Test
    @DisplayName("Проверка получения уникальных чисел (getUniqueNumbers)")
    @Tag("set-logic")   // Работа с Set
    @Tag("deduplication") // Удаление дубликатов
    void getUniqueNumbersTest(TestInfo testInfo) {
        List<Integer> numbers = List.of(1, 2, 2, 3, 3, 3, 4, -4, -6);

        Set<Integer> actualResult = BasicUtils.getUniqueNumbers(numbers);

        Set<Integer> expectedResult = Set.of(1, 2, 3, 4);

        assertThat(actualResult)
                .as("%s Уникальные числа должны быть %s",
                        testInfo.getDisplayName(), expectedResult)
                .containsExactlyInAnyOrderElementsOf(expectedResult);
    }

    @Test
    @DisplayName("Проверка сортировки массива чисел (sortNumbers)")
    @Tag("array-sorting") // Тег для фильтрации: можно запустить отдельно ./gradlew test -PtagName=array-sorting
    void sortNumbersTest(TestInfo testInfo) {
        // --- Случай 1: Обычный массив со случайными числами ---
        int[] inputArray = {5, 2, 9, 1, 5, 6};
        int[] expectedResult = {1, 2, 5, 5, 6, 9};


        int[] actualResult = BasicUtils.sortNumbers(inputArray);

        assertThat(actualResult)
                .as("%s: сортировка обычного массива. Ожидалось %s, а получили %s",
                        testInfo.getDisplayName(), Arrays.toString(expectedResult), Arrays.toString(actualResult))
                .isEqualTo(expectedResult);
    }
}
