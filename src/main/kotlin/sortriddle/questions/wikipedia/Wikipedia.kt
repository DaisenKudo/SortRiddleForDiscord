package sortriddle.questions.wikipedia

import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import sortriddle.questions.wikipedia.retrofit.MediaWikiAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.IllegalStateException

class Wikipedia {
    private var randomAPI: MediaWikiAPI? = null

    init {
        this.randomAPI = Retrofit.Builder()
                .baseUrl(Resource.root)
                .addConverterFactory(GsonConverterFactory.create(Gson()))
                .build()
                .create(MediaWikiAPI::class.java)
    }

    /**
     * Wikipediaのランダムな記事タイトルを返します
     *
     * @return title: String
     */
    fun getRandomWikiTitle(): String = runBlocking {
        withContext(Dispatchers.Default) {
            this@Wikipedia.randomAPI?.call()
        }?.body()?.query?.let{
            it.pages[it.pageids[0]]?.title
        } ?: throw IllegalStateException("Wikipediaからのデータ取得に失敗")
    }
}