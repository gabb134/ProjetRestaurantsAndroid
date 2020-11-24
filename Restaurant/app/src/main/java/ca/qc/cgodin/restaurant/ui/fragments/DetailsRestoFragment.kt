package ca.qc.cgodin.restaurant.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.NearbySearch.RestoAdapter
import ca.qc.cgodin.restaurant.NearbySearch.RestoDetailsAdapter
import ca.qc.cgodin.restaurant.R

import ca.qc.cgodin.restaurant.modeleSearchZomato.Location
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.fragment_details_resto.*


class DetailsRestoFragment : Fragment(R.layout.fragment_details_resto){
    lateinit var viewModel: RestoViewModel
    lateinit var restoDetailsAdapter: RestoDetailsAdapter

    val args : DetailsRestoFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NearbyRestoActivity).viewModel
        val restaurant = args.Restaurant

        setupRC(viewModel)

        restoDetailsAdapter.resto = restaurant
        restoDetailsAdapter.lstLocations = arrayListOf(restaurant.restaurant.location)

        restoDetailsAdapter.setOnItemClickListener {
            val bundle  = Bundle().apply {
                putSerializable("Location", it)
            }
           findNavController().navigate(
               R.id.action_detailsRestoFragment_to_carteFragments,
                bundle
            )

        }
        viewModel.getDetailsResto(restaurant.restaurant.R.res_id)

        viewModel.details.observe(viewLifecycleOwner, Observer { response ->
            restoDetailsAdapter.setDetailsMenuResto(response)
        })


    }


    private fun setupRC(viewModel: RestoViewModel){
        restoDetailsAdapter = RestoDetailsAdapter(viewModel)
        rcDetailsResto.adapter = restoDetailsAdapter
    }
}