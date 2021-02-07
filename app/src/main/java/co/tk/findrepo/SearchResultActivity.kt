package co.tk.findrepo

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


class SearchResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val searchTerm = intent.getStringExtra("searchTerm")

        val callback = object :Callback<GithubSearchResult>{
            override fun onResponse(
                call: Call<GithubSearchResult>,
                response: Response<GithubSearchResult>
            ) {

                val searchResul = response?.body()
                if (searchResul !=null){
                    for (repo in searchResul!!.items){

                        Toast.makeText(applicationContext,"repo ${repo.full_name} ",Toast.LENGTH_SHORT).show()

                    }
                }
            }

            override fun onFailure(call: Call<GithubSearchResult>, t: Throwable) {


                Toast.makeText(applicationContext,"Not working",Toast.LENGTH_SHORT).show()


            }

        }

        val retriever = GithubRetriever()
        if (searchTerm != null) {
            retriever.getRepo(callback, searchTerm)
        }

    }
}