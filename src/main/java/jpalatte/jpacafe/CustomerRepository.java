package jpalatte.jpacafe;

import jpalatte.jpacafe.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em; //@Repository 등록되어있으면 해당 EntityManager사용 : spring-data-jpa

    public Long save(Customer customer) {
        em.persist(customer);
        return customer.getId(); //명령 질의 분리 return Id정도만
    }

    public Customer find(Long id) {
        return em.find(Customer.class, id);
    }
}
