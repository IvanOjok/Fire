package com.ivanojok.fire

data class UserModel(
    val id:String?,
    val name:String?,
    var email:String?,
    var password:String?,
    var phone:String?,
    var position:String = "Owner"
)
