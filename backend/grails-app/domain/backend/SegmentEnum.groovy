package backend

enum SegmentEnum{
    VEHICLES('Vehicles'), SOFTWARE('Software'), DRINKS('Drinks')

    String descricao

    SegmentEnum(String descricao){
        this.descricao = descricao
    }
}
