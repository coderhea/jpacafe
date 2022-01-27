package jpalatte.jpacafe.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Customer {

    @Id @GeneratedValue
    @Column(name="customer_id")
    private Long id;
    private String name;

    @Embedded
    private Team team;

    @OneToMany(mappedBy = "customer") // many Orders mapped by 1 Customer
    private List<Order> orders = new ArrayList<>(); //컬렉션은 필드에서 초기화해야 null exception 애초 방지, 생성자 x
}
