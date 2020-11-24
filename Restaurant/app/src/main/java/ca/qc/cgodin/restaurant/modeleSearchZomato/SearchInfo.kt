package ca.qc.cgodin.restaurant.modeleSearchZomato

data class SearchInfo(
    val restaurants: List<Restaurant>,
    val results_found: Int,
    val results_shown: Int,
    val results_start: Int
)