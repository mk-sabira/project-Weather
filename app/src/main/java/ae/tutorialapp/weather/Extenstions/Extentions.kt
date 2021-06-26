package ae.tutorialapp.weather

import java.text.SimpleDateFormat
import java.util.*

fun Long?.format(pattern:String? = "dd/MM/yyyy"):String{
    this?.let {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())

        return sdf.format(Date(this * 1000))
    }
    return ""
}
