package com.example.homework9

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.homework9.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var allItems: MutableList<Item> = mutableListOf()
    private var displayedItems: MutableList<Item> = mutableListOf()
    private lateinit var binding: ActivityMainBinding
    private lateinit var buttonAdapter: ButtonAdapter
    private lateinit var adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        allItems.add(Item(R.drawable.model_one, "Belt Suit Blazer", "$120", "Type1"))
        allItems.add(Item(R.drawable.model_two, "Belt Suit Blazer", "$120", "Type2"))
        allItems.add(Item(R.drawable.model_three, "Belt Suit Blazer", "$120", "Type3"))
        allItems.add(Item(R.drawable.model_four, "Belt Suit Blazer", "$120", "Type4"))
        allItems.add(Item(R.drawable.model_one, "Belt Suit Blazer", "$120", "Type1"))
        allItems.add(Item(R.drawable.model_two, "Belt Suit Blazer", "$120", "Type2"))
        allItems.add(Item(R.drawable.model_three, "Belt Suit Blazer", "$120", "Type3"))
        allItems.add(Item(R.drawable.model_four, "Belt Suit Blazer", "$120", "Type4"))
        allItems.add(Item(R.drawable.model_one, "Belt Suit Blazer", "$120", "Type1"))
        allItems.add(Item(R.drawable.model_two, "Belt Suit Blazer", "$120", "Type2"))
        allItems.add(Item(R.drawable.model_three, "Belt Suit Blazer", "$120", "Type3"))
        allItems.add(Item(R.drawable.model_four, "Belt Suit Blazer", "$120", "Type4"))

        displayedItems.addAll(allItems)

        val recyclerViewOne = binding.recyclerViewOne
        recyclerViewOne.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)

        val recyclerViewTwo = binding.recyclerViewTwo
        recyclerViewTwo.layoutManager =
            GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false)

        adapter = Adapter()
        recyclerViewTwo.adapter = adapter

        val buttonTitles = listOf(
            "All",
            "\uD83C\uDF89      Party",
            "\uD83C\uDFD5      Camping",
            "Category1",
            "Category2",
            "Category3"
        )
        buttonAdapter = ButtonAdapter(buttonTitles, this, adapter, allItems)
        recyclerViewOne.adapter = buttonAdapter
        adapter.submitList(displayedItems)
    }
}