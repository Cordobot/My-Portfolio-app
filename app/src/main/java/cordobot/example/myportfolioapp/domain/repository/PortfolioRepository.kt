package cordobot.example.myportfolioapp.domain.repository

import cordobot.example.myportfolioapp.domain.model.Experience
import cordobot.example.myportfolioapp.domain.model.Project
import cordobot.example.myportfolioapp.domain.model.TechSkill

interface PortfolioRepository {
    suspend fun getProjects(): List<Project>
    suspend fun getExperience(): List<Experience>
    suspend fun getTechSkills(): List<TechSkill>
}
