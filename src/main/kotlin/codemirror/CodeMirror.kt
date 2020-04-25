@file:JsModule("codemirror")
@file:JsNonModule
@file:Suppress(
    "INTERFACE_WITH_SUPERCLASS",
    "OVERRIDING_FINAL_MEMBER",
    "RETURN_TYPE_MISMATCH_ON_OVERRIDE",
    "CONFLICTING_OVERLOADS",
    "EXTERNAL_DELEGATION"
)

package CodeMirror

import kotlin.js.RegExp
import org.w3c.dom.DragEvent
import org.w3c.dom.HTMLElement
import org.w3c.dom.HTMLTextAreaElement
import org.w3c.dom.events.Event
import org.w3c.dom.events.FocusEvent
import org.w3c.dom.events.KeyboardEvent
import org.w3c.dom.events.MouseEvent

external var Pos: PositionConstructor

external object Pass {
    override fun toString(): String /* "CodeMirror.PASS" */
}

external fun countColumn(line: String, index: Number?, tabSize: Number): Number

external fun fromTextArea(
    host: HTMLTextAreaElement,
    options: EditorConfiguration = definedExternally
): EditorFromTextArea

external fun splitLines(text: String): Array<String>

external fun isWordChar(ch: String): Boolean

external fun startState(
    mode: Mode<Any>,
    a1: Any = definedExternally,
    a2: Any = definedExternally
): dynamic /* Any | Boolean */

external fun cmpPos(a: Position, b: Position): Number

external fun changeEnd(change: EditorChange): Position

external var version: String

external var defaults: Any

external fun defineExtension(name: String, value: Any)

external fun defineDocExtension(name: String, value: Any)

external fun defineOption(name: String, default_: Any, updateFunc: Function<*>)

external fun defineInitHook(func: Function<*>)

external fun registerHelper(namespace: String, name: String, helper: Any)

external interface `T$0` {
    var state: Any
    var mode: Mode<Any>
}

external fun innerMode(mode: Mode<Any>, state: Any): `T$0`

external fun extendMode(name: String, properties: Mode<Any>)

external fun on(element: Any, eventName: String, handler: Function<*>)

external fun off(element: Any, eventName: String, handler: Function<*>)

external fun on(doc: Doc, eventName: String /* 'change' */, handler: (instance: Doc, changeObj: EditorChange) -> Unit)

external fun off(doc: Doc, eventName: String /* 'change' */, handler: (instance: Doc, changeObj: EditorChange) -> Unit)

external fun on(
    doc: Doc,
    eventName: String /* 'beforeChange' */,
    handler: (instance: Doc, change: EditorChangeCancellable) -> Unit
)

external fun off(
    doc: Doc,
    eventName: String /* 'beforeChange' */,
    handler: (instance: Doc, change: EditorChangeCancellable) -> Unit
)

external fun on(doc: Doc, eventName: String /* 'cursorActivity' */, handler: (instance: Editor) -> Unit)

external fun off(doc: Doc, eventName: String /* 'cursorActivity' */, handler: (instance: Editor) -> Unit)

external interface `T$1` {
    var head: Position
    var anchor: Position
}

external fun on(
    doc: Doc,
    eventName: String /* 'beforeSelectionChange' */,
    handler: (instance: Editor, selection: `T$1`) -> Unit
)

external fun off(
    doc: Doc,
    eventName: String /* 'beforeSelectionChange' */,
    handler: (instance: Editor, selection: `T$1`) -> Unit
)

external fun on(line: LineHandle, eventName: String /* 'delete' */, handler: () -> Unit)

external fun off(line: LineHandle, eventName: String /* 'delete' */, handler: () -> Unit)

external fun on(
    line: LineHandle,
    eventName: String /* 'change' */,
    handler: (line: LineHandle, changeObj: EditorChange) -> Unit
)

external fun off(
    line: LineHandle,
    eventName: String /* 'change' */,
    handler: (line: LineHandle, changeObj: EditorChange) -> Unit
)

