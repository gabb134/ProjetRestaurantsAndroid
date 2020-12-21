package ca.qc.cgodin.restaurant.NearbySearch

import android.content.Intent
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
import ca.qc.cgodin.restaurant.RoomDatabase.User
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


        //Ajout du compte google dans la BD
    /*    val intent1 = getIntent()
        val usernameGoogle = intent1.getStringExtra("usernameGoogle")
        val emailGoogle = intent1.getStringExtra("emailGoogle")

        val userGoogle = User(
            0,
            usernameGoogle.toString(),
            "",
            emailGoogle.toString()
        )
        viewModel.searchUser(emailGoogle.toString()).observe(
                this, {
                users -> users.let{
                    if(it.isEmpty()){
                        viewModel.insert(userGoogle)
                        Toast.makeText(applicationContext, "Ce compte Google vient d'etre ajoute dans la BD", Toast.LENGTH_SHORT).show()
                    }else{
                        Toast.makeText(applicationContext, "Ce compte Google se trouve deja dans la BD", Toast.LENGTH_SHORT).show()
                    }
            }})

        //Ajout du compte facebook dans la BD
        val intent2 = getIntent()

        val usernameFacebook = intent2.getStringExtra("usernameFacebook")
        val emailFacebook = intent2.getStringExtra("usernameFacebook")

        val userFacebook = User(
            0,
            usernameFacebook.toString(),
            "",
            emailFacebook.toString()
        )

        viewModel.searchUser(emailFacebook.toString()).observe(
            this, {
                    users -> users.let{
                if(it.isEmpty()){
                    viewModel.insert(userGoogle)
                    Toast.makeText(applicationContext, "Ce compte Facebook vient d'etre ajoute dans la BD", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(applicationContext, "Ce compte Facebook se trouve deja dans la BD", Toast.LENGTH_SHORT).show()
                }
            }})*/







    }
}