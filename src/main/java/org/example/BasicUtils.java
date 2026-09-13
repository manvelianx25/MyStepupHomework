package org.example;

import java.util.ArrayList;
import java.util.List;

public class BasicUtils {
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    public static String checkAccess(int age) {
        return age > 18 ? "Allow" : "Denied";
    }

    public static boolean isPositive(int n) {
        return n >= 0;
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
            return "Error";
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
            if (message.equalsIgnoreCase("bug")) {
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
        int maxValue = 0;

        for (int i : arr) {
            if (i > maxValue) {
                maxValue = i;
            }
        }

        return maxValue;
    }

    public static String[] reverse(String[] arr) {
        if (arr == null) {
            return null; // или можно кинуть IllegalArgumentException, зависит от ТЗ
        }

        String[] result = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = arr[arr.length - 1 - i];
        }
        return result;
    }

    public static double calcAverage(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            return 0.0; // или можно выбросить IllegalArgumentException — зависит от требований
        }

        long sum = 0;          // long, чтобы избежать переполнения при больших суммах
        for (int num : list) {
            sum += num;
        }

        return (double) sum / list.size();
    }

    public static List<String> removeSpecificName(List<String> list, String nameToRemove) {
        if (list == null) {
            return null; // или вернуть пустой список — зависит от требований
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
}
