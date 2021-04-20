package square.ball.group_finder.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import square.ball.group_finder.R

class EventActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_calendar)
        var title = findViewById<EditText>(R.id.etTitle)
        var location = findViewById<EditText>(R.id.etLocation)
        var description = findViewById<EditText>(R.id.etDescription)
        var addEvent = findViewById<Button>(R.id.btnAdd)


        addEvent.setOnClickListener{
            if (!title.getText().toString().isEmpty() && !location.text.toString().isEmpty() &&
                !description.text.toString().isEmpty()){

                val intent = Intent(Intent.ACTION_INSERT)
                intent.data = CalendarContract.Events.CONTENT_URI
                intent.putExtra(CalendarContract.Events.TITLE, title.text.toString())
                intent.putExtra(CalendarContract.Events.TITLE, location.text.toString())
                intent.putExtra(CalendarContract.Events.TITLE, description.text.toString())
                intent.putExtra(CalendarContract.Events.ALL_DAY, "true")
                intent.putExtra(Intent.EXTRA_EMAIL, "test123@gmail,test@gmail.com")

                if (intent.resolveActivity(packageManager) != null) {
                    startActivity(intent)
                } else {
                    Toast.makeText(this@EventActivity, "There is no app can support this action", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this@EventActivity, "Please fill in all the field", Toast.LENGTH_SHORT).show()
            }
        }
    }
}