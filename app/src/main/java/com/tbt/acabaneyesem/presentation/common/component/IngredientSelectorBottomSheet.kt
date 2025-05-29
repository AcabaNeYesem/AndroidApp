package com.tbt.acabaneyesem.presentation.common.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.tbt.acabaneyesem.data.remote.recipie.ingredients.dto.RecipeIngredientsDtoItem
import com.tbt.acabaneyesem.presentation.ui.theme.DarkGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun IngredientSelectorBottomSheet(
    ingredientsExpanded: Boolean,
    onDismissRequest: () -> Unit,
    ingredients: List<RecipeIngredientsDtoItem>,
    selectedIngredients: Set<String>,
    onIngredientsSelected: (Set<String>) -> Unit,
    searchText: String,
    onSearchTextChange: (String) -> Unit
) {
    if (ingredientsExpanded) {
        ModalBottomSheet(
            modifier = Modifier.systemBarsPadding(),
            onDismissRequest = onDismissRequest
        ) {
            Column {
                TextField(
                    value = searchText,
                    onValueChange = onSearchTextChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .clip(RoundedCornerShape(10.dp)),
                    textStyle = TextStyle(
                        fontWeight = FontWeight.W600,
                        fontSize = 20.sp,
                        color = DarkGreen
                    ),
                    placeholder = {
                        Text("Malzeme ara...", color = DarkGreen)
                    },
                    maxLines = 1,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = DarkGreen.copy(0.3f),
                        focusedContainerColor = DarkGreen.copy(0.3f),
                        disabledIndicatorColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                )
                LazyColumn {
                    ingredients.forEach { categoryItem ->
                        val filteredItems = if (searchText.isBlank()) {
                            categoryItem.items
                        } else {
                            categoryItem.items.filter {
                                it.contains(searchText, ignoreCase = true)
                            }
                        }

                        if (filteredItems.isNotEmpty()) {
                            item {
                                Text(
                                    text = categoryItem.category,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(8.dp)
                                )
                            }

                            items(filteredItems.size) { item ->
                                val itemName = filteredItems[item]
                                val isChecked = selectedIngredients.contains(itemName)
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable {
                                            val updatedSet = if (isChecked) {
                                                selectedIngredients - itemName
                                            } else {
                                                selectedIngredients + itemName
                                            }
                                            onIngredientsSelected(updatedSet)
                                        }
                                        .padding(8.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Checkbox(
                                        checked = isChecked,
                                        onCheckedChange = { checked ->
                                            val updatedSet = if (checked) {
                                                selectedIngredients + itemName
                                            } else {
                                                selectedIngredients - itemName
                                            }
                                            onIngredientsSelected(updatedSet)
                                        }
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(text = itemName.lowercase().replaceFirstChar { it.uppercase() })
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
