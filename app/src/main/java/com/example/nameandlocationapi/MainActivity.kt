package com.example.nameandlocationapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var etName: EditText
    lateinit var etLocation: EditText
    lateinit var buttonA: Button
    lateinit var etCheck: EditText
    lateinit var buttonF: Button
    lateinit var tvLocation: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etName = findViewById(R.id.etName)
        etLocation = findViewById(R.id.etLocation)
        buttonA = findViewById(R.id.buttonA)
        etCheck = findViewById(R.id.etCheck)
        buttonF = findViewById(R.id.buttonF)
        tvLocation = findViewById(R.id.tvLocation)
        val apiInterface = APINames().getName()?.create(APIInterface::class.java)
        buttonA.setOnClickListener {
            etName.text.toString()
            etLocation.text.toString()
            val call: Call<namesListItem> = apiInterface!!.addName(namesListItem(etName.text.toString(), etLocation.text.toString(), 0))
            call?.enqueue(object: Callback<namesListItem?> {
                override fun onResponse(
                    call: Call<namesListItem?>,
                    response: Response<namesListItem?>
                ) {
                    Toast.makeText(this@MainActivity, "Name & Location Added", Toast.LENGTH_LONG).show()
                }

                override fun onFailure(call: Call<namesListItem?>, t: Throwable) {
                    Toast.makeText(this@MainActivity, "No Name nor Location Added", Toast.LENGTH_LONG).show()
                }

            })
        }
        buttonF.setOnClickListener {
            var checking = etCheck.text.toString()
            val call: Call<ArrayList<namesListItem?>> = apiInterface!!.showLocation()
            call?.enqueue(object: Callback<ArrayList<namesListItem?>> {
                override fun onResponse(
                    call: Call<ArrayList<namesListItem?>>,
                    response: Response<ArrayList<namesListItem?>>
                ) {
                    for(i in response.body()!!){
                        if(i!!.name == checking){
                            println(true)
                            tvLocation.text = i!!.location
                        }
                }
                }

                override fun onFailure(call: Call<ArrayList<namesListItem?>>, t: Throwable) {
                    Log.d("MainActivity", "${t.message}")
                }
            })
        }
    }
}