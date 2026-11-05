package com.example.m3catalog.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.CheckCircle
import androidx.compose.material.icons.rounded.Code
import androidx.compose.material.icons.rounded.Gavel
import androidx.compose.material.icons.rounded.OpenInNew
import androidx.compose.material.icons.rounded.Palette
import androidx.compose.material.icons.rounded.Shield
import androidx.compose.material.icons.rounded.SystemUpdate
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.m3catalog.R
import kotlinx.coroutines.launch
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * =========================================================================================
 * UNIVERSAL ABOUT PAGE
 * Reusable, standard About architecture across all applications.
 *
 * Design Spec:
 * 1. Top bar: Circular back button and clean title.
 * 2. Hero Visualizer:
 *    - Center circle: "Main Icon Area" containing the full Logo (no padding, edge-to-edge).
 *    - Ambient organic shapes: 4-leaf clover (top-left) and 9-side cookie (bottom-right)
 *      animating continuously in slow floating/rotating motion.
 * 3. App Title & Interactive Version Badge (with Easter egg tap counter).
 * 4. Developer Segmented Group:
 *    - Avatar (developer.png) + Name + Role.
 *    - Dual pill action buttons: GitHub (with SVG) and Email (with SVG).
 * 5. Repository & Specification Segmented Group:
 *    - Check for Updates, Open Source GitHub repo, Upstream repo, Licenses, and M3 Specs.
 * 6. Footer: "Made with ❤️ | Android × Kotlin × Jetpack Compose".
 * =========================================================================================
 */
