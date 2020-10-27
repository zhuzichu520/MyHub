package com.zhuzichu.android.search.viewmodel

import androidx.annotation.StringRes
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.afollestad.recyclical.datasource.DataSource
import com.afollestad.recyclical.datasource.dataSourceTypedOf
import com.hiwitech.android.mvvm.event.SingleLiveEvent
import com.zhuzichu.android.search.R
import com.zhuzichu.android.shared.entity.param.ParamSearchRepositories
import com.zhuzichu.android.shared.entity.param.ParamSearchUsers

/**
 * desc
 * author: 朱子楚
 * time: 2020/10/27 2:23 PM
 * since: v 1.0.0
 */
class ShareViewModel : ViewModel() {

    val position = MutableLiveData<Int>()

    val onUsersSearchChangeEvent = SingleLiveEvent<Unit>()

    val onRepositoriesSearchChangeEvent = SingleLiveEvent<Unit>()

    private val dataRepositories by lazy {
        dataSourceTypedOf(
            SearchMode(
                R.string.best_match,
                "",
                ""
            ),
            SearchMode(
                R.string.most_stars,
                ParamSearchRepositories.SORT_STARS,
                ParamSearchRepositories.ORDER_DESC
            ),
            SearchMode(
                R.string.fewest_stars,
                ParamSearchRepositories.SORT_STARS,
                ParamSearchRepositories.ORDER_ASC
            ),
            SearchMode(
                R.string.most_forks,
                ParamSearchRepositories.SORT_FORKS,
                ParamSearchRepositories.ORDER_DESC
            ),
            SearchMode(
                R.string.fewest_forks,
                ParamSearchRepositories.SORT_FORKS,
                ParamSearchRepositories.ORDER_ASC
            ),
            SearchMode(
                R.string.recently_updated,
                ParamSearchRepositories.SORT_UPDATED,
                ParamSearchRepositories.ORDER_DESC
            ),
            SearchMode(
                R.string.least_recently_updated,
                ParamSearchRepositories.SORT_UPDATED,
                ParamSearchRepositories.ORDER_ASC
            ),
        )
    }

    private val dataUsers by lazy {
        dataSourceTypedOf(
            SearchMode(
                R.string.best_match,
                "",
                ""
            ),
            SearchMode(
                R.string.most_followers,
                ParamSearchUsers.SORT_FOLLOWERS,
                ParamSearchUsers.ORDER_DESC
            ),
            SearchMode(
                R.string.fewest_followers,
                ParamSearchUsers.SORT_FOLLOWERS,
                ParamSearchUsers.ORDER_ASC
            ),
            SearchMode(
                R.string.most_recently_joined,
                ParamSearchUsers.SORT_JOINED,
                ParamSearchUsers.ORDER_DESC
            ),
            SearchMode(
                R.string.least_recently_joined,
                ParamSearchUsers.SORT_JOINED,
                ParamSearchUsers.ORDER_ASC
            ),
            SearchMode(
                R.string.most_repositories,
                ParamSearchUsers.SORT_REPOSITORIES,
                ParamSearchUsers.ORDER_DESC
            ),
            SearchMode(
                R.string.fewest_repositories,
                ParamSearchUsers.SORT_REPOSITORIES,
                ParamSearchUsers.ORDER_ASC
            ),
        )
    }

    private val arr = arrayOf(dataRepositories[0], dataUsers[0])

    fun getDataSource(): DataSource<SearchMode>? {
        return when (position.value) {
            0 -> {
                dataRepositories
            }
            1 -> {
                dataUsers
            }
            else -> {
                null
            }
        }
    }

    fun updateSearchMode(searchMode: SearchMode): Int {
        val position = position.value ?: return 0
        arr[position] = searchMode
        this.position.value = position
        return position
    }

    fun getSearchMode(): SearchMode? {
        val position = position.value ?: return null
        return arr[position]
    }

    data class SearchMode(
        @StringRes val textId: Int,
        val sort: String,
        val order: String,
    )

}