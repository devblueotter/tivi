/*
 * Copyright 2018 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package me.banes.chris.tivi.ui.databinding

import android.databinding.BindingAdapter
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.view.doOnLayout
import me.banes.chris.tivi.GlideApp
import me.banes.chris.tivi.data.entities.Genre
import me.banes.chris.tivi.extensions.loadFromUrl
import me.banes.chris.tivi.tmdb.TmdbImageUrlProvider
import me.banes.chris.tivi.ui.GenreStringer
import me.banes.chris.tivi.ui.MaxLinesToggleClickListener

@BindingAdapter("android:tmdbPosterPath", "android:tmdbImageUrlProvider")
fun loadPoster(view: ImageView, posterPath: String?, tmdbImageUrlProvider: TmdbImageUrlProvider?) {
    GlideApp.with(view).clear(view)

    if (posterPath != null && tmdbImageUrlProvider != null) {
        view.doOnLayout {
            view.loadFromUrl(
                    tmdbImageUrlProvider.getPosterUrl(posterPath, 0),
                    tmdbImageUrlProvider.getPosterUrl(posterPath, it.width)
            )
        }
    }
}

@BindingAdapter("android:genreString")
fun genreString(view: TextView, genres: List<Genre>?) {
    val genreText = genres?.joinToString(" // ") {
        "${view.context.getString(GenreStringer.getLabel(it))} ${GenreStringer.getEmoji(it)}"
    }
    view.text = genreText
}

@BindingAdapter("android:genreContentDescriptionString")
fun genreContentDescriptionString(view: TextView, genres: List<Genre>?) {
    val genreContentDescription = genres?.joinToString(", ") {
        view.context.getString(GenreStringer.getLabel(it))
    }
    view.contentDescription = genreContentDescription
}

@BindingAdapter("android:visibleIfNotNull")
fun visibleIfNotNull(view: View, target: Any?) {
    view.visibility = if (target == null) View.GONE else View.VISIBLE
}

@BindingAdapter("android:srcRes")
fun imageViewSrcRes(view: ImageView, drawableRes: Int) {
    if (drawableRes != 0) {
        view.setImageResource(drawableRes)
    } else {
        view.setImageDrawable(null)
    }
}

@BindingAdapter("android:maxLinesToggle")
fun maxLinesClickListener(view: TextView, collapsedMaxLines: Int) {
    // Default to collapsed
    view.maxLines = collapsedMaxLines
    // Now set click listener
    view.setOnClickListener(MaxLinesToggleClickListener(collapsedMaxLines))
}