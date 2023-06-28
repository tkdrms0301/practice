package com.kit.dormitory;

import com.kit.dormitory.book.BookService;
import com.kit.dormitory.book.BookServiceImpl;
import com.kit.dormitory.fee.FeePolicy;
import com.kit.dormitory.fee.OldFeePolicy;
import com.kit.dormitory.member.FileMemberStorage;
import com.kit.dormitory.member.MemberService;
import com.kit.dormitory.member.MemberServiceImpl;
import com.kit.dormitory.member.MemberStorage;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


public class AppConfig {
    @Bean
    MemberService memberService(){
        return new MemberServiceImpl(memberStorage());
    }

    @Bean
    BookService bookService(){
        return new BookServiceImpl(feePolicy());
    }

    @Bean
    MemberStorage memberStorage(){
        return new FileMemberStorage();
    }

    @Bean
    FeePolicy feePolicy(){
        return new OldFeePolicy();
    }
}
