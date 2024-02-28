package com.bigevent.service.impl;

import com.bigevent.mapper.ArticleMapper;
import com.bigevent.pojo.Article;
import com.bigevent.pojo.PageBean;
import com.bigevent.service.ArticleService;
import com.bigevent.utils.ThreadLocalUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("id"));
        articleMapper.add(article);
    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        PageBean<Article> pageBean = new PageBean<>();

        Map<String,Object> map = ThreadLocalUtil.get();
        PageHelper.startPage(pageNum,pageSize);
        List<Article> articles = articleMapper.list((Integer)map.get("id"),categoryId,state);
        Page<Article> pages = (Page<Article>) articles;
        pageBean.setTotal(pages.getTotal());
        pageBean.setItems(pages.getResult());
        return pageBean;
    }

    @Override
    public void edit(Article article) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        try {
            String s = objectMapper.writeValueAsString(article);
            System.out.println(s);
        } catch (JsonProcessingException e) {
            System.out.println(e.toString());
        }
        articleMapper.edit(article);
    }

    @Override
    public void delete(Article article) {
        articleMapper.delete(article.getId());
    }
}