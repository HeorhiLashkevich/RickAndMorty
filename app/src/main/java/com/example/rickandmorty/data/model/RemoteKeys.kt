/*
 * *
 *  * Created by Rafsan Ahmad on 10/13/21, 10:13 PM
 *  * Copyright (c) 2021 . All rights reserved.
 *
 */

package com.example.rickandmorty.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class RemoteKeys(
    @PrimaryKey(autoGenerate = true)
    val repoId: Int,
    val prevKey: Int?,
    val nextKey: Int?
)