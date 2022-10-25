package dev.oure.fitmax.database

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.oure.fitmax.models.Exercise

@Dao
interface ExerciseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertExercises(exercise: Exercise)

    @Query("SELECT * FROM Exercises")
    fun getExercises() : LiveData<List<Exercise>>

    @Query("SELECT * FROM Exercises WHERE categoryId = :categoryId")
    fun fetchExercisesByCategory(categoryId: String): LiveData<List<Exercise>>

}