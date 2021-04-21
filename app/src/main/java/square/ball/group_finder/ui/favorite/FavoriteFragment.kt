package square.ball.group_finder.ui.favorite

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import square.ball.group_finder.R
import square.ball.group_finder.ui.search.SearchViewModel

class FavoriteFragment : Fragment() {


    private lateinit var favoriteViewModel: FavoriteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        favoriteViewModel =
            ViewModelProvider(this).get(FavoriteViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_favorite, container, false)
        val textView: TextView = root.findViewById(R.id.text_search)
        favoriteViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }



}