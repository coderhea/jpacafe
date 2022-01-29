package jpalatte.jpacafe.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class CustomerForm {
    @NotEmpty(message = "회원 이름은 필수입니다") //javax.validation
    private String name;

    private String teamName;
    private String floor;
}
