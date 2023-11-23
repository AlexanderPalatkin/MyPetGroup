package com.example.mypet.ui.pet

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.app.R
import com.example.mypet.domain.PetRepository
import com.example.mypet.domain.pet.care.PetCareModel
import com.example.mypet.domain.pet.detail.PetModel
import com.example.mypet.domain.pet.food.PetFoodModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetViewModel @Inject constructor(
    private val petRepository: PetRepository,
) : ViewModel() {
    var activePetMyId: Int? = null

    private val _petList = MutableStateFlow<List<PetModel>>(emptyList())
    val petList = _petList.asStateFlow()

    private val _petFoodList = MutableStateFlow<List<PetFoodModel>>(emptyList())
    val petFoodList = _petFoodList.asStateFlow()

    private val _petCareList = MutableStateFlow<List<PetCareModel>>(emptyList())
    val petCareList = _petCareList.asStateFlow()

    init {
        _petFoodList.value =
            listOf(
                PetFoodModel(1, "Утро 1", "08:05", false),
                PetFoodModel(2, "Утро 2", "08:35", true),
                PetFoodModel(3, "Обедчик", "12:25", false),
                PetFoodModel(4, "Супердлинныйвечерпослетяжелогодня", "20:13", true),
            )

        _petCareList.value =
            listOf(
                PetCareModel(1, R.drawable.ic_pet_kind_hamster, "Ванна", "10:00", 30),
                PetCareModel(2, R.drawable.ic_pet_kind_owl, "Чистка", "20|10|23", 50),
                PetCareModel(3, R.drawable.ic_pet_kind_snail, "Вакцинация", "10|02|24", 80),
                PetCareModel(4, R.drawable.ic_pet_kind_madagascar_hissing_cockroach, "Прогулка", "20:00", 70),
                PetCareModel(5, R.drawable.ic_pet_kind_turtle, "Вычесывание шерсти", "10|05|20", 10),
                PetCareModel(6, R.drawable.ic_pet_kind_frog, "Четоеще", "00:00", 0),
            )
    }

    fun updatePetList() = viewModelScope.launch(Dispatchers.IO) {
        if (_petList.value.isEmpty())
            petRepository.observePetList()
                .collectLatest { _petList.value = it }
    }

    fun deletePet(petId: Int) = viewModelScope.launch(Dispatchers.IO) {
        petRepository.observeDeletePet(petId)
    }
}