package com.spinuts.app.data

data class Product(
    val id: String,
    val nameEn: String,
    val nameTa: String,
    val unit: String,
    val category: String,
    val price: Double
)

object ProductRepo {
    val items = listOf(
        Product("SPC-001", "Turmeric Powder", "மஞ்சள் தூள்", "100g", "Spices", 25.0),
        Product("SPC-002", "Chilli Powder", "மிளகாய் தூள்", "100g", "Spices", 30.0),
        Product("SPC-003", "Black Pepper",   "மிளகு",      "50g",  "Spices", 45.0),
        Product("NUT-001", "Cashew (W320)",  "முந்திரி",     "250g", "Nuts",   199.0),
        Product("NUT-002", "Almonds",        "பாதாம்",       "250g", "Nuts",   165.0),
        Product("SPC-004", "Cardamom",       "ஏலக்காய்",    "25g",  "Spices", 120.0),
        Product("SPC-005", "Cumin Seeds",    "சீரகம்",       "100g", "Spices", 32.0),
        Product("OIL-001", "Groundnut Oil",  "நடக்கடலை எண்ணெய்", "1L", "Staples", 150.0),
    )

    fun byId(id: String) = items.firstOrNull { it.id == id }
}
