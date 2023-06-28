package online.shop.repository;

import online.shop.domain.item.Item;
import online.shop.dto.BasicItemDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpringDataItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByPriceGreaterThanEqual(int price);
    List<Item> findByNameLike(String name);
    List<Item> findByNameLikeAndPriceLessThanEqualOrderByPriceAsc(String name, int price);

    Page<Item> findPageBy(Pageable pageable);
    Slice<Item> findSliceBy(Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.price <=?1")
    List<Item> findByPriceLessThan(int price);

    @Query("SELECT new online.shop.dto.BasicItemDTO(i.name, i.price) FROM Item i WHERE i.price <=:price")
    List<BasicItemDTO> findByPriceLessThan3(@Param("price") int price);
}
