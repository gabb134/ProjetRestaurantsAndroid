package ca.qc.cgodin.restaurant.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.NearbySearch.RestoAdapter
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.modeleSearch.Result
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.restaurant.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class NearbyRestaurantsFragments : Fragment(R.layout.restaurant) {
    lateinit var viewModel: RestoViewModel
    lateinit var restoAdapter: RestoAdapter
    private var nearbySearch: MutableList<Result>? = null

    private var detailsResto: MutableList<ca.qc.cgodin.restaurant.modeleDetail.Result>? = null

    lateinit var edText: EditText

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NearbyRestoActivity).viewModel


        setupRC(viewModel)

        restoAdapter.setOnItemClickListener {
            val bundle  = Bundle().apply {
                putSerializable("Restaurant", it)
            }

            findNavController().navigate(
                R.id.action_nearbyRestaurantsFragments_to_detailsRestoFragment,
                bundle
            )

        }
      /*  viewModel.resto.observe(viewLifecycleOwner, Observer { response ->
            restoAdapter.setNearbySearch(response.results)
        })*/

        viewModel.searchVal.observe(viewLifecycleOwner, Observer { response ->
            restoAdapter.setDetailsMenuResto(response.restaurants)
        })


        var job: Job? = null
        etRayonResto.addTextChangedListener() { editable ->
            job?.cancel()
            job = MainScope().launch {
                //delay(SEARCH_NEWS_TIME_DELAY)
                editable?.let {
                    if(editable.toString().isNotEmpty()) {
                        viewModel.getMenu(editable.toString())
                    }
                }
            }
        }





    }

    fun setArticles(nearbySearch: MutableList<Result>) {
        this.nearbySearch = nearbySearch

    }

    fun setDetails(detailsResto: MutableList<ca.qc.cgodin.restaurant.modeleDetail.Result>) {
        this.detailsResto = detailsResto

    }

    private fun setupRC(viewModel: RestoViewModel){
        restoAdapter = RestoAdapter(viewModel)
        rcViewResto.adapter = restoAdapter
    }
}




