package com.tbt.acabaneyesem.presentation.common.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.tbt.acabaneyesem.data.remote.datasource.Feedback
import com.tbt.acabaneyesem.presentation.screens.details.feedBack

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FeedbackBottomSheet(
    feedBackList: List<feedBack>,
    selectedCategory: Int?,
    onSelectCategory: (Int?) -> Unit,
    onSendFeedback: (Feedback) -> Unit,
    isLoading: Boolean,
    error: String,
    recipeId: String,
    onDismiss: () -> Unit
) {
    val context = LocalContext.current

    ModalBottomSheet(
        modifier = Modifier.systemBarsPadding(),
        onDismissRequest = onDismiss
    ) {
        LazyColumn {
            items(feedBackList.size) { index ->
                val isChecked = selectedCategory == feedBackList[index].id
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onSelectCategory(if (isChecked) null else feedBackList[index].id)
                        }
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = isChecked,
                        onCheckedChange = { checked ->
                            onSelectCategory(if (checked) feedBackList[index].id else null)
                        }
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = feedBackList[index].message)
                }
            }

            item {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    verticalArrangement = Arrangement.Bottom,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    CustomButton("Geri Bildirim Yolla") {
                        if (selectedCategory != null) {
                            if (!isLoading) {
                                onSendFeedback(
                                    Feedback(
                                        message = feedBackList[selectedCategory - 1].message,
                                        id = recipeId
                                    )
                                )
                                Toast.makeText(context, "FeedBack Yollandı", Toast.LENGTH_SHORT).show()
                            } else if (error.isNotEmpty()) {
                                Toast.makeText(context, "FeedBack Yollanamadı: $error", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(context, "Lütfen FeedBack Mesajı Seçin", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }
}
