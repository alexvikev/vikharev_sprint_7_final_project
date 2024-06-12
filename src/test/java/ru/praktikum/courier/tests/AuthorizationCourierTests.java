package ru.praktikum.courier.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.models.Courier;
import ru.praktikum.steps.CourierSteps;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

public class AuthorizationCourierTests {
    private CourierSteps courierSteps;
    private Courier courier;

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter());

        courierSteps = new CourierSteps();

        courier = new Courier();
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));
        courier.setFirstName(RandomStringUtils.randomAlphabetic(10));
    }

    @DisplayName("Проверка авторизации курьера")
    @Test
    public void courierAuthReturnOkAndIdTest(){
        courierSteps
                .createCourier(courier);

        courierSteps
                .courierLogin(courier)
                .statusCode(200)
                .body("id", notNullValue());
    }

    @DisplayName("Авторизация курьера без логина")
    @Test
    public void courierAuthWithoutLoginTest(){
        courier.setLogin("");

        courierSteps
                .createCourier(courier);

        courierSteps
                .courierLogin(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }

    @DisplayName("Авторизация курьера без пароля")
    @Test
    public void courierAuthWithoutPasswordTest(){
        courier.setPassword("");

        courierSteps
                .createCourier(courier);

        courierSteps
                .courierLogin(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }

    @DisplayName("Авторизация курьера без логина и пароля")
    @Test
    public void courierAuthWithoutLoginAndPasswordTest(){
        courier.setLogin("");
        courier.setPassword("");

        courierSteps
                .createCourier(courier);

        courierSteps
                .courierLogin(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для входа"));
    }

    @DisplayName("Авторизация курьера c несуществующии логином и паролем")
    @Test
    public void courierAuthInvalidLoginAndPasswordTest(){
        courier.setLogin(RandomStringUtils.randomAlphabetic(10));
        courier.setPassword(RandomStringUtils.randomAlphabetic(10));

        courierSteps
                .courierLogin(courier)
                .statusCode(404)
                .body("message", is("Учетная запись не найдена"));
    }


}
