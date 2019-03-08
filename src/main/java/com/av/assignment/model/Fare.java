package com.av.assignment.model;


import java.util.Currency;

import lombok.Value;

@Value
public class Fare {

    double amount;
    Currency currency;
    String origin, destination;

}