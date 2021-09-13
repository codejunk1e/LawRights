package io.github.codejunk1e.lawrights.ext

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Test
import kotlin.math.roundToInt

class ExtensionsKtTest {
    lateinit var context : Context

    @Before
    fun init() {
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun dpToPxIsCorrect(){
        val pixel = 30
        val result = pixel.dpToPx(context.resources.displayMetrics)
        assertThat(
            "Dp to Px is correctly converted!",
            pixel == (result / context.resources.displayMetrics.density).roundToInt()
        )
    }
}