package cz.mendelu.xadamek5.project.ui.screens.updateProfileScreen

import cz.mendelu.xadamek5.project.model.Log
import cz.mendelu.xadamek5.project.model.Profile

class UpdateProfileScreenData : java.io.Serializable{

    var profile: Profile = Profile("", "unknown", "unknown")
    var emptyNick : Int? = null
    var emptyAddition: Int? = null
    var emptyProfilePic: Int? = null

}