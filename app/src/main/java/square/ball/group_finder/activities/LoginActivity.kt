package square.ball.group_finder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import square.ball.group_finder.R

class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        val currentUser = mAuth.currentUser
        if (currentUser != null) {
            startActivity(Intent(this@LoginActivity, UserActivity::class.java))
            finish()
        }

        login()

        text_view_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    private fun login() {
        login_btn.setOnClickListener {
            val email = email_login.text.toString().trim()
            val password = password_login.text.toString().trim()

            if (email.isEmpty()) {
                email_login.error = "Email Required"
                email_login.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                email_login.error = "Valid Email Required"
                email_login.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 6) {
                password_login.error = "6 char password required"
                password_login.requestFocus()
                return@setOnClickListener
            }

            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        startActivity(Intent(this@LoginActivity, UserActivity::class.java))
                        finish()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed, please try again", Toast.LENGTH_LONG).show()
                    }
                }

        }
    }
}
