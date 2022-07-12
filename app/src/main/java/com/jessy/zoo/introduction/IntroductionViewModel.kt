package com.jessy.zoo.introduction

import android.app.Activity
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jessy.zoo.ZooApplication
import com.jessy.zoo.data.ResultX
import com.jessy.zoo.data.source.PublisherRepository
import com.jessy.zoo.network.AnimalApi
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class IntroductionViewModel(
    private val publisherRepository: PublisherRepository,
    private val arguments: ResultX,
) : ViewModel() {
    private val _resultX = MutableLiveData<ResultX>().apply {
        value = arguments
    }
    val resultX: LiveData<ResultX>
        get() = _resultX

    //    private val url = "file/d/18xJmRLYnq2I953FqXb_40oTkkyHnOFEU/view?usp=sharing"
//    private val url = "https: /drive.google.com/file/d/ 18xJmRLYnq2I953FqXb_40oTkkyHnOFEU/view? usp=sharing\n"


    private val url = "https://drive.google.com/file/d/18xJmRLYnq2I953FqXb_40oTkkyHnOFEU/view"

    private val fileName = url.substring(url.lastIndexOf("/") + 1)
    val context = ZooApplication.instance
    private val pathWhereYouWantToSaveFile = context.filesDir.absolutePath + fileName

    init {
        // getAnimal()
        downloadFile()
    }


    //    private fun getAnimal() {
//        viewModelScope.launch {
//            val apiResult = AnimalApi.animalRetrofitService.getAnimal()
//            Log.v("apiResult","$apiResult")
//
//        }
//    }
    private fun downloadFile() {
        viewModelScope.launch {
            val responseBody = AnimalApi.animalRetrofitService.downloadFile(url).body()

            saveFile(responseBody, pathWhereYouWantToSaveFile)
            Log.v("pathWhereYouWantToSaveFile", "${pathWhereYouWantToSaveFile.toString()}")

        }

    }

    fun saveFile(body: ResponseBody?, pathWhereYouWantToSaveFile: String): String {
        if (body == null)
            return ""
        var input: InputStream? = null

        try {

            input = body.byteStream()
            Log.v("input", "${input.toString()}")
            //val file = File(getCacheDir(), "cacheFileAppeal.srl")
            val fos = FileOutputStream(pathWhereYouWantToSaveFile)
            fos.use { output ->
                val buffer = ByteArray(4 * 1024) // or other buffer size
                var read: Int
                while (input.read(buffer).also { read = it } != -1) {
                    output.write(buffer, 0, read)
                }
                output.flush()
            }
            return pathWhereYouWantToSaveFile

        } catch (e: Exception) {
            Log.e("saveFile", e.toString())
        } finally {
            input?.close()
        }
        return ""
    }

}