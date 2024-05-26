package com.nikolovlazar.goodbyemoney.models


import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import io.realm.kotlin.types.ObjectId

class User : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId.create()
    var email: String = ""
    var password: String = ""
}