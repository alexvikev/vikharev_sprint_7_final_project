package ru.praktikum.order.tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.filter.log.RequestLoggingFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.models.Order;
import ru.praktikum.steps.OrderSteps;
import java.util.List;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationTests {
    private OrderSteps orderSteps;
    private Order order;
    private List<String> color;

    public OrderCreationTests(List<String> color){
        this.color = color;
    }

    @Before
    public void setUp(){
        RestAssured.filters(new RequestLoggingFilter());

        orderSteps = new OrderSteps();
        order = new Order();
    }

    @Parameterized.Parameters
    public static Object[][] data(){
        return new Object[][] {
                {List.of("BLACK")},
                {List.of("GREY")},
                {List.of("BLAC GRAY")},
                {List.of()}
        };
    }

    @DisplayName("Создание заказа с разными цветами самоката")
    @Test
    public void orderCreationTest(){
        orderSteps
                .createOrder(order)
                .statusCode(201)
                .body("track", notNullValue());
    }

    @After
    public void tearDown(){
        String track = orderSteps.cancelOrder(order)
                .extract().body().path("track");

        order.setTrack(track);
        orderSteps.cancelOrder(order);
    }
}


