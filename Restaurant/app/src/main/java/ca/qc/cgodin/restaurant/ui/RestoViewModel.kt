package ca.qc.cgodin.restaurant.ui


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ca.qc.cgodin.restaurant.RoomDatabase.*
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.repository.RestaurantRepository
import kotlinx.coroutines.launch
import ca.qc.cgodin.restaurant.modeleSearchZomato.SearchInfo
import kotlinx.coroutines.Dispatchers


class RestoViewModel(private val  restaurantRepository: RestaurantRepository)  : ViewModel() {

    val resto: MutableLiveData<NearbySearch> = MutableLiveData()

    val details: MutableLiveData<DetailResto> = MutableLiveData()

    val searchVal: MutableLiveData<SearchInfo> = MutableLiveData()
    //private val repository : UserRepository

    val allUsers: LiveData<List<User>>

    val allFavoris: LiveData<List<Favoris>>

    init{
        //val userDao : UserDao = UserRoomDatabase.getDatabase(application).userDao()

        allUsers = restaurantRepository.allUsers
        allFavoris = restaurantRepository.allFavoris
        getRestoNearby("1500")
        getMenu("1500")
    }

    /*fun getUserIdByEmail(email: String) = viewModelScope.launch((Dispatchers.IO)){
        restaurantRepository.getUserIdByEmail(email)
    }*/

    fun insert(user: User) = viewModelScope.launch(Dispatchers.IO){
        restaurantRepository.insert(user)
    }
    fun insertFavoris(favoris: Favoris) = viewModelScope.launch(Dispatchers.IO){
        restaurantRepository.insertFavoris(favoris)
    }
    fun getFavoris() = restaurantRepository.getFavoris()

    fun insertUserFavorisCrossRef(userFavorisCrossRef: UserFavorisCrossRef) = viewModelScope.launch(
        Dispatchers.IO){
        restaurantRepository.insertUserFavorisCrossRef(userFavorisCrossRef)
    }

    fun searchUser(email:String) : LiveData<List<User>> {

        return restaurantRepository.searchUser(email)
    }


    fun getRestoNearby(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getNearbyRestaurants(rayon)

        resto.postValue(response.body())
    }

    fun getDetailsResto(res_id: Int) = viewModelScope.launch {
        val response = restaurantRepository.getDetailsRestaurants(res_id)
        details.postValue(response.body())

    }

    fun getMenu(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getMenu(rayon)
        searchVal.postValue(response.body())
    }
}

