package webmvc.study;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import webmvc.study.controller.HelloController;
import webmvc.study.domain.Member;
import webmvc.study.service.HelloService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HelloController.class)
public class HelloControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    HelloService helloService;

    @Test
    void 회원저장() throws Exception{
        given(helloService.saveMember(new Member("kim", 30)))
                .willReturn("ok"); // stubbing
        Member member = new Member("kim", 30);
        String content = new ObjectMapper().writeValueAsString(member);

        mockMvc.perform(
                post("/hello/member2")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON
        ))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("ok"));
    }

    @Test
    void 회원조회() throws Exception{
        Long memberId = 1L;
        given(helloService.findById(memberId))
                .willReturn(new Member(memberId,"kim",30));
        mockMvc.perform(get("/hello/member/"+memberId))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                        .andExpect(jsonPath("$.name").exists())
                        .andExpect(jsonPath("$.age").exists())
                        .andDo(print());    //verify: 해당 객체의 메소드가 실행되었는지 체크해줌
        verify(helloService).findById(memberId);
    }

}
