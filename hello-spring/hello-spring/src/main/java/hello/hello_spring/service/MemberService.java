package hello.hello_spring.service;

import hello.hello_spring.domain.Member;
import hello.hello_spring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


// JPA를 쓰기 위해서 데이터를 저장, 변경할 때 Transactional이 필요하다.
//@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    };

    // 회원가입
    public Long join(Member member){

        long start = System.currentTimeMillis();

        // 같은 이름이 있는 경우 가입X
        try {
            vaildateDuplicateMember(member);
            memberRepository.save(member);
           return member.getId(); // 가입하면 Id를 반환한다.
        } finally{ // try-finally 쌍은 예외가 발생하든 안하든 반드시 실행시켜야 하는 경우에 작성하는 코드이다.(ex) 실행 후 서버를 종료시켜야 하는 경우 등
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("Join " + timeMs + "ms");
        }

    }

    private void vaildateDuplicateMember(Member member){

        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { // member에 값이 있으면 이미 같은 이름이 있는 회원이 있다는 것
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */

        // ifPresent는 memberRepository.findByName(member.getName())의 반환 값이 옵셔널 형태이기 있기 때문에 가능 한 것임
        memberRepository.findByName(member.getName()).ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다."); // 예외 던지기
        });
    }

    // 전체 회원 조회
    public List<Member> findMembers(){
// 해당 주석 기능들은 AOP로 구현함(공통관심사이기 때문에 핵심 관심 기능만 남겨둠)
//        long start  = System.currentTimeMillis();
//
//        try{
            return memberRepository.findAll();
//        } finally {
//            long finish = System.currentTimeMillis();
//            long timeMs = finish - start;
//            System.out.println("findMembers" + timeMs + "ms");
//        }
    }

    // Id로 조회
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
