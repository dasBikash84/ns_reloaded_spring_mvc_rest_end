package com.dasbikash.news_server_spring_mvc_rest_datasource.utills

import com.dasbikash.news_server_spring_mvc_rest_datasource.exceptions.DataNotFoundException
import com.dasbikash.news_server_spring_mvc_rest_datasource.model.database.DataCoordinatorRestEntity
import org.springframework.http.ResponseEntity

object RestControllerUtills {
    fun <T : DataCoordinatorRestEntity> listEntityToResponseEntity(entityList: List<T>): ResponseEntity<List<T>> {
        if (entityList.isEmpty()) {
            throw DataNotFoundException()
        }
        return ResponseEntity.ok(entityList)
    }

    fun <T : DataCoordinatorRestEntity> entityToResponseEntity(entity: T?): ResponseEntity<T> {
        if (entity == null) {
            throw DataNotFoundException()
        }
        return ResponseEntity.ok(entity)
    }
}