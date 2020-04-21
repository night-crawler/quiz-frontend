@file:JsModule("react-codemirror2")
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION", "PackageDirectoryMismatch"
)
package react.codemirror

import org.w3c.dom.HTMLElement
import org.w3c.dom.events.Event
import react.Component
import react.RProps
import react.RState
import react.ReactElement


external interface IDefineModeOptions {
    var fn: () -> CodeMirror.Mode<Any>
    var name: String
}

external interface ISetScrollOptions {
    var x: Number?
        get() = definedExternally
        set(value) = definedExternally
    var y: Number?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ISetSelectionOptions {
    var anchor: Any
    var head: Any
}

external interface DomEvent {
    @nativeInvoke
    operator fun invoke(editor: CodeMirror.Editor, event: Any = definedExternally)
}

external interface KeyHandledEvent {
    @nativeInvoke
    operator fun invoke(editor: CodeMirror.Editor, name: String, event: Any)
}

external interface EditorChangeEvent {
    @nativeInvoke
    operator fun invoke(editor: CodeMirror.Editor, changeObj: CodeMirror.EditorChange)
}

external interface `T$0` {
    var ranges: Array<ISetSelectionOptions>
    var focus: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ICodeMirror : RProps {
    var autoCursor: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var autoScroll: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var className: String?
        get() = definedExternally
        set(value) = definedExternally
    var cursor: Any?
        get() = definedExternally
        set(value) = definedExternally
    var defineMode: IDefineModeOptions?
        get() = definedExternally
        set(value) = definedExternally
    var editorDidConfigure: ((editor: CodeMirror.Editor) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var editorDidMount: ((editor: CodeMirror.Editor, value: String, cb: () -> Unit) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var editorWillUnmount: ((lib: Any) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onBlur: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onChange: ((editor: CodeMirror.Editor, data: CodeMirror.EditorChange, value: String) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onContextMenu: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onCopy: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onCursor: ((editor: CodeMirror.Editor, data: CodeMirror.Position) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onCut: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onCursorActivity: ((editor: CodeMirror.Editor) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onDblClick: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onDragEnter: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onDragLeave: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onDragOver: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onDragStart: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onDrop: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onFocus: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onGutterClick: ((editor: CodeMirror.Editor, lineNumber: Number, gutter: String, event: Event) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onInputRead: EditorChangeEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onKeyDown: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onKeyHandled: KeyHandledEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onKeyPress: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onKeyUp: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onMouseDown: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onPaste: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onRenderLine: ((editor: CodeMirror.Editor, line: CodeMirror.LineHandle, element: HTMLElement) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onScroll: ((editor: CodeMirror.Editor, data: CodeMirror.ScrollInfo) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onSelection: ((editor: CodeMirror.Editor, data: Any) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onTouchStart: DomEvent?
        get() = definedExternally
        set(value) = definedExternally
    var onUpdate: ((editor: CodeMirror.Editor) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onViewportChange: ((editor: CodeMirror.Editor, start: Number, end: Number) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var options: CodeMirror.EditorConfiguration?
        get() = definedExternally
        set(value) = definedExternally
    var selection: `T$0`?
        get() = definedExternally
        set(value) = definedExternally
    var scroll: ISetScrollOptions?
        get() = definedExternally
        set(value) = definedExternally
}

external interface IControlledCodeMirror : ICodeMirror {
    var onBeforeChange: (editor: CodeMirror.Editor, data: CodeMirror.EditorChange, value: String) -> Unit
    var value: String
}

external interface IUnControlledCodeMirror : ICodeMirror {
    var detach: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var editorDidAttach: ((editor: CodeMirror.Editor) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var editorDidDetach: ((editor: CodeMirror.Editor) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var onBeforeChange: ((editor: CodeMirror.Editor, data: CodeMirror.EditorChange, value: String, next: () -> Unit) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var value: String?
        get() = definedExternally
        set(value) = definedExternally
}

open external class Controlled : Component<IControlledCodeMirror, RState>{
    override fun render(): ReactElement?

}

open external class UnControlled : Component<IUnControlledCodeMirror, RState> {
    override fun render(): ReactElement?
}
