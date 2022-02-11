@Suppress("unused")
object Dependencies {
    const val core_ktx = "androidx.core:core-ktx:1.7.0"
    const val appcompat = "androidx.appcompat:appcompat:1.4.1"
    const val material = "com.google.android.material:material:1.5.0"
    const val constraintlayout = "androidx.constraintlayout:constraintlayout:2.1.3"
    const val lifecycle_livedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycle}"
    const val lifecycle_viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val navigation_fragment = "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigation_ui = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"
    const val timber = "com.jakewharton.timber:timber:5.0.1"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofit_convert_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    const val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    const val gson = "com.google.code.gson:gson:2.8.9"
    const val viewpager2 = "androidx.viewpager2:viewpager2:1.0.0"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    const val glide_okhttp_integration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
    const val glide_transformations = "jp.wasabeef:glide-transformations:4.3.0"
    const val moshi = "com.squareup.moshi:moshi:1.13.0"
    const val retrofit_convert_moshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val brvah = "com.github.CymChad:BaseRecyclerViewAdapterHelper:3.0.7"

    const val junit = "junit:junit:4.13.2"
    const val ext_junit = "androidx.test.ext:junit:1.1.3"
    const val espresso_core = "androidx.test.espresso:espresso-core:3.4.0"
}

object Versions {
    const val lifecycle = "2.4.0"
    const val navigation = "2.3.5"
    const val retrofit = "2.9.0"
    const val okhttp = "4.9.3"
    const val glide = "4.13.0"
}