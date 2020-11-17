package ca.qc.cgodin.restaurant.modeleSearch

import ca.qc.cgodin.restaurant.modeleSearch.Northeast
import ca.qc.cgodin.restaurant.modeleSearch.Southwest


data class Viewport(
    val northeast: Northeast,
    val southwest: Southwest
)