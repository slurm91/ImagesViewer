package by.vzhilko.core.ui.view.search

import androidx.appcompat.widget.SearchView
import androidx.databinding.BindingAdapter

@BindingAdapter("app:searchViewQuery")
fun setSearchViewQuery(view: SearchView, query: String?) {
    if (view.query != query) {
        view.setQuery(query, true)
    }
}

@BindingAdapter("app:onSearchViewQueryChanged")
fun setOnSearchViewQueryChanged(view: SearchView, onQueryChanged: (query: String?) -> Unit) {
    view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            onQueryChanged(newText)
            return true
        }
    })
}