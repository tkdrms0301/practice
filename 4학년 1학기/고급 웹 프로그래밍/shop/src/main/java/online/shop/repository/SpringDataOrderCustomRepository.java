package online.shop.repository;

import online.shop.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataOrderCustomRepository {
    Order findWithMemberItemDynamic(Long orderId);
}
