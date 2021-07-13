package ae.tutorialapp.weather.ui

import ae.tutorialapp.weather.repository.WeatherRepo
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

class MainViewModel(private val  repo: WeatherRepo): ViewModel() {
    private val compositeDisposable = CompositeDisposable()

    private val isLoading = MutableLiveData<Boolean>()
    val _isLoading: LiveData<Boolean>
    get() = isLoading


    fun getWeatherFromApi(){
        isLoading.value = true
        compositeDisposable.add(
            repo.getWeatherFromApi()
                .doOnTerminate{ isLoading.value = false}
                .subscribe({}, {})
        )
    }

    fun getForeCastAsLive() = repo.getForeCastFromDbAsLive()

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}