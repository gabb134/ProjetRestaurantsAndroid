package ca.qc.cgodin.restaurant.ui.fragments

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.NearbySearch.RestoAdapter
import ca.qc.cgodin.restaurant.modeleSearch.Result
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.Remote.RetroFitClient
import ca.qc.cgodin.restaurant.ui.MapsActivity
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.restaurant.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.Exception

class NearbyRestaurantsFragments : Fragment(R.layout.restaurant) {
    lateinit var viewModel: RestoViewModel
    lateinit var restoAdapter: RestoAdapter
    private var nearbySearch: MutableList<Result>? = null
    lateinit var edText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NearbyRestoActivity).viewModel


        setupRC(viewModel)


      //  edText = view.findViewById(R.id.etRayon)


        viewModel.resto.observe(viewLifecycleOwner, Observer { response ->
           restoAdapter.setNearbySearch(response.results)
        })





    }

    fun setArticles(nearbySearch: MutableList<Result>) {
        this.nearbySearch = nearbySearch

    }

    private fun setupRC(viewModel: RestoViewModel){
        restoAdapter = RestoAdapter(viewModel)
        rcViewResto.adapter = restoAdapter
    }
}




