package myapp.demo.controllers;

import myapp.demo.dao.BlogDao;
import myapp.demo.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/")
public class BlogsController {
    private BlogDao blogDao;
    @Autowired
    BlogsController(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    @GetMapping("/")
    public String homePage() {
        return "blogs/index";
    }

    @ResponseBody()
    @GetMapping("/get/blogs")
    public List<Blog> getListBlogs() {
        return blogDao.getBlogs();
    }

    @GetMapping("/add")
    public String addPage() {
        return "blogs/add";
    }

    @ResponseBody()
    @PostMapping("/add/blog")
    public List<String> addBlog(@RequestBody Blog blog) {
        String result;
        try {
            blogDao.addBlog(blog);
            result = "success";
        }
        catch(DataAccessException e) {
            result = "error";
        }

        return List.of(result);
    }
}
