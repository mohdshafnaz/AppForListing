package com.example.appforlisting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appforlisting.models.response.ResponseModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch


class MainViewModel() : ViewModel() {

    private val mainChannelFLow = Channel<MainEvents>()
    val listEvent = mainChannelFLow.receiveAsFlow()

    fun getList() = viewModelScope.launch {
        showProgress(true)
        val repository = MainRepository()
        val response = repository.getInitiativeList()
        if (response == null) {
            sendErrorMessage("Error")
        } else {
            if (response != null) {
                mainChannelFLow.send(MainEvents.GetList(response))

            } else {
                sendErrorMessage("Empty List")
            }
        }
        showProgress(false)
    }

    private fun showProgress(progress: Boolean) = viewModelScope.launch {
        mainChannelFLow.send(MainEvents.ShowProgress(progress))
    }

    private fun sendErrorMessage(message: String) = viewModelScope.launch {
        mainChannelFLow.send(MainEvents.SendErrorMessage(message))
    }

    fun setProgress(progress: Boolean) = viewModelScope.launch {
        mainChannelFLow.send(MainEvents.ShowProgress(progress))
    }

    sealed class MainEvents {
        data class ShowProgress(val progress: Boolean) : MainEvents()
        data class GetList(val data: ResponseModel) : MainEvents()
        data class SendErrorMessage(val message: String) : MainEvents()

    }
}