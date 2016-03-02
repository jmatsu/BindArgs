/**
 * Copyright 2015 Jumpei Matsuda
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.fatdaruma.bindargs

import android.app.Fragment
import android.support.v4.app.Fragment as SupportFragment

fun <T : Any> Fragment.bindArgs(key: String, f: ((Any) -> T)? = null): Lazy<T> = lazy { getArgs(this, key, f) }
fun <T : Any> SupportFragment.bindArgs(key: String, f: ((Any) -> T)? = null): Lazy<T> = lazy { getArgs(this, key, f) }

fun <T : Any> Fragment.bindOptionalArgs(key: String, f: ((Any?) -> T?)? = null): Lazy<T?> = lazy { getOptionalArgs(this, key, f) }
fun <T : Any> SupportFragment.bindOptionalArgs(key: String, f: ((Any?) -> T?)? = null): Lazy<T?> = lazy { getOptionalArgs(this, key, f) }

@Suppress("UNCHECKED_CAST")
private fun <T : Any> getArgs(thisRef: Any, key: String, f: ((Any) -> T)?): T {
    val value: Any = when (thisRef) {
        is Fragment -> {
            thisRef.arguments.get(key) ?: throw IllegalArgumentException("Arguments must have key - $key")
        }
        is SupportFragment -> {
            thisRef.arguments.get(key) ?: throw IllegalArgumentException("Arguments must have key - $key")
        }
        else -> throw IllegalStateException("Only allowed Fragment and SupportFragment")
    }

    f ?: return (value as T)
    return f.invoke(value)
}

@Suppress("UNCHECKED_CAST")
private fun <T : Any> getOptionalArgs(thisRef: Any, key: String, f: ((Any?) -> T?)?): T? {
    val value: Any? = when (thisRef) {
        is Fragment -> {
            thisRef.arguments.get(key) ?: throw IllegalArgumentException("Arguments must have key - $key")
        }
        is SupportFragment -> {
            thisRef.arguments.get(key) ?: throw IllegalArgumentException("Arguments must have key - $key")
        }
        else -> throw IllegalStateException("Only allowed Fragment and SupportFragment")
    }

    f ?: return (value as T)
    return f.invoke(value)
}