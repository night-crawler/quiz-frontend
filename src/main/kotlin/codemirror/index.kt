@file:JsModule("codemirror/lib")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

import org.w3c.dom.HTMLElement

external fun CodeMirror(
    host: HTMLElement,
    options: CodeMirror.EditorConfiguration = definedExternally
): CodeMirror.Editor


//external fun CodeMirror(
//    callback: (host: HTMLElement) -> Unit,
//    options: CodeMirror.EditorConfiguration = definedExternally
//): CodeMirror.Editor
