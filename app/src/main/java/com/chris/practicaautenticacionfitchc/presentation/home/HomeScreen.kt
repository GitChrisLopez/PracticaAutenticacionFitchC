package com.chris.practicaautenticacionfitchc.presentation.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.chris.practicaautenticacionfitchc.presentation.model.Artist
import com.chris.practicaautenticacionfitchc.ui.theme.Black
import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import kotlin.collections.emptyList
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun HomeScreen(viewModel: HomeViewModel = HomeViewModel()) {

    val artists: State<List<Artist>> = viewModel.artist.collectAsState()

    Column(
        Modifier
            .fillMaxSize()
            .background(Black)
    ) {
        Text(
            "Popular artist",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            modifier = Modifier.padding(16.dp)
        )

        LazyRow {
            items(artists.value) {
                ArtistItem(it)
            }
        }

    }
}

@Composable
fun ArtistItem(artist: Artist) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(4.dp))

        AsyncImage(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape),
            model = artist.image,
            contentDescription = "Artists Image",
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(4.dp))

        artist.name?.let {
            Text(
                text = it,
                color = Color.White,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview
@Composable
fun ArtistItemPreview() {
    val artist = Artist(
        "Pepa",
        "El mejor",
        "https://bi.org/wp-content/uploads/2023/11/featured-famous-bi-billie-eilish-1-1024x1024.jpg",
        //emptyList()
    )

    ArtistItem(artist = artist)
}

//
//
//fun createArtist(db: FirebaseFirestore){
//    val random = (1..1000).random()
//    val artist = Artist(name = "Random $random", numberOfSongs = random)
//
//    db.collection("artist")
//        .add(artist)
//        .addOnSuccessListener {
//            Log.i("Aris", "SUCCESS")
//        }
//
//        .addOnFailureListener {
//            Log.i("Aris", "FAILURE")
//        }
//
//        .addOnCompleteListener {
//            Log.i("Aris", "COMPLETE")
//        }
//}