package ae.tutorialapp.weather.storage

import ae.tutorialapp.weather.models.ForeCast
import androidx.room.*
import io.reactivex.Completable


@Dao
interface ForeCastDao {

    @Insert
    fun insert(foreCast: ForeCast): Completable

    @Update
    fun update(foreCast: ForeCast): Completable

    @Delete
    fun delete(foreCast: ForeCast): Completable

}