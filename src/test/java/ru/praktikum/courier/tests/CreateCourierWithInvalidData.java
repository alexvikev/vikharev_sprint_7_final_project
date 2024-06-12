package ru.praktikum.courier.tests;

import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.models.Courier;
import ru.praktikum.steps.CourierSteps;

import static org.hamcrest.Matchers.is;

public class CreateCourierWithInvalidData {

    CourierSteps courierSteps;
    Courier courier;

    @Before
    public void setUp(){
        courierSteps = new CourierSteps();
        courier = new Courier();
    }

    @DisplayName("Нельзя создать курьера без логина")
    @Test
    public void courierCreateWithoutLoginTest(){
        courier.setLogin("");

        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Нельзя создать курьера без пароля")
    @Test
    public void courierCreateWithoutPasswordTest(){
        courier.setPassword("");

        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @DisplayName("Нельзя создать курьера без логина и пароля")
    @Test
    public void courierCreateWithoutPasswordAndLoginTest(){
        courier.setLogin("");
        courier.setPassword("");

        courierSteps
                .createCourier(courier)
                .statusCode(400)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

}
