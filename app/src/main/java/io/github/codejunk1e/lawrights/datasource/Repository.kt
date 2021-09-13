package io.github.codejunk1e.lawrights.datasource

import io.github.codejunk1e.lawrights.datasource.local.RightsDao
import io.github.codejunk1e.lawrights.datasource.mappers.toRightsEntity
import io.github.codejunk1e.lawrights.datasource.network.LawRightsService
import io.github.codejunk1e.lawrights.datasource.network.SessionManager
import io.github.codejunk1e.lawrights.datasource.network.requests.LoginRequestBodyDTO
import io.github.codejunk1e.lawrights.datasource.network.responses.LoginResponseBodyDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class Repository @Inject constructor(
    private val dispatcher: CoroutineDispatcher
) {

    @Inject lateinit var lawRightsService : LawRightsService
    @Inject lateinit var dao: RightsDao
    @Inject lateinit var sessionManager: SessionManager

    suspend fun loginUser(loginRequestBody: LoginRequestBodyDTO): LoginResponseBodyDTO {
        return withContext(dispatcher){
            val response = lawRightsService.loginUser( loginRequestBody )
            sessionManager.saveAuthToken(response.access_token)
            response
        }
    }

    fun getAllRights() = dao.getAllRights()

    suspend fun fetchRights() {
        withContext(dispatcher){
            val response = lawRightsService.getAllRights()
            dao.insertAll(response.data.data.toRightsEntity())
        }
    }
}
