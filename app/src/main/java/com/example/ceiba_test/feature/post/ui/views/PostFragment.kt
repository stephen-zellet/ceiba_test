package com.example.ceiba_test.feature.post.ui.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.ceiba_test.R
import com.example.ceiba_test.core.closeProgressDialog
import com.example.ceiba_test.core.showDialog
import com.example.ceiba_test.core.showProgressDialog
import com.example.ceiba_test.databinding.FragmentPostBinding
import com.example.ceiba_test.feature.post.ui.models.ItemPost
import com.example.ceiba_test.feature.post.ui.adapters.PostAdapter
import com.example.ceiba_test.feature.post.ui.models.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PostFragment : Fragment() {

    private var _binding: FragmentPostBinding? = null

    private val binding get() = _binding!!

    private val args:PostFragmentArgs by navArgs()

    private val user get() =  args.user

    val model: PostViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model.getAllPostByWeb(user.id)

        binding.txtName.text=user.name
        binding.txtNumberPhone.text=user.phoneNumber
        binding.txtEmail.text=user.email

        model.result.observe(viewLifecycleOwner){

            if (it!=null) {
                if (it.isLoading) showProgressDialog() else closeProgressDialog()
                if (it.showError) showDialog(
                    subTitle = "Ha ocurrido un error al descargar los datos.",
                    message = it.error,
                    iconDrawable = R.drawable.ic_error,
                    textBtnCancel = R.string.cerrar,
                    textBtnOK = R.string.reintentar,
                    showCancelBtn = true
                ) { model.getAllPostByWeb(user.id) ;closeProgressDialog()}
                if (it.data.isNotEmpty()) binding.list.adapter = PostAdapter(it.data)
            }else{
                model.getAllPostByWeb(user.id)
            }

        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}