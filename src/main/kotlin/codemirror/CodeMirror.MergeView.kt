@file:JsQualifier("codemirror")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package CodeMirror.MergeView

external interface MergeViewEditorConfiguration : CodeMirror.EditorConfiguration {
    var allowEditingOriginals: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var collapseIdentical: dynamic /* Boolean | Number */
        get() = definedExternally
        set(value) = definedExternally
    var connect: String?
        get() = definedExternally
        set(value) = definedExternally
    val onCollapse: ((mergeView: MergeViewEditor, line: Number, size: Number, mark: CodeMirror.TextMarker) -> Unit)?
        get() = definedExternally
    var orig: Any
    var origLeft: Any?
        get() = definedExternally
        set(value) = definedExternally
    var origRight: Any?
        get() = definedExternally
        set(value) = definedExternally
    var revertButtons: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showDifferences: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface MergeViewEditor : CodeMirror.Editor {
    fun editor(): CodeMirror.Editor
    var left: DiffView
    fun leftChunks(): Array<MergeViewDiffChunk>
    fun leftOriginal(): CodeMirror.Editor
    var right: DiffView
    fun rightChunks(): Array<MergeViewDiffChunk>
    fun rightOriginal(): CodeMirror.Editor
    fun setShowDifferences(showDifferences: Boolean)
}

external interface MergeViewDiffChunk {
    var editFrom: Number
    var editTo: Number
    var origFrom: Number
    var origTo: Number
}

external interface DiffView {
    fun forceUpdate(): (mode: String) -> Unit
    fun setShowDifferences(showDifferences: Boolean)
}
