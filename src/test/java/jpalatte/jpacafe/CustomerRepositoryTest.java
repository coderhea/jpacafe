package jpalatte.jpacafe;

import jpalatte.jpacafe.domain.Customer;
import jpalatte.jpacafe.repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    @Transactional    //중요. entityManager needs Transaction
    public void testCustomer() throws Exception {
        //given
        Customer customer = new Customer();
        customer.setName("Park");

        //when
        Long savedId = customerRepository.save(customer);
        Customer foundCustomer = customerRepository.findOne(savedId);

        //then
        Assertions.assertThat(foundCustomer.getId()).isEqualTo(customer.getId());
        Assertions.assertThat(foundCustomer.getName()).isEqualTo(customer.getName());

    }
}