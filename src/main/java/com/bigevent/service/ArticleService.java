package com.bigevent.service;

import com.bigevent.pojo.Article;
import com.bigevent.pojo.PageBean;

public interface ArticleService {

    void add(Article article);

    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryid, String sate);


    void edit(Article article);

    void delete(Article article);
}
