package ca.qc.cgodin.restaurant.modeleDetailsZomato

data class UserRating(
    val aggregate_rating: String,
    val rating_color: String,
    val rating_obj: RatingObj,
    val rating_text: String,
    val votes: Int
)