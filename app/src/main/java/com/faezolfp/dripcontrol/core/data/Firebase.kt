package com.faezolfp.dripcontrol.core.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class Firebase {
    private val database: DatabaseReference = FirebaseDatabase.getInstance().getReference("DataInfus")

    fun setTpm(data: Int) {
        database.child("BeratInfus").setValue(data).addOnSuccessListener {
        }.addOnFailureListener {
            Log.d("RESULTFIREBASE", it.toString())
        }
    }

    fun getTpm(): LiveData<Int> {
        val result = MutableLiveData<Int>()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataInf = snapshot.child("BeratInfus").value
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
        database.child("Testesan/DataRealtime").setValue(data).addOnSuccessListener {
        }.addOnFailureListener {
            Log.d("RESULTFIREBASE", it.toString())
        }
    }

    fun getDataInfus(): LiveData<Int> {
        val result = MutableLiveData<Int>()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataInf = snapshot.child("Testesan/DataRealtime").value
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
        database.child("Testesan/Max").setValue(data).addOnSuccessListener {
        }.addOnFailureListener {
            Log.d("RESULTFIREBASE", it.toString())
        }
    }

    fun getDataInfusMax(): LiveData<Int> {
        val result = MutableLiveData<Int>()
        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val dataInf = snapshot.child("Testesan/Max").value
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

    companion object {
        private var INSTANCE: Firebase? = null

        fun getInstance(): Firebase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: Firebase()
        }
    }
}
