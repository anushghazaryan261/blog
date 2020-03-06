package com.example.demo.controller;


import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        articleService.createTable();
        this.articleService = articleService;
    }


    @PostMapping(value = "/article")
    public void create(@RequestBody Article article){
        if (articleService.createArticle(article)==1) {
            System.out.println("created");
        }else{
            System.out.println("something went wrong");
        }
    }

    @PutMapping(value = "/article/{id}")
    public void update(@RequestBody Article article, @PathVariable int id){
        if (articleService.updateArticle(article,id)==1) {
            System.out.println("updated");
        }else{
            System.out.println("something went wrong");
        }
    }

    @DeleteMapping(value = "/article/{id}")
    public void delete(@PathVariable int id){
        System.out.println("Deleting article with id " + id);
        articleService.deleteArticle(id);
    }

    @GetMapping(value = "/article/{id}")
    public ResponseEntity<Article> findById(@PathVariable int id){
        Article article=articleService.findArticleById(id);
        return ResponseEntity.ok(article);
    }

    @GetMapping(value = "/article")
    public ResponseEntity<List<Article>> findAll(){
        List<Article> articles=articleService.findAll();
        return ResponseEntity.ok(articles);
    }
}
