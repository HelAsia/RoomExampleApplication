package com.example.roomexampleapplication.view

import com.example.roomexampleapplication.room.entity.User

interface UserContract {
    interface View {
        fun showToastAfterFinishAction()
        fun showError(message: String?)
        fun setUserData(user: User?)
        fun setButtonsAvailabilityAfterInsert()
        fun setButtonsAvailabilityAfterDelete()
        fun setUserDataVisibility(user: User?)
        fun setUserFieldAfterDelete()
        fun setUserId(id: Long)
    }

    interface Presenter {
        fun saveUserInDb(user: User)
        fun deleteUserFromDb(user: User)
        fun updateUserInDb(user: User)
        fun getUser()
        fun onDestroy()
    }
}