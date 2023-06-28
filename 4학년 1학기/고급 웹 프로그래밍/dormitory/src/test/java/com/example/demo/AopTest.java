package com.example.demo;

import com.kit.dormitory.ComAppConfig;
import com.kit.dormitory.member.Member;
import com.kit.dormitory.member.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AopTest {
    @Test
    void timeMeasureTest() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ComAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        Member member = new Member(1L, "An", 4);
        memberService.register(member);
        memberService.findMember(member);

        System.out.println("memberService = " + memberService.getClass());
    }
}
