package online.shop.repository;

import online.shop.domain.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class SpringDataMemberRepositoryTest {
    @Autowired private SpringDataMemberRepository springDataMemberRepository;
    @Test
    void 이름으로_검색_테스트() {
        List<Member> members = springDataMemberRepository.findByName("member1");
        members.stream().forEach(m-> System.out.println("m = " + m.getName()));
    }
}