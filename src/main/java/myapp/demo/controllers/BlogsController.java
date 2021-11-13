package myapp.demo.controllers;

import myapp.demo.dao.BlogDao;
import myapp.demo.models.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.lang.String;

@Controller
@RequestMapping("/")
public class BlogsController {
    private final BlogDao blogDao;
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

    @ResponseBody
    @GetMapping("/{id}/edit")
    public Blog getBlog(@PathVariable("id") int id) {
        return blogDao.getBlogWithID(id);
    }

    @GetMapping("/{id}")
    public String getEditPage(@PathVariable("id") int id, Model model) {
        model.addAttribute("id", id);
        return "blogs/edit";
    }

    @ResponseBody
    @PutMapping("/{id}/edit")
    public List<String> editBlog(@RequestBody Blog blog, @PathVariable("id") int id) {
        String result;
        try {
            blog.setId(id);
            blogDao.editBlog(blog);
            result = "success";
        }
        catch(DataAccessException e) {
            result = "error";
        }
        return List.of(result);
    }

    @ResponseBody
    @DeleteMapping("{id}/delete")
    public List<String> deleteBlog(@PathVariable("id") int id) {
        String result;
        try {
            blogDao.deleteBlogWithId(id);
            result = "success";
        }
        catch(DataAccessException e) {
            result = "error";
        }
        return List.of(result);
    }
}
