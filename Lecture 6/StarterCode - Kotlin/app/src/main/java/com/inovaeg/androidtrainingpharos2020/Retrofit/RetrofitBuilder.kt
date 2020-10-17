package com.inovaeg.androidtrainingpharos2020.Retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder private constructor() {

    // get function return API
    val api: API
        get() = instance.create(API::class.java)

    companion object {
        private val instance = Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        private val builderInstance = RetrofitBuilder()

        fun getInstance(): RetrofitBuilder {
            return builderInstance
        }
    }
}


/*  JAVA

	private static Retrofit instance = new Retrofit.Builder()
		.baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

	private static RetrofitBuilder builderInstance = new RetrofitBuilder();

	private RetrofitBuilder(){
	}

	public static RetrofitBuilder getInstance(){
		return builderInstance;
	}


	public API getAPI(){
		return instance.create(API.class);
	}

 */