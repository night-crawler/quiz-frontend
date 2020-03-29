package fm.force.ui.component.form

import com.benasher44.uuid.uuid4

data class Answer(
    val text: String,
    val isCorrect: Boolean,

    // we need some unique id here to force react use it in favour of just array index
    val uuid: String = uuid4().toString()
)
