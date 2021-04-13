package square.ball.group_finder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*
import square.ball.group_finder.R

class SignUpActivity : AppCompatActivity() {

    private lateinit var mAuth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mAuth = FirebaseAuth.getInstance()

        register_btn.setOnClickListener {
            val email = email_register.text.toString().trim()
            val password = password_register.text.toString().trim()

            if (email.isEmpty()){
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

            registerUser(email, password)
        }

        text_view_login.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java ))
        }
    }

    private fun registerUser(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        // Registration success
                            login()
                    } else {
                        task.exception?.message?.let {
                            toast(it)
                        }
                    }

                }
    }

    override fun onStart(){
        super.onStart()
        mAuth.currentUser?.let {
            login()
        }
    }

}