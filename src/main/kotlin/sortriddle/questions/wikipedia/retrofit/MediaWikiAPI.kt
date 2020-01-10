package sortriddle.questions.wikipedia.retrofit

import sortriddle.questions.wikipedia.Resource
import retrofit2.http.GET
import retrofit2.Response

interface MediaWikiAPI {
    @GET(Resource.random)
    suspend fun call(): Response<MediaWikiAPIResponseModel>
}

data class MediaWikiAPIResponseModel (
    val query: Query
)

data class Query(
    val pageids: Array<String>,
    val pages: HashMap<String, Property>
) {
    override fun equals(other: Any?): Boolean =
        when {
            this === other -> true
            javaClass != other?.javaClass -> false
            else -> {
                other as Query
                when {
                    pageids.contentEquals(other.pageids) -> false
                    pages != other.pages -> false
                    else -> true
                }
            }
        }


    override fun hashCode(): Int = 31 * pageids.contentHashCode() + pages.hashCode()
}

data class Property(val title: String)