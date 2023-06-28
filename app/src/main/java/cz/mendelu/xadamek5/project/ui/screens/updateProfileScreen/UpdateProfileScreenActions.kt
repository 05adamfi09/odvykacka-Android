package cz.mendelu.xadamek5.project.ui.screens.updateProfileScreen

interface UpdateProfileScreenActions {


    fun updateProfile(imageUri: String)


    fun onNickChange(nickname: String)
    fun onAddictionChange(addiction: String)
    fun onProfilePicChange(profilePic: String)

}