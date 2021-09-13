package io.github.codejunk1e.lawrights.datasource.mappers

import io.github.codejunk1e.lawrights.datasource.local.RightsEntity
import io.github.codejunk1e.lawrights.datasource.network.responses.Rights

fun List<Rights>.toRightsEntity() :List<RightsEntity>{
    return this.map {
        RightsEntity(it.id, it.title)
    }
}