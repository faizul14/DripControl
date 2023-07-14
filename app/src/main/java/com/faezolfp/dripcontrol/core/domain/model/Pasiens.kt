package com.faezolfp.dripcontrol.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Pasiens(
    var id: Int = 0,
    var nama: String? = null,
    var umur: String? = null,
    var brtbadan: String? = null,
    var banyakcairaninfus: String? = null,
    var lamapemberianinfus: String? = null,
    var tetsanpermenit: String? = null,
    var kamar: Int? = null,
): Parcelable
