package site.dongik.goffee.view

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import site.dongik.goffee.R
import site.dongik.goffee.data.DeviceContent
import site.dongik.goffee.data.SimContent
import site.dongik.goffee.view.device.DevicesFragment
import site.dongik.goffee.view.sim.SimsFragment

class MainActivity : AppCompatActivity()
        , SimsFragment.OnListFragmentInteractionListener
        , DevicesFragment.OnListFragmentInteractionListener {

    val LOG_TAG = GoffeeActivity::class.java.simpleName

    private lateinit var mSectionsPagerAdapter: SectionsPagerAdapter

    private lateinit var tabLayout : TabLayout
    private lateinit var viewPager: ViewPager

//    private lateinit var ordersFragment: OrdersFragment

//    private val ordersFragment = OrdersFragment.newInstance(1)
//
//    init{
//        ordersFragment.listener =
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activi


        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)
        mSectionsPagerAdapter.addFragment(SimsFragment(),"Sims")
        mSectionsPagerAdapter.addFragment(DevicesFragment(),"Devices")
        viewPager = content
        viewPager.adapter = mSectionsPagerAdapter

//        viewPager = findViewById(R.id.content)
//        tabLayout = findViewById(R.id.tabs)
        tabLayout = tabs
        // Set up the ViewPager with the sections adapter.
//        container.adapter = mSectionsPagerAdapter
        tabLayout.setupWithViewPager(viewPager)
    }



    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }


    /**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        val mFragmentList = ArrayList<Fragment>()
        val mFragmentNameList = ArrayList<String>()

        fun addFragment(fragment: Fragment, name:String){
            mFragmentList.add(fragment)
            mFragmentNameList.add(name)
        }

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
//            return PlaceholderFragment.newInstance(position + 1)

            return mFragmentList[position]
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return mFragmentList.size
        }

        override fun getPageTitle(position: Int): CharSequence? {
//            return super.getPageTitle(position)
            return mFragmentNameList[position]
        }
    }

    override fun onListFragmentInteraction(item: SimContent.SimItem?) {
        Toast.makeText(this,"Hello "+item.toString(), Toast.LENGTH_SHORT).show()
        Log.d(LOG_TAG,"Hello!")
    }

    override fun onDeviceInteraction(item: DeviceContent.Device?) {
        Toast.makeText(this,"Hello "+item.toString(), Toast.LENGTH_SHORT).show()
    }
}
