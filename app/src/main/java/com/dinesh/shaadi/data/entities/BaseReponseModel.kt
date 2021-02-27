package com.dinesh.shaadi.data.entities

import com.google.gson.annotations.SerializedName


data class BaseResponseModel(
	@field:SerializedName("results")
	val results: MutableList<MatchesModel> = mutableListOf(),
	@field:SerializedName("info")
	val info: Info? = null
)

data class Info(
	@field:SerializedName("seed")
	val seed: String? = null,
	@field:SerializedName("results")
	val results: Int? = null,
	@field:SerializedName("page")
	val page: Int? = null,
	@field:SerializedName("version")
	val version: String? = null
)