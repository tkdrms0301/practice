package online.shop.repository;

import online.shop.domain.Order;
import online.shop.domain.OrderItem;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Subgraph;
import java.util.HashMap;
import java.util.Map;

@Repository
public class SpringDataOrderCustomRepositoryImpl implements SpringDataOrderCustomRepository{
    @PersistenceContext
    EntityManager em;

    @Override
    public Order findWithMemberItemDynamic(Long orderId) {
        EntityGraph<Order> graph = em.createEntityGraph(Order.class);
        graph.addAttributeNodes("member");
        Subgraph<OrderItem> orderItems = graph.addSubgraph("orderItems");
        orderItems.addAttributeNodes("item");
        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);
        Order order = em.find(Order.class, orderId, hints);
        return order;
    }
}
