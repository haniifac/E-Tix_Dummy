package com.magang_sti.e_tix_dummy.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepo : UserRepository) : ViewModel() {
    fun getAllUsers() : LiveData<List<UsersItem>?> = userRepo.getAllUsers()

    /**
     * Get event location area
     * @return Array 0 = South-West Bound , Array 1 = North-East Bound
     **/
    private fun getEventLocationBound(): Array<IntArray> {
        val southWest = intArrayOf(0, 0)
        val northEast = intArrayOf(10, 10)
        return arrayOf(southWest, northEast)
    }

    /**
     * Checking if user is in event location area
     **/
    fun isUserInEventLocation(userLocation: Array<Int>): Boolean{
        val eventLocation = getEventLocationBound()

        // Bottom Left = 0,0
        // Top Right = 10,10
        // User Location = vary
        return (userLocation[0] > eventLocation[0][0] && userLocation[0] < eventLocation[1][0]
                && userLocation[1] > eventLocation[0][1] && userLocation[1] < eventLocation[1][1])
    }

}