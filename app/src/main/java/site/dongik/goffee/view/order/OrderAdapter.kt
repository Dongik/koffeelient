package site.dongik.goffee.view.order

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import site.dongik.goffee.R


import site.dongik.goffee.view.order.OrdersFragment.OnListFragmentInteractionListener

import kotlinx.android.synthetic.main.fragment_order.view.*
import site.dongik.goffee.data.OrderContent.OrderItem

class OrderAdapter(
        private val mValues: List<OrderItem>,
        private val mListener: OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    val LOG_TAG = OrderAdapter::class.java.simpleName
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as OrderItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            Log.d(LOG_TAG,"OnClickeListener")
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_order, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mTitle.text = item.title
        holder.mCount.text = item.count.toString()
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
        val mTitle: TextView = mView.title
        val mCount: TextView = mView.count
        val mCardView: CardView = mView.card
//        override fun toString(): String {
//            return super.toString() + " '" +.text + "'"
//        }
    }
}
