package com.example.roomexampleapplication.presenter

import com.example.roomexampleapplication.model.UserServiceImpl
import com.example.roomexampleapplication.room.entity.User
import com.example.roomexampleapplication.view.UserContract
import io.reactivex.disposables.CompositeDisposable

class UserPresenter(
    private val userView: UserContract.View,
    private val userService: UserServiceImpl
): UserContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun saveUserInDb(user: User) {
        compositeDisposable.add(userService.insertUser(user)
            .subscribe(
                {
                    userView.setUserId(it)
                    userView.setButtonsAvailabilityAfterInsert()
                    userView.setUserData(user)
                    userView.setUserDataVisibility(user)
                    userView.showToastAfterFinishAction()
                },
                { t: Throwable -> userView.showError(t.message) }
            )
        )
    }

    override fun deleteUserFromDb(user: User) {
        compositeDisposable.add(userService.deleteUser(user)
            .subscribe(
                {
                    userView.setButtonsAvailabilityAfterDelete()
                    userView.setUserFieldAfterDelete()
                    userView.setUserDataVisibility(null)
                    userView.showToastAfterFinishAction()
                },
                { t: Throwable -> userView.showError(t.message) }
            )
        )
    }

    override fun updateUserInDb(user: User) {
        compositeDisposable.add(userService.updateUser(user)
            .subscribe(
                {
                    userView.setButtonsAvailabilityAfterInsert()
                    userView.setUserData(user)
                    userView.setUserDataVisibility(user)
                    userView.showToastAfterFinishAction()
                },
                { t: Throwable -> userView.showError(t.message) }
            )
        )
    }

    override fun getUser() {
        compositeDisposable.add(userService.getUser()
            .subscribe(
                { userView.setUserData(it) },
                { t: Throwable -> userView.showError(t.message) }
            )
        )
    }


    override fun onDestroy() {
        compositeDisposable.clear()
    }
}