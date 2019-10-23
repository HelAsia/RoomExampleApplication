package com.example.roomexampleapplication.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.example.roomexampleapplication.MyApplication
import com.example.roomexampleapplication.R
import com.example.roomexampleapplication.presenter.UserPresenter
import com.example.roomexampleapplication.room.entity.User
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserContract.View {
    private val presenter: UserContract.Presenter by lazy {
        UserPresenter(this, MyApplication.get(this).getUserService())
    }

    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListeners()
        presenter.getUser()
    }

    override fun setUserId(id: Long) {
        user?.id = id
    }

    override fun showToastAfterFinishAction() {
        Toast.makeText(this, "OK", Toast.LENGTH_LONG).show()
    }

    override fun showError(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun setUserData(user: User?) {
        this.user = user
        userNameMainActivity.text = user?.userName ?: ""
        userSurnameMainActivity.text = user?.userSurname ?: ""
        setUserDataVisibility(user)
    }

    override fun setUserDataVisibility(user: User?){
        if(user != null){
            userDataMainActivity.visibility = View.VISIBLE
            insertUserDataMainActivity.visibility = View.GONE
            setButtonsAvailabilityAfterInsert()
        }else{
            userDataMainActivity.visibility = View.GONE
            insertUserDataMainActivity.visibility = View.VISIBLE
            setButtonsAvailabilityAfterDelete()
        }
    }

    private fun setListeners(){
        insertButtonListener()
        updateButtonListener()
        deleteButtonListener()
    }

    private fun insertButtonListener(){
        insertUserMainActivity.setOnClickListener { presenter.saveUserInDb(getUserFromFields()) }
    }

    private fun updateButtonListener(){
        updateUserMainActivity.setOnClickListener {
            if(insertUserDataMainActivity.isVisible){
                setUserDataToUpdateInDb()
                val temporaryUser = user
                if(temporaryUser != null){
                    presenter.updateUserInDb(temporaryUser)
                }
            } else {
                insertUserDataMainActivity.visibility = View.VISIBLE
                setUserFieldToUpdate()
                updateUserMainActivity.text = resources.getString(R.string.save_button)
            }
        }
    }

    private fun setUserFieldToUpdate(){
        insertUserNameMainActivity.setText(userNameMainActivity.text.toString())
        insertUserSurnameMainActivity.setText(userSurnameMainActivity.text.toString())
    }

    override fun setUserFieldAfterDelete(){
        insertUserNameMainActivity.setText("")
        insertUserSurnameMainActivity.setText("")
    }

    private fun deleteButtonListener(){
        deleteUserMainActivity.setOnClickListener {
            val temporaryUser = user
            if(temporaryUser != null){
                presenter.deleteUserFromDb(temporaryUser)
            }
        }
    }

    private fun getUserFromFields(): User {
        return User(userName = insertUserNameMainActivity.text.toString(),
            userSurname = insertUserSurnameMainActivity.text.toString())
    }

    private fun setUserDataToUpdateInDb() {
        user?.userName = insertUserNameMainActivity.text.toString()
        user?.userSurname = insertUserSurnameMainActivity.text.toString()
    }

    override fun setButtonsAvailabilityAfterInsert() {
        insertUserMainActivity.isEnabled = false
        insertUserMainActivity
            .setBackgroundColor(ContextCompat.getColor(this, R.color.gray))

        updateUserMainActivity.isEnabled = true
        updateUserMainActivity
            .setBackgroundColor(ContextCompat.getColor(this, R.color.black))
        updateUserMainActivity.text = resources.getString(R.string.update_button)

        deleteUserMainActivity.isEnabled = true
        deleteUserMainActivity
            .setBackgroundColor(ContextCompat.getColor(this, R.color.black))
    }

    override fun setButtonsAvailabilityAfterDelete() {
        insertUserMainActivity.isEnabled = true
        insertUserMainActivity
            .setBackgroundColor(ContextCompat.getColor(this, R.color.black))

        updateUserMainActivity.isEnabled = false
        updateUserMainActivity
            .setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
        updateUserMainActivity.text = resources.getString(R.string.update_button)

        deleteUserMainActivity.isEnabled = false
        deleteUserMainActivity
            .setBackgroundColor(ContextCompat.getColor(this, R.color.gray))
    }

    override fun onDestroy() {
        presenter.onDestroy()
        super.onDestroy()
    }
}
