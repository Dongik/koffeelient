package site.dongik.goffee.view.drink

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.jakewharton.rxbinding2.widget.RxTextView
import com.jakewharton.rxbinding2.widget.TextViewTextChangeEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import site.dongik.goffee.R
import site.dongik.goffee.data.DrinkContent
import java.util.concurrent.TimeUnit

class DrinksFragment : Fragment(){

    // TODO: Customize parameters
    private var columnCount = 1
    private lateinit var inputSearch:EditText
    private lateinit var mAdapter: DrinkAdapter
    private val disposable = CompositeDisposable()
    var listener: OnListFragmentInteractionListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        listener = activity as OnListFragmentInteractionListener


        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_drink_list, container, false)

        // Set the adapter
        mAdapter = DrinkAdapter(DrinkContent.ITEMS, listener)
        view.findViewById<RecyclerView>(R.id.list).apply{
            layoutManager = LinearLayoutManager(context)
            adapter =mAdapter
        }

        inputSearch = view.findViewById<EditText>(R.id.search)
        disposable.add(RxTextView.textChangeEvents(inputSearch)
                .skipInitialValue()
                .debounce(300, TimeUnit.MILLISECONDS)
                /*.filter(new Predicate<TextViewTextChangeEvent>() {
                    @Override
                    public boolean test(TextViewTextChangeEvent textViewTextChangeEvent) throws Exception {
                        return TextUtils.isEmpty(textViewTextChangeEvent.text().toString()) || textViewTextChangeEvent.text().toString().length() > 2;
                    }
                })*/
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(searchDrinks()))


        return view
    }

    private fun searchDrinks(): DisposableObserver<TextViewTextChangeEvent> {
        return object : DisposableObserver<TextViewTextChangeEvent>() {
            override fun onNext(textViewTextChangeEvent: TextViewTextChangeEvent) {
//                Log.d(DrinkSearchActivity.TAG, "Search query: " + textViewTextChangeEvent.text())
                mAdapter.getFilter().filter(textViewTextChangeEvent.text())

            }
            override fun onError(e: Throwable) {
                Log.e("DrinksFragment", "onError: " + e.message)
            }
            override fun onComplete() {

            }
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnListFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson
     * [Communicating with Other Fragments](http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onDrinkItemClick(item: DrinkContent.DrinkItem?)
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
                DrinksFragment().apply {
                    arguments = Bundle().apply {
                        putInt(ARG_COLUMN_COUNT, columnCount)
                    }
                }
    }
}
