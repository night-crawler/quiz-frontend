package mui.lab

import com.ccfraser.muirwik.components.StyledPropsWithCommonAttributes
import org.w3c.dom.events.Event

interface AutocompleteProps<T> : UseAutocompleteCommonProps<T>, StyledPropsWithCommonAttributes {
    var value: dynamic
    var defaultValue: dynamic
    var onChange: ((event: Event, value: T, reason: String) -> Unit)?
    var ChipProps: Any?
    var closeIcon: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
    var clearText: String?
    var closeText: String?
    var disabled: Boolean?
    var disablePortal: Boolean?
    var forcePopupIcon: dynamic /* Boolean | 'auto' */
    var getLimitTagsText: ((more: Number) -> dynamic)?
    var ListboxComponent: dynamic /* ComponentClass<React.HTMLAttributes<HTMLElement>, ComponentState> | FunctionComponent<React.HTMLAttributes<HTMLElement>> */
    var ListboxProps: Any?
    var loading: Boolean?
    var loadingText: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
    var limitTags: Number?
    var noOptionsText: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
    var openText: String?
    var PaperComponent: dynamic /* ComponentClass<React.HTMLAttributes<HTMLElement>, ComponentState> | FunctionComponent<React.HTMLAttributes<HTMLElement>> */
    var PopperComponent: dynamic /* ComponentClass<PopperProps, ComponentState> | FunctionComponent<PopperProps> */
    var popupIcon: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
    var renderGroup: ((params: RenderGroupParams) -> dynamic)?
    var renderInput: (params: RenderInputParams) -> dynamic
    var renderOption: ((option: T, state: RenderOptionState) -> dynamic)?

    //    var renderTags: ((value: Array<T>, getTagProps: GetTagProps) -> dynamic)?
    var renderTags: ((value: Array<T>, getTagProps: dynamic) -> dynamic)?
    var size: String /* 'small' | 'medium' */
}

interface AutocompleteMultipleProps<T> : UseAutocompleteMultipleProps<T>, StyledPropsWithCommonAttributes {
    var ChipProps: Any?
    var closeIcon: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
    var clearText: String?
    var closeText: String?
    var disabled: Boolean?
    var disablePortal: Boolean?
    var forcePopupIcon: dynamic /* Boolean | 'auto' */
    var getLimitTagsText: ((more: Number) -> dynamic)?
    var ListboxComponent: dynamic /* ComponentClass<React.HTMLAttributes<HTMLElement>, ComponentState> | FunctionComponent<React.HTMLAttributes<HTMLElement>> */
    var ListboxProps: Any?
    var loading: Boolean?
    var loadingText: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
    var limitTags: Number?
    var noOptionsText: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
    var openText: String?
    var PaperComponent: dynamic /* ComponentClass<React.HTMLAttributes<HTMLElement>, ComponentState> | FunctionComponent<React.HTMLAttributes<HTMLElement>> */
    var PopperComponent: dynamic /* ComponentClass<PopperProps, ComponentState> | FunctionComponent<PopperProps> */
    var popupIcon: dynamic /* ReactElement | String | Number | Any | ReactNodeArray | ReactPortal | Boolean | Nothing? | Nothing? */
    var renderGroup: ((params: RenderGroupParams) -> dynamic)?
    var renderInput: (params: RenderInputParams) -> dynamic
    var renderOption: ((option: T, state: RenderOptionState) -> dynamic)?

    //    var renderTags: ((value: Array<T>, getTagProps: GetTagProps) -> dynamic)?
    var renderTags: ((value: Array<T>, getTagProps: dynamic) -> dynamic)?
    var size: String /* 'small' | 'medium' */
}
