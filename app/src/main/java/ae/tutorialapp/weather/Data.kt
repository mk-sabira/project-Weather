package ae.tutorialapp.weather



object Data {
    val items = listOf<Item>(
        Item(1, "Android", R.drawable.ic_baseline_android_24),
        Item(2, "Java-languageJava- languageJava- languageJava- languageJava- languageJava- language", R.drawable.ic_baseline_grade_24),
        Item(3, "Dart", R.drawable.ic_baseline_bedtime_24)
    )


    fun getLongListOfItems(): MutableList<Any> {
        val list = mutableListOf<Any>()
        for (i in 1..300){
            list.addAll(items)
            if (i %3 == 0)
                list.add("https://www.google.com/search?q=kotlin&safe=strict&sxsrf=ALeKk00V7KvSUO2-ycSauBB25JpLwLYWrg:1622708383844&tbm=isch&source=iu&ictx=1&fir=XapnRuVwiOJWfM%252CC3g9p4Eo2gBs9M%252C%252Fm%252F0_lcrx4&vet=1&usg=AI4_-kTDyTjSQKYlC-_LuwjPYTDctW1YkQ&sa=X&ved=2ahUKEwinyavmg_vwAhXVmFwKHeozCXUQ_B16BAg7EAE#imgrc=XapnRuVwiOJWfM")

        }
        return list
    }
}
