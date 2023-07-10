package com.faezolfp.dripcontrol.core.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class PasienEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "nama")
    var nama: String? = null,

    @ColumnInfo(name = "umur")
    var umur: String? = null,

    @ColumnInfo(name = "brtbadan")
    var brtbadan: String? = null,

    @ColumnInfo(name = "banyakcairaninfus")
    var banyakcairaninfus: String? = null,

    @ColumnInfo(name = "lamapemberianinfus")
    var lamapemberianinfus: String? = null,

    @ColumnInfo(name = "tetsanpermenit")
    var tetsanpermenit: String? = null,

    @ColumnInfo(name = "kamar")
    var kamar: Int? = null,
) : Parcelable
