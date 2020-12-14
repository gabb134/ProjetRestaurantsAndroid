package ca.qc.cgodin.restaurant.NearbySearch

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.UserDao
import ca.qc.cgodin.restaurant.RoomDatabase.UserRoomDatabase
import ca.qc.cgodin.restaurant.repository.RestaurantRepository
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import ca.qc.cgodin.restaurant.ui.RestoViewModelProviderFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.restaurant.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class NearbyRestoActivity : AppCompatActivity() {


    lateinit var viewModel: RestoViewModel

     var idUser:Int = 0

    private val navController by lazy {
        findNavController(R.id.navHostFragmentContainer)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomNavigationView.setupWithNavController(navHostFragmentContainer.findNavController())
        val userDao : UserDao = UserRoomDatabase.getDatabase(application).userDao()
        val repository = RestaurantRepository(userDao)
        val viewModelProviderFactory = RestoViewModelProviderFactory(repository)

        try {
            val viewModelProvider = ViewModelProvider(
                navController.getViewModelStoreOwner(R.id.news_nav_graph),
                viewModelProviderFactory
            )
            viewModel = viewModelProvider.get(RestoViewModel::class.java)
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
        }

        val intent = getIntent()

        idUser = intent.getIntExtra("idUser",-1)




    }
}