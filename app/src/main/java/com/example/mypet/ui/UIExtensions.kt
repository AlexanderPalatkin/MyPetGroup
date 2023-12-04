package com.example.mypet.ui

import android.view.View
import androidx.fragment.app.Fragment
import com.example.mypet.domain.pet.breed.PetBreedCat
import com.example.mypet.domain.pet.breed.PetBreedChameleon
import com.example.mypet.domain.pet.breed.PetBreedDog
import com.example.mypet.domain.pet.breed.PetBreedSnake
import com.example.mypet.domain.pet.breed.PetBreedSpider
import com.example.mypet.domain.pet.kind.PetKind
import com.google.android.material.snackbar.Snackbar

fun View.snackMessage(text: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, text, length).show()
}

fun getPetName(kindOrdinal: Int, breedOrdinal: Int?) =
    getPetBreedName(kindOrdinal, breedOrdinal) ?: PetKind.values()[kindOrdinal].nameResId

private fun getPetBreedName(kindOrdinal: Int, breedOrdinal: Int?) =
    breedOrdinal?.let {
        try {
            when (kindOrdinal) {
                0 -> PetBreedCat.values()[breedOrdinal].nameResId
                1 -> PetBreedDog.values()[breedOrdinal].nameResId
                12 -> PetBreedChameleon.values()[breedOrdinal].nameResId
                14 -> PetBreedSnake.values()[breedOrdinal].nameResId
                17 -> PetBreedSpider.values()[breedOrdinal].nameResId
                else -> null
            }
        } catch (e: Exception) {
            println("UIExtensions -> getPetBreedIcon(): ${e.message}")
            null
        }
    }

fun getPetBreedList(kindOrdinal: Int) =
    try {
        when (kindOrdinal) {
            0 -> PetBreedCat.values().map { it.nameResId }
            1 -> PetBreedDog.values().map { it.nameResId }
            12 -> PetBreedChameleon.values().map { it.nameResId }
            14 -> PetBreedSnake.values().map { it.nameResId }
            17 -> PetBreedSpider.values().map { it.nameResId }
            else -> null
        }
    } catch (e: Exception) {
        println("UIExtensions -> getPetBreedIcon(): ${e.message}")
        null
    }

fun getPetIcon(kindOrdinal: Int, breedOrdinal: Int?) =
    getPetBreedIcon(kindOrdinal, breedOrdinal) ?: PetKind.values()[kindOrdinal].iconResId

private fun getPetBreedIcon(kindOrdinal: Int, breedOrdinal: Int?) =
    breedOrdinal?.let {
        try {
            when (kindOrdinal) {
                1 -> PetBreedCat.values()[breedOrdinal].iconResId
                2 -> PetBreedDog.values()[breedOrdinal].iconResId
                13 -> PetBreedChameleon.values()[breedOrdinal].iconResId
                15 -> PetBreedSnake.values()[breedOrdinal].iconResId
                18 -> PetBreedSpider.values()[breedOrdinal].iconResId
                else -> null
            }
        } catch (e: Exception) {
            println("UIExtensions -> getPetBreedIcon(): ${e.message}")
            null
        }
    }

fun Fragment.getActionBar() =
    (requireActivity() as? MainActivity)?.supportActionBar