package ca.qc.cgodin.restaurant.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.NearbySearch.RestoAdapter
import ca.qc.cgodin.restaurant.NearbySearch.RestoDetailsAdapter
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.fragment_details_resto.*
import kotlinx.android.synthetic.main.restaurant.*

class DetailsRestoFragment : Fragment(R.layout.fragment_details_resto){
    lateinit var viewModel: RestoViewModel
    lateinit var restoDetailsAdapter: RestoDetailsAdapter

    val args : DetailsRestoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NearbyRestoActivity).viewModel
        val result = args.Result



        setupRC(viewModel)



        viewModel.getDetailsResto(result.place_id)
        viewModel.details.observe(viewLifecycleOwner, Observer { response ->
            restoDetailsAdapter.setDetailsResto(response.result)
        })


    }

    private fun setupRC(viewModel: RestoViewModel){
        restoDetailsAdapter = RestoDetailsAdapter(viewModel)
        rcDetailsResto.adapter = restoDetailsAdapter
    }
}