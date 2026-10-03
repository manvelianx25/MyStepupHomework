package com.mystepup.homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * В классе com.mystepup.homework.BasicUtils перечислены все методы из задания №1 курса АТ на Java
 * <p>
 * Задача 1 - метод isEven <br>
 * Задача 2 - метод checkAccess <br>
 * Задача 3 - метод isPositive <br>
 * Задача 4 - метод getGrade <br>
 * Задача 5 - метод blastOff <br>
 * Задача 6 - метод sumToN <br>
 * Задача 7 - метод hasBug <br>
 * Задача 8 - метод getEvenInRange <br>
 * Задача 9 - метод findMax <br>
 * Задача 10 - метод reverse <br>
 * Задача 11 - метод calcAverage <br>
 * Задача 12 - метод removeSpecificName <br>
 * Задача 13* (3-е ДЗ) - метод hasWordInSentence <br>
 * Задача 14* (3-е ДЗ) - метод getLastLongestWord <br>
 * Задача 15* (3-е ДЗ) - метод getUniqueNumbers  <br>
 * </p>
 */

public class BasicUtils {
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static String checkAccess(int age) {
        return age > 18 ? "Allowed" : "Denied";
    }

    public static boolean isPositive(int n) {
        return n >= 0 ? true : false;
    }

    public static String getGrade(int score) {
        if (score >= 0 && score <= 20) {
            return "E";
        } else if (score >= 21 && score <= 40) {
            return "D";
        } else if (score >= 41 && score <= 60) {
            return "C";
        } else if (score >= 61 && score <= 80) {
            return "B";
        } else if (score >= 81 && score <= 100) {
            return "A";
        } else {
            return "Error!";
        }
    }

    public static String blastOff(int start) {
        if (start < 1) {
            return "Поехали!";
        }

        StringBuilder result = new StringBuilder();
        for (int i = start; i >= 1; i--) {
            result.append(i).append(" ");
        }
        result.append("Поехали!");

        return result.toString();
    }

    public static int sumToN(int n) {
        if (n < 1) {
            return 0;
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    public static boolean hasBug(String[] messages) {
        if (messages == null) {
            return false;
        }
        for (String message : messages) {
            if (message != null && message.equalsIgnoreCase("bug")) {
                return true;
            }
        }
        return false;
    }


    public static String getEvenInRange(int start, int end) {
        if (start > end) {
            return "Error!";
        }

        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) {
                if (!first) {
                    result.append(" ");
                }
                result.append(i);
                first = false;
            }
        }

        return result.toString();
    }

    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0) {
            throw new IllegalArgumentException("Array must not be null or empty");
        }

        // берём первый элемент как начальный максимум
        int maxValue = arr[0];
        for (int i : arr) {
            if (i > maxValue) {
                maxValue = i;
            }
        }
        return maxValue;
    }

    public static String[] reverse(String[] arr) {
        if (arr == null) {
            return null;
        }

        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }

    public static double calcAverage(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0.0;
        }

        // long, чтобы избежать переполнения при больших суммах
        long sum = 0;
        for (int num : list) {
            sum += num;
        }

        return (double) sum / list.size();
    }

    public static List<String> removeSpecificName(List<String> list, String nameToRemove) {
        if (list == null) {
            return null;
        }

        List<String> result = new ArrayList<>();
        for (String name : list) {
            // Если nameToRemove == null, убираем все null-элементы
            if (nameToRemove == null) {
                if (name != null) {
                    result.add(name);
                }
            } else {
                if (!nameToRemove.equals(name)) {
                    result.add(name);
                }
            }
        }
        return result;
    }

    public static boolean hasWordInSentence(String sentence, String word) {
        if (sentence == null || word == null) {
            return false;
        }
        String[] words = sentence.split("\\W+");

        for (String searchedWord : words) {
            if (searchedWord.equalsIgnoreCase(word)) {
                return true;
            }
        }
        return false;
    }

    public static String getLastLongestWord(String[] words) {
        String longestWord = "";

        for (String word : words) {
            // специально допустил ошибку в условии, по требованиям ДЗ
            // Данный метод будет возвращать не последнее встречающееся длинное слово, а первое
            // (для последнего нужно нестрогое сравнение <=)
            if (longestWord.length() < word.length()) {
                longestWord = word;
            }
        }

        return longestWord;
    }

    public static Set<Integer> getUniqueNumbers(List<Integer> numbers) {
        return new HashSet<>(numbers);
    }

    public static int[] sortNumbers(int[] numbers) {
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }
        return numbers;
    }

}