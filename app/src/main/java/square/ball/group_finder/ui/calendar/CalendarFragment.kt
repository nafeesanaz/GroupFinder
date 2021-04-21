package square.ball.group_finder.ui.calendar

import android.annotation.SuppressLint
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.provider.CalendarContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.Observer
import square.ball.group_finder.R


class CalendarFragment : Fragment() {



    private lateinit var calendarViewModel: CalendarViewModel



    @SuppressLint("CutPasteId")
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        calendarViewModel =
                ViewModelProvider(this).get(CalendarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
//        val textView: TextView = root.findViewById(R.id.text_search)
//        calendarViewModel.text.observe(viewLifecycleOwner, Observer {
//            textView.text = it
//        })
       val button = root.findViewById<Button>(R.id.btnAdd)
       val title = root.findViewById<EditText>(R.id.etTitle)
        val location = root.findViewById<EditText>(R.id.etLocation)
        val description = root.findViewById<EditText>(R.id.etTitle)


        button.setOnClickListener {

//            val location = "My test location"
//            val description = "My test description"
            if (!title.getText().toString().isEmpty() && !location.text.toString().isEmpty() &&
                    !description.text.toString().isEmpty()){
                val intent = Intent(Intent.ACTION_INSERT)
                intent.data = CalendarContract.Events.CONTENT_URI
                intent.putExtra(CalendarContract.Events.TITLE, title.text.toString())
                intent.putExtra(CalendarContract.Events.TITLE, location.text.toString())
                intent.putExtra(CalendarContract.Events.TITLE, description.text.toString())
                intent.putExtra(CalendarContract.Events.ALL_DAY, "true")
                intent.putExtra(Intent.EXTRA_EMAIL, "test123@gmail,test@gmail.com")

                startActivity(intent)
            }else {

                Toast.makeText(activity, "Please fill in all the fields", Toast.LENGTH_SHORT).show()
            }

        }


        return root
    }

}