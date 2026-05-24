package com.example.demo;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemberRepository {
    private final Map<Integer, Member> memberMap = new HashMap<>();

    public Member save(Member member) {
        memberMap.put(member.getId(), member);
        return member;
    }

    public Optional<Member> findById(int id) {
        return Optional.ofNullable(memberMap.get(id));
    }
}
