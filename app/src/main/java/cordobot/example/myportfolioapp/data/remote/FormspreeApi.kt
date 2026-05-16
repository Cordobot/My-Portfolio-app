package cordobot.example.myportfolioapp.data.remote

import kotlinx.serialization.Serializable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

@Serializable
data class ContactRequest(
    val name: String,
    val email: String,
    val message: String
)

interface FormspreeApi {
    @POST("f/xjgjdggg")
    suspend fun sendMessage(@Body request: ContactRequest): Response<Unit>
}
