package ca.qc.cgodin.restaurant.modeleMenu

data class DailyMenuX(
    val daily_menu_id: String,
    val dishes: List<Dishe>,
    val end_date: String,
    val name: String,
    val start_date: String
)