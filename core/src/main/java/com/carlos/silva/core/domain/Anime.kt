package com.carlos.silva.core.domain
import com.carlos.silva.core.Utils
import org.jsoup.nodes.Element
import java.io.Serializable

data class Anime(
    val videoId: Int,
    val title: String,
    val timer: String,
    val imagePath: String,
    val link: String
): Serializable {
    companion object {

        const val ANIME_EXTRA = "anime_bundle"

        fun mount(element: Element): Anime {
            val elementA = element.select("a")
            val link = elementA.attr("href").toString()
            val title = elementA.attr("title").toString()
            val imagePath = element.select("img").attr("src")
            val videoId = Utils.getDigitFromString(link)
            return Anime(
                videoId,
                title,
                "",
                imagePath,
                link
            )
        }
    }
}

