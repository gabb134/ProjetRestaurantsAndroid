package ca.qc.cgodin.restaurant.ui.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.telephony.SmsManager
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.DividerItemDecoration
import ca.qc.cgodin.restaurant.NearbySearch.NearbyRestoActivity
import ca.qc.cgodin.restaurant.NearbySearch.RestoDetailsAdapter
import ca.qc.cgodin.restaurant.R
import ca.qc.cgodin.restaurant.RoomDatabase.Favoris
import ca.qc.cgodin.restaurant.RoomDatabase.UserFavorisCrossRef
import ca.qc.cgodin.restaurant.RoomDatabase.UserViewModel
import ca.qc.cgodin.restaurant.modeleSearchZomato.Locations
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

    var estOuvert : Boolean = false;
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

        //ajout dans les favoris
        /*if(restoDetailsAdapter.favoris!=null){
            viewModel.insertFavoris(restoDetailsAdapter.favoris)
            Toast.makeText(context,"favoris a été ajouté dans la BD", Toast.LENGTH_LONG).show()
        }*/



        setupRC(viewModel,idUserConnection)

        val restaurant = args.Restaurant
        tvNomResto.text = restaurant.restaurant.name

        viewModel.getMenu(16507624)
        viewModel.menu.observe(viewLifecycleOwner, { response ->
            restoDetailsAdapter.setDetailsMenu(response.daily_menus)
        })

        restoDetailsAdapter.resto = restaurant
        restoDetailsAdapter.lstLocations = arrayListOf(restaurant.restaurant.location)


        viewModel.getDetailsResto(restaurant.restaurant.R.res_id)

        viewModel.details.observe(viewLifecycleOwner, { response ->
            restoDetailsAdapter.setDetailsMenuResto(response)
        })



        var lstLoc: Locations? = arrayListOf(restaurant.restaurant.location)?.let { Locations(it) }

        fabLocation2.setOnClickListener {
            val bundle  = Bundle().apply {
                putSerializable("Location", lstLoc)
            }
            findNavController().navigate(
                R.id.action_detailsRestoFragment_to_carteFragments,
                bundle
            )
        }


        fabCall2.setOnClickListener{
            if(ActivityCompat.checkSelfPermission(
                    context as Activity,
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(android.Manifest.permission.CALL_PHONE), 1
                )
            }else{

                restaurant.restaurant.phone_numbers?.let { it1 -> startCall(
                    context as Activity,
                    it1
                ) }
            }


        }

        fabSMS2.setOnClickListener{

            if(ActivityCompat.checkSelfPermission(
                    context as Activity,
                    android.Manifest.permission.SEND_SMS
                ) != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(
                    context as Activity,
                    arrayOf(android.Manifest.permission.SEND_SMS), 2
                )
            }else{

                restaurant.restaurant.phone_numbers?.let { it1 -> sendSMS(context as Activity, it1) }
            }

        }

        fabWeb.setOnClickListener {
            val callIntent = Intent(Intent.ACTION_VIEW, Uri.parse(restaurant.restaurant.url))
            context?.startActivity(callIntent)
        }
        fabFavoris2.setOnClickListener {
            Toast.makeText(context, "Favoris 2!! ${restaurant.restaurant.id}", Toast.LENGTH_SHORT).show()

            val favoris = Favoris(restaurant.restaurant.R.res_id.toLong())
            if (favoris != null) {


                //  onItem1ClickListener(favoris)
                //val id = intent.getIntArrayExtra("idUser")

                viewModel.insertFavoris(favoris)
                val crossRef = UserFavorisCrossRef(idUserConnection, favoris.RestoId) // voir comment obtenir le id de la personne qui a ajoute un resto dans ses favoris
                viewModel.insertUserFavorisCrossRef(crossRef)
                Toast.makeText(context,"favoris a été ajouté dans la BD", Toast.LENGTH_LONG).show()
                Toast.makeText(context, "Valeur id User dans le adapteur ${idUserConnection} , valeurr de favorisId ${favoris.RestoId}", Toast.LENGTH_LONG).show()
            }

        }

    fabOption.setOnClickListener {

        if(!fabWeb.isShown){
            fabWeb.show()
            fabLocation2.show()
            fabCall2.show()
            fabSMS2.show()
            fabFavoris2.show()
            estOuvert = false

        }else{
            fabLocation2.hide()
            fabCall2.hide()
            fabSMS2.hide()
            fabWeb.hide()
            fabFavoris2.hide()
            estOuvert = true
        }


    }

    }


    private fun startCall(context: Context, phoneNumbers: String) {
        val callIntent = Intent(Intent.ACTION_CALL)
        callIntent.data = Uri.parse("tel:$phoneNumbers")
        context.startActivity(callIntent)
    }
    private fun sendSMS(context: Context, phoneNumbers: String) {

        val text = "Message"

        SmsManager.getDefault().sendTextMessage(phoneNumbers, null, text, null, null)
        Toast.makeText(context, "SMS envoyé", Toast.LENGTH_SHORT).show()


    }

    private var onItemClickListener: ((Locations) -> Unit)? = null

    fun setOnItemClickListener(listener: (Locations) -> Unit) {
        onItemClickListener = listener
    }

    private fun setupRC(viewModel: RestoViewModel, idUserConnection: Int){
        restoDetailsAdapter = RestoDetailsAdapter(viewModel,idUserConnection)
        rcDetailsResto.adapter = restoDetailsAdapter

        rcDetailsResto.addItemDecoration(
            DividerItemDecoration(
                context,
                DividerItemDecoration.VERTICAL
            )
        )
    }


}