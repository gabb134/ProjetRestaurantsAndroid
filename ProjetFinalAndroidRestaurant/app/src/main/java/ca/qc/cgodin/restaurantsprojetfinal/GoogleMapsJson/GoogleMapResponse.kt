package ca.qc.cgodin.restaurantsprojetfinal.GoogleMapsJson

data class GoogleMapResponse(
    val candidates: List<Candidate>,
    val debug_log: DebugLog,
    val status: String
)