package ca.qc.cgodin.restaurant.NearbySearch

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import ca.qc.cgodin.restaurant.R
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


            val repository = RestaurantRepository()
            val viewModelProviderFactory = RestoViewModelProviderFactory(repository)
            viewModel = ViewModelProvider(this,viewModelProviderFactory).get(RestoViewModel::class.java)
    }
}