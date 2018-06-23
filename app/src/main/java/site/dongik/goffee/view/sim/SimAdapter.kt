package site.dongik.goffee.view.sim

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_order.view.*
import site.dongik.goffee.R
import site.dongik.goffee.data.SimContent
import site.dongik.goffee.data.SimContent.SimItem
import site.dongik.goffee.view.sim.SimsFragment

class SimAdapter(
        private val mValues: List<SimItem>,
        private val mListener: SimsFragment.OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<SimAdapter.ViewHolder>() {

    val LOG_TAG = SimAdapter::class.java.simpleName
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as SimItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            Log.d(LOG_TAG,"OnClickeListener")
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_sim, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mName.text = item.name
        holder.mNumber.text = item.number
        holder.mOwner.text = item.owner
        holder.mHolder.text = item.holder
        holder.mCardView.apply{
            tag = item
            setOnClickListener(mOnClickListener)
        }
//
//        with(holder.mView) {
//            tag = item
//            setOnClickListener(mOnClickListener)
//        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mName: TextView = mView.findViewById(R.id.name)
        val mNumber: TextView = mView.findViewById(R.id.number)
        val mOwner: TextView = mView.findViewById(R.id.owner)
        val mHolder:TextView = mView.findViewById(R.id.holder)
        val mCardView: CardView = mView.findViewById(R.id.card)
//        override fun toString(): String {
//            return super.toString() + " '" +.text + "'"
//        }
    }
}
