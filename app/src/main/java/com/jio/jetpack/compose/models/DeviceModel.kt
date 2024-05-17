package com.jio.jetpack.compose.models

/**
 * Status 0 = IDLE, 1 = READY, 2 = RUN, 3 = STAND BY, 4 = ERROR, 5 = OFFLINE
 */
data class DeviceModel(
    val deviceName: String,
    val icon: Int,
    val status: Int = 2,
    val roomName: String = "Hall"
) {
    val id = 0
    val roomId = 0
    val deviceFirmwareVersion = ""
    val deviceManufacturer = ""
    val onOffStatus = true
}
