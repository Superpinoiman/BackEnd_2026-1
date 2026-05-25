package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {
    private final Map<Integer, Member> memberMap = new HashMap<>();

    public MemberRepository() {
        memberMap.put(1, new Member(1, "장지훈", "jjh5025d@test.com", "kkkk123"));
        memberMap.put(2, new Member(2, "홍길동", "hong@test.com", "1234"));
        memberMap.put(3, new Member(3, "김길환", "killhwan@test.com", "8888"));

    }

    public Optional<Member> findById(int id) {
        return Optional.ofNullable(memberMap.get(id));
    }
}
