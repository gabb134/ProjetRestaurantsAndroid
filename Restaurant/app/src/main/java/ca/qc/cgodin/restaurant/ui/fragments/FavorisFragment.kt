package ca.qc.cgodin.restaurant.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import ca.qc.cgodin.restaurant.NearbySearch.FavorisAdapter
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.NearbySearch.RestoAdapter
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.Favoris
import ca.qc.cgodin.restaurant.ui.RestoViewModel
import kotlinx.android.synthetic.main.fragment_favoris.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavorisFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavorisFragment : Fragment(R.layout.fragment_favoris) {
    lateinit var favorisAdapter: FavorisAdapter
             var idUserConnection = 0;
    lateinit var viewModel: RestoViewModel

    private lateinit var  restoAdapter: RestoAdapter

    var comp = 0

    val listFavUser = mutableListOf<Favoris>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favoris, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {      super.onViewCreated(view, savedInstanceState)
        viewModel= (activity as NearbyRestoActivity).viewModel
        idUserConnection = (activity as NearbyRestoActivity).idUser

        favorisAdapter = FavorisAdapter()
        rcRestoFavoris.adapter = favorisAdapter
      /*  viewModel.allFavoris.observe(
            viewLifecycleOwner, Observer {
                favoriss-> favorisAdapter.setfavoris(favoriss)
            }
        )*/


        viewModel.getFavorisUser(idUserConnection).observe(
            viewLifecycleOwner, Observer { it ->
                for(test in it){
                    comp++
                    //Log.i("valursRestoID", it.toString())

                        viewModel.getFavoris2(test.RestoId.toInt()).observe(
                            viewLifecycleOwner, Observer {


                                var fav : Favoris = it.get(0);
                                listFavUser.addAll(listOf(it[0]));
                                Log.i("valursRestoID", it.get(0).title)
                                Log.i("valursRestoID", listFavUser.size.toString())
                                favorisAdapter.setfavoris(listFavUser)
                            }
                        )
                }


                //Toast.makeText(context, "resto id du user couranrt : ${ it.}", Toast.LENGTH_LONG).show()
            }
        )






       /* favorisAdapter = FavorisAdapter()
        rcRestoFavoris.adapter = favorisAdapter
        viewModel.getFavorisUser(idUserConnection).observe(viewLifecycleOwner,{ favoris->
          //restoAdapter.se
            for(item in favoris){

                viewModel.getDetailsFavoris(item.RestoId.toInt())
             //
            //Log.i("FAVORIS","${favoris[0].RestoId}")

            }

        })*/
     //   Log.i("FAVORIS","${viewModel.detailsFav[0].value}")
       // favorisAdapter.setDetails(viewModel.detailsFav)






    }

}