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
import com.kakao.vectormap.LatLng
import com.kakao.vectormap.MapLifeCycleCallback
import com.kakao.vectormap.MapView
import com.kakao.vectormap.camera.CameraUpdateFactory
import com.kakao.vectormap.label.LabelOptions
import com.kakao.vectormap.label.LabelStyle
import com.kakao.vectormap.label.LabelStyles
import com.kuit.ourmenu.R

@Composable
fun MapViewWithLifecycle(): View {
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
                        }

                        override fun onMapError(error: Exception) {
                            // 인증 실패 및 지도 사용 중 에러가 발생할 때 호출됩니다.
                            Log.d("MapViewWithLifecycle", "onMapError")
                        }
                    },
                    object : KakaoMapReadyCallback() {
//                        override fun getPosition(): LatLng {
//                            return LatLng.from()
//                        }

                        override fun onMapReady(kakaoMap: KakaoMap) {
                            // 지도 초기화 및 설정 작업
                            Log.d("MapViewWithLifecycle", "onMapReady")
                            // 카메라 이동
                            val cameraUpdate = CameraUpdateFactory.newCenterPosition(LatLng.from(37.5416, 127.0793))
                            // 라벨 아이콘 설정
                            val style = kakaoMap.labelManager?.addLabelStyles(
                                // R.drawable에서 vector를 가져오면 화면에 보이지 않는 이슈 존재
                                LabelStyles.from(LabelStyle.from(R.drawable.img_popup_dice))
                            )
                            // 라벨 아이콘 적용
                            val options = LabelOptions.from(LatLng.from(37.5406, 127.0763)).setStyles(style)
                            // 레이어
                            val layer = kakaoMap.labelManager?.layer
                            layer?.addLabel(options)
                            kakaoMap.moveCamera(cameraUpdate)
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