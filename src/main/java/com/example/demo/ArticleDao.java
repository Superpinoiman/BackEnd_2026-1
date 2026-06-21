package com.example.demo;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class ArticleDao {

    private final JdbcTemplate jdbcTemplate;

    public ArticleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Article> rowMapper = (rs, rowNum) -> {
        Article article = new Article();
        article.setId(rs.getLong("id"));
        article.setAuthorId(rs.getLong("author_id"));
        article.setBoardId(rs.getLong("board_id"));
        article.setTitle(rs.getString("title"));
        article.setContent(rs.getString("content"));
        article.setCreatedTime(rs.getTimestamp("created_date").toLocalDateTime());
        article.setUpdatedTime(rs.getTimestamp("modified_date").toLocalDateTime());
        return article;
    };

    public Long insert(ArticleCreateRequest request) {
        String sql = "insert into article (author_id, board_id, title, content) values (?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, request.getAuthorId());
            ps.setLong(2, request.getBoardId());
            ps.setString(3, request.getTitle());
            ps.setString(4, request.getContent());
            return ps;
        }, keyHolder);

        return keyHolder.getKey().longValue();
    }

    public Optional<Article> findById(Long id) {
        String sql = "select * from article where id = ?";
        List<Article> result = jdbcTemplate.query(sql, rowMapper, id);
        return result.stream().findFirst();
    }

    public List<Article> findAll() {
        String sql = "select * from article order by id desc";
        return jdbcTemplate.query(sql, rowMapper);
    }

    public List<Article> findByBoardId(Long boardId) {
        String sql = "select * from article where board_id = ? order by id desc";
        return jdbcTemplate.query(sql, rowMapper, boardId);
    }

    public int update(Long id, ArticleUpdateRequest request) {
        String sql = "update article set board_id = ?, title = ?, content = ? where id = ?";
        return jdbcTemplate.update(sql, request.getBoardId(), request.getTitle(), request.getContent(), id);
    }

    public int deleteById(Long id) {
        String sql = "delete from article where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public boolean existsByAuthorId(Long authorId) {
        String sql = "select count(*) from article where author_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, authorId);
        return count != null && count > 0;
    }

    public boolean existsByBoardId(Long boardId) {
        String sql = "select count(*) from article where board_id = ?";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, boardId);
        return count != null && count > 0;
    }
}
