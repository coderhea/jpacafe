package jpalatte.jpacafe.service;

import jpalatte.jpacafe.domain.Customer;
import jpalatte.jpacafe.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional //database 변경 꼭
@RequiredArgsConstructor  //구식 2. 대체. private final (required) 변수로 알아서 생성자
public class CustomerService {

    //    구식 1.field spring injection
    //    @Autowired
    //    private CustomerRepository customerRepository;

    //    구식 2. 생성자 Injection , 주입받는 값은 꼭 final 선언해야 의존성 확인
     private final CustomerRepository customerRepository;
//
//    @Autowired
//    public CustomerService(CustomerRepository customerRepository) {
//        this.customerRepository = customerRepository;
//    }

    /**
     * 회원 가입
     */
    public Long join(Customer customer) {
        validateDuplicateCustomer(customer);
        customerRepository.save(customer);
        return customer.getId(); //pk로 확인 가능
    }

    private void validateDuplicateCustomer(Customer customer) {
        List<Customer> findCustomers = customerRepository.findByName(customer.getName());
        if (!findCustomers.isEmpty()) {
            throw new IllegalStateException("already registered name");
        }
    }

    /**
     * 회원 전체 조회
     */
    @Transactional(readOnly = true)  //조회용 최적화, dirty checking 안함. 리소스도 가볍게
    public List<Customer> findCustomers() {
        return customerRepository.findAll();
    }

    /**
     * 회원 단일 조회
     */
    @Transactional(readOnly = true)  //조회용 최적화, dirty checking 안함. 리소스도 가볍게
    public Customer findOne(Long customerId) {
        return customerRepository.findOne(customerId);
    }
}
