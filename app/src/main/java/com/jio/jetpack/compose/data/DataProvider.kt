package com.jio.jetpack.compose.data

import com.jio.jetpack.compose.R
import com.jio.jetpack.compose.dashboard.models.BottomSheetModel
import com.jio.jetpack.compose.dashboard.models.DrawerModel
import com.jio.jetpack.compose.models.DeviceModel
import com.jio.jetpack.compose.dashboard.models.FavoriteModel
import com.jio.jetpack.compose.dashboard.models.StatusModel

class DataProvider {
    companion object {
        fun getInstance(): DataProvider {
            val instance: DataProvider by lazy {
                DataProvider()
            }
            return instance
        }
    }

    fun getStatusDataList(): List<StatusModel> {
        val statusList = ArrayList<StatusModel>()
        statusList.add(StatusModel("Armed", R.drawable.ic_mode_armed_on))
        statusList.add(StatusModel("Disarmed", R.drawable.ic_mode_disarmed_on, true))
        statusList.add(StatusModel("Partially Armed", R.drawable.ic_mode_parciallyarmed_on))
        statusList.add(StatusModel("Cooling", R.drawable.ic_mode_cooling_on, true))
        statusList.add(StatusModel("Heating", R.drawable.ic_mode_heating_on))
        statusList.add(StatusModel("At Home", R.drawable.ic_mode_athome_on))
        statusList.add(StatusModel("Away", R.drawable.ic_mode_away_on))
        statusList.add(StatusModel("Away", R.drawable.ic_mode_away_on))
        statusList.add(StatusModel("Sunrise", R.drawable.ic_mode_sunrise_on))
        statusList.add(StatusModel("Sunset", R.drawable.ic_mode_sunset_on))
        statusList.add(StatusModel("Night", R.drawable.ic_mode_night_on))
        statusList.add(StatusModel("Day", R.drawable.ic_mode_day_on))
        statusList.add(StatusModel("Silence", R.drawable.ic_mode_silence_on))

        return statusList
    }

    fun getFavoriteList(): List<FavoriteModel> {
        val favoriteList = ArrayList<FavoriteModel>()
        favoriteList.add(
            FavoriteModel(
                "WISE Bridge - CB20",
                "Hall",
                R.drawable.btmesh_dashboard_bridge
            )
        )
        favoriteList.add(FavoriteModel("Bulb 123", "Kitchen", R.drawable.ic_resource_white_device))
        favoriteList.add(
            FavoriteModel(
                "Bulb 124",
                "Bedroom 1",
                R.drawable.ic_resource_white_device
            )
        )
        favoriteList.add(
            FavoriteModel(
                "Bulb 125",
                "Bedroom 2",
                R.drawable.ic_resource_white_device,
                false
            )
        )

        return favoriteList
    }

    fun getDeviceList(): List<DeviceModel> {
        val deviceList = ArrayList<DeviceModel>()
        deviceList.add(DeviceModel("BLE MESH Smart Bulb 1127", R.drawable.ic_resource_white_device))
        deviceList.add(
            DeviceModel(
                "BLE MESH Smart Bulb 1128",
                R.drawable.ic_resource_white_device,
                5
            )
        )
        deviceList.add(DeviceModel("BLE MESH Smart Bulb 1132", R.drawable.ic_resource_white_device))
        deviceList.add(
            DeviceModel(
                "BLE MESH Smart Bulb 1258",
                R.drawable.ic_resource_white_device,
                4
            )
        )

        return deviceList
    }

    fun getDrawerList(): List<DrawerModel> {
        val drawerList = ArrayList<DrawerModel>()
        drawerList.add(DrawerModel("Accounts", R.drawable.ic_bm_myaccount_off))
        drawerList.add(DrawerModel("Voice Assistants", R.drawable.nav_mic_white))
        drawerList.add(DrawerModel("Notifications", R.drawable.ic_bm_notifications_off))
        drawerList.add(DrawerModel("Group Control", R.drawable.nav_group_control))
        drawerList.add(DrawerModel("Settings", R.drawable.ic_bm_settings_on))
        drawerList.add(DrawerModel("FAQ", R.drawable.img_faq))
        drawerList.add(DrawerModel("Recommendations", R.drawable.nav_recommendations))
        drawerList.add(DrawerModel("About", R.drawable.ic_bm_about_off))
        drawerList.add(DrawerModel("Terms & Conditions", R.drawable.terms_and_conditions))
        drawerList.add(DrawerModel("Privacy Policy", R.drawable.jsl_privacy_policy))
        drawerList.add(DrawerModel("Logout", R.drawable.ic_bm_logout_off))

        return drawerList
    }

    fun getAddDeviceBottomSheetList(): List<BottomSheetModel> {
        val bottomSheetList = ArrayList<BottomSheetModel>()
        bottomSheetList.add(BottomSheetModel("Add Home", R.drawable.ic_home_black))
        bottomSheetList.add(BottomSheetModel("Add Device", R.drawable.ic_bulb_black))
        bottomSheetList.add(BottomSheetModel("Add Camera", R.drawable.ic_camera_black))
        bottomSheetList.add(BottomSheetModel("Add Room", R.drawable.ic_room_black))
        bottomSheetList.add(BottomSheetModel("Group Control", R.drawable.ic_group_control_black))
        bottomSheetList.add(BottomSheetModel("Add Scene", R.drawable.ic_scene_black))
        bottomSheetList.add(BottomSheetModel("Add Family Member", R.drawable.ic_users_black))
        bottomSheetList.add(BottomSheetModel("Add Voice Assistants", R.drawable.ic_mic_black))

        return bottomSheetList
    }
}