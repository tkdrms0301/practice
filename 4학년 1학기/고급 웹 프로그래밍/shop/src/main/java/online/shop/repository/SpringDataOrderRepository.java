package online.shop.repository;

import online.shop.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SpringDataOrderRepository extends JpaRepository<Order, Long>, SpringDataOrderCustomRepository {
    @Query("Select o FROM Order o join fetch o.member")
    List<Order> findWithMemberJPQL();

    @EntityGraph(value = "order.member.graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT o FROM Order o")
    List<Order> findWithMemberGraph();

    @EntityGraph(value = "orderWithMember.orderItem.order.graph", type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT o FROM Order o")
    List<Order> findWithOrderItemItemGraph();
}
