package com.example.demo.service;

import com.example.demo.repository.ArticleRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.domain.Member;
import com.example.demo.dto.MemberRequest;
import com.example.demo.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final ArticleRepository articleRepository;

    public MemberService(MemberRepository memberRepository, ArticleRepository articleRepository) {
        this.memberRepository = memberRepository;
        this.articleRepository = articleRepository;
    }

    @Transactional
    public Member createMember(MemberRequest request) {
        Member foundMember = memberRepository.findByEmail(request.getEmail());
        if (foundMember != null) {
            throw new ApiException(HttpStatus.CONFLICT);
        }

        Member member = new Member(request.getName(), request.getEmail(), request.getPassword());
        return memberRepository.save(member);
    }

    public Member getMember(Long id) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }
        return member;
    }

    public List<Member> getMembers() {
        return memberRepository.findAll();
    }

    @Transactional
    public Member updateMember(Long id, MemberRequest request) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        Member foundMember = memberRepository.findByEmail(request.getEmail());
        if (foundMember != null && !foundMember.getId().equals(id)) {
            throw new ApiException(HttpStatus.CONFLICT);
        }

        member.update(request.getName(), request.getEmail(), request.getPassword());
        return member;
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        if (articleRepository.existsByAuthorId(id)) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        memberRepository.delete(member);
    }
}
