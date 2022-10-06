package net.ivanvega.misrecyclersviews.data

object DataSource {
    val lsFlower  = mutableListOf<Flower>()
    fun add(flower: Flower) {
        lsFlower.add(flower)
    }
}