package backend

import java.time.LocalDateTime

class Stock {

    Long id
    double price
    LocalDateTime priceDate

    static belongsTo = [company: Company]


    static constraints = {
        price(scale: 2)
        priceDate nullable:false
    }
}
