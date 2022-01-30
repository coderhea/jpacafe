package jpalatte.jpacafe.repository;

import jpalatte.jpacafe.domain.OrderStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class OrderSearch {

    private String customerName; //회원 이름 검색
    private OrderStatus orderStatus; //주문상태 ORDER, CANCEL

}
