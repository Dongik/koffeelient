package site.dongik.goffee.view.user

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import site.dongik.goffee.R
import site.dongik.goffee.model.Model

class UserAdapter(
        private val context: Context,
        private val userList: List<Model.User>,
        private val listener: UserAdapterListener):
        RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var name: TextView = view.findViewById(R.id.name)
        var userId :TextView = view.findViewById(R.id.user_id)
        var status: TextView = view.findViewById(R.id.status)
//        var status: TextView = view.findViewById(R.id.stust)
        //        var thumbnail: ImageView
        init {
//            thumbnail = view.findViewById(R.id.thumbnail)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
                .from(parent.context).inflate(R.layout.item_user, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = userList[position]
        holder.name.text = user.name
        holder.userId.text = user.id
        holder.status.text = user.status
//        holder.price.text = order.number.toString()
    }
    override fun getItemCount() =  userList.size

    interface UserAdapterListener {
        fun onUserSelected(user: Model.User)
    }
}