@Composable
fun AboutScreen(
    onBack: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    // Easter Egg Tap Counter
    var versionTapCount by remember { mutableIntStateOf(0) }
    var lastTapTime by remember { mutableLongStateOf(0L) }

    fun onVersionTap() {
        val now = System.currentTimeMillis()
        if (now - lastTapTime > 3000L) {
            versionTapCount = 0
        }
        lastTapTime = now
        versionTapCount++

        coroutineScope.launch {
            if (versionTapCount >= 7) {
                versionTapCount = 0
                snackbarHostState.showSnackbar("🎉 Developer Mode / Error Collector Activated!")
            } else if (versionTapCount >= 3) {
                val remaining = 7 - versionTapCount
                snackbarHostState.showSnackbar("You are $remaining step${if (remaining > 1) "s" else ""} away from Developer Mode")
            }
        }
    }

    fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(intent)
    }

    // -------------------------------------------------------------------------------------
    // Ambient Infinite Motion for Organic Shapes (4-Leaf Clover & 9-Side Cookie)
    // -------------------------------------------------------------------------------------
    val infiniteTransition = rememberInfiniteTransition(label = "AboutShapesAmbient")

    // 4-leaf clover rotation & float
    val cloverRotation by infiniteTransition.animateFloat(
        initialValue = -12f,
        targetValue = 18f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 14000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "CloverRotation"
    )
    val cloverOffsetY by infiniteTransition.animateFloat(
        initialValue = -8f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 9000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "CloverFloat"
    )

    // 9-side cookie rotation & scale
    val cookieRotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 32000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "CookieRotation"
    )
    val cookieScale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "CookieScale"
    )

    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 32.dp)
        ) {
            // -----------------------------------------------------------------------------
            // TOP BAR
            // -----------------------------------------------------------------------------
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    onClick = onBack,
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceContainerHigh,
                    modifier = Modifier.size(48.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "About",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            // -----------------------------------------------------------------------------
            // HERO ILLUSTRATION: LOGO + AMBIENT 4-LEAF CLOVER & 9-SIDE COOKIE
            // -----------------------------------------------------------------------------
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentAlignment = Alignment.Center
            ) {
                // Background Layer: Top-Left Animated 4-Leaf Clover
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .offset(x = 10.dp, y = (-30).dp + cloverOffsetY.dp)
                        .graphicsLayer {
                            rotationZ = cloverRotation
                        }
                ) {
                    Canvas(modifier = Modifier.size(130.dp)) {
                        val c = Offset(size.width / 2f, size.height / 2f)
                        val r = size.width * 0.38f
                        val path = Path()
                        val steps = 60
                        for (i in 0..steps) {
                            val angle = i * (2.0 * PI / steps)
                            val mod = 1f + 0.28f * sin(angle * 4.0).toFloat()
                            val curR = r * mod
                            val x = c.x + (curR * cos(angle)).toFloat()
                            val y = c.y + (curR * sin(angle)).toFloat()
                            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                        }
                        path.close()
                        drawPath(path, color = Color(0xFF6750A4).copy(alpha = 0.85f))
                    }
                }

                // Background Layer: Bottom-Right Animated 9-Side Cookie
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .offset(x = (-10).dp, y = 50.dp)
                        .graphicsLayer {
                            rotationZ = cookieRotation
                            scaleX = cookieScale
                            scaleY = cookieScale
                        }
                ) {
                    Canvas(modifier = Modifier.size(140.dp)) {
                        val c = Offset(size.width / 2f, size.height / 2f)
                        val r = size.width * 0.38f
                        val path = Path()
                        val steps = 90
                        for (i in 0..steps) {
                            val angle = i * (2.0 * PI / steps)
                            // 9 harmonic lobes
                            val mod = 1f + 0.16f * sin(angle * 9.0).toFloat()
                            val curR = r * mod
                            val x = c.x + (curR * cos(angle)).toFloat()
                            val y = c.y + (curR * sin(angle)).toFloat()
                            if (i == 0) path.moveTo(x, y) else path.lineTo(x, y)
                        }
                        path.close()
                        drawPath(path, color = Color(0xFF6750A4).copy(alpha = 0.85f))
                    }
                }

                // Foreground Layer: Main Icon Area (Full-Bleed Logo, No Padding)
                Surface(
                    modifier = Modifier
                        .size(200.dp)
                        .border(
                            width = 2.dp,
                            color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.4f),
                            shape = CircleShape
                        ),
                    shape = CircleShape,
                    color = MaterialTheme.colorScheme.surfaceContainerLowest
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.app_logo),
                            contentDescription = "App Logo",
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }

            // -----------------------------------------------------------------------------
            // APP TITLE & VERSION BADGE
            // -----------------------------------------------------------------------------
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "M3 Expressive Catalog",
                    style = MaterialTheme.typography.headlineMedium,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-0.5).sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(8.dp))

                Surface(
                    onClick = { onVersionTap() },
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.6f)
                ) {
                    Text(
                        text = "Version 1.2.0",
                        modifier = Modifier.padding(horizontal = 14.dp, vertical = 6.dp),
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                Text(
                    text = "A production-grade, interactive Android Jetpack Compose encyclopedia and showcase app built strictly around Google's Material 3 Expressive design system.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            // -----------------------------------------------------------------------------
            // DEVELOPER SEGMENTED GROUP
            // -----------------------------------------------------------------------------
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
            ) {
                Column {
                    ListItem(
                        headlineContent = { Text("Abhijeet Yadav", fontWeight = FontWeight.Bold) },
                        supportingContent = { Text("Lead Developer & Architect") },
                        leadingContent = {
                            Image(
                                painter = painterResource(id = R.drawable.developer),
                                contentDescription = "Developer Avatar",
                                modifier = Modifier
                                    .size(46.dp)
                                    .clip(CircleShape)
                            )
                        },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier.clickable { openUrl("https://github.com/junksidetm") }
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        // GitHub Pill Button
                        Surface(
                            onClick = { openUrl("https://github.com/junksidetm") },
                            shape = RoundedCornerShape(100),
                            color = MaterialTheme.colorScheme.surfaceContainerHighest,
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_github),
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp),
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "GitHub",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }

                        // Email Pill Button
                        Surface(
                            onClick = { openUrl("mailto:contact.dsidetm@gmail.com") },
                            shape = RoundedCornerShape(100),
                            color = MaterialTheme.colorScheme.surfaceContainerHighest,
                            modifier = Modifier.weight(1f)
                        ) {
                            Row(
                                modifier = Modifier.padding(vertical = 10.dp, horizontal = 14.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_email_at),
                                    contentDescription = null,
                                    modifier = Modifier.size(18.dp),
                                    tint = MaterialTheme.colorScheme.onSurface
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text(
                                    text = "Email",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // -----------------------------------------------------------------------------
            // REPOSITORY & ACTIONS SEGMENTED GROUP
            // -----------------------------------------------------------------------------
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                shape = RoundedCornerShape(24.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh)
            ) {
                Column {
                    ListItem(
                        headlineContent = { Text("Check for Updates") },
                        supportingContent = { Text("v1.2.0 • Latest release with verified signature") },
                        leadingContent = { Icon(Icons.Rounded.SystemUpdate, contentDescription = null) },
                        trailingContent = { Icon(Icons.Rounded.OpenInNew, contentDescription = null, modifier = Modifier.size(18.dp)) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier.clickable { openUrl("https://github.com/junksidetm/m3-expressive-catalog/releases/latest") }
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                    ListItem(
                        headlineContent = { Text("Open Source Repository") },
                        supportingContent = { Text("View and contribute on GitHub") },
                        leadingContent = { Icon(Icons.Rounded.Code, contentDescription = null) },
                        trailingContent = { Icon(Icons.Rounded.OpenInNew, contentDescription = null, modifier = Modifier.size(18.dp)) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier.clickable { openUrl("https://github.com/junksidetm/m3-expressive-catalog") }
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                    ListItem(
                        headlineContent = { Text("Material 3 Guidelines") },
                        supportingContent = { Text("Official design system authority (m3.material.io)") },
                        leadingContent = { Icon(Icons.Rounded.Palette, contentDescription = null) },
                        trailingContent = { Icon(Icons.Rounded.OpenInNew, contentDescription = null, modifier = Modifier.size(18.dp)) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier.clickable { openUrl("https://m3.material.io/") }
                    )
                    HorizontalDivider(modifier = Modifier.padding(horizontal = 16.dp))

                    ListItem(
                        headlineContent = { Text("License") },
                        supportingContent = { Text("Apache License 2.0") },
                        leadingContent = { Icon(Icons.Rounded.Gavel, contentDescription = null) },
                        colors = ListItemDefaults.colors(containerColor = Color.Transparent),
                        modifier = Modifier.clickable { openUrl("https://github.com/junksidetm/m3-expressive-catalog/blob/main/LICENSE") }
                    )
                }
            }

            Spacer(modifier = Modifier.height(36.dp))

            // -----------------------------------------------------------------------------
            // FOOTER
            // -----------------------------------------------------------------------------
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Text(
                    text = "Made with ❤️",
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
                )

                Text(
                    text = "Android  ×  Kotlin  ×  Jetpack Compose",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                )
            }
        }

        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
        )
    }
}
