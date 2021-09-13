package io.github.codejunk1e.lawrights.datasource.local

import io.github.codejunk1e.lawrights.datasource.mappers.toRightsEntity
import io.github.codejunk1e.lawrights.datasource.network.responses.Rights
import org.junit.Assert.*
import org.junit.Test

class RightsEntityKtTest {

    @Test
    fun `database mapper test`() {
        val rights = listOf(
            Rights(10, 0, "TAXATION", "2020-08-03 01:25:38"),
            Rights(11, 0, "DATA PROTECTION AND PRIVACY", "2020-08-03 01:25:38"),
        )
        val result = rights.toRightsEntity()

        result.forEachIndexed { index, value ->
            assertEquals(value.id, rights[index].id)
            assertEquals(value.title, rights[index].title)
        }
    }
}