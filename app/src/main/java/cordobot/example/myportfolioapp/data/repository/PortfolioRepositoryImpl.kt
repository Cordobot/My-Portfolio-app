package cordobot.example.myportfolioapp.data.repository

import cordobot.example.myportfolioapp.domain.model.Experience
import cordobot.example.myportfolioapp.domain.model.Project
import cordobot.example.myportfolioapp.domain.model.TechSkill
import cordobot.example.myportfolioapp.domain.repository.PortfolioRepository
import cordobot.example.myportfolioapp.R

class PortfolioRepositoryImpl : PortfolioRepository {
    override suspend fun getProjects(): List<Project> = listOf(
        Project(
            id = 1,
            title = "Mi Portafolio",
            description = "Mi portafolio personal desarrollado con las últimas tecnologías web y ahora en Android nativo.",
            tags = listOf("Kotlin", "Compose"),
            githubUrl = "https://github.com/Cordobot/My-Portfolio-app"
        ),
        Project(
            id = 2,
            title = "Mallero",
            description = "Gestión de turnos de trabajo personalizada con integración de base de datos en tiempo real.",
            tags = listOf("Kotlin", "Supabase"),
            githubUrl = "https://github.com/Cordobot/mallero"
        ),
        Project(
            id = 3,
            title = "Arbarrio",
            description = "Plataforma de alquiler de propiedades con autenticación moderna y Google Maps.",
            tags = listOf("Firebase", "Maps"),
            githubUrl = "https://github.com/Cordobot/Arbarrio"
        ),
        Project(
            id = 4,
            title = "Minicosto",
            description = "Modernización de carrusel de héroe y marquesina de marcas para e-commerce.",
            tags = listOf("JS", "CSS"),
            githubUrl = "https://github.com/Cordobot"
        )
    )

    override suspend fun getExperience(): List<Experience> = listOf(
        Experience(1, "2023 - Presente", "Senior Android Developer", "Freelance / Cordobot", "Desarrollo de aplicaciones robustas utilizando Jetpack Compose, Koin para DI y arquitecturas escalables."),
        Experience(2, "2022 - 2023", "Desarrollador Web Fullstack", "Proyectos Independientes", "Creación de plataformas web modernas con React, Next.js y TailwindCSS.")
    )

    override suspend fun getTechSkills(): List<TechSkill> = listOf(
        TechSkill(1, "Kotlin", R.drawable.ic_tech_kotlin, "#7F52FF"),
        TechSkill(2, "Compose", R.drawable.ic_tech_compose, "#4285F4"),
        TechSkill(3, "Android Studio", R.drawable.ic_tech_sdk, "#3DDC84"),
        TechSkill(4, "Git / GitHub", R.drawable.ic_github, "#F05032"),
        TechSkill(5, "JUnit + MockK", R.drawable.ic_tech_clean, "#E74C3C"),
        TechSkill(6, "Retrofit", R.drawable.ic_tech_sdk, "#3498DB"),
        TechSkill(7, "Firebase", R.drawable.ic_tech_firebase, "#FFCA28"),
        TechSkill(8, "Room", R.drawable.ic_tech_room, "#2ECC71")
    )
}
