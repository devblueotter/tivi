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

package me.banes.chris.tivi.showdetails.details

import me.banes.chris.tivi.data.entities.TiviShow
import me.banes.chris.tivi.tmdb.TmdbImageUrlProvider

data class ShowDetailsFragmentViewState(
    val show: TiviShow,
    val relatedShows: List<TiviShow>,
    val tmdbImageUrlProvider: TmdbImageUrlProvider,
    val inMyShows: Boolean
)