package square.ball.group_finder.ui.calendar

import android.content.Intent

import android.os.Bundle
import android.provider.CalendarContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProvider
import square.ball.group_finder.R
import square.ball.group_finder.activities.MainActivity



class CalendarFragment : Fragment() {


    private lateinit var calendarViewModel: CalendarViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        calendarViewModel =
                ViewModelProvider(this).get(CalendarViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_calendar, container, false)
//        val textView: TextView = root.findViewById(R.id.text_search)


        return root
    }




}