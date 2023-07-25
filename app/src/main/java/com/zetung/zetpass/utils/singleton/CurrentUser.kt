package com.zetung.zetpass.utils.singleton

import com.zetung.zetpass.repository.model.UserModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CurrentUser @Inject constructor (){
    var userModel = UserModel(null,"-","-", false)
}