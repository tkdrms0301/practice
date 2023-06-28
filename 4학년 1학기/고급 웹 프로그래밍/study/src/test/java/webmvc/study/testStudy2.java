package webmvc.study;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import webmvc.study.domain.Member;
import webmvc.study.persistance.MemberRepository;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class testStudy2 {

    @Test
    void simpleTest(){
        //given
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComAppConfig.class);
        MemberRepository memberRepository = ac.getBean(MemberRepository.class);
        Member member = new Member("kim",1);

        //when
        memberRepository.save(member);
        Member findMember = memberRepository.findById(1L);

        // then
        System.out.println("member = " + member);
        System.out.println("findMember = " + findMember);
        Assertions.assertEquals(member,findMember, () -> member + "와 " + findMember + "는 같아야 함");
    }

    @Test
    void ThrowAssert테스트() {
        IllegalAccessError exception = Assertions.assertThrows(IllegalAccessError.class, () -> new Member("an", 25));
        String message = exception.getMessage();
        System.out.println("message = " + message);
    }

    @Test
    void assert_timeout테스트() {
        Assertions.assertTimeout(Duration.ofMillis(100), () -> {
            new Member("an", 25);
            Thread.sleep(200);
        }, () -> "Member 생성은 100ms 안에 끝나야 함");
    }

    @Test
    void 같은지테스트() {
        Member member = new Member("an", 25);
        Member member2 = new Member("an", 25);
        assertThat(member2)
                .withFailMessage("저장한 멤버와 조회한 멤버는 같아야 함")
                .isEqualTo(member);
    }
}
