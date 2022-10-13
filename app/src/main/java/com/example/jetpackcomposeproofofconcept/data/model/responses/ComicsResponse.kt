package com.example.jetpackcomposeproofofconcept.data.model

import com.google.gson.annotations.SerializedName

data class ComicsResponse(
    @SerializedName("code")
    var code: Int? = null,
    @SerializedName("status")
    var status: String? = null,
    @SerializedName("copyright")
    var copyright: String? = null,
    @SerializedName("attributionText")
    var attributionText: String? = null,
    @SerializedName("attributionHTML")
    var attributionHTML: String? = null,
    @SerializedName("etag")
    var etag: String? = null,
    @SerializedName("data")
    var data: CharacterData? = CharacterData()
)

data class CharacterData(
    @SerializedName("offset")
    var offset: Int? = null,
    @SerializedName("limit")
    var limit: Int? = null,
    @SerializedName("total")
    var total: Int? = null,
    @SerializedName("count")
    var count: Int? = null,
    @SerializedName("results")
    var results: ArrayList<Results> = arrayListOf()
)

data class Results(
    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("digitalId")
    var digitalId: Int? = null,
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("issueNumber")
    var issueNumber: Int? = null,
    @SerializedName("variantDescription")
    var variantDescription: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("modified")
    var modified: String? = null,
    @SerializedName("isbn")
    var isbn: String? = null,
    @SerializedName("upc")
    var upc: String? = null,
    @SerializedName("diamondCode")
    var diamondCode: String? = null,
    @SerializedName("ean")
    var ean: String? = null,
    @SerializedName("issn")
    var issn: String? = null,
    @SerializedName("format")
    var format: String? = null,
    @SerializedName("pageCount")
    var pageCount: Int? = null,
    @SerializedName("textObjects")
    var textObjects: ArrayList<TextObjects> = arrayListOf(),
    @SerializedName("resourceURI")
    var resourceURI: String? = null,
    @SerializedName("urls")
    var urls: ArrayList<Urls> = arrayListOf(),
    @SerializedName("series")
    var series: Series? = Series(),
    @SerializedName("variants")
    var variants: ArrayList<String> = arrayListOf(),
    @SerializedName("collections")
    var collections: ArrayList<String> = arrayListOf(),
    @SerializedName("collectedIssues")
    var collectedIssues: ArrayList<String> = arrayListOf(),
    @SerializedName("dates")
    var dates: ArrayList<Dates> = arrayListOf(),
    @SerializedName("prices")
    var prices: ArrayList<Prices> = arrayListOf(),
    @SerializedName("thumbnail")
    var thumbnail: Thumbnail? = Thumbnail(),
    @SerializedName("images")
    var images: ArrayList<Images> = arrayListOf(),
    @SerializedName("creators")
    var creators: Creators? = Creators(),
    @SerializedName("characters")
    var characters: Characters? = Characters(),
    @SerializedName("stories")
    var stories: Stories? = Stories(),
    @SerializedName("events")
    var events: Events? = Events()
)

data class TextObjects(
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("language")
    var language: String? = null,
    @SerializedName("text")
    var text: String? = null
)

data class Dates(
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("date")
    var date: String? = null
)

data class Prices(
    @SerializedName("type")
    var type: String? = null,
    @SerializedName("price")
    var price: Int? = null
)

data class Images(
    @SerializedName("path")
    var path: String? = null,
    @SerializedName("extension")
    var extension: String? = null
)

data class Creators(
    @SerializedName("available")
    var available: Int? = null,
    @SerializedName("collectionURI")
    var collectionURI: String? = null,
    @SerializedName("items")
    var items: ArrayList<String> = arrayListOf(),
    @SerializedName("returned")
    var returned: Int? = null
)

data class Characters(
    @SerializedName("available")
    var available: Int? = null,
    @SerializedName("collectionURI")
    var collectionURI: String? = null,
    @SerializedName("items")
    var items: ArrayList<String> = arrayListOf(),
    @SerializedName("returned")
    var returned: Int? = null

)
