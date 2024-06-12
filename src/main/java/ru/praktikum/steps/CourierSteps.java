package ru.praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.models.Courier;
import static io.restassured.RestAssured.given;
import static ru.praktikum.steps.EndPoints.*;
import static ru.praktikum.steps.TestStends.RC_STEND;

public class CourierSteps {

    @Step("Создание нового курьера")
    public ValidatableResponse createCourier(Courier courier) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(RC_STEND)
                .body(courier)
                .when()
                .post(COURIER_CREATE_URL)
                .then();
    }

    @Step("Авторизация курьреа в системе")
    public ValidatableResponse courierLogin(Courier courier) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(RC_STEND)
                .body(courier)
                .when()
                .post(COURIER_LOGIN_URL)
                .then();
    }

    @Step("Удаление курьера по id")
    public ValidatableResponse deleteCourier(Courier courier) {
        return given()
                .contentType(ContentType.JSON)
                .baseUri(RC_STEND)
                .pathParam("id", courier.getId())
                .when()
                .delete(COURIER_DELETE_URL)
                .then();

    }
}
