package io.android.bisiparis.singleton

import io.android.bisiparis.R


object DataHolder {
    lateinit var userName: String
    lateinit var imagesSoup: Array<Int>
    lateinit var imagesMainCourse: Array<Int>
    lateinit var imagesDrink: Array<Int>
    lateinit var imagesDesert: Array<Int>

    fun getSoup(): ArrayList<String> {
        val soups: ArrayList<String> = arrayListOf("Mercimek", "Ezogelin", "Tarhana", "Domates")
        imagesSoup = arrayOf(R.drawable.mercimek, R.drawable.ezogelin, R.drawable.tarhana, R.drawable.domates)

        return soups
    }

    fun getMainMeal(): ArrayList<String> {
        val mainMeals: ArrayList<String> = arrayListOf("Kuru Fasulye", "Adana Kebap", "İzmir Köfte", "Maklube")
        imagesMainCourse = arrayOf(R.drawable.kurufasulye, R.drawable.adanakebap, R.drawable.izmirkofte, R.drawable.maklube)

        return mainMeals
    }

    fun getDrink(): ArrayList<String> {
        val drinks: ArrayList<String> = arrayListOf("Milkshake", "Ayran", "Kola", "Alkollü Kokteyl")
        imagesDrink = arrayOf(R.drawable.milkshake, R.drawable.ayran, R.drawable.kola, R.drawable.kokteyl)

        return drinks
    }

    fun getDesert(): ArrayList<String> {
        val desert: ArrayList<String> = arrayListOf("Dondurma", "Profiterol", "Asuman", "Tiramisu")
        imagesDesert = arrayOf(R.drawable.dondurma, R.drawable.profiterol, R.drawable.asuman, R.drawable.tiramisu)

        return desert
    }
}