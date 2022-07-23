package com.mehmetkurt.techcareerfinal.service

import com.mehmetkurt.techcareerfinal.model.BasketResponse
import com.mehmetkurt.techcareerfinal.model.FoodAddResponse
import com.mehmetkurt.techcareerfinal.model.FoodModel
import com.mehmetkurt.techcareerfinal.model.FoodResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface FoodAPI {
    //val BASE_URL = "http://kasimadalan.pe.hu/"
    //http://kasimadalan.pe.hu/
    // yemekler/tumYemekleriGetir.php
    @GET("yemekler/tumYemekleriGetir.php")
    suspend fun getAllFood() : FoodResponse //bu fonksiyon ürünlerin listesini geri döndürücek.(Product daki veri tabanındaki nesneler)
    //suspend olmasının sebebi coroutineleri kullanıcak olmam.
    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    suspend fun yemekEkle(
                  @Field("yemek_adi") yemek_adi: String,
                  @Field("yemek_resim_adi") yemek_resim_adi: String,
                  @Field("yemek_fiyat") yemek_fiyat: String,
                  @Field("yemek_siparis_adet") yemek_siparis_adet: Int,
                  @Field("kullanici_adi") kullanici_adi:String): FoodAddResponse

    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    suspend fun tumSepettekiYemekler(@Field("kullanici_adi") kullanici_adi: String): BasketResponse

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    suspend fun sepettenYemekSil(@Field("sepet_yemek_id") yemek_id: Int, @Field("kullanici_adi") kullanici_adi: String): FoodAddResponse

}