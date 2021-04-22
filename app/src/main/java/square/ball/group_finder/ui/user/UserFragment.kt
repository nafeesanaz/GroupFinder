package square.ball.group_finder.ui.user


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_user.*
import square.ball.group_finder.R
import square.ball.group_finder.activities.LoginActivity


class UserFragment : Fragment() {

    private lateinit var userViewModel: UserViewModel
    lateinit var mAuth : FirebaseAuth
    private var databaseReference: DatabaseReference?= null
    private var database: FirebaseDatabase?= null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        userViewModel =
            ViewModelProvider(this).get(UserViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user, container, false)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("user")


        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadProfile()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        logout_btn.setOnClickListener(View.OnClickListener {
            mAuth.signOut()
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        })
    }
    private fun loadProfile() {
        val user = mAuth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        text_email.text = user?.email

        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                text_name.text = snapshot.child("name").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

    }

}