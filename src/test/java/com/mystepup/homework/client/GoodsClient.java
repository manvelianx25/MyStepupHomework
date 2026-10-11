
package com.mystepup.homework.client;

import com.mystepup.homework.config.ApiConfig;
import io.restassured.response.Response;

import java.math.BigDecimal;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GoodsClient {

    public Response getGoodsList() {
        return getGoodsList(0, 1000);
    }

    public Response getGoodsList(int page, int size) {
        Response response = given()
                .spec(ApiConfig.basicRq())
                .queryParam("page", page)
                .queryParam("size", size)
                .when()
                .get("/goods/list");

        if (response.statusCode() >= 400) {
            response.then().log().all();
        }

        return response;
    }

    public Response createGoods(String name, BigDecimal price) {
        return given()
                .spec(ApiConfig.adminRq())
                .body(Map.of("name", name, "price", price))
                .when()
                .post("/goods/add");
    }

    public Response getGoodsById(long id) {
        return given()
                .spec(ApiConfig.adminRq())
                .pathParam("id", id)
                .when()
                .get("/goods/{id}");
    }

    public Response deleteGoods(long id) {
        return given()
                .spec(ApiConfig.adminRq())
                .pathParam("id", id)
                .when()
                .delete("/goods/{id}");
    }

    public Response updateGoods(long id, String name, BigDecimal price) {
        return given()
                .spec(ApiConfig.adminRq())
                .pathParam("id", id)
                .body(Map.of("name", name, "price", price))
                .when()
                .patch("/goods/{id}");
    }

    public Response createGoodsWithRawBody(String body) {
        return given()
                .spec(ApiConfig.adminRq())
                .body(body)
                .when()
                .post("/goods/add");
    }
}
