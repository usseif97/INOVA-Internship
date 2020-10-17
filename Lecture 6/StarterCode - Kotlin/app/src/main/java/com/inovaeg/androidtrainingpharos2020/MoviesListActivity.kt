package com.inovaeg.androidtrainingpharos2020

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.inovaeg.androidtrainingpharos2020.Retrofit.GuestSessionIDRequestResult
import com.inovaeg.androidtrainingpharos2020.Retrofit.MoviesRequestResult
import com.inovaeg.androidtrainingpharos2020.Retrofit.RetrofitBuilder
import kotlinx.android.synthetic.main.activity_movies_list.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList

class MoviesListActivity : AppCompatActivity() {
    private var loadMore = true
    private var search = false
    private var query:String? = null
    private var moviesAdapter: MoviesAdapter? = null
    private var pageNumber = 1
    private lateinit var sharedPref : SharedPreferences


    private val API_KEY = "2679cb447a37eabd0bd4031296f5b843"
    private val BASE_URL = "https://api.themoviedb.org/3/"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)

        Log.d("APICALL", "here We Are")

        // Declare SharedPreferences
        sharedPref = getSharedPreferences("MoviesListActivity", Context.MODE_PRIVATE)

        // get Guest Session ID & save it in the Shared Preferences
        getSessionGuestID()

        // SearchBar Watcher
        search_bar.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                if(p0.toString().isNotEmpty()){
                    Log.d("SearchBar", "Full")
                    search = true
                    pageNumber = 1
                    query = p0.toString()
                    search(query!!)
                } else {
                    Log.d("SearchBar", "Empty")
                    search = false
                    pageNumber = 1
                    loadData()
                }
            }
        })

        // Adjust the Adapter for the Movies
        moviesAdapter = MoviesAdapter(ArrayList())
        val linearLayoutManager = LinearLayoutManager(this)
        rv_movies.apply {
            adapter = moviesAdapter
            layoutManager = linearLayoutManager
            if(!search)
                loadData()
            else {
                if (query != null)
                    search(query!!)
            }
            // Pagination
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    if (loadMore)
                        if (dy > 0) {
                            val visibleItemCount = linearLayoutManager.childCount
                            val totalItemCount = linearLayoutManager.itemCount
                            val pastVisibleItems = linearLayoutManager.findFirstVisibleItemPosition()
                            if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                                if(!search)
                                    loadData()
                                else {
                                    if (query != null)
                                        search(query!!)
                                }
                                loadMore = false
                            }
                        }
                }
            })
        }

    }

    private fun getSessionGuestID(){
        RetrofitBuilder.getInstance().api
                .getGuestSessionID(API_KEY)
                .enqueue(object : Callback<GuestSessionIDRequestResult>{
                    override fun onResponse(call: Call<GuestSessionIDRequestResult>, response: Response<GuestSessionIDRequestResult>) {
                        if(response.isSuccessful){
                            val id = response.body()?.guestSessionID
                            if(id != null){
                                Log.d("APICALL", id)
                                sharedPref
                                        .edit()
                                        .putString("GuestID", id)
                                        .apply()
                            }
                        } else {
                            showToast(response.errorBody().toString())
                        }
                    }
                    override fun onFailure(call: Call<GuestSessionIDRequestResult>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
    }

    private fun loadData() {
        progress_bar.visibility = View.VISIBLE
        RetrofitBuilder.getInstance().api
                .getMovies(API_KEY, pageNumber)
                .enqueue(object : Callback<MoviesRequestResult>{
                    override fun onResponse(call: Call<MoviesRequestResult>, response: Response<MoviesRequestResult>) {
                        if(response.isSuccessful){
                            //showToast(response.body().toString())
                            Log.d("APICALL", response.body().toString())
                            val list = response.body()?.movies
                            if(pageNumber == 1)
                                moviesAdapter!!.updateDataSet(list!!)
                            else
                                moviesAdapter!!.appendData(list!!)
                            if(list.size < 20)
                                loadMore = false
                            else{
                                loadMore = true
                                pageNumber++
                            }
                        } else {
                            showToast(response.errorBody().toString())
                        }
                        progress_bar.visibility = View.GONE
                    }

                    override fun onFailure(call: Call<MoviesRequestResult>, t: Throwable) {
                        t.printStackTrace()
                        progress_bar.visibility = View.GONE
                    }


                })
    }

    private fun search(query : String){
        RetrofitBuilder.getInstance().api
                .searchForMovie(API_KEY, pageNumber, query)
                .enqueue(object : Callback<MoviesRequestResult>{
                    override fun onResponse(call: Call<MoviesRequestResult>, response: Response<MoviesRequestResult>) {
                        if(response.isSuccessful){
                            //showToast(response.body().toString())
                            Log.d("APICALL", response.body().toString())
                            val list = response.body()?.movies
                            if(pageNumber == 1)
                                moviesAdapter!!.updateDataSet(list!!)
                            else
                                moviesAdapter!!.appendData(list!!)

                            if(list.size < 20)
                                loadMore = false
                            else{
                                loadMore = true
                                pageNumber++
                            }
                        } else {
                            showToast(response.errorBody().toString())
                        }
                    }

                    override fun onFailure(call: Call<MoviesRequestResult>, t: Throwable) {
                        t.printStackTrace()
                    }
                })
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
