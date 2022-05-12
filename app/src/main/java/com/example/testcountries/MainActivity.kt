package com.example.testcountries

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcountries.model.response.PaisesDTO
import com.example.testcountries.model.Post
import com.example.testcountries.request.PaisesRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), LocationListener {
    private lateinit var myAdapter: AdapterRV
    var posts: List<PaisesDTO>? = null
    private var recyclerView: RecyclerView? = null
    private lateinit var viewDialog: ViewDialog
    private val PERMISSION_REQUEST_ACCESS_FINE_LOCATION: Int = 101
    lateinit var locationManager: LocationManager
    var location: Location? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getLocation()

        recyclerView = findViewById(R.id.myRecyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        viewDialog = ViewDialog()
        myAdapter = AdapterRV(applicationContext, posts ?: listOf()) { pais ->
            viewDialog.showDialog(
                this,
                "Click, " + pais.pais + "!",
                "Has dado click en el país de " + pais.pais,
                location ?: Location("")
            )
        }
        recyclerView?.adapter = myAdapter

        getPosts()
    }

    private fun getPosts() {
        val apiService = ApiClient().getClient()!!.create(ApiInterface::class.java)
        val call: Call<Post> = apiService.getCountries(PaisesRequest(""))
        call.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                posts = response.body()?.dsRespuesta?.paises
                myAdapter.setCountriesList(posts ?: listOf())
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                Log.e("Error", t.stackTraceToString())
            }

        })
    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSION_REQUEST_ACCESS_FINE_LOCATION)
        } else {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5f, this)
        }
    }

    override fun onLocationChanged(location: Location) {
        this.location = location
        Log.e("coordenadas", "latitude: " + location.latitude.toString() + ", longitude: " + location.longitude.toString())
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == PERMISSION_REQUEST_ACCESS_FINE_LOCATION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show()
                getLocation()
            }
            else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
