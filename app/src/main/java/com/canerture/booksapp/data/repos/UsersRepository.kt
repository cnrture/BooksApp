package com.canerture.booksapp.data.repos

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.canerture.booksapp.common.Constants.COLLECTION_PATH
import com.canerture.booksapp.common.Constants.E_MAIL
import com.canerture.booksapp.common.Constants.FAILURE
import com.canerture.booksapp.common.Constants.ID
import com.canerture.booksapp.common.Constants.NICKNAME
import com.canerture.booksapp.common.Constants.PHONE_NUMBER
import com.canerture.booksapp.common.Constants.SIGN_IN
import com.canerture.booksapp.common.Constants.SIGN_UP
import com.canerture.booksapp.common.Constants.SUCCESS
import com.canerture.booksapp.data.model.UserModel
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsersRepository {

    var isSignIn = MutableLiveData<Boolean>()

    var isSignUp = MutableLiveData<Boolean>()

    var userInfo = MutableLiveData<UserModel>()

    private var auth = Firebase.auth
    private val db = Firebase.firestore

    fun signIn(eMail: String, password: String) {

        auth.signInWithEmailAndPassword(eMail, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                isSignIn.value = true
                Log.d(SIGN_IN, SUCCESS)
            } else {
                isSignIn.value = false
                Log.w(SIGN_IN, FAILURE, task.exception)
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
                            isSignUp.value = true
                            Log.d(SIGN_UP, SUCCESS)
                        }
                        .addOnFailureListener { e ->
                            isSignUp.value = false
                            Log.w(SIGN_UP, FAILURE, e)
                        }
                }

            } else {
                isSignUp.value = false
                Log.w(SIGN_UP, FAILURE, task.exception)
            }
        }
    }

    fun getUserInfo() {
        auth.currentUser?.let { user ->

            val docRef = db.collection("users").document(user.uid)
            docRef.get()
                .addOnSuccessListener { document ->
                    document?.let {
                        userInfo.value = UserModel(
                            user.email,
                            document.get("nickname") as String,
                            document.get("phonenumber") as String
                        )
                    }
                }
                .addOnFailureListener { exception ->
                    Log.d(ContentValues.TAG, "get failed with ", exception)
                }
        }
    }

    fun signOut() {
        auth.signOut()
    }
}