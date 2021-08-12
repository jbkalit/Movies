package com.jbkalit.data.source.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jbkalit.data.service.MovieService
import com.jbkalit.domain.model.Movie
import com.jbkalit.domain.model.request.Movies
import retrofit2.HttpException
import java.io.IOException

class MoviesPagingSource(val movieService: MovieService, val query: Int)
    : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        //for first case it will be null, then we can pass some default value, in our case it's 1
        val page = params.key ?: DEFAULT_PAGE_INDEX
        return try {
            val response = loadPage(page)
            LoadResult.Page(
                response.results,
                prevKey = if (page == DEFAULT_PAGE_INDEX) null else page - 1,
                nextKey = if (page < response.total_pages) response.page.plus(1) else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    private suspend fun loadPage(page: Int): Movies {
        return movieService.getMoviesByGenre(page, query)
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPageIndex = state.pages.indexOf(state.closestPageToPosition(anchorPosition))
            state.pages.getOrNull(anchorPageIndex + 1)?.prevKey ?: state.pages.getOrNull(anchorPageIndex - 1)?.nextKey
        }
    }

    companion object {
        const val DEFAULT_PAGE_INDEX = 1
    }

}
