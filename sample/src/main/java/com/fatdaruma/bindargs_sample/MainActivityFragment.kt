package com.fatdaruma.bindargs_sample

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.fatdaruma.bindargs.bindArgs
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * A placeholder fragment containing a simple view.
 */
class MainActivityFragment : Fragment() {
    companion object {
        @JvmStatic
        fun newInstance(): MainActivityFragment = MainActivityFragment().apply {
            with (Bundle()) {
                putString(keyMessage, "This is test message")

                arguments = this
            }
        }

        private val keyMessage: String = "keyMessage"
    }

    private val msg: String by bindArgs(keyMessage)
    private val msgCustom: String by bindArgs(keyMessage) { s -> s as String + " with adding buf" }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        t.text = msg            // => "This is test message"
        t2.text = msgCustom     // => "This is test message" -> "This is test message" + " with adding buf" = "This is test message with adding buf"
    }
}
