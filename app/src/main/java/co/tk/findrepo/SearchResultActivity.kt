package co.tk.findrepo

import android.content.Context
import android.content.Intent
import android.graphics.Typeface
import android.icu.number.Notation.simple
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)

        val searchTerm = intent.getStringExtra("searchTerm")

        val callback = object : Callback<GithubSearchResult> {
            override fun onResponse(
                call: Call<GithubSearchResult>,
                response: Response<GithubSearchResult>
            ) {

                val searchResul = response.body()
                if (searchResul != null) {
                    for (repo in searchResul.items) {

                    }

                    val listView = findViewById<ListView>(R.id.repoListView)

                    val adapter = RepoAdapter(this@SearchResultActivity, android.R.layout.simple_list_item_1, searchResul.items)

                    listView.adapter = adapter


                }
            }

            override fun onFailure(call: Call<GithubSearchResult>, t: Throwable) {


                Toast.makeText(applicationContext, "Not working", Toast.LENGTH_SHORT).show()


            }

        }

        val retriever = GithubRetriever()
        if (searchTerm != null) {
            retriever.getRepo(callback, searchTerm)
        }

    }

    class RepoAdapter(context: Context, resource: Int, objects: List<Repo>) :
        ArrayAdapter<Repo>(context, resource, objects) {

        override fun getCount(): Int {
            return super.getCount()

        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val inflator =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val repoView =
                inflator.inflate(android.R.layout.simple_list_item_1, parent, false) as TextView

            val repo = getItem(position)
            repoView.text = repo?.full_name


            return repoView
        }

    }

}