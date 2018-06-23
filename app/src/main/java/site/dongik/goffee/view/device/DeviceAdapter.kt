package site.dongik.goffee.view.device

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import site.dongik.goffee.R
import site.dongik.goffee.data.DeviceContent.Device


class DeviceAdapter(
        private val mValues: List<Device>,
        private val mListener: DevicesFragment.OnListFragmentInteractionListener?)
    : RecyclerView.Adapter<DeviceAdapter.ViewHolder>() {

    val LOG_TAG = DeviceAdapter::class.java.simpleName
    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as Device
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            Log.d(LOG_TAG,"OnClickeListener")
            mListener?.onDeviceInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_device, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mName.text = item.name
        holder.mSerialNumber.text = item.serialNumber
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
        val mSerialNumber: TextView = mView.findViewById(R.id.serial_number)
        val mOwner: TextView = mView.findViewById(R.id.owner)
        val mHolder: TextView = mView.findViewById(R.id.holder)
        val mCardView: CardView = mView.findViewById(R.id.card)
//        override fun toString(): String {
//            return super.toString() + " '" +.text + "'"
//        }
    }
}
