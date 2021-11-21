package com.canerture.booksapp.data.repos

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsersRepository {

    private var _isSignIn = MutableLiveData<Boolean>()

    private var _isSignUp = MutableLiveData<Boolean>()

    private var auth = Firebase.auth
    private val db = Firebase.firestore

    fun getIsSignIn(): MutableLiveData<Boolean> {
        return _isSignIn
    }

    fun getIsSignUp(): MutableLiveData<Boolean> {
        return _isSignUp
    }

    fun signIn(eMail: String, password: String) {

        auth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                _isSignIn.value = true
                Log.d(SIGN_IN_TAG, SUCCESS)
            } else {
                _isSignIn.value = false
                Log.w(SIGN_IN_TAG, FAILURE, task.exception)
            }

        }
    }

    fun signUp(eMail: String, password: String, nickname: String, phoneNumber: String) {

        auth.createUserWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {

                val currentUser = auth.currentUser
                currentUser?.let { fbUser ->
                    val user = hashMapOf(
                        ID to fbUser.uid,
                        E_MAIL to eMail,
                        NICKNAME to nickname,
                        PHONE_NUMBER to phoneNumber
                    )

                    db.collection(COLLECTION_PATH).document(fbUser.uid)
                        .set(user)
                        .addOnSuccessListener {
                            Log.d(SIGN_UP_TAG, SUCCESS)
                        }
                        .addOnFailureListener { e ->
                            Log.w(SIGN_UP_TAG, FAILURE, e)
                        }
                }

                _isSignUp.value = true
                Log.d(SIGN_UP_TAG, SUCCESS)

            } else {
                _isSignUp.value = false
                Log.w(SIGN_UP_TAG, FAILURE, task.exception)
            }

        }
    }

    companion object {
        private const val SIGN_IN_TAG = "SIGN IN"
        private const val SIGN_UP_TAG = "SIGN UP"
        private const val COLLECTION_PATH = "users"
        private const val SUCCESS = "Success"
        private const val FAILURE = "Failure"
        private const val ID = "id"
        private const val E_MAIL = "email"
        private const val NICKNAME = "nickname"
        private const val PHONE_NUMBER = "phonenumber"

    }

}