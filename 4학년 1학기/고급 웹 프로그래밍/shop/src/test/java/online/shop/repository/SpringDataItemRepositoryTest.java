package online.shop.repository;

import online.shop.domain.item.Item;
import online.shop.dto.BasicItemDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class SpringDataItemRepositoryTest {
    @Autowired SpringDataItemRepository springDataItemRepository;

    @Test
    void 특정가격_이상_상품검색(){
        List<Item> items = springDataItemRepository.findByPriceGreaterThanEqual(20000);
        items.stream().forEach(i-> System.out.println("i.getPrice() = " + i.getPrice()));
    }

    @Test
    void 이름포함_상품검색(){
        List<Item> items = springDataItemRepository.findByNameLike("spring%");
        items.stream().forEach(i-> System.out.println("i.getName() = " + i.getName()));
    }

    @Test
    void 이름포함_이하가격으로_조회_가격기준_오름차순정렬(){
        List<Item> items = springDataItemRepository.findByNameLikeAndPriceLessThanEqualOrderByPriceAsc("spring%",30000);
        items.stream().forEach(i-> System.out.println("i.getName() = " + i.getName()));
    }

    @Test void 페이징_테스트(){
        Pageable firstPage = PageRequest.of(0, 2);//페이지 size를 2로 설정했을 때 첫 번째 페이지
        Pageable secondPage = PageRequest.of(1, 2);//페이지 size를 2로 설정했을 때 두 번째 페이지
        Page<Item> firstItems = springDataItemRepository.findAll(firstPage);
        firstItems.stream().forEach(i-> System.out.println("i.getName() = " + i.getName()));
        System.out.println("=============================");
        Page<Item> secondItems = springDataItemRepository.findAll(secondPage);
        secondItems.stream().forEach(i-> System.out.println("i.getName() = " + i.getName()));
    }

    @Test void Page_Slice(){
        PageRequest pageRequest = PageRequest.of(1, 2, Sort.by(Sort.Direction.ASC, "price"));
        Page<Item> pageBy = springDataItemRepository.findPageBy(pageRequest);
        System.out.println("==================================================");
        Slice<Item> sliceBy = springDataItemRepository.findSliceBy(pageRequest);
    }
    
    @Test void 가격으로_검색_JPQL() {
        List<Item> items = springDataItemRepository.findByPriceLessThan(20000);
        items.forEach(i -> System.out.println("i.getName() = " + i.getName()));
    }

    @Test void 가격으로_검색_전용DTO() {
        List<BasicItemDTO> items = springDataItemRepository.findByPriceLessThan3(20000);
        items.forEach(i -> System.out.println("i.getName() = " + i.getName()));
    }
}