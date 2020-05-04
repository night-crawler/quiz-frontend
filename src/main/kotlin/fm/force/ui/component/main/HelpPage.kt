package fm.force.ui.component.main

import com.ccfraser.muirwik.components.MTypographyVariant
import com.ccfraser.muirwik.components.card.mCard
import com.ccfraser.muirwik.components.card.mCardContent
import com.ccfraser.muirwik.components.card.mCardHeader
import com.ccfraser.muirwik.components.list.mList
import com.ccfraser.muirwik.components.list.mListItem
import com.ccfraser.muirwik.components.mTypography
import fm.force.ui.component.misc.readOnlyQuestionCode
import fm.force.ui.component.question.markdownWithCode
import fm.force.ui.extension.CodeLanguage
import kotlinx.css.pct
import kotlinx.css.width
import react.RBuilder
import react.RComponent
import react.RProps
import react.RState
import styled.css

class HelpPage(props: RProps) : RComponent<RProps, RState>(props) {
    private val codeSample = """
        ## This is a header
        ```kotlin
        import fm.force.fun
        
        fun qwe() {}
        ```
    """.trimIndent()

    override fun RBuilder.render() {
        mCard {
            mCardHeader("Quiz project")
            mCardContent {
                mTypography(variant = MTypographyVariant.h6) { +"Preface" }
                mTypography(variant = MTypographyVariant.caption) {
                    +"I started working on this project after finishing reading a book called “Make It Stick”. "
                    +"First of all, I wanted to get some Kotlin/Java/Spring/whatever experience, because "
                    +"I'd got sick of Python world with its never-ending extralinguistic mischiefs. "
                    +"Second, I wanted to do something with my exasperating inclination to forget everything "
                    +"within three months. Third, I have a friend, who wants a tool that facilitates the "
                    +"assessment of his students' English knowledge through multiple-choice questions."
                }
            }
            mCardContent {
                mTypography(variant = MTypographyVariant.h6) { +"1. Intended use: Questions" }
                mList {
                    mListItem {
                        +"Create a Question. Use Add Answer button to add new answers. "
                        +"Mark correct answers accordingly. "
                    }
                    mListItem {
                        +"While creating the question, attach appropriate topics and tags to them. "
                        +"They will be used later for results analysis. "
                    }
                    mListItem {
                        +"Question editor supports markdown formatting. "
                        +"Also, it supports code embedding with triple ` sign:"
                    }
                    mListItem {
                        readOnlyQuestionCode(codeSample, CodeLanguage.MARKDOWN)
                    }
                    mListItem {
                        +"This will be effectively translated to:"
                    }
                    mListItem {
                        markdownWithCode(codeSample)
                    }
                    mListItem {
                        +"Also, keep in mind that tags and topics are unique for different users. "
                        +"It means that your 'Kotlin' tag is not the same as your friend's 'Kotlin' tag."
                    }
                    mListItem {
                        +"You can navigate through the questions you've created. "
                    }
                }
            }
            mCardContent {
                mTypography(variant = MTypographyVariant.h6) { +"2. Difficulty scales" }
                mList {
                    mListItem {
                        +"All question difficulty values must be normalized first. "
                        +"You can use any maximum value you want, I personally prefer 1e6. "
                    }
                    mListItem {
                        +"Add ranges that don't intersect and cover the desired interval."
                    }
                    mListItem {
                        +"You can navigate through the scales you've created."
                    }
                }
            }

            mCardContent {
                mTypography(variant = MTypographyVariant.h6) { +"3. Quizzes" }
                mList {
                    mListItem { +"You can add new questions to a quiz. " }
                    mListItem { +"Use autocomplete to find your questions. " }
                    mListItem { +"You can combine and recombine question you've created. " }
                    mListItem { +"You can change the other of questions." }
                    mListItem { +"You can navigate through the quizzes you've created." }
                }
            }

            mCardContent {
                mTypography(variant = MTypographyVariant.h6) { +"4. Sessions" }
                mList {
                    mListItem {
                        +"On the page with you quizzes click `Start session` button. "
                        +"It will give you a preview with all the questions of the quiz."
                    }
                    mListItem {
                        +"Then, click start. "
                        +"It will create a quiz session. "
                        +"Quiz session store only answered questions. "
                        +"All sessions are completable, so you can resume it clicking the continue button. "
                        +"When you will have answered to all questions of the quiz, you'll see the report button. "
                        +"There you'll gen a simple analysis by tags and topics."
                    }
                }
            }
        }
    }
}

fun RBuilder.helpPage() = child(HelpPage::class) { }
