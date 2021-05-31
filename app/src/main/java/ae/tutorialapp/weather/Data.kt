package ae.tutorialapp.weather



object Data {
    val items = listOf<Item>(
        Item(1, "Android", R.drawable.ic_baseline_android_24),
        Item(2, "Java", R.drawable.ic_baseline_grade_24),
        Item(3, "Dart", R.drawable.ic_baseline_bedtime_24)
    )


    fun getLongListOfItems(): MutableList<Item>{
        val list = mutableListOf<Item>()
        for (i in 0..50){
            list.addAll(items)
        }
        return list
    }
}
