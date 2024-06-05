package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.models.Order;
import static io.restassured.RestAssured.given;
import static ru.praktikum.steps.EndPoints.*;
import static ru.praktikum.steps.TestStends.RC_STEND;

public class OrderSteps {

    @Step("Создание нового заказа")
    public ValidatableResponse createOrder(Order order){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(RC_STEND)
                .body(order)
                .when()
                .post(ORDER_CREATE_URL)
                .then();
    }

    @Step("Отмена заказа")
    public ValidatableResponse cancelOrder(Order order){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(RC_STEND)
                .body(order)
                .when()
                .put(ORDER_CANCEL_URL)
                .then();
    }

    @Step("Получение списка заказов")
    public ValidatableResponse orderList(){
        return given()
                .contentType(ContentType.JSON)
                .baseUri(RC_STEND)
                .when()
                .get(ORDER_CREATE_URL)
                .then();
    }

}
