package hello.core.member;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

// AppConfig에서 bean등록을 해주었는데, AutoAppConfig로 바꾸면서
@Component
public class MemoryMemberRepository implements MemberRepository {

    // ConcurrentHashMap : 동시성 이슈 해결을 위해 이걸 써야하지만 간단한 예제니 HashMap 사용
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
