package com.team12.foodforall.paypal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Subscription {
    private Long projectID;
    private String frequency;
    private String name;
    private String productID;
    private String description;
    private String currency;
    private Float price;

}
