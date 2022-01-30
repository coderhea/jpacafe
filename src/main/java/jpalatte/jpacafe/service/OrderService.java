package jpalatte.jpacafe.service;

import jpalatte.jpacafe.domain.Customer;
import jpalatte.jpacafe.domain.Order;
import jpalatte.jpacafe.domain.OrderItem;
import jpalatte.jpacafe.domain.item.Item;
import jpalatte.jpacafe.repository.CustomerRepository;
import jpalatte.jpacafe.repository.ItemRepository;
import jpalatte.jpacafe.repository.OrderRepository;
import jpalatte.jpacafe.repository.OrderSearch;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ItemRepository itemRepository;
    private final CustomerRepository customerRepository;

    /**
     * Order 생성 : orderRepository.save(order);
     * Entity Order has @OneToMany(mappedBy="order", cascade=CascadeType.All) : order만 persist, orderItems 자동
     * 단, 만약 orderItems 다른 곳에서도 사용한다면, cascade(X) 별도 orderItemRepository로 자체 persist lifecycle
     */
    @Transactional
    public Long order(Long customerId, Long itemId, int count) { //customerId가 itemId선택, count개수만큼 : repository 2+
        //엔티티 조회
        Customer customer = customerRepository.findOne(customerId);
        Item item = itemRepository.findOne(itemId);

        //주문 상품 생성
        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), count); //count 개수만큼 생성성

        //주문 생성
        Order order = Order.createOrder(customer, orderItem);

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    /**
     * 주문 취소
     */
    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);
        order.cancel(); //JPA 더티체킹으로 직접 변경내역 쿼리없이 orderId = stockQuantity 바뀌는 것만 처리
    }

    /**
     * 주문 검색
     */
    public List<Order> findOrdersFromSearch(OrderSearch orderSearch) {
        return orderRepository.findAllFromSearch(orderSearch);
    }
}

