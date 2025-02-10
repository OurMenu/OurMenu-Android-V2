package com.kuit.ourmenu.dummy.repository

import com.kuit.ourmenu.dummy.data.DummyData
import kotlinx.coroutines.flow.Flow

interface DummyRepository {
    fun getDummyData(): Flow<DummyData>
}