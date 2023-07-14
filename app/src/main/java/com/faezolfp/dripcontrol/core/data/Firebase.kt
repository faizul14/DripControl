package com.faezolfp.dripcontrol.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*

class Firebase {
    private val database: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("DripControl")

    fun setTpm(data: Int) {
        database.child("TPM").setValue(data).addOnSuccessListener {
        }.addOnFailureListener {
            Log.d("RESULTFIREBASE", it.toString())
        }
    }

    fun getTpm(): LiveData<Int> {
        val result = MutableLiveData<Int>()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataInf = snapshot.child("TPM").value
                result.value = dataInf.toString().toInt()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("RESULTFIREBASE", error.toString())
            }
        }

        database.addValueEventListener(listener)

        return result
    }

    fun setDataInfus(data: Int) {
        database.child("VolumeInfus/CurrentVolume").setValue(data).addOnSuccessListener {
        }.addOnFailureListener {
            Log.d("RESULTFIREBASE", it.toString())
        }
    }

    fun getDataInfus(): LiveData<Int> {
        val result = MutableLiveData<Int>()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataInf = snapshot.child("VolumeInfus/CurrentVolume").value
                result.value = dataInf.toString().toInt()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("RESULTFIREBASE", error.toString())
            }
        }

        database.addValueEventListener(listener)

        return result
    }

    fun setDataInfusMax(data: Int) {
        database.child("VolumeInfus/MaxVolume").setValue(data).addOnSuccessListener {
        }.addOnFailureListener {
            Log.d("RESULTFIREBASE", it.toString())
        }
    }

    fun getDataInfusMax(): LiveData<Int> {
        val result = MutableLiveData<Int>()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataInf = snapshot.child("VolumeInfus/MaxVolume").value
                result.value = dataInf.toString().toInt()
                Log.d("TRACKING", dataInf.toString())
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("RESULTFIREBASE", error.toString())
            }
        }

        database.addValueEventListener(listener)

        return result
    }

    fun setStatusInfus(data: String) {
        database.child("StatusInfus").setValue(data).addOnSuccessListener {
        }.addOnFailureListener {
            Log.d("RESULTFIREBASE", it.toString())
        }
    }

    fun getStatusInfus(): LiveData<String> {
        val result = MutableLiveData<String>()

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val data = snapshot.child("StatusInfus").value
                result.value = data.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d("RESULTFIREBASE", error.toString())
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
