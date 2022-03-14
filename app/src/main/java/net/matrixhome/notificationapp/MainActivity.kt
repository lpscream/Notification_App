package net.matrixhome.notificationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import net.matrixhome.notificationapp.ui.MainFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bundle = Bundle()
        bundle.putString(FRAGMENT_ID, intent.getStringExtra(FRAGMENT_ID))
        val fragment = MainFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, fragment)
            .commit()
    }
}