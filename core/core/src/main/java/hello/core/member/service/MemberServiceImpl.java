package hello.core.member.service;

import hello.core.member.entity.Member;
import hello.core.member.repository.MemberRepository;
import hello.core.member.repository.MemoryMemberRepository;

public class MemberServiceImpl implements MemberService {
/*
    // ��ü���� ��Ģ�� ����ȴ�.
    private final MemberRepository memberRepository = new MemoryMemberRepository();
*/

    private MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
