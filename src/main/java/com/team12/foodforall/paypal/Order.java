package com.team12.foodforall.paypal;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Integer projectID;
    private Integer quantity;
    private String name;
    private String description;
    private String currency;
    private String price;

}
