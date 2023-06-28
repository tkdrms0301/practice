package online.shop.service;

import online.shop.domain.Member;
import online.shop.domain.Order;
import online.shop.domain.OrderStatus;
import online.shop.domain.item.Book;
import online.shop.exception.NotEnougthStockException;
import online.shop.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class OrderServiceTest {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    MemberService memberService;
    @Autowired
    ItemService itemService;

    @Test
    public void 상품주문() throws Exception{
        // 회원가입
        Member member = createMember();

        // 책 입고
        Book book = createBook("JPA", 10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCount);
        Order findOrder = orderRepository.findOne(orderId);

        assertThat(findOrder.getStatus())
                .as("상품 주문시 상태는 ORDER")
                .isEqualTo(OrderStatus.ORDER);
        assertThat(findOrder.getOrderItems().size())
                .as("주문한 상품 종류 수가 정확 해야 한다.")
                .isEqualTo(1);
        assertThat(book.getStockQuantity())
                .as("주문 수량만큼 재고가 줄어야 한다.")
                .isEqualTo(10 - orderCount);
    }

    @Test
    public void 상품주문_재고수량초과 () throws Exception {
        Member member = createMember();
        Book item = createBook("JPA", 10000, 10);
        int orderCount = 11;
        NotEnougthStockException exception = assertThrows(
                NotEnougthStockException.class, () -> orderService.order(member.getId(), item.getId(), orderCount)
        );
        String message = exception.getMessage();
        System.out.println("message = " + message);
    }

    @Test
    public void 주문취소() {
        Member member = createMember();
        Book item = createBook("JPA", 10000, 10);
        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);

        Order getOrder = orderRepository.findOne(orderId);

        assertThat(getOrder.getStatus())
                .as("주문 취소시 상태는 cancled 이다")
                .isEqualTo(OrderStatus.CANCELED);
        assertThat(item.getStockQuantity())
                .as("주문이 취소된 상품은 재고가 회복되어야한다.")
                .isEqualTo(10);
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("안상근");
        memberService.join(member);
        return member;
    }

    private Book createBook(String name, int price, int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        itemService.saveItem(book);
        return book;
    }


}
