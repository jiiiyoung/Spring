package hello.core.member.repository;

import hello.core.member.entity.Member;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository {

//    private static Map<Long, Member> members = new HashMap<>();

    private static Map<Long, Member> members = new ConcurrentHashMap<>();

    /*
    HashMap과 ConcurrentHashMap의 차이
     HashMap은 여러 스레드가 동시에 액세스하고 하나가 수정하면 ConcurrentModificationException을 발생시킬 수 있습니다.
     ConcurrentHashMap은 여러 스레드에서 동시에 액세스 하도록 설계되어 있어, 동시성 문제를 해결할 수 있습니다.
     ConcurrentHashMap은 세그먼트기반잠금 또는 잠금 스트라이핑을 사용합니다. 따라서 다중 스레드 환경에서 더 높은 동시성과 더 나은 성능을 허용합니다.
    */

    @Override
    public void save(Member member) {
        members.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return members.get(memberId);
    }
}
