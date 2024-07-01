package hello.hello_spring.controller;

import hello.hello_spring.domain.Member;
import hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    // private final MemberService memberService = new MemberService();
    // 주문, 멤버 등 다양한 곳에서 쓰이는데 그 곳에서 쓰일때마다 생성하는것은 비효율 적이므로
    // 한번만 생성하고 그것을 가져다가 쓰면 좋다.
    private final MemberService memberService;


    // @Autowired가 있으면 스프링이 연관된 객체를 스프링 컨테이너에서 찾아서 넣어준다.(DI; 의존성 주입)
    // 서비스, 레포지토리 등의 어노테이션을 보고 자동으로 찾는 것임.
//    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    // 처음 url들어 왔을 때, 폼 띄워주기 위해서
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // submit이 동작할 때 들어올 것
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);

        return "members/memberList";
    }
}
