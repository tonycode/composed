package dev.tonycode.composed.mbank.ui.screens.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.tonycode.composed.mbank.model.UserProfile
import dev.tonycode.composed.mbank.usecase.user.GetAuthorizedUserIdUsecase
import dev.tonycode.composed.mbank.usecase.user.GetUserProfileUsecase
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor() : ViewModel() {

    // user

    private val _userProfile = mutableStateOf<UserProfile?>(null)

    val userProfile: State<UserProfile?>
        get() = _userProfile


    init {
        loadData()
    }

    private fun loadData() {
        viewModelScope.launch {
            val authorizedUserId = GetAuthorizedUserIdUsecase().execute()
            _userProfile.value = GetUserProfileUsecase(authorizedUserId).execute()
        }
    }

}
