package com.jbkalit.data.mock

import com.jbkalit.domain.model.Genre
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.Review
import com.jbkalit.domain.model.Video
import com.jbkalit.domain.model.request.Genres
import com.jbkalit.domain.model.request.Movies
import com.jbkalit.domain.model.request.Reviews
import com.jbkalit.domain.model.request.Videos

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

val genre = Genre(
    28,
    "Action"
)

val genres = Genres(
    listOf(genre)
)

val movie = Movie(9485,
    false,
    "/xXHZeb1yhJvnSHPzZDqee0zfMb6.jpg",
    "en",
    "F9",
    "Dominic Toretto and his crew battle the most skilled assassin and high-performance driver they've ever encountered: his forsaken brother.",
    3064.667,
    "/bOFaAXmWWXC3Rbv4u4uM9ZSzRXP.jpg",
    "2021-05-19",
    "F9",
    false,
    7.8,
    311
)

val movies = Movies(
    1,
    1,
    1,
    listOf(movie)
)

val review = Review(
    "60d73bb6109cd0004709b24e",
    "Peter89Spencer",
    "https://www.themoviedb.org/review/60d73bb6109cd0004709b24e",
    "This delay was worth waiting for...First of, it was really cool how they did the flashback"
)

val reviews = Reviews(
    1,
    1,
    1,
    listOf(review)
)

val video = Video(
    "5e33a7384ca67600124e7bb2",
    "Fast & Furious 9 - Teaser Trailer | \"Things Change\" (2021)",
    "AvrW9mVE9qU",
    1080,
)

val videos = Videos(
    listOf(video)
)
