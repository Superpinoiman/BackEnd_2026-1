package com.example.demo;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMember(int id) {
        return memberRepository.findById(id).orElse(null);
    }

    public Member createMember(MemberRequest request) {
        Member member = new Member(
                memberRepository.nextId(),
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return memberRepository.save(member);
    }

    public Member updateMember(int id, MemberRequest request) {
        Member oldMember = memberRepository.findById(id).orElse(null);

        if (oldMember == null) {
            return null;
        }

        Member updatedMember = new Member(
                oldMember.getId(),
                request.getName(),
                request.getEmail(),
                request.getPassword()
        );

        return memberRepository.save(updatedMember);
    }

    public Member deleteMember(int id) {
        Member member = memberRepository.findById(id).orElse(null);

        if (member == null) {
            return null;
        }

        memberRepository.delete(id);
        return member;
    }
}
