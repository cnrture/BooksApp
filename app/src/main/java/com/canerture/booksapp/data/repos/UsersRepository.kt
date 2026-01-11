package com.canerture.booksapp.data.repos

import com.canerture.booksapp.common.Constants.COLLECTION_PATH
import com.canerture.booksapp.common.Constants.E_MAIL
import com.canerture.booksapp.common.Constants.ID
import com.canerture.booksapp.common.Constants.NICKNAME
import com.canerture.booksapp.common.Constants.PHONE_NUMBER
import com.canerture.booksapp.common.Constants.USERS
import com.canerture.booksapp.data.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class UsersRepository(
    private val auth: FirebaseAuth,
    private val db: FirebaseFirestore,
) {
    suspend fun signIn(eMail: String, password: String): Result<Unit> {
        return try {
            val signInTask = auth.signInWithEmailAndPassword(eMail, password).await()

            if (signInTask.user != null) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("User not found."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun signUp(eMail: String, password: String, nickname: String, phoneNumber: String): Result<Unit> {

        return try {
            val signUpTask = auth.createUserWithEmailAndPassword(eMail, password).await()

            return if (signUpTask.user != null) {
                val currentUser = signUpTask.user
                val user = hashMapOf(
                    ID to currentUser!!.uid,
                    E_MAIL to eMail,
                    NICKNAME to nickname,
                    PHONE_NUMBER to phoneNumber
                )

                db.collection(COLLECTION_PATH).document(currentUser.uid).set(user).await()

                Result.success(Unit)
            } else {
                Result.failure(Exception("User not found."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getUserInfo(): Result<UserModel> {

        return try {
            val currentUser = auth.currentUser

            if (currentUser != null) {
                val userData = db.collection(USERS).document(currentUser.uid).get().await()

                val user = UserModel(
                    currentUser.email,
                    userData.get(NICKNAME) as String,
                    userData.get(PHONE_NUMBER) as String
                )

                Result.success(user)
            } else {
                Result.failure(Exception("User not found."))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    fun signOut() {
        auth.signOut()
    }
}