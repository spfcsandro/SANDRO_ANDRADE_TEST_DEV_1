package backend

class Company {

   Long id
    String name
    SegmentEnum segment

    static hasMany = [stocks: Stock]

    static constraints = {
        name (blank:false, nullable: false)
        segment nullable: false
    }

    static mapping = {
        stocks fetch: 'join'
    }
}
