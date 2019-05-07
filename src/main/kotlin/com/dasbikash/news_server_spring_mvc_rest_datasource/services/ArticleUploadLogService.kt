package com.dasbikash.news_server_spring_mvc_rest_datasource.services

import com.dasbikash.news_server_spring_mvc_rest_datasource.exceptions.DataNotFoundException
import com.dasbikash.news_server_spring_mvc_rest_datasource.model.database.log_entities.ArticleUploadLog
import com.dasbikash.news_server_spring_mvc_rest_datasource.repositories.ArticleUploadLogRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ArticleUploadLogService @Autowired constructor(val articleUploadLogRepository: ArticleUploadLogRepository){

    fun getLatestArticleUploadLogs(pageSize: Int): List<ArticleUploadLog> {
        return articleUploadLogRepository.getLatestArticleUploadLogs(pageSize)
    }

    fun getArticleUploadLogsBeforeGivenId(lastArticleUploadLogId: Int, pageSize: Int): List<ArticleUploadLog> {
        val lastArticleUploadLog = articleUploadLogRepository.findById(lastArticleUploadLogId)
        if (!lastArticleUploadLog.isPresent){
            throw DataNotFoundException()
        }
        return articleUploadLogRepository.getSettingsUpdateLogsBeforeGivenId(lastArticleUploadLog.get().id!!,pageSize)
    }
}