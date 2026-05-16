package cordobot.example.myportfolioapp.domain.model

data class Project(
    val id: Int,
    val title: String,
    val description: String,
    val tags: List<String>,
    val githubUrl: String,
    val imageUrl: String? = null,
    val imageRes: Int? = null
)
