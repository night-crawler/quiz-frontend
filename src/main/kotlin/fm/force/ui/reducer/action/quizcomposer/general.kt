package fm.force.ui.reducer.action.quizcomposer

import fm.force.quiz.common.dto.*
import redux.RAction

class QuizComposerBootstrapSuccess(val quiz: QuizFullDTO) :
    RAction

class QuizComposerSetTags(val tags: List<TagFullDTO>) :
    RAction

class QuizComposerSetTopics(val topics: List<TopicFullDTO>) :
    RAction

class QuizComposerSetDifficultyScale(val scale: DifficultyScaleFullDTO) :
    RAction

class QuizComposerSetTitle(val title: String) : RAction
class QuizComposerDeleteQuestion(val question: QuestionFullDTO) :
    RAction

class QuizComposerClearQuestions : RAction
class QuizComposerClear : RAction
