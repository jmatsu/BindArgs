# BindArgs
Arguments of Fragment injection library for Android. This works with Kotlin.

# Samples

    public class SampleFragment : Fragment() {
        private val msg : String by bindArgs("test") // => "test"
        private val msgOpt : String? by bindOptionalArgs("test2") // => ""+null => "null"
        private val msgCustom : String by bindArgs("test", {s -> s as String + "msgCustom"}) // => "test" -> "test"+"msgCustom" = "testmsgCustom"
        private val msgOptDef : String? by bindOptionalArgs("test2", {s -> s ?: "msgOptDef"}) // => null -> "msgOptDef"

        public static SampleFragment getSampleInstance() {
            SampleFragment sf = new SampleFragment();
            Bundle bundle = new Bundle();
            bundle.putString("test", "test")
            sf.setArguments(bundle);
            return sf;
        }
    }

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
