package jpalatte.jpacafe.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("C")
@Getter @Setter
public class Coffee extends Item { //extends then Id, price, stockQuantity
    private boolean sizeUp;
    private String topping;
}
