package jpalatte.jpacafe.domain;

import jpalatte.jpacafe.domain.item.Item;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item; //추상클래스, 여러 종류 아이템

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문 가격
    private int count; //주문 수량

    /**
     * 생성 static
     */
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);
        //orderItem만큼, 재고수량 줄여야 한 메서드 안에서
        item.removeStock(count);
        return orderItem;
    }

    /**
     * Order의 Cancel : all OrderItem Cancel
     */
    public void cancel() {
        item.addStock(this.count); //해당 Item의 재고수량 count만큼 원복
    }

    /**
     * Total Price
     */
    public int getTotalPrice() {
        return this.orderPrice * this.count;
    }
}
