package com.dinesh.shaadi.data.entities

import com.google.gson.annotations.SerializedName

data class MatchesModel(

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("name")
	val name: Name? = null,

	@field:SerializedName("location")
	val location: Location? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("dob")
	val dob: Dob? = null,

	@field:SerializedName("phone")
	val phone: String? = null,

	@field:SerializedName("picture")
	val picture: Picture? = null,

)


data class Dob(

	@field:SerializedName("date")
	val date: String? = null,

	@field:SerializedName("age")
	val age: Int? = null
)

data class Name(

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("first")
	val first: String? = null,

	@field:SerializedName("last")
	val last: String? = null
)

data class Picture(

	@field:SerializedName("large")
	val large: String? = null,

	@field:SerializedName("medium")
	val medium: String? = null,

	@field:SerializedName("thumbnail")
	val thumbnail: String? = null
)

data class Location(
	@field:SerializedName("city")
	val city: String? = null,

	@field:SerializedName("state")
	val state: String? = null,
)
