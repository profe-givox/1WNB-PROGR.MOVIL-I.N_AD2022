package net.ivanvega.misrecyclersviews.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import net.ivanvega.misrecyclersviews.MainActivity
import net.ivanvega.misrecyclersviews.R
import net.ivanvega.misrecyclersviews.data.listFlowers

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentFlowerDetail.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentFlowerDetail : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var  txtName : TextView
    lateinit var  txtDescription : TextView
    lateinit var btnDeletev : Button
    lateinit var imgFlower : ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val layout =
            inflater.inflate(R.layout.layout_fragment_flower_detail, container, false)

        txtName = layout.findViewById(R.id.flower_detail_name)
        txtDescription = layout.findViewById(R.id.flower_detail_description)
        imgFlower = layout.findViewById(R.id.flower_detail_image)
        btnDeletev = layout.findViewById(R.id.remove_button)

         //val id =  param1?:1

        param1?.let {
            cargarDetailFlower(it)
        }



        return layout
    }

     fun cargarDetailFlower(id: Any) {
        val flor = listFlowers(resources).filter {
            it.id ==    id.toString().toLong()
        }[0]

        if (flor != null) {

            txtName.text = flor.name
            txtDescription.text = flor.description
            imgFlower.setImageResource(flor.image ?: R.drawable.ic_launcher_background)

            btnDeletev.setOnClickListener {
                val act = activity as MainActivity
                act.deleteFlower(id)
            }


        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentFlowerDetail.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentFlowerDetail().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}