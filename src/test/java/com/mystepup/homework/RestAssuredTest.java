package com.mystepup.homework;

import com.mystepup.homework.client.GoodsClient;
import com.mystepup.homework.config.ApiConfig;
import com.mystepup.homework.utils.TestDataFactory;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;

@Tag("api")
public class RestAssuredTest {

    private final GoodsClient goodsClient = new GoodsClient();

    // Вспомогательный метод: создаёт товар и возвращает его ID
    private long createGoodsAndGetId() {
        String name = TestDataFactory.generateGoodsName();
        BigDecimal price = TestDataFactory.generateGoodsPrice();

        Response response = goodsClient.createGoods(name, price);

        response.then()
                .statusCode(200);

        return response.jsonPath().getLong("data.id");
    }

    // Задача 1. Тест 1: список без RequestSpecification
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

    // Задача 1. Тест 2: список с RequestSpecification
    @Test
    void getGoodsListWithSpecTest() {
        goodsClient.getGoodsList()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("goods", empty());
    }

    // Задача 1. Тест 3: создание товара и проверка через REST Assured
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

    // Задача 1. Тест 4: создание товара и проверка через AssertJ
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

    // POST /goods/add: некорректный JSON -> 400
    @Test
    void createGoodsWithInvalidJsonTest() {
        goodsClient.createGoodsWithRawBody("{")
                .then()
                .statusCode(400);
    }

    // GET /goods/{id}: существующий товар -> 200
    @Test
    void getGoodsByIdTest() {
        long id = createGoodsAndGetId();

        goodsClient.getGoodsById(id)
                .then()
                .statusCode(200);
    }

    // GET /goods/{id}: отсутствующий товар -> 404
    @Test
    void getGoodsByMissingIdTest() {
        goodsClient.getGoodsById(Long.MAX_VALUE)
                .then()
                .statusCode(404);
    }

    // DELETE /goods/{id}: существующий товар -> 200
    @Test
    void deleteGoodsTest() {
        long id = createGoodsAndGetId();

        goodsClient.deleteGoods(id)
                .then()
                .statusCode(200);

        // Дополнительно проверяем, что товар больше не доступен
        goodsClient.getGoodsById(id)
                .then()
                .statusCode(404);
    }

    // DELETE /goods/{id}: отсутствующий товар -> 404
    @Test
    void deleteMissingGoodsTest() {
        goodsClient.deleteGoods(Long.MAX_VALUE)
                .then()
                .statusCode(404);
    }

    // PATCH /goods/{id}: успешное обновление -> 200
    @Test
    void updateGoodsTest() {
        long id = createGoodsAndGetId();
        String updatedName = TestDataFactory.generateGoodsName();
        BigDecimal updatedPrice = TestDataFactory.generateGoodsPrice();

        goodsClient.updateGoods(id, updatedName, updatedPrice)
                .then()
                .statusCode(200);

        goodsClient.getGoodsList()
                .then()
                .statusCode(200)
                .body("goods.name", hasItem(updatedName));
    }

    // PATCH /goods/{id}: дублирование имени -> 400
    @Test
    void updateGoodsWithDuplicateNameTest() {
        long firstId = createGoodsAndGetId();
        long secondId = createGoodsAndGetId();

        Response response = goodsClient.getGoodsList()
                .then()
                .statusCode(200)
                .extract()
                .response();

        List<Map<String, Object>> goods =
                response.jsonPath().getList("goods");

        String firstName = goods.stream()
                .filter(good ->
                        ((Number) good.get("id")).longValue() == firstId)
                .map(good -> (String) good.get("name"))
                .findFirst()
                .orElseThrow(() ->
                        new AssertionError("Первый товар не найден")
                );

        BigDecimal price = TestDataFactory.generateGoodsPrice();

        goodsClient.updateGoods(secondId, firstName, price)
                .then()
                .statusCode(400);
    }

    // PATCH /goods/{id}: отсутствующий товар -> 404
    @Test
    void updateMissingGoodsTest() {
        goodsClient.updateGoods(
                        Long.MAX_VALUE,
                        TestDataFactory.generateGoodsName(),
                        TestDataFactory.generateGoodsPrice()
                )
                .then()
                .statusCode(404);
    }

    // GET /goods/list: успешное получение списка -> 200
    @Test
    void getGoodsListStatusTest() {
        goodsClient.getGoodsList()
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("goods", org.hamcrest.Matchers.notNullValue());
    }
}