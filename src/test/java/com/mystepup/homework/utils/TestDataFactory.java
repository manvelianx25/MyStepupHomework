
package com.mystepup.homework.utils;

import java.math.BigDecimal;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class TestDataFactory {

    private TestDataFactory() {
    }

    public static String generateGoodsName() {
        return "Test product " + UUID.randomUUID();
    }

    public static BigDecimal generateGoodsPrice() {
        int kopecks = ThreadLocalRandom.current()
                .nextInt(1000, 100001);

        return BigDecimal.valueOf(kopecks, 2);
    }
}
