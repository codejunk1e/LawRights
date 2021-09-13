package io.github.codejunk1e.lawrights.ui.home

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.codejunk1e.lawrights.models.Result
import io.github.codejunk1e.lawrights.datasource.network.requests.LoginRequestBodyDTO
import io.github.codejunk1e.lawrights.datasource.network.responses.LoginResponseBodyDTO
import io.github.codejunk1e.lawrights.datasource.Repository
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    val repository : Repository
) : ViewModel() {

    val loginState = MutableLiveData<Result<LoginResponseBodyDTO>>()

    fun loginUser() {
        viewModelScope.launch(provideExceptionHandler(loginState)) {
            loginState.postValue(Result.Loading)
            val result = repository.loginUser(LoginRequestBodyDTO(
                "mobileinterview@lawpavilion.com",
                "mobileinterview"
            ))
            loginState.postValue(Result.Success(result))
            repository.fetchRights()
        }
    }

    fun getAllRights()= repository.getAllRights()
    fun fetchAllRights(){
        viewModelScope.launch(provideExceptionHandler(loginState)) {
            repository.fetchRights()
        }
    }
    private fun reautenticateAndFetch() {
        viewModelScope.launch {
            loginUser()
            fetchAllRights()
        }
    }


    private fun <T> provideExceptionHandler(liveData : MutableLiveData<Result<T>> ): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, throwable ->
            var message = throwable.message.toString()
            if (throwable is UnknownHostException){
                message = "Please check data!"
            }else if (throwable is HttpException && throwable.code() == 401){
                reautenticateAndFetch()
                message = "Loading..."
            }
            liveData.postValue(Result.Error(message))
        }
    }
}