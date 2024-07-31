package com.example.openinapp.ui

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.openinapp.R
import com.example.openinapp.databinding.VerticalItemLayoutBinding
import com.example.openinapp.network.response.DashboardResponse
import java.text.SimpleDateFormat
import java.util.Locale

class DashboardAdapter(private val links: List<DashboardResponse.Link>) :
    RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val binding = VerticalItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DashboardViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        holder.bind(links[position])
    }

    override fun getItemCount(): Int = links.size

    inner class DashboardViewHolder(private val binding: VerticalItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun formatDate(dateString: String): String {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
            val outputFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
            val date = inputFormat.parse(dateString)
            return outputFormat.format(date)
        }

        fun bind(link: DashboardResponse.Link) {
            Glide.with(binding.image.context)
                .load(link.originalImage)
                .placeholder(R.drawable.ic_def)
                .into(binding.image)

            binding.linkName.text = link.title
            binding.date.text = formatDate(link.createdAt)
            binding.clicksValue.text = link.totalClicks.toString()
            binding.link.text = link.webLink

            binding.copyBtn.setOnClickListener {
                val clipboard = it.context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("Copied Link", binding.link.text.toString())
                clipboard.setPrimaryClip(clip)
                Toast.makeText(it.context, "Link copied to clipboard", Toast.LENGTH_SHORT).show()
            }
        }
    }
}