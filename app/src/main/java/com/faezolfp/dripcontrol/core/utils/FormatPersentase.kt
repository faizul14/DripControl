package com.faezolfp.dripcontrol.core.utils

object FormatPersentase {
    fun persentaseRealtime(dataMax: Int, dataRealtime: Int): Int{
        var result = 0
        if (dataMax != 0) {
            result = (dataRealtime * 100) / dataMax
        }
        return result
    }
}