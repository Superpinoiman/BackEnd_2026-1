package com.example.demo;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MemberService {

    private final MemberDao memberDao;
    private final ArticleDao articleDao;

    public MemberService(MemberDao memberDao, ArticleDao articleDao) {
        this.memberDao = memberDao;
        this.articleDao = articleDao;
    }

    public List<Member> getAllMembers() {
        return memberDao.findAll();
    }

    public Member getMember(Long id) {
        return memberDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Member createMember(MemberRequest request) {
        if (memberDao.existsByEmail(request.getEmail())) {
            throw new ApiException(HttpStatus.CONFLICT);
        }

        Long id = memberDao.insert(request);

        return memberDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Member updateMember(Long id, MemberRequest request) {
        memberDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        if (memberDao.existsByEmailAndIdNot(request.getEmail(), id)) {
            throw new ApiException(HttpStatus.CONFLICT);
        }

        memberDao.update(id, request);

        return memberDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));
    }

    @Transactional
    public Member deleteMember(Long id) {
        Member member = memberDao.findById(id)
                .orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND));

        if (articleDao.existsByAuthorId(id)) {
            throw new ApiException(HttpStatus.BAD_REQUEST);
        }

        memberDao.deleteById(id);
        return member;
    }
}
