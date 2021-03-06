package com.example.mobilliumcase.binding

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.mobilliumcase.data.model.MovieDetail
import com.example.mobilliumcase.data.model.MovieResult
import com.example.mobilliumcase.util.Constants

@BindingAdapter("moviePoster")
fun setMoviePoster(imageView: AppCompatImageView, url: String?) {
    Glide.with(imageView.context)
        .load(Constants.BASE_IMAGE_URL_API + url)
        .timeout(15000)
        .fitCenter()
        .into(imageView)
}

@BindingAdapter("movieBackground")
fun setMovieBackground(imageView: AppCompatImageView, url: String?) {
    Glide.with(imageView.context)
        .load(Constants.BASE_IMAGE_URL_w500_API + url)
        .into(imageView)
}

@BindingAdapter("searchText")
fun setSearchText(textView: AppCompatTextView, movie: MovieResult) {
    textView.text = if (movie.release_date.length > 3) {
        val year = movie.release_date.subSequence(0, 4)
        movie.title + " (" + year + ")"
    } else {
        movie.title
    }
}

@BindingAdapter("detailText")
fun setDetailText(textView: AppCompatTextView, movie: MovieDetail) {
   /* if (!movie.releaseDate.isNullOrEmpty()) {
        textView.text = if (movie.releaseDate.length > 3) {
            val year = movie.releaseDate.subSequence(0, 4)
            movie.title + " (" + year + ")"
        } else {
            movie.title
        }
    } else {
        textView.text = movie.title
    }*/
    textView.text = movie.title
}

@BindingAdapter("releaseDate")
fun setReleaseDate(textView: AppCompatTextView, text: String) {
    if (text.isNotEmpty())
        textView.text = text.replace('-', '.', false)
    else
        textView.text = ""
}