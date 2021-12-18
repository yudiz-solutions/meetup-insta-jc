package com.example.meetupdemo

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.glide.rememberGlidePainter


@Composable
fun InstaPostHeader(
    data: InstagramPostData,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (data.authorHasStory) {
                InstaStory(modifier = Modifier.size(35.dp))
            } else {
                RoundedProfilePic(modifier = Modifier.size(35.dp))
            }

            Text(
                text = data.author,
                color = Color.White,
                modifier = Modifier.padding(horizontal = 12.dp)
            )
        }

        Icon(imageVector = Icons.Default.MoreVert, contentDescription = "More", tint = Color.White)
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun InstaImageSection(
    data: InstagramPostData,
    modifier: Modifier = Modifier
) {
    Image(
        painter = rememberGlidePainter(request = data.authorImageId),
        contentDescription = "Post",
        modifier = modifier
            .fillMaxWidth()
            .height(400.dp),
        contentScale = ContentScale.Crop
    )
}


@Composable
fun InstaPostLikeIconSection(
    modifier: Modifier = Modifier,
    isLiked: Boolean,
    isBookmarked: Boolean,
    onLikeButtonClick: () -> Unit,
    onBookmarkClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 6.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { onLikeButtonClick.invoke() }) {
                Image(
                    imageVector = if (isLiked) {
                        Icons.Filled.Favorite
                    } else {
                        Icons.Outlined.FavoriteBorder
                    }, contentDescription = "Like",
                    colorFilter = if (isLiked)
                        ColorFilter.tint(Color.Red)
                    else
                        ColorFilter.tint(Color.White),
                    modifier = Modifier.size(28.dp)
                )
            }
            IconButton(onClick = { }) {
                Image(
                    painter = rememberGlidePainter(request = R.drawable.ic_insta_comment),
                    contentDescription = "Like",
                    colorFilter = ColorFilter.tint(
                        Color.White
                    ),
                    modifier = Modifier.size(36.dp)
                )
            }
            IconButton(onClick = { }) {
                Image(
                    painter =
                    rememberGlidePainter(request = R.drawable.ic_insta_send),
                    contentDescription = "Like",
                    colorFilter = ColorFilter.tint(
                        Color.White
                    ),
                    modifier = Modifier.size(22.dp)

                )
            }
        }

        IconButton(onClick = { onBookmarkClick.invoke() }) {
            Image(
                painter = if (isBookmarked)
                    rememberGlidePainter(request = R.drawable.ic_insta_bookmarked_fillded)
                else
                    rememberGlidePainter(request = R.drawable.ic_insta_bookmarked_outlined),
                contentDescription = "Bookmark",
                colorFilter = ColorFilter.tint(Color.White),
                modifier = Modifier.size(28.dp)
            )
        }
    }
}

@Composable
fun InstaHomeScreenTopBar(
    modifier: Modifier = Modifier,
    addPostClick: () -> Unit,
    messagesClick: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        elevation = 8.dp,
        backgroundColor = Color.Black
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_insta_text),
                contentDescription = "Instagram",
                colorFilter = ColorFilter.tint(color = Color.White),
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
            )

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier
                    .padding(end = 8.dp)
            ) {
                IconButton(onClick = addPostClick) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.ic_insta_add_post),
                        contentDescription = "Add Post",
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )
                }

                IconButton(onClick = messagesClick) {
                    Image(
                        modifier = Modifier.size(24.dp),
                        painter = painterResource(R.drawable.ic_insta_messenger),
                        contentDescription = "Messages",
                        colorFilter = ColorFilter.tint(color = Color.White)
                    )
                }
            }
        }

    }
}


@Preview
@Composable
fun InstaHomeScreenTopBarPreview() {
    InstaHomeScreenTopBar(modifier = Modifier, {}, {})
}


@Composable
fun RoundedProfilePic(
    modifier: Modifier = Modifier,
    profilePic: Painter = painterResource(R.drawable.mark_zuckerberg_profile)
) {
    Image(
        modifier = modifier
            .clip(CircleShape),
        painter = profilePic,
        contentDescription = "Story",
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun RoundedCornerProfilePicPreview() {
    RoundedProfilePic(modifier = Modifier.size(120.dp))
}


@Composable
fun InstaStory(
    modifier: Modifier = Modifier,
    profilePic: Painter = painterResource(id = R.drawable.sundar_pichai_profile)
) {
    val gradientColor = listOf(
        Color(0xFFF9AA1B),
        Color(0xFFF76E12),
        Color(0xFFE95D3A),
        Color(0xFFC20998),
        Color(0xFFC6078B),
        Color(0xFFF9AA1B),
    ).reversed()
    Image(
        modifier = modifier
            .sizeIn(60.dp, 60.dp)
            .clip(CircleShape)
            .background(brush = Brush.sweepGradient(colors = gradientColor))
            .padding(1.8.dp)
            .clip(CircleShape)
            .background(Color.Black)
            .padding(1.5.dp)
            .clip(CircleShape),
        painter = profilePic,
        contentDescription = "Story",
        contentScale = ContentScale.Crop
    )
}

@Preview
@Composable
fun InstaStoryPreview() {
    InstaStory(
        modifier = Modifier.size(120.dp),
        profilePic = painterResource(R.drawable.sundar_pichai_profile)
    )
}

@Composable
fun InstaPostBottomSection(
    modifier: Modifier,
    data: InstagramPostData
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            RoundedProfilePic(
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = buildAnnotatedString {
                    append("Liked by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("Mark Zukerberg, Elon Musk")
                    }
                    append(" and ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append("12,12,123 others")
                    }
                },
                color = Color.White,
                modifier = Modifier.padding(horizontal = 8.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        // if caption is there
        if (data.caption.isNotEmpty()) {
            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(data.author)
                    }
                    append(" ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                        append(data.caption)
                    }
                },
                color = Color.White,
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 2.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }

        Text(
            text = "View all 140000 Comments",
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 2.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = data.time,
            color = Color.LightGray,
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 0.dp),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}