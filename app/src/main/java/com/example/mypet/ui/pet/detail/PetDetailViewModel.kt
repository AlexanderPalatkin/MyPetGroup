package com.example.mypet.ui.pet.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypet.domain.PetDetailRepository
import com.example.mypet.domain.pet.detail.PetFoodModel
import com.example.mypet.domain.pet.detail.PetModel
import com.example.mypet.domain.pet.detail.SwitchPetFoodAlarmStateModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PetDetailViewModel @Inject constructor(
    private val petDetailRepository: PetDetailRepository,
) : ViewModel() {
    private val _petList = MutableStateFlow<List<PetModel>>(emptyList())
    val petList = _petList.asStateFlow()

    private fun updatePetList() = viewModelScope.launch(Dispatchers.IO) {
        petDetailRepository.observePetListDetail()
            .collectLatest { _petList.value = it }
    }

    init {
        updatePetList()
    }

    var activePetId: Int? = null

    fun switchPetFoodAlarmState(petFoodModel: PetFoodModel) {
        petFoodModel.alarmId ?: return
        petFoodModel.alarmIsActive ?: return

        viewModelScope.launch(Dispatchers.IO) {
            val switchPetFoodAlarmStateModel =
                SwitchPetFoodAlarmStateModel(
                    alarmId = petFoodModel.alarmId,
                    alertIsActive = !petFoodModel.alarmIsActive
                )
            petDetailRepository.switchPetFoodAlarmState(switchPetFoodAlarmStateModel)
        }
    }
}