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
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty
import android.support.v4.app.Fragment as SupportFragment

fun <T : Any> Fragment.bindArgs(key: String, f: ((Any) -> T)? = null): ReadOnlyProperty<Any, T> = bindingDelegator(key, f)
fun <T : Any> SupportFragment.bindArgs(key: String, f: ((Any) -> T)? = null): ReadOnlyProperty<Any, T> = bindingDelegator(key, f)

fun <T> Fragment.bindOptionalArgs(key: String, f: ((Any?) -> T?)? = null): ReadOnlyProperty<Any, T> = bindingOptionalDelegator(key, f)
fun <T> SupportFragment.bindOptionalArgs(key: String, f: ((Any?) -> T?)? = null): ReadOnlyProperty<Any, T> = bindingOptionalDelegator(key, f)

@Suppress("UNCHECKED_CAST")
private fun <T : Any> getArgs(thisRef: Any, key: String, f: ((Any) -> Any)?, property: KProperty<*>): T {
    val value: Any = when (thisRef) {
        is Fragment -> {
            thisRef.arguments.get(key) ?: throw IllegalArgumentException("Arguments must have key - $key - in ${property.name}")
        }
        is SupportFragment -> {
            thisRef.arguments.get(key) ?: throw IllegalArgumentException("Arguments must have key - $key - in ${property.name}")
        }
        else -> throw IllegalStateException("Only allowed Fragment and SupportFragment")
    }

    return (f?.invoke(value) ?: value) as T
}

@Suppress("UNCHECKED_CAST")
private fun <T> getOptionalArgs(thisRef: Any, key: String, f: ((Any?) -> Any?)?, property: KProperty<*>): T {
    val value: Any? = when (thisRef) {
        is Fragment -> {
            thisRef.arguments.get(key) ?: throw IllegalArgumentException("Arguments must have key - ${key} - in ${property.name}")
        }
        is SupportFragment -> {
            thisRef.arguments.get(key) ?: throw IllegalArgumentException("Arguments must have key - ${key} - in ${property.name}")
        }
        else -> throw IllegalStateException("Only allowed Fragment and SupportFragment")
    }

    return (f?.invoke(value) ?: value) as T
}

private class bindingDelegator<T : Any>(val key: String, val f: ((Any) -> Any)?) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return getArgs(thisRef, key, f, property)
    }
}

private class bindingOptionalDelegator<T>(val key: String, val f: ((Any?) -> Any?)?) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return getOptionalArgs(thisRef, key, f, property)
    }
}