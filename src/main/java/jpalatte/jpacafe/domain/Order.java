package jpalatte.jpacafe.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") //그래야 Order아닌 Orders 테이블명
public class Order {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) //FetchType.LAZY? EAGER?
    @JoinColumn(name = "customer_id") // many Orders : 1 Customer by Customerpk
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL) //원래 모든 entity는 개별 persist BUTALL? Order => OrderItems까지 다
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING) //꼭 ORDINAL 아닌 STRING해야 새로 enum type추가되어도 컴파일 오류 안남
    private OrderStatus orderStatus; // ORDER, CANCEL

    /**
     * 연관 관계 편의 메서드 : 핵심 로직 쪽에 써용
     */
    public void setCustomer(Customer customer){
        this.customer= customer;
        customer.getOrders().add(this); //customer, order 별도 생성x
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem);
        orderItem.setOrder(this); //
    }
}
