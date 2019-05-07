package com.dasbikash.news_server_spring_mvc_rest_datasource.services

import com.dasbikash.news_server_spring_mvc_rest_datasource.exceptions.DataNotFoundException
import com.dasbikash.news_server_spring_mvc_rest_datasource.model.database.log_entities.ArticleDownloadLog
import com.dasbikash.news_server_spring_mvc_rest_datasource.repositories.ArticleDownloadLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleDownloadLogService @Autowired constructor(val articleDownloadLogRepository: ArticleDownloadLogRepository){
    fun getLatestArticleDownloadLogs(pageSize: Int): List<ArticleDownloadLog> {
        return articleDownloadLogRepository.getLatestArticleDownloadLogs(pageSize)
    }

    fun getArticleDownloadLogsBeforeGivenId(lastArticleDownloadLogId: Int, pageSize: Int): List<ArticleDownloadLog> {
        val lastArticleDownloadLog = articleDownloadLogRepository.findById(lastArticleDownloadLogId)
        if (!lastArticleDownloadLog.isPresent){
            throw DataNotFoundException()
        }
        return articleDownloadLogRepository.getArticleDownloadLogsBeforeGivenId(lastArticleDownloadLog.get().id!!,pageSize)
    }
}