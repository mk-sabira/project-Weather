package ae.tutorialapp.weather.storage

import ae.tutorialapp.weather.models.ForeCast
import androidx.room.Dao
import androidx.room.Insert


@Dao
interface ForeCastDao {

    @Insert
    fun insert(foreCast: ForeCast)
}