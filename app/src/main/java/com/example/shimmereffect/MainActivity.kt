package com.example.shimmereffect

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.shimmereffect.adapter.MainAdapter
import com.example.shimmereffect.models.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUI()
        setupAPICall()
    }

    private fun setupUI() {
        rvData.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        rvData.addItemDecoration(
            DividerItemDecoration(
                rvData.context,
                (rvData.layoutManager as LinearLayoutManager).orientation
            )
        )
        rvData.adapter = adapter
    }

    private fun setupAPICall() {
        AndroidNetworking.initialize(applicationContext)
        AndroidNetworking.get("https://5e510330f2c0d300147c034c.mockapi.io/users")
            .build()
            .getAsObjectList(User::class.java, object : ParsedRequestListener<List<User>>{
                override fun onResponse(response: List<User>?) {
                    shimmerFrameLayout.stopShimmerAnimation()
                    shimmerFrameLayout.visibility = View.GONE
                    rvData.visibility = View.VISIBLE
                    response?.let { adapter.addData(it) }
                    adapter.notifyDataSetChanged()
                }

                override fun onError(anError: ANError?) {
                    shimmerFrameLayout.visibility = View.GONE
                    Toast.makeText(applicationContext, "Something went wrong", Toast.LENGTH_LONG).show()
                }

            })
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmerAnimation()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmerAnimation()
        super.onPause()
    }
}