package ca.qc.cgodin.restaurant.modeleSearchZomato

data class UserRating(
    val aggregate_rating: Any,
    val rating_color: String,
    val rating_obj: RatingObj,
    val rating_text: String,
    val votes: Int
)