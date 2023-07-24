package com.example.kotlinstandardapplication.page.permission

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

object PermissionManager {
    fun isPermissionAccessed(context: Context, permission: String): Boolean{
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}