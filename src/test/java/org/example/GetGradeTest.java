package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetGradeTest {

    // 1. Проверка нижней границы диапазона E (0)
    @Test
    public void testGetGrade_BoundaryE_Lower() {
        assertEquals("E", BasicUtils.getGrade(0));
    }

    // 2. Проверка верхней границы диапазона E (20)
    @Test
    public void testGetGrade_BoundaryE_Upper() {
        assertEquals("E", BasicUtils.getGrade(20));
    }

    // 3. Проверка нижней границы диапазона D (21)
    @Test
    public void testGetGrade_BoundaryD_Lower() {
        assertEquals("D", BasicUtils.getGrade(21));
    }

    // 4. Проверка верхней границы диапазона D (40)
    @Test
    public void testGetGrade_BoundaryD_Upper() {
        assertEquals("D", BasicUtils.getGrade(40));
    }

    // 5. Проверка нижней границы диапазона C (41)
    @Test
    public void testGetGrade_BoundaryC_Lower() {
        assertEquals("C", BasicUtils.getGrade(41));
    }

    // 6. Проверка верхней границы диапазона B (80)
    @Test
    public void testGetGrade_BoundaryB_Upper() {
        assertEquals("B", BasicUtils.getGrade(80));
    }

    // 7. Проверка нижней границы диапазона A (81)
    @Test
    public void testGetGrade_BoundaryA_Lower() {
        assertEquals("A", BasicUtils.getGrade(81));
    }

    // 8. Проверка верхней границы диапазона A (100)
    @Test
    public void testGetGrade_BoundaryA_Upper() {
        assertEquals("A", BasicUtils.getGrade(100));
    }

    // 9. Проверка значения внутри диапазона E (например, 10)
    @Test
    public void testGetGrade_InsideRangeE() {
        assertEquals("E", BasicUtils.getGrade(10));
    }

    // 10. Проверка значения внутри диапазона D (например, 30)
    @Test
    public void testGetGrade_InsideRangeD() {
        assertEquals("D", BasicUtils.getGrade(30));
    }

    // 11. Проверка значения внутри диапазона C (например, 50)
    @Test
    public void testGetGrade_InsideRangeC() {
        assertEquals("C", BasicUtils.getGrade(50));
    }

    // 12. Проверка значения внутри диапазона B (например, 70)
    @Test
    public void testGetGrade_InsideRangeB() {
        assertEquals("B", BasicUtils.getGrade(70));
    }

    // 13. Проверка значения внутри диапазона A (например, 90)
    @Test
    public void testGetGrade_InsideRangeA() {
        assertEquals("A", BasicUtils.getGrade(90));
    }

    // 14. Проверка ошибки: значение ниже допустимого диапазона (-1)
    @Test
    public void testGetGrade_BelowRange() {
        assertEquals("Error!", BasicUtils.getGrade(-1));
    }

    // 15. Проверка ошибки: значение выше допустимого диапазона (101)
    @Test
    public void testGetGrade_AboveRange() {
        assertEquals("Error!", BasicUtils.getGrade(101));
    }

    // 16. Проверка ошибки: ещё более низкое значение (-100)
    @Test
    public void testGetGrade_FarBelowRange() {
        assertEquals("Error!", BasicUtils.getGrade(-100));
    }

    // 17. Проверка ошибки: ещё более высокое значение (200)
    @Test
    public void testGetGrade_FarAboveRange() {
        assertEquals("Error!", BasicUtils.getGrade(200));
    }
}