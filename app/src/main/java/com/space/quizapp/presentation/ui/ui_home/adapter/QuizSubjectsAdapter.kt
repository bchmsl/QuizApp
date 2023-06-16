package com.space.quizapp.presentation.ui.ui_home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.space.quizapp.databinding.QuizItemSubjectBinding
import com.space.quizapp.presentation.base.adapter.QuizBaseItemCallback
import com.space.quizapp.presentation.model.quiz.QuizSubjectUiModel

class QuizSubjectsAdapter :
    ListAdapter<QuizSubjectUiModel, QuizSubjectsAdapter.SubjectsViewHolder>(QuizBaseItemCallback<QuizSubjectUiModel>()) {

    private var itemCallback: ((QuizSubjectUiModel) -> Unit)? = null
    fun setOnClickListener(block: (QuizSubjectUiModel) -> Unit) {
        itemCallback = block
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectsViewHolder =
        SubjectsViewHolder(
            QuizItemSubjectBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: SubjectsViewHolder, position: Int) {
        holder.onBind(getItem(position), itemCallback)
    }

    class SubjectsViewHolder(private val binding: QuizItemSubjectBinding) :
        ViewHolder(binding.root) {
        fun onBind(model: QuizSubjectUiModel, itemCallback: ((QuizSubjectUiModel) -> Unit)?) {
            with(binding) {
                root.setContent(model.quizTitle, model.quizDescription, model.quizIcon)
                root.setPointsCount()
                root.setCustomClickListener {
                    itemCallback?.invoke(model)
                }
            }
        }
    }
}
