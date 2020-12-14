package ca.qc.cgodin.restaurant.modeleMenu

data class Menu(
    val daily_menus: List<DailyMenu>,
    val status: String
)