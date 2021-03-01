package com.axis.jgbbackend.service.impl

import com.axis.jgbbackend.model.Product
import org.slf4j.LoggerFactory
import java.util.*

class ProductService {
    var logger = LoggerFactory.getLogger(ProductService::class.java)
    fun getProducts():List<Product> {
            logger.debug("Product service returning list of products")
            return Arrays.asList(Product("Laptop", 31000.00), Product("Mobile", 16000.00),
                    Product("Tablet", 15000.00), Product("Camera", 23000.00))
        }
}