package myapp.demo.dao;

import myapp.demo.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BlogDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BlogDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Blog> getBlogs() {
        return jdbcTemplate.query("select * from blogs", new BeanPropertyRowMapper<>(Blog.class));
    }

    public void addBlog(Blog blog) {
        jdbcTemplate.update("insert into blogs values (default, ?, ?, ?)", blog.getHeader(),
                blog.getBody(), blog.getDescription());
    }
}
