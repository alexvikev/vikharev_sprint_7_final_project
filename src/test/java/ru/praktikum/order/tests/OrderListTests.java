package ru.praktikum.order.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.steps.OrderSteps;
import static org.hamcrest.Matchers.notNullValue;

public class OrderListTests {
    private OrderSteps orderSteps;

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter());

        orderSteps = new OrderSteps();

    }

    @DisplayName("Получить список заказов")
    @Test
    public void orderGetOrderListTest(){
        orderSteps
                .orderList()
                .statusCode(200)
                .body("orders", notNullValue());
    }
}
