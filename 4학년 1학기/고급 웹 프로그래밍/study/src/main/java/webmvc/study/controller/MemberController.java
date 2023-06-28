package webmvc.study.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import webmvc.study.domain.Member;
import webmvc.study.persistance.MemberRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("member")
@AllArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping("/new")
    public String newMember(){
        return "new-form";
    }

    @PostMapping(value = "/add")
    public String addMember(@ModelAttribute Member member, Model model) {
        memberRepository.save(member);
        model.addAttribute("member", member);
        return "add-result";
    }

    @RequestMapping("/all")
    public ModelAndView allMember() {
        List<Member> members = memberRepository.findAll();
        ModelAndView mv = new ModelAndView("member-list");
        mv.addObject("members", members);
        return mv;
    }

    @GetMapping("/api/{id}")
    public String getMemberById(@PathVariable("id") Long id) {
        return memberRepository.findById(id).getName();
    }

    @PostMapping("/request")
    public String requestBodyHandler(@RequestBody Member member) {
        logger.info("member={}", member);
        logger.info("age={}", member.getAge());
        return "ok";
    }

    @PostMapping("/responsebody2")
    public  Member requestBodyHandler2(@RequestBody Member member) {
        System.out.println("member.getName() = " + member.getName());
        System.out.println("member.getName() = " + member.getAge());
        return member;
    }
}
