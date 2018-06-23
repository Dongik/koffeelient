package site.dongik.goffee.view.drink

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_drink.view.*
import site.dongik.goffee.R
import site.dongik.goffee.data.DrinkContent
import site.dongik.goffee.data.DrinkContent.DrinkItem
import java.util.ArrayList

class DrinkAdapter(
        private val mValues: List<DrinkItem>,
        private val mListener: DrinksFragment.OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<DrinkAdapter.ViewHolder>(), Filterable {


    private var drinkListFiltered = DrinkContent.ITEMS
    val LOG_TAG = DrinkAdapter::class.java.simpleName
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as DrinkItem
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            Log.d(LOG_TAG,"OnClickeListener")
            mListener?.onDrinkItemClick(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_drink, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = drinkListFiltered[position]
        holder.mNameView.text = item.name
        holder.mPriceView.text = item.price.toString()
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

    override fun getItemCount(): Int = drinkListFiltered.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mNameView: TextView = mView.name
        val mPriceView: TextView = mView.price
        val mCardView: CardView = mView.findViewById(R.id.card)
//        override fun toString(): String {
//            return super.toString() + " '" + .text + "'"
//        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                Log.d(LOG_TAG,"performingFiltering $charSequence")
                when(charString.isEmpty()){
                    true -> drinkListFiltered = DrinkContent.ITEMS
                    false -> {
                        val filteredList = ArrayList<DrinkItem>()
                        //Search
                        for (row in DrinkContent.ITEMS) {
                            if (row.name.toLowerCase().contains(charString.toLowerCase()) or
                                    row.name.contains(charSequence))
                                filteredList.add(row)
                        }
                        drinkListFiltered = filteredList
                    }
                }

                val filterResults = Filter.FilterResults()
                filterResults.values = drinkListFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                drinkListFiltered = filterResults.values as ArrayList<DrinkItem>
                notifyDataSetChanged()
            }
        }
    }
}