package com.axis.jgbbackend.model

class Product {
    lateinit var name:String
    var price:Double = 0.0
    constructor()
    constructor(
            name: String,
            price: Double,
    ) {
        this.name=name
        this.price = price

    }
    override fun toString(): String {
        return "Product{name='$name', price=$price}"
    }
}