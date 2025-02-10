package com.kuit.ourmenu.dummy.repository

import android.util.Log
import com.kuit.ourmenu.dummy.data.DummyData
import com.kuit.ourmenu.dummy.data.toDummyData
import com.kuit.ourmenu.dummy.service.DummyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

class DummyRepository @Inject constructor(
    private val dummyService: DummyService
) {
    fun getDummyData(): Flow<DummyData> = flow {
        try {
            val response = dummyService.getDummyData()
            emit(response.toDummyData())
        } catch (e: Exception) {
            Log.e("DummyRepository", "getDummyData: $e")
        }
    }.flowOn(Dispatchers.IO)
}