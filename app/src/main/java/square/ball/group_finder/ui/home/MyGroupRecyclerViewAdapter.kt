package square.ball.group_finder.ui.home

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import square.ball.group_finder.Group
import square.ball.group_finder.R


class MyGroupRecyclerViewAdapter(
        private val values: ArrayList<Group>)
    : RecyclerView.Adapter<MyGroupRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_homegroup, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val group = values[position]
        holder.nameView.text = group.name
        holder.locationView.text = group.location
        Picasso.get().load(group.image).into(holder.imageView)
        holder.tagsView.text = group.tags
        holder.membercountView.text = group.membercount
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView = view.findViewById(R.id.name)
        val locationView: TextView = view.findViewById(R.id.location)
        val imageView: ImageView = view.findViewById(R.id.image)
        val tagsView: TextView = view.findViewById(R.id.tags)
        val membercountView: TextView = view.findViewById(R.id.membercount)

    }
}