package io.github.codejunk1e.lawrights.datasource.local

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProdDB

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class InMemoryDB