external fun on(marker: TextMarker, eventName: String /* 'beforeCursorEnter' */, handler: () -> Unit)

external fun off(marker: TextMarker, eventName: String /* 'beforeCursorEnter' */, handler: () -> Unit)

external fun on(marker: TextMarker, eventName: String /* 'clear' */, handler: () -> Unit)

external fun off(marker: TextMarker, eventName: String /* 'clear' */, handler: () -> Unit)

external fun on(marker: TextMarker, eventName: String /* 'hide' */, handler: () -> Unit)

external fun off(marker: TextMarker, eventName: String /* 'hide' */, handler: () -> Unit)

external fun on(marker: TextMarker, eventName: String /* 'unhide' */, handler: () -> Unit)

external fun off(marker: TextMarker, eventName: String /* 'unhide' */, handler: () -> Unit)

external fun on(line: LineWidget, eventName: String /* 'redraw' */, handler: () -> Unit)

external fun off(line: LineWidget, eventName: String /* 'redraw' */, handler: () -> Unit)

external fun signal(target: Any, name: String, vararg args: Any)

external interface Token {
    var start: Number
    var end: Number
    var string: String
    var type: String?
        get() = definedExternally
        set(value) = definedExternally
    var state: Any
}

external interface KeyMap {
    @nativeGetter
    operator fun get(keyName: String): dynamic /* Boolean | String | (instance: Editor) -> dynamic */

    @nativeSetter
    operator fun set(keyName: String, value: Boolean)

    @nativeSetter
    operator fun set(keyName: String, value: String)

    @nativeSetter
    operator fun set(keyName: String, value: (instance: Editor) -> dynamic)
}

