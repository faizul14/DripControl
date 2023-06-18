package com.faezolfp.dripcontrol.core.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class Firebase {
    val database: DatabaseReference

    init {
        database = FirebaseDatabase.getInstance().getReference("DataInfus")
    }

    fun setTpm(data: Int) {
        database.child("BeratInfus").setValue(data).addOnSuccessListener {

        }.addOnFailureListener {

        }
    }

    fun getTpm(): LiveData<Int> {
        var result = MutableLiveData<Int>()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataInf = snapshot.child("BeratInfus").value
                result.value = dataInf.toString().toInt()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        database.addValueEventListener(listener)

        return result
    }

    companion object {
        private var INSTANCE: Firebase? = null

        fun getInstance(): Firebase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Firebase()
        }
    }
}