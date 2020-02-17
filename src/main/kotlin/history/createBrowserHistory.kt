@file:JsModule("history")
@file:JsNonModule

package history

external fun <S> createBrowserHistory(options: BrowserHistoryBuildOptions = definedExternally): History<S>
