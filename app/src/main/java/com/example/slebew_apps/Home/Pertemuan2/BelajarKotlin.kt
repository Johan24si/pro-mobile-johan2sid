package com.example.slebew_apps.Home.Pertemuan2
fun main() {
    println("Hai rekan-rekan,...")
    println("Selamat datan di bahasa pemograman kotlin")

    println("============")

    var angka = 15
    println("HAsil dari 15 + 10 =  ${angka + 10}")

    var nilaiInt = 1000
    var nilaiDouble = 100.003
    var nilaiFloat = 1000.0f

    println("Nilai Integer = $nilaiInt")
    println("Nilai Double = $nilaiDouble")
    println("Nilai Float = $nilaiFloat")

    println("=====String=====")
    val huruf = 'a'
    println("ini penggunaan karakter '$huruf")

    val nilaiString = "Mawar"
    println("Halo $nilaiString!\napa kabar?")

    println("=====kondisi=====")

    val nilai = 10
    if(nilai<0)
        println("Nilai Bialangan negatif")
    else {
        if(nilai%2 == 0)
            println("Bilangan Genap")
        else
            println("Bilangan Ganjil")

    }
    println("=====Perulangan=====")
    val kampusku: Array<String> = arrayOf("Kampus","Politeknik","Caltex", "Riau")
    for (kampus in kampusku)
        println(kampus)




}
