package com.jrodiriguezva.rsskotlin.presentation.base

import android.os.Bundle
import com.jrodiriguezva.rsskotlin.R
import com.jrodiriguezva.rsskotlin.utils.extension.inTransaction
import kotlinx.android.synthetic.main.toolbar.*


/**
 * Base Fragment Activity class
 *
 * @see android.support.v7.app.AppCompatActivity
 */
abstract class BaseFragmentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayout())
        setSupportActionBar(toolbar)
        addFragment(savedInstanceState)
    }

    override fun onBackPressed() {
        (supportFragmentManager.findFragmentById(R.id.fragmentContainer) as BaseFragment).onBackPressed()
        super.onBackPressed()
    }

    private fun addFragment(savedInstanceState: Bundle?) =
        savedInstanceState ?: supportFragmentManager.inTransaction {
            add(R.id.fragmentContainer, fragment())
        }

    abstract fun fragment(): BaseFragment

    abstract fun getLayout(): Int
}