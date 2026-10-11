
package com.mystepup.homework;

import com.mystepup.homework.client.GoodsClient;
import com.mystepup.homework.config.ApiConfig;
import com.mystepup.homework.utils.TestDataFactory;
import io.restassured.response.Response;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasItem;

public class RestAssuredTest {

    private final GoodsClient goodsClient = new GoodsClient();

    // Тест 1. Получаем список без использования RequestSpecification
    @Test
    void getGoodsListTest() {
        given()
                .baseUri(ApiConfig.baseUri())
                .accept(ContentType.JSON)
                .log().all()
                .queryParam("page", 0)
                .queryParam("size", 1000)
                .when()
                .get("/goods/list")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("goods", empty());
    }

    // Тест 2. Получаем список через RequestSpecification
    @Test
    void getGoodsListWithSpecTest() {
        goodsClient.getGoodsList()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("goods", empty());
    }

    // Тест 3. Создаём товар и проверяем его средствами REST Assured
    @Test
    void createGoodsAndCheckInListTest() {
        String name = TestDataFactory.generateGoodsName();
        BigDecimal price = TestDataFactory.generateGoodsPrice();

        goodsClient.createGoods(name, price)
                .then()
                .statusCode(200);

        goodsClient.getGoodsList()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("goods.name", hasItem(name))
                .body(
                        "goods.find { it.name == '%s' }.price.toString().toBigDecimal()"
                                .formatted(name),
                        comparesEqualTo(price)
                );
    }

    // Тест 4. Создаём товар и проверяем его с помощью AssertJ
    @Test
    void createGoodsAndCheckWithAssertJTest() {
        String name = TestDataFactory.generateGoodsName();
        BigDecimal price = TestDataFactory.generateGoodsPrice();

        goodsClient.createGoods(name, price)
                .then()
                .statusCode(200);

        Response response = goodsClient.getGoodsList()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .extract()
                .response();

        List<Map<String, Object>> goods =
                response.jsonPath().getList("goods");

        Map<String, Object> createdGoods = goods.stream()
                .filter(good -> name.equals(good.get("name")))
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError("Товар не найден: " + name)
                );

        BigDecimal actualPrice =
                new BigDecimal(createdGoods.get("price").toString());

        assertThat(actualPrice)
                .isEqualByComparingTo(price);
    }
}
