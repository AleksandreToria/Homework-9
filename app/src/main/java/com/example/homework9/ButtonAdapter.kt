package com.example.homework9

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.homework9.databinding.ItemButtonBinding

class ButtonAdapter(
    private val buttonTitles: List<String>,
    private val context: Context,
    private val adapter: Adapter,
    private val allItems: List<Item>
) : RecyclerView.Adapter<ButtonAdapter.ButtonViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder {
        val binding = ItemButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {
        val title = buttonTitles[position]
        holder.bind(title)
    }

    override fun getItemCount(): Int {
        return buttonTitles.size
    }

    inner class ButtonViewHolder(private val binding: ItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(title: String) {
            binding.button.text = title
            binding.button.setTextColor(ContextCompat.getColor(context, R.color.text))
            binding.button.setBackgroundResource(R.drawable.rounded_other)

            when (title) {
                "All" -> {
                    setButtonSize(binding.button, 52, 52)
                    binding.button.setBackgroundResource(R.drawable.rounded_all)
                    binding.button.setTextColor(ContextCompat.getColor(context, R.color.white))
                }

                "\uD83C\uDF89      Party" -> setButtonSize(binding.button, 116, 52)
                else -> setButtonSize(binding.button, 139, 52)
            }

            val layoutParams = binding.button.layoutParams as ViewGroup.MarginLayoutParams

            layoutParams.leftMargin = dpToPx(if (adapterPosition == 0) 27 else 10)
            binding.button.layoutParams = layoutParams

            binding.button.setOnClickListener {
                handleButtonClick(title)
            }
        }

        private fun handleButtonClick(title: String) {
            when (title) {
                "All" -> adapter.submitList(allItems)
                "\uD83C\uDF89      Party" -> adapter.submitList(getPartyItems())
                "\uD83C\uDFD5      Camping" -> adapter.submitList(getCampingItems())
                "Category1" -> adapter.submitList(getCategory1Items())
                "Category2" -> adapter.submitList(getCategory2Items())
                "Category3" -> adapter.submitList(getCategory3Items())
            }
        }

        private fun getPartyItems(): List<Item> {
            return allItems.filter { it.type == "Type2" || it.type == "Type3" }
        }

        private fun getCampingItems(): List<Item> {
            return allItems.filter { it.type == "Type1" || it.type == "Type3" }
        }

        private fun getCategory1Items(): List<Item> {
            return allItems.filter { it.type == "Type1" || it.type == "Type2" }
        }

        private fun getCategory2Items(): List<Item> {
            return allItems.filter { it.type == "Type3" }
        }

        private fun getCategory3Items(): List<Item> {
            return allItems.filter { it.type == "Type4" }
        }

        private fun setButtonSize(button: Button, widthDp: Int, heightDp: Int) {
            val density = context.resources.displayMetrics.density
            val widthPx = (widthDp * density).toInt()
            val heightPx = (heightDp * density).toInt()
            val layoutParams = button.layoutParams
            layoutParams.width = widthPx
            layoutParams.height = heightPx
            button.layoutParams = layoutParams
        }

        private fun dpToPx(dp: Int): Int {
            val density = context.resources.displayMetrics.density
            return (dp * density).toInt()
        }
    }
}

