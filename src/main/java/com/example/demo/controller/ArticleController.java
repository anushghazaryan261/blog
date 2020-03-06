package com.example.demo.controller;


import com.example.demo.model.Article;
import com.example.demo.service.ArticleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public void findById(@PathVariable int id){
        if (articleService.findArticleById(id)!=null) {
            System.out.println("getting by id " + id);
            System.out.println(articleService.findArticleById(id));
        }else{
            System.out.println("Something went wrong");
        }
    }

    @GetMapping(value = "/article")
    public void findAll(){
        System.out.println("All users");
        articleService.findAll().forEach(System.out::println);
    }
}
