package com.ivanojok.fire

class ProductModel {
    var name:String ?= null
    var brand:String?= null
    var price:String?= null
    var size:String?= null
    var desc:String?= null
    var quantity:String = "Worker"

    constructor(
        name: String?,
        brand: String?,
        price: String?,
        size: String?,
        desc: String?,
        quantity: String
    ) {
        this.name = name
        this.brand = brand
        this.price = price
        this.size = size
        this.desc = desc
        this.quantity = quantity
    }

    constructor()
}