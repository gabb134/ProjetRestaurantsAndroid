package ca.qc.cgodin.restaurant.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
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
import kotlinx.android.synthetic.main.restaurant_detail_item.*
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
private const val ARG_PARAM1 = "param1"


class NearbyRestaurantsFragments : Fragment(R.layout.restaurant) {
    private var param1: Int? = null


    lateinit var viewModel: RestoViewModel
    lateinit var restoAdapter: RestoAdapter
    var intPage = 0;
    var intNbItem = 0;


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
        //setupBtnSuivantPrecedent()


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
            Log.i("Nb",restoAdapter.itemCount.toString())
            if(etTypeResto.toString().isNotEmpty()) {

            viewModel.getSearchByName(etTypeResto.text.toString(),etRayonResto.text.toString(),"0")
            viewModel.searchVal.observe(
                viewLifecycleOwner,
                Observer { response ->
                    restoAdapter.setDetailsMenuResto(response.restaurants)
                    intNbItem = response.results_shown
                    setupBtnSuivantPrecedent()
                }
            )


        }


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
            Log.i("Nb","${intNbItem}")
            if(intNbItem < 20){
                btnPageSuivant.isEnabled = false
                btnPageSuivant.isClickable = false
            }else{
                if(intPage == 80){
                    btnPageSuivant.isEnabled = false
                    btnPageSuivant.isClickable = false
                }
                else{
                    btnPageSuivant.isEnabled = true
                    btnPageSuivant.isClickable = true
                }

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




