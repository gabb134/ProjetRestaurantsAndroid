package ca.qc.cgodin.restaurant.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
import kotlinx.android.synthetic.main.restaurant_detail_item.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
private const val ARG_PARAM1 = "param1"


class NearbyRestaurantsFragments : Fragment(R.layout.restaurant) {
    private var param1: Int? = null


    lateinit var viewModel: RestoViewModel
    lateinit var restoAdapter: RestoAdapter
    private var nearbySearch: MutableList<Result>? = null

    private var detailsResto: MutableList<ca.qc.cgodin.restaurant.modeleDetail.Result>? = null

    lateinit var edText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NearbyRestoActivity).viewModel


        arguments?.let {
            param1 = it.getInt(ARG_PARAM1)

        }

        //Toast.makeText(context,"Id user dans NerbyRestaurantsFragments : ${param1}", Toast.LENGTH_LONG).show()
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
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Int) =
            NearbyRestaurantsFragments().apply {
                arguments = Bundle().apply {
                    putInt(ARG_PARAM1, param1)

                }
            }
    }
}




