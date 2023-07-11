package com.faezolfp.dripcontrol.core.domain.model

import androidx.room.ColumnInfo
import androidx.room.PrimaryKey

data class Notifikasi(
    var id: Int = 0,
    var kamar: String? = null,
)
