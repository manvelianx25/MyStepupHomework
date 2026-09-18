package org.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RemoveSpecificNameTest {

    // 1. Базовый случай: удаляем существующее имя
    @Test
    public void testRemoveSpecificNameBasicCase() {
        List<String> input = Arrays.asList("Alice", "Bob", "Charlie");
        List<String> expected = Arrays.asList("Alice", "Charlie");

        List<String> result = BasicUtils.removeSpecificName(input, "Bob");

        assertEquals(expected, result);
    }

    // 2. Нет совпадений: имя не найдено в списке
    @Test
    public void testRemoveSpecificNameNoMatch() {
        List<String> input = Arrays.asList("Alice", "Bob");
        List<String> expected = Arrays.asList("Alice", "Bob");

        List<String> result = BasicUtils.removeSpecificName(input, "Dave");

        assertEquals(expected, result);
    }

    // 3. Все элементы совпадают: список становится пустым
    @Test
    public void testRemoveSpecificNameAllMatch() {
        List<String> input = Arrays.asList("Bob", "Bob", "Bob");
        List<String> expected = Collections.emptyList();

        List<String> result = BasicUtils.removeSpecificName(input, "Bob");

        assertEquals(expected, result);
    }

    // 4. Пустой список: на входе пусто, на выходе пусто
    @Test
    public void testRemoveSpecificNameEmptyList() {
        List<String> input = Collections.emptyList();
        List<String> expected = Collections.emptyList();

        List<String> result = BasicUtils.removeSpecificName(input, "Bob");

        assertEquals(expected, result);
    }

    // 5. null-список: проверяем обработку null на входе
    @Test
    public void testRemoveSpecificNameNullList() {
        assertNull(BasicUtils.removeSpecificName(null, "Bob"));
    }

    // 6. Удаление null-элементов: nameToRemove == null
    @Test
    public void testRemoveSpecificNameRemoveNulls() {
        // Используется ArrayList, так как List.of() не принимает null
        List<String> input = new ArrayList<>();
        input.add("Alice");
        input.add(null);
        input.add("Charlie");
        input.add(null);

        List<String> expected = new ArrayList<>();
        expected.add("Alice");
        expected.add("Charlie");

        List<String> result = BasicUtils.removeSpecificName(input, null);

        assertEquals(expected, result);
    }

    // 7. Проверка неизменности исходного списка
    @Test
    public void testRemoveSpecificNameOriginalListUnchanged() {
        List<String> original = new ArrayList<>(Arrays.asList("Alice", "Bob", "Charlie"));
        List<String> copy = new ArrayList<>(original); // копия для сравнения

        BasicUtils.removeSpecificName(original, "Bob");

        // Метод должен вернуть новый список, не меняя оригинал
        assertEquals(copy, original);
    }
}