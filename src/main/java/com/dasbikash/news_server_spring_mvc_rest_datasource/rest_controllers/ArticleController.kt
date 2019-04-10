package com.dasbikash.news_server_spring_mvc_rest_datasource.rest_controllers

import com.dasbikash.news_server_spring_mvc_rest_datasource.model.database.Article
import com.dasbikash.news_server_spring_mvc_rest_datasource.services.ArticleService
import com.dasbikash.news_server_spring_mvc_rest_datasource.utills.RestControllerUtills
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("articles")
class ArticleController @Autowired
constructor(val articleService: ArticleService) {

    @Value("\${article.default_page_size}")
    var defaultPageSize: Int = 5

    @Value("\${article.max_page_size}")
    var maxPageSize: Int = 10

    @GetMapping("/page-id/{pageId}")
    fun getLatestArticlesForPage(@PathVariable pageId: String,@RequestParam("article_count") articleCount:Int?): ResponseEntity<List<Article>> {

        var pageSize = defaultPageSize

        articleCount?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                else -> pageSize = it
            }
        }
        return RestControllerUtills.listEntityToResponseEntity(articleService.getLatestArticlesForPage(pageId,pageSize))
    }

    @GetMapping("/page-id/{pageId}/last-article-id/{lastArticleId}")
    fun getArticlesForPageAfterArticle
            (@PathVariable pageId: String,@PathVariable lastArticleId: String,@RequestParam("article_count") articleCount:Int?): ResponseEntity<List<Article>> {

        var pageSize = defaultPageSize

        articleCount?.let {
            when{
                it>=maxPageSize -> pageSize = maxPageSize
                else -> pageSize = it
            }
        }
        return RestControllerUtills.listEntityToResponseEntity(articleService.getArticlesForPageAfterLast(pageId,pageSize,lastArticleId))
    }
}