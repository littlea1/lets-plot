/*
 * Copyright (c) 2019. JetBrains s.r.o.
 * Use of this source code is governed by the MIT license that can be found in the LICENSE file.
 */

package jetbrains.datalore.base.async.asyncAssert

import jetbrains.datalore.base.async.Async
import jetbrains.datalore.base.async.Asyncs
import jetbrains.datalore.base.async.PlatformAsyncs
import jetbrains.datalore.base.async.SimpleAsync
import jetbrains.datalore.base.async.asyncAssert.AsyncMatchers.failed
import jetbrains.datalore.base.async.asyncAssert.AsyncMatchers.failureIs
import jetbrains.datalore.base.async.asyncAssert.AsyncMatchers.result
import org.hamcrest.Matcher
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import kotlin.test.Test
import kotlin.test.fail

class AsyncMatchersTest {

    @Test
    fun resultSucceeded() {
        assertionFailure(Asyncs.constant(239), result(`is`(238)),
                "\n" +
                        "Expected: a successful async which result is <238>\n" +
                        "     but: result was <239>"
        )
    }

    @Test
    fun resultFailed() {
        assertionFailure(Asyncs.failure<Int>(Throwable()), result(`is`(0)),
                "\n" +
                        "Expected: a successful async which result is <0>\n" +
                        "     but: failed with exception: <java.lang.Throwable>"
        )
    }

    @Test
    fun resultUnfinished() {
        val first = SimpleAsync<Int>()
        val second = SimpleAsync<Int>()
        val composite = PlatformAsyncs.composite(listOf(first, second))
        first.success(0)
        assertionFailure(composite, AsyncMatchers.succeeded(),
                "\n" +
                        "Expected: a successful async which result ANYTHING\n" +
                        "     but: isn't finished yet"
        )
    }

    @Test
    fun failureSucceeded() {
        assertionFailure(Asyncs.constant(239), failed(),
                "\n" +
                        "Expected: a failed async which failure ANYTHING\n" +
                        "     but: was a successful async with value: <239>"
        )
    }

    @Test
    fun failureMessagePrefix() {
        assertThat(Asyncs.failure<Any>(IllegalStateException("12")), failureIs(IllegalStateException::class.java, "1"))
    }

    @Test
    fun failureMessage() {
        assertionFailure(Asyncs.failure<Any>(IllegalStateException("1")), failureIs(IllegalStateException::class.java, "2"),
                "\n" +
                        "Expected: a failed async which failure <java.lang.IllegalStateException: 2>\n" +
                        "     but: failure was <java.lang.IllegalStateException: 1>"
        )
    }

    @Test
    fun failureClassMismatch() {
        assertionFailure(Asyncs.failure<Any>(IllegalStateException("1")), failureIs(RuntimeException::class.java, "1"),
                "\n" +
                        "Expected: a failed async which failure <java.lang.RuntimeException: 1>\n" +
                        "     but: failure was <java.lang.IllegalStateException: 1>"
        )
    }

    private fun <ResultT> assertionFailure(async: Async<ResultT>, matcher: Matcher<in Async<ResultT>>, expectedMessage: String) {
        try {
            assertThat(async, matcher)
            fail()
        } catch (e: AssertionError) {
            assertThat<String>(e.message, `is`(expectedMessage))
        }

    }
}
