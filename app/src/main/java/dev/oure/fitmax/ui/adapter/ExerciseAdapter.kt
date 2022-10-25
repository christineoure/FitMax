package dev.oure.fitmax.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import dev.oure.fitmax.R
import dev.oure.fitmax.models.Exercise


class ExerciseAdapter(context: Context, var exercise: List<Exercise>): ArrayAdapter<Exercise>(context, 0, exercise){
    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getExerciseCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return getExerciseCustomView(position, convertView, parent)
    }

    fun getExerciseCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = LayoutInflater.from(context).inflate(R.layout.exercise_custom_layout, parent, false)
        val tvSpinnerText = view.findViewById<TextView>(R.id.tvSpinnerText)
        tvSpinnerText.text = exercise.get(position).exerciseName
        return view
    }
}