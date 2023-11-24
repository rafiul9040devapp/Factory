package com.rafiul.factory

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import com.rafiul.factory.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel = ViewModelProvider(
            this,
            MainViewModelFactory(applicationContext)
        )[MainViewModel::class.java]

        setQuotes(mainViewModel.getQuote())

        binding.apply {
            btnPrevious.setOnClickListener {
                setQuotes(mainViewModel.previousQuote())
            }

            btnNext.setOnClickListener {
                setQuotes(mainViewModel.nextQuote())
            }

            floatingActionButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND)
                intent.type = "text/plain"
                intent.putExtra(Intent.EXTRA_TEXT, mainViewModel.getQuote().text)
                startActivity(intent)
            }
        }
    }

    private fun setQuotes(quote: Quote) {
        binding.apply {
            quoteText.text = quote.text
            quoteAuthor.text = quote.author
        }
    }

}