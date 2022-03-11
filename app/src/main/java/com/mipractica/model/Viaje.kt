package com.mipractica.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "viaje")
data class Viaje(
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name="nombre")
    val nombre: String,
    @ColumnInfo(name="lugar")
    val lugar: String,
    @ColumnInfo(name="poblacion")
    val poblacion: Double,
    @ColumnInfo(name="precio")
    val precio: Double
) : Parcelable

