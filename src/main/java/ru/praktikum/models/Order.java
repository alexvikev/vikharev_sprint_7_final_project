package ru.praktikum.models;

import lombok.Data;
import java.util.List;

@Data
public class Order {
    private String firstName;
    private String lastname;
    private String address;
    private Integer metroStation;
    private String phone;
    private Integer rentTime;
    private String deliveryDate;
    private String track;
    private List<String> color;
    private String comment;
}
