package square.ball.group_finder.ui.user

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import square.ball.group_finder.R
import square.ball.group_finder.activities.LoginActivity

class UserFragment : Fragment() {

    private var databaseReference: DatabaseReference?= null
    private var database: FirebaseDatabase?= null
    private lateinit var userViewModel: UserViewModel
    private lateinit var mAuth: FirebaseAuth

    private val currentUser = FirebaseAuth.getInstance().currentUser


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("Users")
//        userViewModel =
//            ViewModelProvider(this).get(UserViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_user, container, false)
//        val textView: TextView = root.findViewById(R.id.text_user)
//        userViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
        val root = inflater.inflate(R.layout.fragment_user, container, false)
        val logoutButton = root.findViewById<Button>(R.id.logout_btn)
        logoutButton.setOnClickListener{
            logout()
        }
        return root

    }

    private fun logout() {
        mAuth.signOut()
        val intent = Intent(activity , LoginActivity::class.java)
        startActivity(intent)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}