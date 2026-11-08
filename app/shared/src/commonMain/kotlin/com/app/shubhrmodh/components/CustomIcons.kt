package com.app.shubhrmodh.components

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

object CustomIcons {
    val Menu: ImageVector by lazy {
        ImageVector.Builder(
            name = "Menu",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(3f, 6f)
                horizontalLineTo(21f)
                moveTo(3f, 12f)
                horizontalLineTo(21f)
                moveTo(3f, 18f)
                horizontalLineTo(21f)
            }
        }.build()
    }

    val Close: ImageVector by lazy {
        ImageVector.Builder(
            name = "Close",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round
            ) {
                moveTo(18f, 6f)
                lineTo(6f, 18f)
                moveTo(6f, 6f)
                lineTo(18f, 18f)
            }
        }.build()
    }

    val GitHub: ImageVector by lazy {
        ImageVector.Builder(
            name = "GitHub",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.White)
            ) {
                moveTo(12f, 2f)
                curveTo(6.477f, 2f, 2f, 6.477f, 2f, 12f)
                curveTo(2f, 16.418f, 4.865f, 20.166f, 8.839f, 21.489f)
                curveTo(9.339f, 21.581f, 9.521f, 21.272f, 9.521f, 21.008f)
                curveTo(9.521f, 20.771f, 9.512f, 19.982f, 9.508f, 19.148f)
                curveTo(6.726f, 19.752f, 6.139f, 17.959f, 6.139f, 17.959f)
                curveTo(5.685f, 16.805f, 5.03f, 16.498f, 5.03f, 16.498f)
                curveTo(4.122f, 15.877f, 5.099f, 15.889f, 5.099f, 15.889f)
                curveTo(6.103f, 15.96f, 6.632f, 16.921f, 6.632f, 16.921f)
                curveTo(7.525f, 18.45f, 8.971f, 18.009f, 9.542f, 17.753f)
                curveTo(9.633f, 17.106f, 9.892f, 16.664f, 10.178f, 16.414f)
                curveTo(7.957f, 16.162f, 5.621f, 15.304f, 5.621f, 11.474f)
                curveTo(5.621f, 10.383f, 6.011f, 9.491f, 6.651f, 8.793f)
                curveTo(6.548f, 8.541f, 6.205f, 7.524f, 6.749f, 6.155f)
                curveTo(6.749f, 6.155f, 7.588f, 5.886f, 9.497f, 7.179f)
                curveTo(10.294f, 6.957f, 11.148f, 6.846f, 12f, 6.842f)
                curveTo(12.852f, 6.846f, 13.706f, 6.957f, 14.505f, 7.179f)
                curveTo(16.412f, 5.886f, 17.249f, 6.155f, 17.249f, 6.155f)
                curveTo(17.795f, 7.524f, 17.452f, 8.541f, 17.349f, 8.793f)
                curveTo(17.991f, 9.491f, 18.377f, 10.383f, 18.377f, 11.474f)
                curveTo(18.377f, 15.314f, 16.037f, 16.159f, 13.809f, 16.406f)
                curveTo(14.167f, 16.714f, 14.488f, 17.325f, 14.488f, 18.258f)
                curveTo(14.488f, 19.599f, 14.476f, 20.68f, 14.476f, 21.008f)
                curveTo(14.476f, 21.275f, 14.655f, 21.587f, 15.165f, 21.488f)
                curveTo(19.136f, 20.163f, 22f, 16.417f, 22f, 12f)
                curveTo(22f, 6.477f, 17.523f, 2f, 12f, 2f)
                close()
            }
        }.build()
    }

    val LinkedIn: ImageVector by lazy {
        ImageVector.Builder(
            name = "LinkedIn",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                fill = SolidColor(Color.White)
            ) {
                moveTo(19f, 3f)
                horizontalLineTo(5f)
                curveTo(3.895f, 3f, 3f, 3.895f, 3f, 5f)
                verticalLineTo(19f)
                curveTo(3f, 20.105f, 3.895f, 21f, 5f, 21f)
                horizontalLineTo(19f)
                curveTo(20.105f, 21f, 21f, 20.105f, 21f, 19f)
                verticalLineTo(5f)
                curveTo(21f, 3.895f, 20.105f, 3f, 19f, 3f)
                close()
                moveTo(8.339f, 18.337f)
                horizontalLineTo(5.667f)
                verticalLineTo(9.722f)
                horizontalLineTo(8.339f)
                verticalLineTo(18.337f)
                close()
                moveTo(7.003f, 8.545f)
                curveTo(6.145f, 8.545f, 5.451f, 7.848f, 5.451f, 6.992f)
                curveTo(5.451f, 6.136f, 6.145f, 5.439f, 7.003f, 5.439f)
                curveTo(7.859f, 5.439f, 8.555f, 6.136f, 8.555f, 6.992f)
                curveTo(8.555f, 7.848f, 7.859f, 8.545f, 7.003f, 8.545f)
                close()
                moveTo(18.338f, 18.337f)
                horizontalLineTo(15.67f)
                verticalLineTo(14.152f)
                curveTo(15.67f, 13.154f, 15.65f, 11.87f, 14.281f, 11.87f)
                curveTo(12.891f, 11.87f, 12.678f, 12.955f, 12.678f, 14.08f)
                verticalLineTo(18.337f)
                horizontalLineTo(10.01f)
                verticalLineTo(9.722f)
                horizontalLineTo(12.57f)
                verticalLineTo(10.898f)
                horizontalLineTo(12.606f)
                curveTo(12.962f, 10.222f, 13.834f, 9.509f, 15.134f, 9.509f)
                curveTo(17.841f, 9.509f, 18.338f, 11.291f, 18.338f, 13.605f)
                verticalLineTo(18.337f)
                close()
            }
        }.build()
    }

    val ExternalLink: ImageVector by lazy {
        ImageVector.Builder(
            name = "ExternalLink",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(18f, 13f)
                verticalLineTo(19f)
                curveTo(18f, 19.53f, 17.789f, 20.039f, 17.414f, 20.414f)
                curveTo(17.039f, 20.789f, 16.53f, 21f, 16f, 21f)
                horizontalLineTo(5f)
                curveTo(4.47f, 21f, 3.961f, 20.789f, 3.586f, 20.414f)
                curveTo(3.211f, 20.039f, 3f, 19.53f, 3f, 19f)
                verticalLineTo(8f)
                curveTo(3f, 7.47f, 3.211f, 6.961f, 3.586f, 6.586f)
                curveTo(3.961f, 6.211f, 4.47f, 6f, 5f, 6f)
                horizontalLineTo(11f)
                moveTo(15f, 3f)
                horizontalLineTo(21f)
                verticalLineTo(9f)
                moveTo(10f, 14f)
                lineTo(21f, 3f)
            }
        }.build()
    }

    val Mail: ImageVector by lazy {
        ImageVector.Builder(
            name = "Mail",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(4f, 4f)
                horizontalLineTo(20f)
                curveTo(21.1f, 4f, 22f, 4.9f, 22f, 6f)
                verticalLineTo(18f)
                curveTo(22f, 19.1f, 21.1f, 20f, 20f, 20f)
                horizontalLineTo(4f)
                curveTo(2.9f, 20f, 2f, 19.1f, 2f, 18f)
                verticalLineTo(6f)
                curveTo(2f, 4.9f, 2.9f, 4f, 4f, 4f)
                close()
                moveTo(22f, 6f)
                lineTo(12f, 13f)
                lineTo(2f, 6f)
            }
        }.build()
    }

    val Phone: ImageVector by lazy {
        ImageVector.Builder(
            name = "Phone",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(22f, 16.92f)
                verticalLineTo(19.92f)
                curveTo(22f, 20.48f, 21.54f, 20.97f, 20.98f, 20.92f)
                curveTo(11.6f, 20.09f, 3.91f, 12.4f, 3.08f, 3.02f)
                curveTo(3.03f, 2.46f, 3.52f, 2f, 4.08f, 2f)
                horizontalLineTo(7.08f)
                curveTo(7.58f, 2f, 8.01f, 2.37f, 8.09f, 2.86f)
                curveTo(8.26f, 3.92f, 8.57f, 4.95f, 9.02f, 5.91f)
                curveTo(9.19f, 6.27f, 9.1f, 6.7f, 8.8f, 6.96f)
                lineTo(7.33f, 8.2f)
                curveTo(9.08f, 11.51f, 12.49f, 14.92f, 15.8f, 16.67f)
                lineTo(17.04f, 15.2f)
                curveTo(17.3f, 14.9f, 17.73f, 14.81f, 18.09f, 14.98f)
                curveTo(19.05f, 15.43f, 20.08f, 15.74f, 21.14f, 15.91f)
                curveTo(21.63f, 15.99f, 22f, 16.42f, 22f, 16.92f)
                close()
            }
        }.build()
    }

    val Location: ImageVector by lazy {
        ImageVector.Builder(
            name = "Location",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(21f, 10f)
                curveTo(21f, 17f, 12f, 23f, 12f, 23f)
                curveTo(12f, 23f, 3f, 17f, 3f, 10f)
                curveTo(3f, 5.03f, 7.03f, 1f, 12f, 1f)
                curveTo(16.97f, 1f, 21f, 5.03f, 21f, 10f)
                close()
                moveTo(15f, 10f)
                curveTo(15f, 11.66f, 13.66f, 13f, 12f, 13f)
                curveTo(10.34f, 13f, 9f, 11.66f, 9f, 10f)
                curveTo(9f, 8.34f, 10.34f, 7f, 12f, 7f)
                curveTo(13.66f, 7f, 15f, 8.34f, 15f, 10f)
                close()
            }
        }.build()
    }

    val Code: ImageVector by lazy {
        ImageVector.Builder(
            name = "Code",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(16f, 18f)
                lineTo(22f, 12f)
                lineTo(16f, 6f)
                moveTo(8f, 6f)
                lineTo(2f, 12f)
                lineTo(8f, 18f)
            }
        }.build()
    }

    val Work: ImageVector by lazy {
        ImageVector.Builder(
            name = "Work",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(4f, 7f)
                horizontalLineTo(20f)
                verticalLineTo(19f)
                curveTo(20f, 19.53f, 19.789f, 20.039f, 19.414f, 20.414f)
                curveTo(19.039f, 20.789f, 18.53f, 21f, 18f, 21f)
                horizontalLineTo(6f)
                curveTo(5.47f, 21f, 4.961f, 20.789f, 4.586f, 20.414f)
                curveTo(4.211f, 20.039f, 4f, 19.53f, 4f, 19f)
                verticalLineTo(7f)
                close()
                moveTo(9f, 7f)
                verticalLineTo(5f)
                curveTo(9f, 4.47f, 9.211f, 3.961f, 9.586f, 3.586f)
                curveTo(9.961f, 3.211f, 10.47f, 3f, 11f, 3f)
                horizontalLineTo(13f)
                curveTo(13.53f, 3f, 14.039f, 3.211f, 14.414f, 3.586f)
                curveTo(14.789f, 3.961f, 15f, 4.47f, 15f, 5f)
                verticalLineTo(7f)
            }
        }.build()
    }

    val School: ImageVector by lazy {
        ImageVector.Builder(
            name = "School",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(22f, 10f)
                verticalLineTo(16f)
                moveTo(2f, 10f)
                lineTo(12f, 5f)
                lineTo(22f, 10f)
                lineTo(12f, 15f)
                lineTo(2f, 10f)
                close()
                moveTo(6f, 12.5f)
                verticalLineTo(16.5f)
                curveTo(6f, 18.5f, 8.5f, 20f, 12f, 20f)
                curveTo(15.5f, 20f, 18f, 18.5f, 18f, 16.5f)
                verticalLineTo(12.5f)
            }
        }.build()
    }

    val Trophy: ImageVector by lazy {
        ImageVector.Builder(
            name = "Trophy",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            path(
                stroke = SolidColor(Color.White),
                strokeLineWidth = 2f,
                strokeLineCap = StrokeCap.Round,
                strokeLineJoin = StrokeJoin.Round
            ) {
                moveTo(8f, 21f)
                horizontalLineTo(16f)
                moveTo(12f, 17f)
                verticalLineTo(21f)
                moveTo(7f, 4f)
                horizontalLineTo(17f)
                verticalLineTo(10f)
                curveTo(17f, 12.76f, 14.76f, 15f, 12f, 15f)
                curveTo(9.24f, 15f, 7f, 12.76f, 7f, 10f)
                verticalLineTo(4f)
                close()
                moveTo(7f, 6f)
                horizontalLineTo(4f)
                curveTo(3.47f, 6f, 3f, 6.47f, 3f, 7f)
                verticalLineTo(8f)
                curveTo(3f, 9.66f, 4.34f, 11f, 6f, 11f)
                horizontalLineTo(7f)
                moveTo(17f, 6f)
                horizontalLineTo(20f)
                curveTo(20.53f, 6f, 21f, 6.47f, 21f, 7f)
                verticalLineTo(8f)
                curveTo(21f, 9.66f, 19.66f, 11f, 18f, 11f)
                horizontalLineTo(17f)
            }
        }.build()
    }
}
