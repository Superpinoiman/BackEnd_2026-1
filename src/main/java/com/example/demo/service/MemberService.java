package com.example.demo.service;

import com.example.demo.dao.ArticleDao;
import com.example.demo.dao.MemberDao;
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

    private final MemberDao memberDao;
    private final ArticleDao articleDao;

    public MemberService(MemberDao memberDao, ArticleDao articleDao) {
        this.memberDao = memberDao;
        this.articleDao = articleDao;
    }

    @Transactional
    public Member createMember(MemberRequest request) {
        Member foundMember = memberDao.findByEmail(request.getEmail());
        if (foundMember != null) {
            throw new ApiException(HttpStatus.CONFLICT);
        }

        Member member = new Member(request.getName(), request.getEmail(), request.getPassword());
        return memberDao.save(member);
    }

    public Member getMember(Long id) {
        Member member = memberDao.findById(id);
        if (member == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }
        return member;
    }

    public List<Member> getMembers() {
        return memberDao.findAll();
    }

    @Transactional
    public Member updateMember(Long id, MemberRequest request) {
        Member member = memberDao.findById(id);
        if (member == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        Member foundMember = memberDao.findByEmail(request.getEmail());
        if (foundMember != null && !foundMember.getId().equals(id)) {
            throw new ApiException(HttpStatus.CONFLICT);
        }

        member.update(request.getName(), request.getEmail(), request.getPassword());
        return member;
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberDao.findById(id);
        if (member == null) {
            throw new ApiException(HttpStatus.NOT_FOUND);
        }

        if (articleDao.existsByAuthorId(id)) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        memberDao.delete(member);
    }
}
