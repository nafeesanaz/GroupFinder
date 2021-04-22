package square.ball.group_finder.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import kotlinx.android.synthetic.main.fragment_user.view.*
import square.ball.group_finder.R
import square.ball.group_finder.ui.user.UserFragment


class LoginActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mAuth = FirebaseAuth.getInstance()

        login_func()

        text_view_register.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }

    }

    private fun login_func() {
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
                       login()
                    } else {
                        Toast.makeText(this@LoginActivity, "Login failed, please try again", Toast.LENGTH_LONG).show()
                    }
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