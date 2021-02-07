package co.tk.findrepo

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


interface GithubService {

    @GET("/search/repositories?")
    fun searchRepos(@Query("q") searchTerm: String): Call<GithubSearchResult>

}

class GithubSearchResult(val items: List<Repo>)
class Repo(val full_name: String, val owner: GithubUser, val html_user: String)
class GithubUser(val avatar_url: String)

class GithubRetriever {

    val service: GithubService

    init {

        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        service = retrofit.create(GithubService::class.java)

    }

    fun getRepo(callback: Callback<GithubSearchResult>, searchTerm: String){

        var searchT = searchTerm
        if (searchT == ""){

            searchT == "Android"
        }

        val call  = service.searchRepos(searchT)
        call.enqueue(callback)

    }

}