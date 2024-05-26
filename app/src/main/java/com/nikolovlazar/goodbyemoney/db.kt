package com.nikolovlazar.goodbyemoney

import com.nikolovlazar.goodbyemoney.models.Category
import com.nikolovlazar.goodbyemoney.models.Expense
import com.nikolovlazar.goodbyemoney.models.User
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

val config = RealmConfiguration.create(schema = setOf(Expense::class, Category::class, User::class))
val db: Realm = Realm.open(config)