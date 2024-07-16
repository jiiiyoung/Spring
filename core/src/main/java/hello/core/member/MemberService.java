
package hello.core.member;

public interface MemberService {
    // 회원 가입
    void join(Member member);

    // 회원 조회(회원 아이디를 받아서 멤버를 보내줘야함)
    Member findMember(Long memberId);
}