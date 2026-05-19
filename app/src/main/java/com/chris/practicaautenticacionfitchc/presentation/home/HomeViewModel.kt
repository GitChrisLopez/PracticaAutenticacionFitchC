package com.chris.practicaautenticacionfitchc.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import com.chris.practicaautenticacionfitchc.presentation.model.Artist
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
    private var db: FirebaseFirestore = Firebase.firestore
    private val _artist = MutableStateFlow<List<Artist>>(emptyList())
    val artist: StateFlow<List<Artist>> = _artist

    init {
//        repeat(20) {
//            loadData()
//        }
        getArtists()
    }
//
//    private fun loadData() {
//        val random = (1..1000).random()
//        val artist = Artist(name = "Random $random", description = "Descripcion random #$random", image = "https://media.tenor.com/cRTQk6N_FxMAAAAe/swag-cat-swagbilli-cutecat-cats-cat-swag-ok-yooo-yo.png")
//
//        db.collection("artist").add(artist)
//    }

    private fun getArtists() {
        viewModelScope.launch {
            val result: List<Artist> = withContext(Dispatchers.IO) {
                getAllArtists()
            }

            _artist.value = result

        }
    }

    suspend fun getAllArtists(): List<Artist> {

        return try {
            db.collection("artist")
                .get()
                .await()
                .documents
                .mapNotNull { snapshot ->
                    snapshot.toObject(Artist::class.java)
                }

        } catch (e: Exception) {
            Log.i("aris", e.toString())
            emptyList()

        }
    }
}