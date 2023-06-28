package com.kit.dormitory;

import com.kit.dormitory.book.BookService;
import com.kit.dormitory.member.Member;
import com.kit.dormitory.member.MemberServiceImpl;
import com.kit.dormitory.member.MemberService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberService memberService = ac.getBean("memberService", MemberService.class);
        BookService bookService = ac.getBean("bookService", BookService.class);

        Member member = new Member(1L,"kim",1);
        memberService.register(member);
        Member findMember = memberService.findMember(member);
        System.out.println("findMember = " + findMember.getName());
        System.out.println("member = " + member.getName());
    }
}
