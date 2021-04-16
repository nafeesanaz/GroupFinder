package square.ball.group_finder.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import square.ball.group_finder.Group
import square.ball.group_finder.R

/**
 * A fragment representing a list of groups.
 */
class HomeGroupFragment : Fragment() {

    private lateinit var groups: ArrayList<Group>
    private lateinit var rcList: RecyclerView
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = Firebase.database.reference

        groups = arrayListOf()

        /*for(i in 0..10){
            groups.add("group $i") //testcode
        }*/

        database.child("groups").get().addOnSuccessListener{
            if(it.value != null){
                val groupsFromDB = it.value as HashMap<String,Any>
                groups.clear()
                groupsFromDB.map { (key,value) ->
                    val groupFromDB = value as HashMap<String,Any>

                    val groupName = groupFromDB.get("groupname").toString()
                    val groupLocation = groupFromDB.get("location").toString()
                    val groupImage = groupFromDB.get("image").toString()
                    val groupTags = groupFromDB.get("tags").toString()
                    val groupMembercount = groupFromDB.get("membercount").toString()

                    val group = Group(groupName, groupLocation, groupImage, groupTags, groupMembercount)

                    groups.add(group)
                }
                    rcList.adapter?.notifyDataSetChanged()
            }
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_group_list, container, false)

        rcList = view.findViewById(R.id.list)
        rcList.layoutManager = LinearLayoutManager(context)
        rcList.adapter = MyGroupRecyclerViewAdapter(groups)


        return view
    }

}