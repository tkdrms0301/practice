package webmvc.study;

import org.junit.jupiter.api.*;
import webmvc.study.domain.Member;

public class testStudy {

    @Test
    void 객체_생성() {
        Member member = new Member("an", 25);
        Assertions.assertNotNull(member);
    }

    @Test
    void test1() {
        System.out.println("test1");
    }

    @Test
    void test2() {
        System.out.println("test2");
    }

    @BeforeAll
    @Test
    static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    @Test
    static void AfterAll() {
        System.out.println("AfterAll");
    }

    @BeforeEach
    @Test
    void BeforeEach() {
        System.out.println("BeforeEach");
    }

    @BeforeEach
    @Test
    void BeforeEach2() {
        System.out.println("BeforeEach2");
    }

    @AfterEach
    @Test
    void AfterEach() {
        System.out.println("AfterEach");
    }
}
