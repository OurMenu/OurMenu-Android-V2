package com.kuit.ourmenu.ui.common.map

import android.util.Log
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.kakao.vectormap.KakaoMap
import com.kakao.vectormap.KakaoMapReadyCallback
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MapController {
    private val _kakaoMap = MutableStateFlow<KakaoMap?>(null)
    val kakaoMap: StateFlow<KakaoMap?> = _kakaoMap
    
    fun setMap(map: KakaoMap) {
        _kakaoMap.value = map
    }

    fun resetMap(){
        _kakaoMap.value = null
    }
}

@Composable
fun mapViewWithLifecycle(
    mapController: MapController? = null,
    mapSettings: (kakaoMap : KakaoMap) -> Unit
): View {
    val context = LocalContext.current
    val mapView = remember { MapView(context) }
    val lifecycle = LocalLifecycleOwner.current.lifecycle

    DisposableEffect(lifecycle) {
        val observer = object : DefaultLifecycleObserver {
            override fun onCreate(owner: LifecycleOwner) {
                mapView.start(
                    object : MapLifeCycleCallback() {
                        override fun onMapDestroy() {
                            // 지도 API가 정상적으로 종료될 때 호출됩니다.
                            Log.d("MapViewWithLifecycle", "onMapDestroy")
                            mapController?.resetMap()
                        }

                        override fun onMapError(error: Exception) {
                            // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됩니다.
                            Log.d("MapViewWithLifecycle", "onMapError")
                        }
                    },
                    object : KakaoMapReadyCallback() {
                        override fun onMapReady(kakaoMap: KakaoMap) {
                            // 지도 초기화 및 설정 작업
                            Log.d("MapViewWithLifecycle", "onMapReady")
                            mapController?.setMap(kakaoMap)
                            mapSettings(kakaoMap)
                        }
                    }
                )
            }

            override fun onResume(owner: LifecycleOwner) {
                Log.d("MapViewWithLifecycle", "onMapResume")
                mapView.resume()
            }

            override fun onPause(owner: LifecycleOwner) {
                Log.d("MapViewWithLifecycle", "onMapPause")
                mapView.pause()
            }
        }

        lifecycle.addObserver(observer)
        onDispose {
            lifecycle.removeObserver(observer)
        }
    }

    return mapView
}