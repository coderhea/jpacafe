package jpalatte.jpacafe.controller;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CoffeeForm {
    //Item 공통
    private Long id; //추후 수정 위해서 꼭 필요

    private String name;
    private int price;
    private int stockQuantity;

    private boolean sizeUp;
    private String topping;
}
