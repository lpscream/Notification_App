package net.matrixhome.notificationapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import net.matrixhome.notificationapp.R

class PageFragment(): Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.viewpage_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val text: TextView = view.findViewById(R.id.fragment_number)
        val plusBtn: Button = view.findViewById(R.id.btn_plus)
        val minusBtn: Button = view.findViewById(R.id.btn_minus)
        val pushBtn: Button = view.findViewById(R.id.push_button)
        text.text = arguments?.getInt(PAGE_NUMBER).toString()
    }
}