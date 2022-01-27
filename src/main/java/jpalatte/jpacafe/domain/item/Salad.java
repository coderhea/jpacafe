package jpalatte.jpacafe.domain.item;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("S")
@Getter @Setter
public class Salad extends Item {
    private String name;
    private String sauce;
    private String topping;
}
