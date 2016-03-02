package com.fatdaruma.bindargs_sample

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.fatdaruma.bindargs.bindArgs
import com.fatdaruma.bindargs.bindOptionalArgs


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment : Fragment() {
    private val msg : String by bindArgs("test")
    private val msgOpt : String? by bindOptionalArgs("test2")
    private val msgCustom : String by bindArgs("test", {s -> s as String + "msgCustom"})
    private val msgOptDef : String? by bindOptionalArgs("test2", {s -> s ?: "msgOptDef"})

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater.inflate(R.layout.fragment_main, container, false)
        (view.findViewById(R.id.t) as TextView).setText(msg) // => "test"
        (view.findViewById(R.id.t2) as TextView).setText(""+msgOpt) // => ""+null => "null"
        (view.findViewById(R.id.t3) as TextView).setText(msgCustom) // => "test" -> "test"+"msgCustom" = "testmsgCustom"
        (view.findViewById(R.id.t4) as TextView).setText(msgOptDef) // => null -> "msgOptDef"
        return view
    }
}
