package ca.qc.cgodin.restaurant.modeleSearch


import android.location.Location


data class Geometry(
    val location: Location,
    val viewport: Viewport
)