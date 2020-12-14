package ca.qc.cgodin.restaurant.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.NearbySearch.RestoAdapter
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.fragment_details_resto.*
import kotlinx.android.synthetic.main.restaurant.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class NearbyRestaurantsFragments : Fragment(R.layout.restaurant) {
    lateinit var viewModel: RestoViewModel
    lateinit var restoAdapter: RestoAdapter
    var intPage = 0;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NearbyRestoActivity).viewModel


        setupRC(viewModel)
        setupBtnSuivantPrecedent()


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
        viewModel.getNearbyResto("1500")

        viewModel.searchVal.observe(viewLifecycleOwner, Observer { response ->
            restoAdapter.setDetailsMenuResto(response.restaurants)
        })


        btnType.setOnClickListener {
            if(etTypeResto.toString().isNotEmpty()) {

            viewModel.getSearchByName(etTypeResto.text.toString(),etRayonResto.text.toString(),"0")
            viewModel.searchVal.observe(
                viewLifecycleOwner,
                Observer { response ->
                    restoAdapter.setDetailsMenuResto(response.restaurants)
                }
            )
        }
            setupBtnSuivantPrecedent()

        }

        btnPageSuivant.setOnClickListener {
            intPage += 20;
            viewModel.getSearchByName(etTypeResto.text.toString(),etRayonResto.text.toString(),intPage.toString())
            viewModel.searchVal.observe(
                viewLifecycleOwner,
                Observer { response ->
                    restoAdapter.setDetailsMenuResto(response.restaurants)
                }
            )
            setupBtnSuivantPrecedent()

        }

        btnPagePrecedent.setOnClickListener {
            intPage -= 20;
            viewModel.getSearchByName(etTypeResto.text.toString(),etRayonResto.text.toString(),intPage.toString())
            viewModel.searchVal.observe(
                viewLifecycleOwner,
                Observer { response ->
                    restoAdapter.setDetailsMenuResto(response.restaurants)
                }
            )
            setupBtnSuivantPrecedent()
        }







    }

        private fun setupBtnSuivantPrecedent(){
            if(intPage == 0){
                btnPagePrecedent.isEnabled = false
                btnPagePrecedent.isClickable = false
            }else{
                btnPagePrecedent.isEnabled = true
                btnPagePrecedent.isClickable = true
            }
            if(intPage == 80){
                btnPageSuivant.isEnabled = false
                btnPageSuivant.isClickable = false
            }
            else{
                btnPageSuivant.isEnabled = true
                btnPageSuivant.isClickable = true
            }
        }
    private fun setupRC(viewModel: RestoViewModel){
        restoAdapter = RestoAdapter()
        rcViewResto.adapter = restoAdapter

        rcViewResto.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }
}




