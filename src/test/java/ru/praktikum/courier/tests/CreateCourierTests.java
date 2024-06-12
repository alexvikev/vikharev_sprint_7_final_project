package ru.praktikum.courier.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.models.Courier;
import ru.praktikum.steps.CourierSteps;
import static org.hamcrest.Matchers.is;

public class CreateCourierTests {
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

    @DisplayName("Курьера можно создать")
    @Test
    public void courierCreationReturnOkTrueTest(){
        courierSteps
                .createCourier(courier)
                .statusCode(201)
                .body("ok", is(true));
    }

    @DisplayName("Нельзя создать двух одинаковых курьеров")
    @Test
    public void courierDuplicateTest(){
        courierSteps
                .createCourier(courier);

        courierSteps
                .createCourier(courier)
                .statusCode(409)
                .body("message", is("Этот логин уже используется. Попробуйте другой."));
    }

    @After
    public void tearDown() {
        Integer currentID = courierSteps.courierLogin(courier)
                .extract().body().path("id");
        courier.setId(currentID);
        courierSteps.deleteCourier(courier);

    }

}
