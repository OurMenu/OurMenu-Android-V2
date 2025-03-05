package com.kuit.ourmenu.dummy.repositoryimpl

import android.util.Log
import com.kuit.ourmenu.dummy.data.DummyData
import com.kuit.ourmenu.dummy.data.toDummyData
import com.kuit.ourmenu.dummy.repository.DummyRepository
import com.kuit.ourmenu.dummy.service.DummyService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DummyRepositoryImpl @Inject constructor(
    private val dummyService: DummyService
): DummyRepository {
    override fun getDummyData(): Flow<DummyData> = flow {
        try {
            val response = dummyService.getDummyData()
            emit(response.toDummyData())
        } catch (e: Exception) {
            Log.e("DummyRepository", "getDummyData: $e")
        }
    }.flowOn(Dispatchers.IO)
}