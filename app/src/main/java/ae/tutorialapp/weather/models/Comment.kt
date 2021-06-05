package ae.tutorialapp.weather.models

data class Comment(
    var userId: Int? = null,
    var id: Int? = null,
    var name: String? = null,
    var email: String? = null,
    var body: String? = null
)
