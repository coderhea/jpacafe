package jpalatte.jpacafe.domain.item;

import jpalatte.jpacafe.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE) //한 테이블에 Salad, Coffee표시
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    /**
     * 재고 수량 그 객체가 관리
     */
    public void addStock(int quantity) {
        this.stockQuantity += quantity;
    }
    public void removeStock(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new NotEnoughStockException("need more stock!");
        }
        this.stockQuantity -= quantity;
    }
}
