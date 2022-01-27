package jpalatte.jpacafe.repository;

import jpalatte.jpacafe.domain.Customer;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository //@Repository(Spring Component scan)
public class CustomerRepository {

    @PersistenceContext
    private EntityManager em; //Spring에서 알아서 해당 EntityManager 주입 : spring-data-jpa

    public Long save(Customer customer) {
        em.persist(customer);
        return customer.getId(); //명령 질의 분리 return Id정도만
    }

    public Customer findOne(Long id) {
        return em.find(Customer.class, id);  //entityManager.find(XX.class, id)
    }

    public List<Customer> findAll(){
        return em.createQuery("select c from Customer c", Customer.class)
                .getResultList();  //(jpql : alias 를 Entity기준으, rtnClass)
    }

    public List<Customer> findByName(String name) {
        return em.createQuery("select c from Customer c where c.name = :name", Customer.class)
                .setParameter("name", name) //:XXX, input parameter
                .getResultList();
    }
}
