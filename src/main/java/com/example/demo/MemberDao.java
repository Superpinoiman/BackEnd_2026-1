package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class MemberDao {

    private final JdbcTemplate jdbcTemplate;

    public MemberDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Member> findById(Long id) {
        String sql = "select * from member where id = ?";
        List<Member> result = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Member(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                ), id);

        return result.stream().findFirst();
    }

    public List<Member> findAll() {
        String sql = "select * from member order by id asc";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Member(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password")
                ));
    }

    public Long insert(MemberRequest request) {
        String sql = "insert into member (name, email, password) values (?, ?, ?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, request.getName());
            ps.setString(2, request.getEmail());
            ps.setString(3, request.getPassword());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public int update(Long id, MemberRequest request) {
        String sql = "update member set name = ?, email = ?, password = ? where id = ?";
        return jdbcTemplate.update(sql, request.getName(), request.getEmail(), request.getPassword(), id);
    }

    public int deleteById(Long id) {
        String sql = "delete from member where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public boolean existsByEmailAndIdNot(String email, Long id) {
        String sql = "select count(*) from member where email = ? and id <> ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email, id);
        return count != null && count > 0;
    }

    public boolean existsByEmail(String email) {
        String sql = "select count(*) from member where email = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, email);
        return count != null && count > 0;
    }
}
