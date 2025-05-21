package com.kuit.ourmenu.ui.menuFolder.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kuit.ourmenu.R
import com.kuit.ourmenu.data.model.menuFolder.response.SortOrderType
import com.kuit.ourmenu.ui.common.topappbar.BackButtonTopAppBar
import com.kuit.ourmenu.ui.menuFolder.component.AddButton
import com.kuit.ourmenu.ui.menuFolder.component.MenuFolderMenuButton
import com.kuit.ourmenu.ui.menuFolder.component.SortDropdown
import com.kuit.ourmenu.ui.menuFolder.viewmodel.MenuFolderDetailViewModel
import com.kuit.ourmenu.ui.theme.Neutral50
import com.kuit.ourmenu.ui.theme.NeutralWhite
import com.kuit.ourmenu.ui.theme.ourMenuTypography

@Composable
fun MenuFolderDetailScreen(
    menuFolderId: Int,
//    onNavigateToMenuInfo: () -> Unit, // TODO: Menu Info로 화면 이동 구현
//    onNavigateToMap: () -> Unit, // TODO: Map으로 화면 이동 구현
    onNavigateToAddMenu: () -> Unit, // TODO: AddMenu로 화면 이동 구현
    onNavigateBack: () -> Unit,
    viewModel: MenuFolderDetailViewModel = hiltViewModel()
) {
    val menuFolderDetails by viewModel.menuFolderDetails.collectAsStateWithLifecycle()

    LaunchedEffect(menuFolderId) {
        viewModel.getMenuFolderDetails(menuFolderId)
    }

    val options = SortOrderType.entries
    var selectedOption by rememberSaveable { mutableStateOf(SortOrderType.TITLE_ASC) }

    Scaffold(
        topBar = {},
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
            ) {
                Box(
                    modifier = Modifier
                        .height(192.dp),
                ) {
                    Image(
                        painter = painterResource(R.drawable.img_dummy_pizza),
                        contentDescription = "menu folder image",
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier.fillMaxWidth()
                    )

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(
                                        Color.Black.copy(alpha = 0f),  // 투명 (rgba(0, 0, 0, 0.00))
                                        Color.Black.copy(alpha = 0.5f) // 불투명 (rgba(0, 0, 0, 0.70))
                                    ),
                                    startY = 0f,
                                    endY = 400f // 점점 어두워지는 위치 설정
                                )
                            )
                    )

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(top = 144.dp, start = 20.dp, end = 20.dp)
                            .fillMaxWidth()
                    ) {

                        Row(
                            verticalAlignment = Alignment.CenterVertically,

                            ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_popup_dice),
                                contentDescription = "Folder Icon",
                                modifier = Modifier.size(32.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = stringResource(R.string.menu_folder_name),
                                color = NeutralWhite,
                                style = ourMenuTypography().pretendard_600_20,
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = String.format(stringResource(R.string.count), menuFolderDetails.size),
                                color = Neutral50,
                                style = ourMenuTypography().pretendard_500_14,
                            )
                        }

                        SortDropdown(
                            options = options.map { it.displayName },
                            selectedOption = selectedOption.displayName,
                            color = NeutralWhite
                        ) { selectedDisplayName ->
                            val newSortOption = options.first { it.displayName == selectedDisplayName }
                            selectedOption = newSortOption
                            viewModel.updateSortOrder(newSortOption, menuFolderId)
                        }
                    }
                }

                LazyColumn(
                    modifier = Modifier.padding(top = 16.dp),
                ) {
                    items(menuFolderDetails.size) { index ->
                        MenuFolderMenuButton(
                            menuFolderDetail = menuFolderDetails[index],
                            onMenuClick = {
//                                onNavigateToMenuInfo()
                            },
                            onMapClick = {
//                                onNavigateToMap()
                            }
                        )
                    }

                    item {
                        AddButton(
                            stringResource(R.string.add_menu),
                            modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
                        ) {
                            onNavigateToAddMenu()
                        }
                    }
                }
            }

            BackButtonTopAppBar(NeutralWhite, true) {
                onNavigateBack()
            }
        }
    )

}

@Preview(showBackground = true)
@Composable
private fun MenuFolderDetailScreenPreview() {
    MenuFolderDetailScreen(
        menuFolderId = 0,
//        onNavigateToMenuInfo = {},
//        onNavigateToMap = {},
        onNavigateToAddMenu = {},
        onNavigateBack = {},
    )
}