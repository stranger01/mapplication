package co.tk.findrepo

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SearchResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_result)



        val searchTerm = intent.getStringExtra("searchTerm")
        print(searchTerm)
    }
}