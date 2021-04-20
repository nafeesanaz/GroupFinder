package square.ball.group_finder.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_user.*
import square.ball.group_finder.R

class UserActivity : AppCompatActivity() {

    lateinit var mAuth : FirebaseAuth
    private var databaseReference: DatabaseReference ?= null
    private var database: FirebaseDatabase ?= null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_user)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("user")

        loadProfile()
    }



    private fun loadProfile() {
        val user = mAuth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        text_email.text = user?.email

        userreference?.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        logout_btn.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this@UserActivity, LoginActivity::class.java))
            finish()
        }
    }
}