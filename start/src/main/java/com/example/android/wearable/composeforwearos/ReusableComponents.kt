/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.wearable.composeforwearos

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Message
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material.icons.rounded.SelfImprovement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.*
import com.example.android.wearable.composeforwearos.theme.WearAppTheme
import com.google.firebase.firestore.FirebaseFirestore

/* Contains individual Wear OS demo composables for the code lab. */
var result: StringBuffer = StringBuffer()
const val test = ""

// TODO: Create a Text Composable
@Composable
fun TextExample(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = ("Welcome to Healthify")
    )
}

// TODO: Create a Card (specifically, an AppCard) Composable
@Composable
fun CardExample(
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {

    AppCard(
        modifier = modifier,
//        appImage = {
//            Icon(
//                imageVector = Icons.Rounded.Message,
//                contentDescription = "triggers open message action",
//                modifier = iconModifier
//            )
//        },
        appName = { Text("Steps Counter") },
        time = { Text("") },
        title = { Text("Number of Steps:") },
        onClick = {}
    ) {
        Text("0$test")
    }
}

// TODO: Create a Chip Composable
@Composable
fun ChipExample(
    modifier: Modifier = Modifier,
//    iconModifier: Modifier = Modifier
) {
    Chip(
        modifier = modifier,
        onClick = { val addWater: Int = 250;
            saveFirestoreWater(addWater)
                  },
        label = {
            Text(
                text = "Update Water",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
//        icon = {
//            Icon(
//                imageVector = Icons.Rounded.SelfImprovement,
//                contentDescription = "triggers meditation action",
//                modifier = iconModifier
//            )
//        },
    )
}

@Composable
fun ChipExample2(
    modifier: Modifier = Modifier,
//    iconModifier: Modifier = Modifier
) {
    Chip(
        modifier = modifier,
        onClick = {
            val addFood = 250
            saveFirestoreFood(addFood)
                  },
        label = {
            Text(
                text = "Update Food",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
//        icon = {
//            Icon(
//                imageVector = Icons.Rounded.SelfImprovement,
//                contentDescription = "triggers meditation action",
//                modifier = iconModifier
//            )
//        },
    )
}

fun saveFirestoreWater(water: Int) {
    val db = FirebaseFirestore.getInstance()

//    val users: MutableMap<Int, > = HashMap()
//    users["waterDrank"] = water
    val users = hashMapOf(
        "age" to "20",
        "calorieGoal" to "1500",
        "caloriesConsumed" to 2500,
        "email" to "gabriel.chetcuti@gmail.com",
        "firstName" to "Gabriel",
        "height" to "185",
        "lastName" to "Chetcuti",
        "stepCounter" to 0,
        "waterDrank" to 2400,
        "waterGoal" to "3000",
        "weight" to "95",
        "weightGoal" to "90"
    )
    db.collection("users").document("waterDrank")
        .set(users)
        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
}

fun saveFirestoreFood(food: Int) {
    val db = FirebaseFirestore.getInstance()

//    val users: MutableMap<Int, > = HashMap()
//    users["waterDrank"] = water
    val users = hashMapOf(
        "age" to "20",
        "calorieGoal" to "1500",
        "caloriesConsumed" to 2200,
        "email" to "gabriel.chetcuti@gmail.com",
        "firstName" to "Gabriel",
        "height" to "185",
        "lastName" to "Chetcuti",
        "stepCounter" to 0,
        "waterDrank" to 2000,
        "waterGoal" to "3000",
        "weight" to "95",
        "weightGoal" to "90"
    )
    db.collection("users").document("caloriesConsumed")
        .set(users)
        .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
        .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }
}

fun readFireStoreData()
{
    val db = FirebaseFirestore.getInstance()
    db.collection("users")
        .get()
        .addOnCompleteListener{
//            val result: StringBuffer = StringBuffer()
            if(it.isSuccessful){
                for(document in it.result!!){
                    result.append(document.data.getValue("stepCounter")).append("stepCounter")
                    val test = result.elementAt(8)
                }
            }
        }
}

fun getStepData(){
    val db = FirebaseFirestore.getInstance()
    val docRef = db.collection("users").document("stepCounter")
    docRef.get()
        .addOnSuccessListener { document ->
            if (document != null) {
                Log.d(TAG, "DocumentSnapshot data: ${document.data}")
            } else {
                Log.d(TAG, "No such document")
            }
        }
        .addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
}

// TODO: Create a ToggleChip Composable
//@Composable
//fun ToggleChipExample(modifier: Modifier = Modifier) {
//    var checked by remember { mutableStateOf(true) }
//    ToggleChip(
//        modifier = modifier,
//        checked = checked,
//        toggleControl = {
//            Switch(
//                checked = checked,
//                modifier = Modifier.semantics {
//                    this.contentDescription = if (checked) "On" else "Off"
//                }
//            )
//        },
//        onCheckedChange = {
//            checked = it
//        },
//        label = {
//            Text(
//                text = "Sound",
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//        }
//    )
//}

// Function only used as a demo for when you start the code lab (removed as step 1).
@Composable
fun StartOnlyTextComposables() {
    Text(
        modifier = Modifier.fillMaxSize(),
        textAlign = TextAlign.Center,
        color = MaterialTheme.colors.primary,
        text = stringResource(R.string.hello_world_starter)
    )
}

/* Previews of Composables. */
// Note: Preview in Android Studio doesn't support the round view yet (coming soon).

// Hello, world starter text preview
@Preview(
    group = "Starter",
    widthDp = WEAR_PREVIEW_ELEMENT_WIDTH_DP,
    heightDp = WEAR_PREVIEW_ELEMENT_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun StartOnlyTextComposablesPreview() {
    WearAppTheme {
        StartOnlyTextComposables()
    }
}

// Button Preview
@Preview(
    group = "Button",
    widthDp = WEAR_PREVIEW_ELEMENT_WIDTH_DP,
    heightDp = WEAR_PREVIEW_ELEMENT_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun ButtonExamplePreview() {
    WearAppTheme {
//        ButtonExample(
//            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
//            iconModifier = Modifier.size(24.dp).wrapContentSize(align = Alignment.Center)
//        )
    }
}

// Text Preview
@Preview(
    group = "Text",
    widthDp = WEAR_PREVIEW_ROW_WIDTH_DP,
    heightDp = WEAR_PREVIEW_ROW_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun TextExamplePreview() {
    WearAppTheme {
        TextExample(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
    }
}

// Card Preview
@Preview(
    group = "Card",
    widthDp = WEAR_PREVIEW_ROW_WIDTH_DP,
    heightDp = WEAR_PREVIEW_ROW_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun CardExamplePreview() {
    WearAppTheme {
        CardExample(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
            iconModifier = Modifier.size(24.dp).wrapContentSize(align = Alignment.Center)
        )
    }
}


// Chip Preview
@Preview(
    group = "Chip",
    widthDp = WEAR_PREVIEW_ROW_WIDTH_DP,
    heightDp = WEAR_PREVIEW_ROW_HEIGHT_DP,
    apiLevel = WEAR_PREVIEW_API_LEVEL,
    uiMode = WEAR_PREVIEW_UI_MODE,
    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
)
@Composable
fun ChipExamplePreview() {
    WearAppTheme {
        ChipExample(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
//            iconModifier = Modifier.size(24.dp).wrapContentSize(align = Alignment.Center)
        )
    }
    WearAppTheme {
        ChipExample2(
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp),
//            iconModifier = Modifier.size(24.dp).wrapContentSize(align = Alignment.Center)
        )
    }
}

// Toggle Chip Preview
//@Preview(
//    group = "Toggle Chip",
//    widthDp = WEAR_PREVIEW_ROW_WIDTH_DP,
//    heightDp = WEAR_PREVIEW_ROW_HEIGHT_DP,
//    apiLevel = WEAR_PREVIEW_API_LEVEL,
//    uiMode = WEAR_PREVIEW_UI_MODE,
//    backgroundColor = WEAR_PREVIEW_BACKGROUND_COLOR_BLACK,
//    showBackground = WEAR_PREVIEW_SHOW_BACKGROUND
//)
//@Composable
//fun ToggleChipExamplePreview() {
//    WearAppTheme {
//        ToggleChipExample(
//            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
//        )
//    }
//}
