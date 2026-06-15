package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public MemberService(MemberRepository memberRepository, ArticleRepository articleRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
    }

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMember(int id) {
        return memberRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
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
        Member oldMember = memberRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        if (memberRepository.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new ApiException(HttpStatus.CONFLICT);
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
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        if (articleRepository.existsByAuthorId(id)) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        memberRepository.delete(id);
        return member;
    }
}
