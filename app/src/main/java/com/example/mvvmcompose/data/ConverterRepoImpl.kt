package com.example.mvvmcompose.data

import kotlinx.coroutines.flow.Flow

class ConverterRepoImpl(private val dao: ConverterDAO) : ConverterRepository {
    override suspend fun insertResult(result: ConversionResult) {
        dao.insertResult(result)
    }

    override suspend fun deleteResult(result: ConversionResult) {
        dao.deleteResult(result)
    }

    override suspend fun deleteAllResult() {
        dao.deleteAll()
    }

    override fun getSavedResult(): Flow<List<ConversionResult>> {
        return dao.getResults()
    }
}