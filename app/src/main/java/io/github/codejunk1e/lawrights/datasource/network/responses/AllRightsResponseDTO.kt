package io.github.codejunk1e.lawrights.datasource.network.responses

data class AllRightsResponseDTO(
    val `data`: Data,
    val message: String,
    val status: Int
)

data class Data(
    val current_page: Int,
    val `data`: List<Rights>,
    val first_page_url: String,
    val from: Int,
    val last_page: Int,
    val last_page_url: String,
    val next_page_url: Any,
    val path: String,
    val per_page: String,
    val prev_page_url: Any,
    val to: Int,
    val total: Int
)

data class Rights(
    val id: Int,
    val is_deleted: Int,
    val title: String,
    val updated_at: String
)