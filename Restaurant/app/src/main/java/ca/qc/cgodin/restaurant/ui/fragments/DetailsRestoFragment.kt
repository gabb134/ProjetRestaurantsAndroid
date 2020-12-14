package ca.qc.cgodin.restaurant.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.NearbySearch.RestoDetailsAdapter
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.UserViewModel
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.fragment_details_resto.*
import kotlinx.android.synthetic.main.restaurant_detail_item.*

private const val ARG_IdUser = "idUserLogin"


class DetailsRestoFragment : Fragment(R.layout.fragment_details_resto){
    // TODO: Rename and change types of parameters
    private var idUserLogin: Int? = null


    private var idUserConnection:Int = 0

    lateinit var viewModel: RestoViewModel
    lateinit var restoDetailsAdapter: RestoDetailsAdapter

    lateinit var  userViewModel: UserViewModel

    val args : DetailsRestoFragmentArgs by navArgs()

    //val fab = findViewById(R.id.fabFavoris) as FloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            idUserLogin = it.getInt(ARG_IdUser)


            //Toast.makeText(context,"Id user dans DetailsRestoFragment : ${idUserLogin}", Toast.LENGTH_LONG).show()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)

       //  val idUser = this.arguments?.getInt("idUser",-1)
        idUserConnection = (activity as NearbyRestoActivity).idUser

        //Toast.makeText(context,"Id user dans DetailsRestoFragment : ${idUserConnection}", Toast.LENGTH_LONG).show()

      //  restoDetailsAdapter.id = idUserConnection

        viewModel= (activity as NearbyRestoActivity).viewModel
        val restaurant = args.Restaurant

        //ajout dans les favoris
        /*if(restoDetailsAdapter.favoris!=null){
            viewModel.insertFavoris(restoDetailsAdapter.favoris)
            Toast.makeText(context,"favoris a été ajouté dans la BD", Toast.LENGTH_LONG).show()
        }*/



        setupRC(viewModel,idUserConnection)

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



    private fun setupRC(viewModel: RestoViewModel, idUserConnection: Int){
        restoDetailsAdapter = RestoDetailsAdapter(viewModel,idUserConnection)
        rcDetailsResto.adapter = restoDetailsAdapter

    }
    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param idUserLogin Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BlankFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(idUserLogin: Int) =
            DetailsRestoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_IdUser, idUserLogin)

                    //idUserConnection = idUserLogin

                    //restoDetailsAdapter = RestoDetailsAdapter(viewModel)
                  //  restoDetailsAdapter.id = idUserLogin
                   // Toast.makeText(context,"Id user dans DetailsRestoFragment : ${idUserLogin}", Toast.LENGTH_LONG).show()
                    Log.i("IdUser dans le log : ","${idUserLogin}")
                }


            }
    }
}