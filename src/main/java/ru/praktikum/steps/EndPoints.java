package ru.praktikum.steps;

// Класс содержит эндпойинты для различных функций
public class EndPoints {

    // эндпоинты для курьера
    public static final String COURIER_CREATE_URL = "/api/v1/courier";
    public static final String COURIER_LOGIN_URL = "/api/v1/courier/login";
    public static final String COURIER_DELETE_URL = "/api/v1/courier/{id}";

    // эндпоинты для закзов
    public static final String ORDER_CREATE_URL = "/api/v1/orders";
    public static final String ORDER_CANCEL_URL = "/api/v1/orders/cancel";

}