external interface `T$2` {
    var line: Number
    var ch: Number
    var hitSide: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$3` {
    var bias: Number?
        get() = definedExternally
        set(value) = definedExternally
    var origin: String?
        get() = definedExternally
        set(value) = definedExternally
    var scroll: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface `T$4` {
    var line: Any
    var handle: Any
    var text: String
    var gutterMarkers: Any
    var textClass: String
    var bgClass: String
    var wrapClass: String
    var widgets: Any
}

external interface `T$5` {
    var left: Number
    var top: Number
    var right: Number
    var bottom: Number
}

external interface `T$6` {
    var line: Number
    var ch: Number
}

external interface `T$7` {
    var from: Position
    var to: Position
}

external interface `T$8` {
    var left: Number
    var top: Number
    var bottom: Number
}

external interface `T$9` {
    var left: Number
    var right: Number
    var top: Number
    var bottom: Number
}

external interface `T$10` {
    var left: Number
    var top: Number
}

external interface `T$11` {
    var from: Number
    var to: Number
}

external interface `T$12` {
    var head: Position
    var anchor: Position
}

external interface Editor : Doc {
    fun hasFocus(): Boolean
    fun findPosH(start: Position, amount: Number, unit: String, visually: Boolean): `T$2`
    fun findPosV(start: Position, amount: Number, unit: String): `T$2`
    fun findWordAt(pos: Position): Range
    fun <K : Any> setOption(option: K, value: Any)
    fun <K : Any> getOption(option: K): Any
    fun addKeyMap(map: String, bottom: Boolean = definedExternally)
    fun addKeyMap(map: KeyMap, bottom: Boolean = definedExternally)
    fun removeKeyMap(map: String)
    fun removeKeyMap(map: KeyMap)
    fun addOverlay(mode: Any, options: Any = definedExternally)
    fun removeOverlay(mode: Any)
    fun getDoc(): CodeMirror.Doc
    fun swapDoc(doc: CodeMirror.Doc): CodeMirror.Doc
    fun setGutterMarker(line: Any, gutterID: String, value: HTMLElement?): LineHandle
    fun clearGutter(gutterID: String)
    fun addLineClass(line: Any, where: String, _class_: String): LineHandle
    fun removeLineClass(line: Any, where: String, class_: String = definedExternally): LineHandle
    fun lineAtHeight(height: Number, mode: String = definedExternally): Number
    fun heightAtLine(line: Any, mode: String = definedExternally, includeWidgets: Boolean = definedExternally): Number
    fun lineInfo(line: Any): `T$4`
    fun addWidget(pos: Position, node: HTMLElement, scrollIntoView: Boolean)
    fun setSize(width: Any, height: Any)
    fun scrollTo(x: Number? = definedExternally, y: Number? = definedExternally)
    fun getScrollInfo(): ScrollInfo
    fun scrollIntoView(pos: Position?, margin: Number = definedExternally)
    fun scrollIntoView(pos: `T$5`, margin: Number = definedExternally)
    fun scrollIntoView(pos: `T$6`, margin: Number = definedExternally)
    fun scrollIntoView(pos: `T$7`, margin: Number = definedExternally)
    fun cursorCoords(where: Boolean = definedExternally, mode: String = definedExternally): `T$8`
    fun cursorCoords(where: Position? = definedExternally, mode: String = definedExternally): `T$8`
    fun charCoords(pos: Position, mode: String = definedExternally): `T$9`
    fun coordsChar(obj: `T$10`, mode: String = definedExternally): Position
    fun defaultTextHeight(): Number
    fun defaultCharWidth(): Number
    fun getViewport(): `T$11`
    fun refresh()
    fun getModeAt(pos: Position): Any
    fun getTokenAt(pos: Position, precise: Boolean = definedExternally): Token
    fun getTokenTypeAt(pos: Position): String
    fun getLineTokens(line: Number, precise: Boolean = definedExternally): Array<Token>
    fun getStateAfter(line: Number = definedExternally): Any
    fun <T> operation(fn: () -> T): T
    fun startOperation()
    fun endOperation()
    fun indentLine(line: Number, dir: String = definedExternally)
    fun isReadOnly(): Boolean
    fun toggleOverwrite(value: Boolean = definedExternally)
    fun execCommand(name: String)
    fun focus()
    fun getInputField(): HTMLTextAreaElement
    fun getWrapperElement(): HTMLElement
    fun getScrollerElement(): HTMLElement
    fun getGutterElement(): HTMLElement
    fun on(eventName: String /* 'change' */, handler: (instance: Editor, changeObj: EditorChangeLinkedList) -> Unit)
    fun off(eventName: String /* 'change' */, handler: (instance: Editor, changeObj: EditorChangeLinkedList) -> Unit)
    fun on(
        eventName: String /* 'changes' */,
        handler: (instance: Editor, changes: Array<EditorChangeLinkedList>) -> Unit
    )

    fun off(
        eventName: String /* 'changes' */,
        handler: (instance: Editor, changes: Array<EditorChangeLinkedList>) -> Unit
    )

    fun on(
        eventName: String /* 'beforeChange' */,
        handler: (instance: Editor, changeObj: EditorChangeCancellable) -> Unit
    )

    fun off(
        eventName: String /* 'beforeChange' */,
        handler: (instance: Editor, changeObj: EditorChangeCancellable) -> Unit
    )

    fun on(eventName: String, handler: (instance: Editor) -> Unit)
    fun off(eventName: String, handler: (instance: Editor) -> Unit)
    fun on(
        eventName: String /* 'keyHandled' */,
        handler: (instance: Editor, name: String, event: KeyboardEvent) -> Unit
    )

    fun off(
        eventName: String /* 'keyHandled' */,
        handler: (instance: Editor, name: String, event: KeyboardEvent) -> Unit
    )

    fun on(eventName: String /* 'inputRead' */, handler: (instance: Editor, changeObj: EditorChange) -> Unit)
    fun off(eventName: String /* 'inputRead' */, handler: (instance: Editor, changeObj: EditorChange) -> Unit)
    fun on(eventName: String /* 'electricInput' */, handler: (instance: Editor, line: Number) -> Unit)
    fun off(eventName: String /* 'electricInput' */, handler: (instance: Editor, line: Number) -> Unit)
    fun on(eventName: String /* 'beforeSelectionChange' */, handler: (instance: Editor, selection: `T$12`) -> Unit)
    fun off(eventName: String /* 'beforeSelectionChange' */, handler: (instance: Editor, selection: `T$12`) -> Unit)
    fun on(eventName: String /* 'viewportChange' */, handler: (instance: Editor, from: Number, to: Number) -> Unit)
    fun off(eventName: String /* 'viewportChange' */, handler: (instance: Editor, from: Number, to: Number) -> Unit)
    fun on(eventName: String /* 'swapDoc' */, handler: (instance: Editor, oldDoc: CodeMirror.Doc) -> Unit)
    fun off(eventName: String /* 'swapDoc' */, handler: (instance: Editor, oldDoc: CodeMirror.Doc) -> Unit)
    fun on(
        eventName: String /* 'gutterClick' */,
        handler: (instance: Editor, line: Number, gutter: String, clickEvent: MouseEvent) -> Unit
    )

    fun off(
        eventName: String /* 'gutterClick' */,
        handler: (instance: Editor, line: Number, gutter: String, clickEvent: MouseEvent) -> Unit
    )

    fun on(
        eventName: String /* 'gutterContextMenu' */,
        handler: (instance: Editor, line: Number, gutter: String, contextMenu: MouseEvent) -> Unit
    )

    fun off(
        eventName: String /* 'gutterContextMenu' */,
        handler: (instance: Editor, line: Number, gutter: String, contextMenu: MouseEvent) -> Unit
    )

    fun on(eventName: String, handler: (instance: Editor, event: FocusEvent) -> Unit)
    fun off(eventName: String, handler: (instance: Editor, event: FocusEvent) -> Unit)
    fun on(eventName: String /* 'optionChange' */, handler: (instance: Editor, option: String) -> Unit)
    fun off(eventName: String /* 'optionChange' */, handler: (instance: Editor, option: String) -> Unit)
    fun on(eventName: String /* 'scrollCursorIntoView' */, handler: (instance: Editor, event: Event) -> Unit)
    fun off(eventName: String /* 'scrollCursorIntoView' */, handler: (instance: Editor, event: Event) -> Unit)
    fun on(
        eventName: String /* 'renderLine' */,
        handler: (instance: Editor, line: LineHandle, element: HTMLElement) -> Unit
    )

    fun off(
        eventName: String /* 'renderLine' */,
        handler: (instance: Editor, line: LineHandle, element: HTMLElement) -> Unit
    )

    fun <K : String> on(eventName: K, handler: (instance: Editor, event: Any) -> Unit)
    fun <K : String> off(eventName: K, handler: (instance: Editor, event: Any) -> Unit)
    fun on(eventName: String /* "overwriteToggle" */, handler: (instance: Editor, overwrite: Boolean) -> Unit)
    fun on(eventName: String, handler: (instance: Editor) -> Unit)
    fun off(eventName: String, handler: (instance: Editor) -> Unit)
    fun lineAtHeight(height: Number): Number
    fun heightAtLine(line: Any): Number
    fun cursorCoords(): `T$8`
    fun charCoords(pos: Position): `T$9`
    fun coordsChar(obj: `T$10`): Position
}

external interface EditorFromTextArea : Editor {
    fun save()
    fun toTextArea()
    fun getTextArea(): HTMLTextAreaElement
}

external interface DocConstructor {
    @nativeInvoke
    operator fun invoke(
        text: String,
        mode: Any = definedExternally,
        firstLineNumber: Number = definedExternally,
        lineSep: String = definedExternally
    ): Doc
}

external interface `T$13` {
    var anchor: Position
    var head: Position
}

external interface `T$14` {
    var sharedHist: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var from: Number?
        get() = definedExternally
        set(value) = definedExternally
    var to: Number?
        get() = definedExternally
        set(value) = definedExternally
    var mode: Any
}

external interface `T$15` {
    var undo: Number
    var redo: Number
}

external interface `T$16` {
    var widget: HTMLElement?
        get() = definedExternally
        set(value) = definedExternally
    var insertLeft: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface Doc {
    fun getValue(seperator: String = definedExternally): String
    fun setValue(content: String)
    fun getRange(from: Position, to: Position, seperator: String = definedExternally): String
    fun replaceRange(
        replacement: String,
        from: Position,
        to: Position = definedExternally,
        origin: String = definedExternally
    )

    fun getLine(n: Number): String
    fun setLine(n: Number, text: String)
    fun removeLine(n: Number)
    fun lineCount(): Number
    fun firstLine(): Number
    fun lastLine(): Number
    fun getLineHandle(num: Number): LineHandle
    fun getLineNumber(handle: LineHandle): Number?
    fun eachLine(f: (line: LineHandle) -> Unit)
    fun eachLine(start: Number, end: Number, f: (line: LineHandle) -> Unit)
    fun markClean()
    fun changeGeneration(closeEvent: Boolean = definedExternally): Number
    fun isClean(generation: Number = definedExternally): Boolean
    fun getSelection(): String
    fun getSelections(lineSep: String = definedExternally): Array<String>
    fun replaceSelection(replacement: String, collapse: String = definedExternally)
    fun getCursor(start: String = definedExternally): Position
    fun listSelections(): Array<`T$13`>
    fun somethingSelected(): Boolean
    fun setCursor(pos: Position, ch: Number = definedExternally, options: `T$3` = definedExternally)
    fun setCursor(pos: Number, ch: Number = definedExternally, options: `T$3` = definedExternally)
    fun setSelection(anchor: Position, head: Position = definedExternally, options: `T$3` = definedExternally)
    fun setSelections(ranges: Array<`T$13`>, primary: Number = definedExternally, options: `T$3` = definedExternally)
    fun extendSelection(from: Position, to: Position = definedExternally)
    fun setExtending(value: Boolean)
    fun getEditor(): Editor?
    fun copy(copyHistory: Boolean): CodeMirror.Doc
    fun linkedDoc(options: `T$14`): CodeMirror.Doc
    fun unlinkDoc(doc: CodeMirror.Doc)
    fun iterLinkedDocs(fn: (doc: CodeMirror.Doc, sharedHist: Boolean) -> Unit)
    fun undo()
    fun redo()
    fun historySize(): `T$15`
    fun clearHistory()
    fun getHistory(): Any
    fun setHistory(history: Any)
    fun markText(from: Position, to: Position, options: TextMarkerOptions = definedExternally): TextMarker
    fun setBookmark(pos: Position, options: `T$16` = definedExternally): TextMarker
    fun findMarks(from: Position, to: Position): Array<TextMarker>
    fun findMarksAt(pos: Position): Array<TextMarker>
    fun getAllMarks(): Array<TextMarker>
    fun addLineWidget(line: Any, node: HTMLElement, options: LineWidgetOptions = definedExternally): LineWidget
    fun removeLineWidget(widget: LineWidget)
    fun getMode(): Any
    fun posFromIndex(index: Number): Position
    fun indexFromPos(obj: Position): Number
    var state: Any

//    companion object :  by definedExternally
}

external interface LineHandle {
    var text: String
}

external interface ScrollInfo {
    var left: Any
    var top: Any
    var width: Any
    var height: Any
    var clientWidth: Any
    var clientHeight: Any
}

external interface TextMarker {
    fun clear()
    fun find(): `T$7`
    fun changed()
    fun getOptions(copyWidget: Boolean): TextMarkerOptions
    fun on(eventName: String, handler: () -> Unit)
    fun off(eventName: String, handler: () -> Unit)
    fun on(eventName: String /* 'clear' */, handler: (from: Position, to: Position) -> Unit)
    fun off(eventname: String, handler: () -> Unit)
}

external interface LineWidget {
    fun clear()
    fun changed()
}

external interface LineWidgetOptions {
    var coverGutter: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var noHScroll: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var above: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var showIfHidden: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var handleMouseEvents: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var insertAt: Number?
        get() = definedExternally
        set(value) = definedExternally
    var className: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EditorChange {
    var from: Position
    var to: Position
    var text: Array<String>
    var removed: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var origin: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EditorChangeLinkedList : EditorChange {
    var next: EditorChangeLinkedList?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EditorChangeCancellable : EditorChange {
    val update: ((from: Position, to: Position, text: Array<String>) -> Unit)?
        get() = definedExternally

    fun cancel()
}

external interface PositionConstructor {
    @nativeInvoke
    operator fun invoke(line: Number, ch: Number = definedExternally, sticky: String = definedExternally): Position
}

external interface Range {
    var anchor: Position
    var head: Position
    fun from(): Position
    fun to(): Position
}

external interface Position {
    var ch: Number
    var line: Number
    var sticky: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface EditorConfiguration {
    var value: Any?
        get() = definedExternally
        set(value) = definedExternally
    var mode: Any?
        get() = definedExternally
        set(value) = definedExternally
    var theme: String?
        get() = definedExternally
        set(value) = definedExternally
    var indentUnit: Number?
        get() = definedExternally
        set(value) = definedExternally
    var smartIndent: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var tabSize: Number?
        get() = definedExternally
        set(value) = definedExternally
    var indentWithTabs: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var electricChars: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var rtlMoveVisually: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var keyMap: String?
        get() = definedExternally
        set(value) = definedExternally
    var extraKeys: dynamic /* String | KeyMap */
        get() = definedExternally
        set(value) = definedExternally
    var lineWrapping: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var lineNumbers: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var firstLineNumber: Number?
        get() = definedExternally
        set(value) = definedExternally
    var lineNumberFormatter: ((line: Number) -> String)?
        get() = definedExternally
        set(value) = definedExternally
    var gutters: Array<String>?
        get() = definedExternally
        set(value) = definedExternally
    var foldGutter: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var fixedGutter: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var scrollbarStyle: String?
        get() = definedExternally
        set(value) = definedExternally
    var coverGutterNextToScrollbar: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var inputStyle: String /* "textarea" | "contenteditable" */
    var readOnly: Any?
        get() = definedExternally
        set(value) = definedExternally
    var showCursorWhenSelecting: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var lineWiseCopyCut: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var undoDepth: Number?
        get() = definedExternally
        set(value) = definedExternally
    var historyEventDelay: Number?
        get() = definedExternally
        set(value) = definedExternally
    var tabindex: Number?
        get() = definedExternally
        set(value) = definedExternally
    var autofocus: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var dragDrop: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var onDragEvent: ((instance: Editor, event: DragEvent) -> Boolean)?
        get() = definedExternally
        set(value) = definedExternally
    var onKeyEvent: ((instance: Editor, event: KeyboardEvent) -> Boolean)?
        get() = definedExternally
        set(value) = definedExternally
    var cursorBlinkRate: Number?
        get() = definedExternally
        set(value) = definedExternally
    var cursorScrollMargin: Number?
        get() = definedExternally
        set(value) = definedExternally
    var cursorHeight: Number?
        get() = definedExternally
        set(value) = definedExternally
    var workTime: Number?
        get() = definedExternally
        set(value) = definedExternally
    var workDelay: Number?
        get() = definedExternally
        set(value) = definedExternally
    var pollInterval: Number?
        get() = definedExternally
        set(value) = definedExternally
    var flattenSpans: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var maxHighlightLength: Number?
        get() = definedExternally
        set(value) = definedExternally
    var viewportMargin: Number?
        get() = definedExternally
        set(value) = definedExternally
    var lint: dynamic /* Boolean | LintOptions */
        get() = definedExternally
        set(value) = definedExternally
    var placeholder: String?
        get() = definedExternally
        set(value) = definedExternally
}

external interface TextMarkerOptions {
    var className: String?
        get() = definedExternally
        set(value) = definedExternally
    var inclusiveLeft: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var inclusiveRight: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var atomic: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var collapsed: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var clearOnEnter: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var clearWhenEmpty: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var replacedWith: HTMLElement?
        get() = definedExternally
        set(value) = definedExternally
    var handleMouseEvents: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var readOnly: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var addToHistory: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var startStyle: String?
        get() = definedExternally
        set(value) = definedExternally
    var endStyle: String?
        get() = definedExternally
        set(value) = definedExternally
    var css: String?
        get() = definedExternally
        set(value) = definedExternally
    var title: String?
        get() = definedExternally
        set(value) = definedExternally
    var shared: Boolean?
        get() = definedExternally
        set(value) = definedExternally
}

external interface StringStreamConstructor

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface StringStream {
    var lastColumnPos: Number
    var lastColumnValue: Number
    var lineStart: Number
    var pos: Number
    var start: Number
    var string: String
    var tabSize: Number
    fun eol(): Boolean
    fun sol(): Boolean
    fun peek(): String?
    fun next(): String?
    fun eat(match: String): String
    fun eat(match: RegExp): String
    fun eat(match: (char: String) -> Boolean): String
    fun eatWhile(match: String): Boolean
    fun eatWhile(match: RegExp): Boolean
    fun eatWhile(match: (char: String) -> Boolean): Boolean
    fun eatSpace(): Boolean
    fun skipToEnd()
    fun skipTo(ch: String): Boolean
    fun match(pattern: String, consume: Boolean = definedExternally, caseFold: Boolean = definedExternally): Boolean
    fun match(pattern: RegExp, consume: Boolean = definedExternally): Array<String>
    fun backUp(n: Number)
    fun column(): Number
    fun indentation(): Number
    fun current(): String

//    companion object :  by definedExternally
}

external interface Mode<T> {
    var name: String?
        get() = definedExternally
        set(value) = definedExternally
    var token: ((stream: StringStream, state: T) -> String?)?
        get() = definedExternally
        set(value) = definedExternally
    var startState: (() -> T)?
        get() = definedExternally
        set(value) = definedExternally
    var blankLine: ((state: T) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
    var copyState: ((state: T) -> T)?
        get() = definedExternally
        set(value) = definedExternally
    var indent: ((state: T, textAfter: String) -> Number)?
        get() = definedExternally
        set(value) = definedExternally
    var lineComment: String?
        get() = definedExternally
        set(value) = definedExternally
    var blockCommentStart: String?
        get() = definedExternally
        set(value) = definedExternally
    var blockCommentEnd: String?
        get() = definedExternally
        set(value) = definedExternally
    var blockCommentLead: String?
        get() = definedExternally
        set(value) = definedExternally
    var electricChars: String?
        get() = definedExternally
        set(value) = definedExternally
    var electricinput: RegExp?
        get() = definedExternally
        set(value) = definedExternally
}

external interface ModeFactory<T> {
    @nativeInvoke
    operator fun invoke(config: EditorConfiguration, modeOptions: Any = definedExternally): Mode<T>
}

external fun defineMode(id: String, modefactory: ModeFactory<Any>)

external fun <T> defineMode(id: String, modefactory: ModeFactory<T>)

external fun <T> getMode(config: EditorConfiguration, mode: Any): Mode<T>

external fun <T, S> overlayMode(base: Mode<T>, overlay: Mode<S>, combine: Boolean = definedExternally): Mode<Any>

external interface ModeMap {
    @nativeGetter
    operator fun get(modeName: String): ModeFactory<Any>?

    @nativeSetter
    operator fun set(modeName: String, value: ModeFactory<Any>)
}

external var modes: ModeMap

external fun defineMIME(mime: String, modeSpec: Any)

external interface MimeModeMap {
    @nativeGetter
    operator fun get(mimeName: String): Any?

    @nativeSetter
    operator fun set(mimeName: String, value: Any)
}

external var mimeModes: MimeModeMap

external interface CommandActions {
    fun selectAll(cm: Editor)
    fun singleSelection(cm: Editor)
    fun killLine(cm: Editor)
    fun deleteLine(cm: Editor)
    fun delLineLeft(cm: Editor)
    fun delWrappedLineLeft(cm: Editor)
    fun delWrappedLineRight(cm: Editor)
    fun undo(cm: Editor)
    fun redo(cm: Editor)
    fun undoSelection(cm: Editor)
    fun redoSelection(cm: Editor)
    fun goDocStart(cm: Editor)
    fun goDocEnd(cm: Editor)
    fun goLineStart(cm: Editor)
    fun goLineStartSmart(cm: Editor)
    fun goLineEnd(cm: Editor)
    fun goLineRight(cm: Editor)
    fun goLineLeft(cm: Editor)
    fun goLineLeftSmart(cm: Editor)
    fun goLineUp(cm: Editor)
    fun goLineDown(cm: Editor)
    fun goPageUp(cm: Editor)
    fun goPageDown(cm: Editor)
    fun goCharLeft(cm: Editor)
    fun goCharRight(cm: Editor)
    fun goColumnLeft(cm: Editor)
    fun goColumnRight(cm: Editor)
    fun goWordLeft(cm: Editor)
    fun goWordRight(cm: Editor)
    fun goGroupLeft(cm: Editor)
    fun goGroupRight(cm: Editor)
    fun delCharBefore(cm: Editor)
    fun delCharAfter(cm: Editor)
    fun delWordBefore(cm: Editor)
    fun delWordAfter(cm: Editor)
    fun delGroupBefore(cm: Editor)
    fun delGroupAfter(cm: Editor)
    fun indentAuto(cm: Editor)
    fun indentMore(cm: Editor)
    fun indentLess(cm: Editor)
    fun insertTab(cm: Editor)
    fun insertSoftTab(cm: Editor)
    fun defaultTabTab(cm: Editor)
    fun transposeChars(cm: Editor)
    fun newlineAndIndent(cm: Editor)
    fun toggleOverwrite(cm: Editor)
}

external var commands: CommandActions

external interface LintStateOptions {
    var async: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var hasGutters: Boolean?
        get() = definedExternally
        set(value) = definedExternally
    var onUpdateLinting: ((annotationsNotSorted: Array<Annotation>, annotations: Array<Annotation>, codeMirror: Editor) -> Unit)?
        get() = definedExternally
        set(value) = definedExternally
}

external interface LintOptions : LintStateOptions {
    var getAnnotations: dynamic /* Linter | AsyncLinter */
        get() = definedExternally
        set(value) = definedExternally
}

external interface Linter {
    @nativeInvoke
    operator fun invoke(
        content: String,
        options: LintStateOptions,
        codeMirror: Editor
    ): dynamic /* Array<Annotation> | PromiseLike<Array<Annotation>> */
}

external interface AsyncLinter {
    @nativeInvoke
    operator fun invoke(
        content: String,
        updateLintingCallback: UpdateLintingCallback,
        options: LintStateOptions,
        codeMirror: Editor
    )
}

external interface UpdateLintingCallback {
    @nativeInvoke
    operator fun invoke(codeMirror: Editor, annotations: Array<Annotation>)
}

external interface Annotation {
    var from: Position
    var message: String?
        get() = definedExternally
        set(value) = definedExternally
    var severity: String?
        get() = definedExternally
        set(value) = definedExternally
    var to: Position?
        get() = definedExternally
        set(value) = definedExternally
}

external fun MergeView(
    element: HTMLElement,
    options: CodeMirror.MergeView.MergeViewEditorConfiguration = definedExternally
): CodeMirror.MergeView.MergeViewEditor
