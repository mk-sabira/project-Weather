package ae.tutorialapp.weather.storage

import ae.tutorialapp.weather.models.ForeCast
import android.content.Context
import androidx.room.*


@Database(
    entities = [ForeCast::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(ModelsConverter::class, CollectionConverter::class)
abstract class ForeCastDatabase:RoomDatabase() {

    abstract fun foreCastDao(): ForeCastDao

    companion object{
        const val DB_NAME = "foreCastDb"

        private  var DB: ForeCastDatabase? = null

        fun getInstance(context: Context): ForeCastDatabase{
            if (DB == null){
                DB = Room.databaseBuilder(
                    context,
                    ForeCastDatabase::class.java,
                    DB_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return DB!!
        }
    }
}