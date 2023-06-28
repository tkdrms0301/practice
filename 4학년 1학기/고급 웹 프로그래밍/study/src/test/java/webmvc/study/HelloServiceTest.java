package webmvc.study;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestTemplate;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;
import webmvc.study.domain.Member;

import java.net.URI;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HelloServiceTest {
    @Test
    void helloApi() {
        TestRestTemplate rest = new TestRestTemplate();
        ResponseEntity<String> res = rest.getForEntity("http://localhost:8080/hello?name={name}", String.class, "an");

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        Assertions.assertThat(res.getBody()).isEqualTo("hello an");
    }

    @Test
    void helloApi2() {
        TestRestTemplate rest = new TestRestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("hello")
                .queryParam("name", "an")
                .build()
                .toUri();
        ResponseEntity<String> res = rest.getForEntity(uri, String.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        Assertions.assertThat(res.getBody()).isEqualTo("hello an");
    }

    @Test
    void helloPathVariable() {
        TestRestTemplate rest = new TestRestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/hello/{name}")
                .encode()
                .build()
                .expand("kim")
                .toUri();
        ResponseEntity<String> res = rest.getForEntity(uri, String.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        Assertions.assertThat(res.getBody()).isEqualTo("hello kim");
    }

    @Test
    void helloApiPostMemberReg(){
        TestRestTemplate rest = new TestRestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/hello/member")
                .queryParam("name","kim")
                .queryParam("age",30)
                .encode()
                .build()
                .toUri();
        ResponseEntity<String> res = rest.postForEntity(uri,null, String.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        Assertions.assertThat(res.getBody()).isEqualTo("hello kim");
    }

    @Test
    @Order(1)
    void helloApiPostMemberRegRequestBody(){
        TestRestTemplate rest = new TestRestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/hello/member")
                .encode()
                .build()
                .toUri();

        Member member = new Member("kim", 30);
        ResponseEntity<String> res = rest.postForEntity(uri,member, String.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.TEXT_PLAIN_VALUE);
        Assertions.assertThat(res.getBody()).isEqualTo("ok");
    }

    @Test
    @Order(2)
    void helloApiGetMember(){
        TestRestTemplate rest = new TestRestTemplate();
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080")
                .path("/hello/member/{id}")
                .encode()
                .build()
                .expand("1")
                .toUri();

        Member member = new Member("kim", 30);
        ResponseEntity<Member> res = rest.getForEntity(uri, Member.class);

        Assertions.assertThat(res.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(res.getHeaders().getFirst(HttpHeaders.CONTENT_TYPE)).startsWith(MediaType.APPLICATION_JSON_VALUE);
        Assertions.assertThat(res.getBody().getName()).isEqualTo(member.getName());
    }
}
