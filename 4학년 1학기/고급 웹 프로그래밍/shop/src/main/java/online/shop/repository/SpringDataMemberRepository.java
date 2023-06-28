package online.shop.repository;

import online.shop.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SpringDataMemberRepository extends JpaRepository<Member, Long> {
    List<Member> findByName(String name);
}
