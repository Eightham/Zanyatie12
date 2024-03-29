package ru.netology.myapplication.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import ru.netology.myapplication.databinding.FragmentNewPostBinding
import androidx.navigation.fragment.findNavController
import ru.netology.myapplication.dto.Post
import ru.netology.myapplication.util.AndroidUtils
import ru.netology.myapplication.util.StringArg
import ru.netology.myapplication.viewmodel.PostViewModel

class NewPostFragment : Fragment() {
    companion object{
        var Bundle.textArg : String? by StringArg
    }
    private val viewModel: PostViewModel by viewModels(
        ownerProducer = ::requireParentFragment
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        val binding: FragmentNewPostBinding = FragmentNewPostBinding.inflate(inflater,
            container,
            false)
        val text = arguments?.textArg
        binding.edit.setText(when {
            text != null -> text
            else         -> ""
        })
        binding.edit.requestFocus()
        binding.ok.setOnClickListener {
            viewModel.changeContent(binding.edit.text.toString())
            viewModel.save()
            AndroidUtils.hideKeyboard(requireView())
            findNavController().navigateUp()
        }
        return binding.root
    }
}