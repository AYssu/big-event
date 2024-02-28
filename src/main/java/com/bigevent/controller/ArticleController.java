package com.bigevent.controller;

import com.bigevent.pojo.Article;
import com.bigevent.pojo.PageBean;
import com.bigevent.pojo.Result;
import com.bigevent.service.ArticleService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @GetMapping("/list")
    public Result<String> list(@RequestHeader(name = "Authorization") String token , HttpServletResponse response)
    {
        return Result.success("列表数据...");
    }

    @PostMapping
    public Result add(@RequestBody @Validated Article article)
    {
        System.out.println("添加文章");
        articleService.add(article);
        return Result.success();
    }

    @PostMapping("/edit")
    public Result edit(@RequestBody @Validated Article article)
    {
        articleService.edit(article);
        return Result.success();
    }
    @GetMapping
    public Result<PageBean<Article>> list(Integer pageNum,Integer pageSize,@RequestParam(required = false) Integer categoryId,@RequestParam(required = false)String state)
    {
        PageBean<Article> pageBean = articleService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pageBean);
    }
    @PostMapping("/delete")
    public Result delete(@RequestBody @Validated Article article)
    {
        articleService.delete(article);
        return Result.success();
    }

}
