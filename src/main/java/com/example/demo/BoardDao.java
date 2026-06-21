package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class BoardDao {

    private final JdbcTemplate jdbcTemplate;

    public BoardDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Optional<Board> findById(Long id) {
        String sql = "select * from board where id = ?";
        List<Board> result = jdbcTemplate.query(sql, (rs, rowNum) ->
                new Board(
                        rs.getLong("id"),
                        rs.getString("name")
                ), id);

        return result.stream().findFirst();
    }

    public List<Board> findAll() {
        String sql = "select * from board order by id asc";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                new Board(
                        rs.getLong("id"),
                        rs.getString("name")
                ));
    }

    public Long insert(BoardRequest request) {
        String sql = "insert into board (name) values (?)";

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, request.getName());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public int update(Long id, BoardRequest request) {
        String sql = "update board set name = ? where id = ?";
        return jdbcTemplate.update(sql, request.getName(), id);
    }

    public int deleteById(Long id) {
        String sql = "delete from board where id = ?";
        return jdbcTemplate.update(sql, id);
    }
}
