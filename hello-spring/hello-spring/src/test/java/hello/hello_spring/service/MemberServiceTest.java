package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class MemberServiceTest {
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    public void 회원가입() throws Exception{

        // Given - 상황
        Member member = new Member();
        member.setName("Hello");

        // When - 실행
        Long saveId = memberService.join(member);

        // Then - 결과
        Member findMember = memberRepository.findById(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        // Given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");


        // When
        memberService.join(member1);
        /*
        memberService.join(member2); // 예외 발생. try-catch 문

        try{
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }

        */

        // try-catch 대신 assertThrows 사용
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));


        // then
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    }


}
