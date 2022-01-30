package jpalatte.jpacafe.repository;

import jpalatte.jpacafe.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;


@Repository
@RequiredArgsConstructor
public class OrderRepository {

    private final EntityManager em;

    public void save(Order order) {
        em.persist(order);
    }

    public Order findOne(Long id) {
        return em.find(Order.class, id);
    }

    /**
     * 동적 query, 조건문 없이 Criteria 없이 queryDSL
     */
    public List<Order> findAllFromSearch(OrderSearch orderSearch) {  //객체 order 와 customer 조인
        String jpql = "select o From Order o join o.customer c";
        boolean isFirstCondition = true;
        //주문 상태 검색
        if (orderSearch.getOrderStatus() != null) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " o.orderStatus = :orderStatus";
        }
        //회원 이름 검색
        if (StringUtils.hasText(orderSearch.getCustomerName())) {
            if (isFirstCondition) {
                jpql += " where";
                isFirstCondition = false;
            } else {
                jpql += " and";
            }
            jpql += " c.name like :customerName";
        }
        TypedQuery<Order> query = em.createQuery(jpql, Order.class) .setMaxResults(1000); //최대 1000건
        if (orderSearch.getOrderStatus() != null) {
            query = query.setParameter("orderStatus", orderSearch.getOrderStatus());
        }
        if (StringUtils.hasText(orderSearch.getCustomerName())) {
            query = query.setParameter("customerName", orderSearch.getCustomerName());
        }
        return query.getResultList();
    }

/*
        QOrder order = QOrder.order;
        QCustomer customer = QCustomer.customer;

        return query
                .select(order)
                .from(order)
                .join(order.customer, customer)
                .where(statusEq(orderSearch.getOrderStatus()),
                        nameLike(orderSearch.getCustomerName()))
                .limit(1000)
                .fetch();
    }

 */

}
