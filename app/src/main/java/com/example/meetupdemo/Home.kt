package com.example.meetupdemo

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter

@Composable
fun InstaHomePage() {
    Scaffold(
        topBar = {
            InstaHomeScreenTopBar(addPostClick = { }, messagesClick = {})
        },
        backgroundColor = Color.Black
    ) {
        LazyColumn(
            modifier = Modifier.padding(it),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                InstaStories()
            }

            items(items = instaPostList) { instaPost ->
                InstaPosts(data = instaPost)
            }
        }
    }
}

@Composable
fun InstaStories(
    modifier: Modifier = Modifier
) {
    val storySize = 70.dp
    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                RoundedProfilePic(
                    modifier = Modifier.size(storySize)
                )
                Spacer(modifier = Modifier.height(1.dp))
                Text("Your Story", color = Color.White)
            }
        }
        items(items = instaPostList) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                InstaStory(
                    profilePic = rememberGlidePainter(it.painter),
                    modifier = Modifier.size(storySize)
                )

                Spacer(modifier = Modifier.height(1.dp))

                Text(
                    text = it.author,
                    color = Color.White,
                    modifier = Modifier
                        .padding(top = 1.dp)
                        .width(60.dp),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun InstaStoriesPreview() {
    InstaStories(modifier = Modifier.fillMaxWidth())
}

@Composable
fun InstaPosts(
    modifier: Modifier = Modifier,
    data: InstagramPostData
) {
    var isLiked by remember(data.id) {
        mutableStateOf(false)
    }
    var isBookmarked by remember(data.id) {
        mutableStateOf(false)
    }
    Column(
        modifier = modifier
    ) {
        InstaPostHeader(data)

        InstaImageSection(
            modifier = Modifier.padding(vertical = 4.dp),
            data = data
        )

        Spacer(modifier = Modifier.height(4.dp))

        InstaPostLikeIconSection(
            modifier = Modifier,
            isLiked = isLiked,
            isBookmarked = isBookmarked,
            onLikeButtonClick = {
                isLiked = !isLiked
            },
            onBookmarkClick = {
                isBookmarked = !isBookmarked
            }
        )

        InstaPostBottomSection(modifier = Modifier, data = data)
    }
}

@ExperimentalAnimationApi
@Preview(showBackground = true, backgroundColor = 0xFF000000)
@Composable
fun InstaPostsPreview() {
    InstaPosts(
        data = InstagramPostData(
            id = 1,
            painter = R.drawable.sundar_pichai_profile,
            author = "Sundar Pichai",
            authorHasStory = false,
            authorImageId = R.drawable.sundar_pichai_profile,
            caption = "Wear your failures as a badge of honor.",
            time = "15 Minutes Ago"
        )
    )
}