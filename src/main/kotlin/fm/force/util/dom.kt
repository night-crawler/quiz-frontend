package fm.force.util

import history.Hash
import history.Location
import history.Pathname
import history.Search

fun <S> createLocation(pathname: Pathname = "/", search: Search = "", hash: Hash = "", state: S? = null) =
    jsApply<Location<S>> {
        this.pathname = pathname
        this.search = search
        this.hash = hash
        this.state = state
    }
