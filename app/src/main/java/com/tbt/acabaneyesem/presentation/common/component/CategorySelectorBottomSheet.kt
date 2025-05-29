package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import com.tbt.acabaneyesem.domain.model.Category

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CategorySelectorBottomSheet(
    categoryExpanded: Boolean,
    onDismissRequest: () -> Unit,
    categories: List<Category>,
    selectedCategories: Set<Int>,
    onCategorySelected: (Set<Int>) -> Unit
) {
    if (categoryExpanded) {
        ModalBottomSheet(
            modifier = Modifier.systemBarsPadding(),
            onDismissRequest = onDismissRequest
        ) {
            LazyColumn {
                items(categories.size) { index ->
                    val isChecked = selectedCategories.contains(categories[index].id)
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                val updatedSet = if (isChecked) {
                                    selectedCategories - categories[index].id
                                } else {
                                    selectedCategories + categories[index].id
                                }
                                onCategorySelected(updatedSet)
                            }
                            .padding(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Checkbox(
                            checked = isChecked,
                            onCheckedChange = { checked ->
                                val updatedSet = if (checked) {
                                    selectedCategories + categories[index].id
                                } else {
                                    selectedCategories - categories[index].id
                                }
                                onCategorySelected(updatedSet)
                            }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(text = categories[index].name)
                    }
                }
            }
        }
    }
}
