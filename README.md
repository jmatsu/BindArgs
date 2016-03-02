# BindArgs
Arguments of Fragment injection library for Android. This works with Kotlin.

+ Use `val` instead of `var`
+ Easy to obtain a value applied a function

# Usage

```gradle
ext {
    bindargsVersion="1.0.0"
}

repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    compile "com.github.jmatsu:BindArgs:$bindargsVersion"
}
```

This depends on support library v4.

# Samples

A simple case without BindArgs, following

```kotlin
class SampleFragment : Fragment() {
    private lateinit var msg : String
    private lateinit var msgCustom : String
    private var primitive : Int = 0

    companion object {
        fun getSampleInstance() : SampleFragment {
            val sf = SampleFragment()
            val bundle = Bundle()
            bundle.putString("test", "test")
            bundle.putInt("test2", 3)
            sf.arguments = bundle
            return sf;
        }
    }

    // sample invocations.
    override onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        val view = inflater.inflate(LAYOUT_RESOURCE_ID, container, false)

        msg = arguments.getString("test")
            // => "test"
        msgCustom = arguments.getString("test") + "msgCustom"
            // => "test"+"msgCustom"
        primitive = arguments.getInteger("test2")

        return view;
    }
}
```

Use with BindArgs case, following

```kotlin
class SampleFragment : Fragment() {
    private val msg : String by bindArgs("test")
        // => "test"
    private val msgCustom : String by bindArgs("test", {s -> s as String + "msgCustom"})
        // => "testmsgCustom" ("test" -> "test"+"msgCustom")
    private val primitive : Int by bindArgs("test2")

    companion object {
        fun getSampleInstance() : SampleFragment {
            val sf = SampleFragment()
            val bundle = Bundle()
            bundle.putString("test", "test")
            bundle.putInteger("test2", 3)
            sf.arguments = bundle
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
