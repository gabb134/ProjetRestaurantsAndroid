package ca.qc.cgodin.restaurant.ui


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ca.qc.cgodin.restaurant.RoomDatabase.*
import ca.qc.cgodin.restaurant.modeleDetailsZomato.DetailResto
import ca.qc.cgodin.restaurant.modeleMenu.Menu
import ca.qc.cgodin.restaurant.modeleSearch.NearbySearch
import ca.qc.cgodin.restaurant.repository.RestaurantRepository
import kotlinx.coroutines.launch
import ca.qc.cgodin.restaurant.modeleSearchZomato.SearchInfo
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope


class RestoViewModel(private val  restaurantRepository: RestaurantRepository)  : ViewModel() {

    val resto: MutableLiveData<NearbySearch> = MutableLiveData()

    val details: MutableLiveData<DetailResto> = MutableLiveData()

    val detailsFav: ArrayList<MutableLiveData<DetailResto>> = arrayListOf(MutableLiveData())


    val menu: MutableLiveData<Menu> = MutableLiveData()

    val searchVal: MutableLiveData<SearchInfo> = MutableLiveData()
    //private val repository : UserRepository

    val allUsers: LiveData<List<User>>

    val allFavoris: LiveData<List<Favoris>>


    init{
        allUsers = restaurantRepository.allUsers
        allFavoris = restaurantRepository.allFavoris
       // getRestoNearby("1500")
       getNearbyResto("1500")
    }
    /*fun getRestoNearby(rayon: String) = viewModelScope.launch {
        //val userDao : UserDao = UserRoomDatabase.getDatabase(application).userDao()

        getRestoNearby("1500")
       // getMenu("1500")
    }*/

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

     fun getFavorisUser(id: Int): LiveData<List<UserFavorisCrossRef>>{
        return restaurantRepository.getFavorisUser(id)
    }

    fun insertUserFavorisCrossRef(userFavorisCrossRef: UserFavorisCrossRef) = viewModelScope.launch(
        Dispatchers.IO){
        restaurantRepository.insertUserFavorisCrossRef(userFavorisCrossRef)
    }

    fun searchUser(email:String) : LiveData<List<User>> {

        return restaurantRepository.searchUser(email)
    }


    fun getDetailsResto(res_id: Int) = viewModelScope.launch {
        val response = restaurantRepository.getDetailsRestaurants(res_id)
        details.postValue(response.body())

    }
    fun getDetailsFavoris(res_id: Int) = viewModelScope.launch {
        val response = restaurantRepository.getDetailsRestaurants(res_id)
      //  Log.i("FAVORIS","${response.body()?.name}")
        details.postValue(response.body())

        detailsFav.add(details)
     //   Log.i("FAVORIS","${detailsFav.size}")

    }

    fun getNearbyResto(rayon: String) = viewModelScope.launch {
        val response = restaurantRepository.getNearbyResto(rayon)
        searchVal.postValue(response.body())
    }

    fun getMenu(res_id: Int) = viewModelScope.launch {
        val response = restaurantRepository.getMenu(res_id)
        menu.postValue(response.body())
    }

    fun getSearchByName(q: String, rayon: String,start: String) = viewModelScope.launch {

        val response = restaurantRepository.getSearchByName(q, rayon, start)
        searchVal.postValue(response.body())

    }



}

