package ae.tutorialapp.weather.storage

import ae.tutorialapp.weather.models.ForeCast
import androidx.room.*


@Database(
    entities = [ForeCast::class],
    version = 2,
    exportSchema = false
)

@TypeConverters(ModelsConverter::class, CollectionConverter::class)
abstract class ForeCastDatabase:RoomDatabase() {

    abstract fun foreCastDao(): ForeCastDao

    companion object{
        const val DB_NAME = "foreCastDb"
    }
}