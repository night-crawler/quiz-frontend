package fm.force.ui.component

import com.ccfraser.muirwik.components.button.mButton
import com.ccfraser.muirwik.components.button.mButtonGroup
import com.ccfraser.muirwik.components.button.mIconButton
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.list.mListItemSecondaryAction
import com.ccfraser.muirwik.components.mTextField
import com.ccfraser.muirwik.components.targetInputValue
import fm.force.quiz.common.dto.DifficultyScaleFullDTO
import fm.force.quiz.common.dto.QuestionFullDTO
import fm.force.quiz.common.dto.TagFullDTO
import fm.force.quiz.common.dto.TopicFullDTO
import fm.force.ui.component.field.DifficultyScalesAutocompleteField
import fm.force.ui.component.field.TagsAutocompleteField
import fm.force.ui.component.field.TopicsAutocompleteField
import fm.force.ui.hook.useRouterMatchParamsId
import fm.force.ui.util.Icon
import fm.force.ui.util.jsApply
import react.RProps
import react.child
import react.functionalComponent
import react.useEffect

val SENTINEL = listOf(null)

interface QuizComposerProps : RProps {
    var tags: List<TagFullDTO>
    var topics: List<TopicFullDTO>
    var title: String
    var bootstrap: (Long) -> Unit
    var questions: List<QuestionFullDTO>
    var onTopicsChanged: (topics: List<TopicFullDTO>) -> Unit
    var onTagsChanged: (tags: List<TagFullDTO>) -> Unit
    var onDifficultyScaleChanged: (scale: DifficultyScaleFullDTO) -> Unit
    var difficultyScale: DifficultyScaleFullDTO?
    var onTitleChanged: (title: String) -> Unit
    var onClear: (Any) -> Unit
    var onSave: (Any) -> Unit
    var onDeleteQuestion: (question: QuestionFullDTO) -> Unit
}

val QuizComposer = functionalComponent<QuizComposerProps> { props ->
    val id = useRouterMatchParamsId()

    useEffect(SENTINEL) {
        if (id != 0L)
            props.bootstrap(id)
    }

    mTextField(
        label = "Title",
        value = props.title,
        onChange = { props.onTitleChanged(it.targetInputValue) },
        fullWidth = true
    )

    child(TagsAutocompleteField) {
        attrs {
            input = jsApply {
                label = "Tags"
                value = props.tags.toTypedArray()
                onChange = { tags: Array<TagFullDTO> ->
                    props.onTagsChanged(tags.toList())
                }
            }
        }
    }

    child(TopicsAutocompleteField) {
        attrs {
            input = jsApply {
                label = "Topics"
                value = props.topics.toTypedArray()
                onChange = { topics: Array<TopicFullDTO> ->
                    props.onTopicsChanged(topics.toList())
                }
                meta = jsApply {
                    error = undefined
                }
            }
        }
    }

    child(DifficultyScalesAutocompleteField) {
        attrs {
            input = jsApply {
                label = "Difficulty scale"
                value = props.difficultyScale ?: ""
                onChange = { scale: DifficultyScaleFullDTO ->
                    props.onDifficultyScaleChanged(scale)
                }
                meta = jsApply {
                    error = undefined
                }
            }
        }
    }

    mList {
        props.questions.forEach { question ->
            mListItem(primaryText = question.title, secondaryText = question.text) {
                mListItemSecondaryAction {
                    mIconButton(Icon.DELETE.iconMame, onClick = { props.onDeleteQuestion(question) })
                }
            }
        }
    }

    mButtonGroup(fullWidth = true) {
        mButton("Clear", onClick = props.onClear)
        mButton("Save", onClick = props.onSave)
    }
}
