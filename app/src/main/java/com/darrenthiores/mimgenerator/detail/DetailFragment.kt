package com.darrenthiores.mimgenerator.detail

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.darrenthiores.core.utils.Constant
import com.github.dhaval2404.imagepicker.ImagePicker
import com.darrenthiores.mimgenerator.databinding.FragmentDetailBinding
import com.watermark.androidwm_light.WatermarkBuilder
import com.watermark.androidwm_light.bean.WatermarkText
import java.util.*
import android.provider.MediaStore
import com.watermark.androidwm_light.bean.WatermarkImage


class DetailFragment : Fragment() {

    private var _binding:FragmentDetailBinding? = null
    private val binding get() = _binding
    private var watermarkImage:Bitmap? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding?.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val url = DetailFragmentArgs.fromBundle(arguments as Bundle).url
        var state = false

        binding?.apply {

            btBack.setOnClickListener { requireActivity().onBackPressed() }

            Glide.with(this@DetailFragment)
                .load(url)
                .apply(
                    RequestOptions.placeholderOf(com.darrenthiores.core.R.drawable.ic_loading)
                        .error(com.darrenthiores.core.R.drawable.ic_error)
                )
                .into(ivDetail)

            btAddLogo.setOnClickListener {

                ImagePicker.with(this@DetailFragment)
                    .start(Constant.REQUEST_FOR_PICTURE)

            }

            btAddText.setOnClickListener {

                state = !state
                showAddText(state)

            }

            btEnter.setOnClickListener {

                val watermarkText: WatermarkText = WatermarkText(edText)
                    .setPositionX(0.5)
                    .setPositionY(0.1)
                    .setTextColor(Color.WHITE)
                    .setTextShadow(0.1f, 5f, 5f, Color.BLACK)
                    .setTextAlpha(150)
                    .setTextSize(20.0)

                val textWaterMark = WatermarkBuilder
                    .create(requireContext(), ivDetail)
                    .loadWatermarkText(watermarkText)
                    .watermark

                textWaterMark.setToImageView(ivDetail)
                watermarkImage = textWaterMark.outputImage

                edText.setText("")
                state = false
                showAddText(state)

            }

            btSave.setOnClickListener {

                if(watermarkImage!=null){

                    MediaStore.Images.Media.insertImage(
                        requireActivity().contentResolver,
                        watermarkImage,
                        "${UUID.randomUUID()}",
                        "image-${UUID.randomUUID()}"
                    )

                }

            }

            btShare.setOnClickListener {

                editImage.visibility = View.GONE
                button.visibility = View.GONE
                state = false
                showAddText(state)
                shareButton.visibility = View.VISIBLE

            }

            btCancel.setOnClickListener {

                editImage.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
                shareButton.visibility = View.GONE

            }

            btFb.setOnClickListener {

                val intent = Intent()

                intent.putExtra(Intent.EXTRA_STREAM, watermarkImage)
                intent.type = "image/jpeg"
                intent.setPackage("com.facebook.katana")

                startActivity(intent)

            }

            btTwitter.setOnClickListener {

                val intent = Intent()

                intent.putExtra(Intent.EXTRA_STREAM, watermarkImage)
                intent.type = "image/jpeg"
                intent.setPackage("com.twitter.android")

                startActivity(intent)

            }

        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == Constant.REQUEST_FOR_PICTURE && resultCode == Activity.RESULT_OK){

            val watermark : Bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, data?.data)
            val imageWaterMark = WatermarkImage(watermark)
                .setPositionX(0.05)
                .setPositionY(0.05)
                .setSize(0.25)
                .setImageAlpha(150)

            val builder = WatermarkBuilder
                .create(requireContext(), binding!!.ivDetail)
                .loadWatermarkImage(imageWaterMark)
                .watermark

            builder.setToImageView(binding!!.ivDetail)
            watermarkImage = builder.outputImage

        }

    }

    private fun showAddText(state:Boolean){

        binding?.apply {

            if(state){

                edText.visibility = View.VISIBLE
                btEnter.visibility = View.VISIBLE

            } else {

                edText.visibility = View.GONE
                btEnter.visibility = View.GONE

            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}