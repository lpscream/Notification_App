package net.matrixhome.notificationapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnLayout
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.snackbar.Snackbar
import net.matrixhome.notificationapp.App
import net.matrixhome.notificationapp.FRAGMENT_ID
import net.matrixhome.notificationapp.R

class MainFragment : Fragment(), ViewPagerAdapter.ButtonClickListener {

    private lateinit var pagerAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager2
    private lateinit var notification: AppNotification
    private var pageNum: Int? = null
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((activity?.application as App).repo)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pagerAdapter = ViewPagerAdapter(this)
        viewPager = view.findViewById(R.id.viewpager)
        viewPager.adapter = pagerAdapter
        notification = AppNotification(requireContext())
        pageNum = arguments?.getString(FRAGMENT_ID)?.toInt()
        viewModel.pages.observe(requireActivity(), Observer {
            if (savedInstanceState == null){
                pagerAdapter.update(it)
            }
            if (pageNum != null && pageNum != -1){
                pageNum = pageNum!! - 1
                viewPager.setCurrentItem(pageNum!!, false)
                pageNum = -1
            }
        })
    }

    override fun onButtonClick(view: View) {
        when(view.id){
            R.id.btn_plus -> viewModel.insert()
            R.id.btn_minus -> viewModel.delete()
            R.id.push_button -> {notification.build((viewPager.currentItem + 1).toString())}
        }
    }
}

