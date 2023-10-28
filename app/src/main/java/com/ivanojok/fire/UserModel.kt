package com.ivanojok.fire

//data class UserModel(
//    val id:String?,
//    val name:String?,
//    var email:String?,
//    var password:String?,
//    var phone:String?,
//    var position:String = "Worker"
//)

class UserModel {
    var id:String ?= null
    var name:String?= null
    var email:String?= null
    var password:String?= null
    var phone:String?= null
    var position:String = "Worker"
    constructor(id:String?,
               name:String?,
               email:String?,
               password:String?,
               phone:String?,
               position:String = "Worker") {
        this.id = id
        this.name = name
        this.email = email
        this.position = position
        this.password = password
        this.phone = phone
    }

    constructor()
}