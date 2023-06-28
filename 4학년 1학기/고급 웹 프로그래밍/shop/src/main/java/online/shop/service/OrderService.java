package online.shop.service;

import lombok.RequiredArgsConstructor;
import online.shop.domain.Delivery;
import online.shop.domain.Member;
import online.shop.domain.Order;
import online.shop.domain.OrderItem;
import online.shop.domain.item.Item;
import online.shop.repository.MemberRepository;
import online.shop.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final MemberRepository memberRepository;
    private final OrderRepository orderRepository;
    private final ItemService itemService;

    public Long order(Long memberId,
                      Long itemId,
                      int quantity) {
        Member member = memberRepository.findOne(memberId);
        Item item = itemService.findOne(itemId);
        Delivery delivery = new Delivery();
        delivery.setAddress(member.getAddress());

        OrderItem orderItem = OrderItem.createOrderItem(item, item.getPrice(), quantity);
        Order order = Order.createOrder(member, delivery, orderItem);
        orderRepository.save(order);
        return order.getId();
    }

    public void cancelOrder(Long orderId) {
        Order order = orderRepository.findOne(orderId);
        order.cancel();
    }
}
