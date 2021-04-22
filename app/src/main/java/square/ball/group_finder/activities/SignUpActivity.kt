package square.ball.group_finder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import square.ball.group_finder.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth
    var databaseReference : DatabaseReference? = null
    var database : FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("user")

        register_btn.setOnClickListener {
            val email = email_register.text.toString().trim()
            val password = password_register.text.toString().trim()


            if (email.isEmpty()) {
                email_register.error = "Email Required"
                email_register.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email_register.error = "Valid Email Required"
                email_register.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                password_register.error = "6 char password required"
                password_register.requestFocus()
                return@setOnClickListener
            }
            register(email, password)
        }

        text_view_login.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }
    }
    private fun register(email: String, password: String) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                if (it.isSuccessful) {
                    val currentUser = mAuth.currentUser
                    val currentUserDb = databaseReference?.child(currentUser?.uid!!)
                    currentUserDb?.child("name")?.setValue(name_register.text.toString())


                    val intent = Intent(this@SignUpActivity, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    }
                    startActivity(intent)
                    Toast.makeText(this@SignUpActivity, "Registration Success", Toast.LENGTH_LONG).show()
                    finish()
                } else {
                    Toast.makeText(this@SignUpActivity, "Registration failed, please try again", Toast.LENGTH_LONG).show()
                }
            }
        }
    override fun onStart() {
        super.onStart()
        mAuth.currentUser?.let {
            login()
        }
    }


}