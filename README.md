# BindArgs
Arguments of Fragment injection library for Android. This works with Kotlin.

You can

+ use `val` instead of `var`
+ easily retrieve a value applied a function
+ make your codes be slim

# Usage

```gradle
ext.bindargs_version="0.0.1"

repositories {
    maven { url 'http://jmatsu.github.io/BindArgs/repo' }
}

dependencies {
    compile "com.fatdaruma:bindargs:$bindargs_version"
}
```

This depends on support library v4.

# Samples

A simple case without BindArgs, following

```kotlin
class SampleFragment : Fragment() {
    private var msg : String = ""
    private var msgOpt : String? = null
    private var msgCustom : String = ""
    private var msgOptDef : String? = null

    companion object {
        fun getSampleInstance() : SampleFragment {
            val sf = SampleFragment()
            Bundle bundle = Bundle()
            bundle.putString("test", "test")
            sf.setArguments(bundle)
            return sf;
        }
    }

    // sample invocations.
    override onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        val view = inflater.inflate(LAYOUT_RESOURCE_ID, container, false)

        msg = getArguments().getString("test")
            // => "test"
        msgOpt = getArguments().getString("test2")
            // => null
        msgCustom = getArguments().getString("test") + msgCustom
            // => "test"+"msgCustom"
        msgOptDef = getArguments().getString("test2", "msgOptDef")
            // => "msgOptDef"

        return view;
    }
}
```

Use with BindArgs case, following

```kotlin
class SampleFragment : Fragment() {
    private val msg : String by bindArgs("test")
        // => "test"
    private val msgOpt : String? by bindOptionalArgs("test2")
        // => null
    private val msgCustom : String by bindArgs("test", {s -> s as String + "msgCustom"})
        // => "testmsgCustom" ("test" -> "test"+"msgCustom")
    private val msgOptDef : String? by bindOptionalArgs("test2", {s -> s ?: "msgOptDef"})
        // => "msgOptDef" (null -> null ?: "msgOptDef")

    companion object {
        fun getSampleInstance() : SampleFragment {
            val sf = SampleFragment()
            Bundle bundle = Bundle()
            bundle.putString("test", "test")
            sf.setArguments(bundle)
            return sf;
        }
    }
}
```

# License

    Copyright 2015 Jumpei Matsuda

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
