package net.matrixhome.notificationapp.ui

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import net.matrixhome.notificationapp.api.PageRoomApi
import net.matrixhome.notificationapp.model.PageRoom
import java.lang.IllegalArgumentException

class MainViewModel(private val repository: PageRoomApi) : ViewModel() {

    val pages: LiveData<MutableList<PageRoom>> = repository.allPages.asLiveData()

    fun insert() = viewModelScope.launch {
        repository.insert(PageRoom(pages.value!!.size, false))
    }

    fun delete() = viewModelScope.launch {
        if (pages.value!!.size > 1)
        repository.delete(PageRoom(pages.value!!.size - 1))
    }
}


class MainViewModelFactory(private val repo: PageRoomApi): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(repo) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel Class")
    }
}





