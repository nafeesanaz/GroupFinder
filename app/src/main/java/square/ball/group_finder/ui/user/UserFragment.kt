package square.ball.group_finder.ui.user

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import square.ball.group_finder.R

class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var mAuth: FirebaseAuth

    private val currentUser = FirebaseAuth.getInstance().currentUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        userViewModel =
//            ViewModelProvider(this).get(UserViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_user, container, false)
//        val textView: TextView = root.findViewById(R.id.text_user)
//        userViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        return inflater.inflate(R.layout.fragment_user, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}