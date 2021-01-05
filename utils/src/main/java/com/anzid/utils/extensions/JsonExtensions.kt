package com.anzid.utils.extensions

import com.anzid.utils.helpers.tryOrNull
import com.google.gson.*

fun JsonObject.getArrayOrNull(key: String): JsonArray? {
    val member = get(key) ?: return null
    return member as? JsonArray
}

fun JsonElement.asIntOrNull() = tryOrNull { asInt }

fun JsonElement.asStringOrNull() = tryOrNull { asString }

fun JsonElement.asJsonObjectOrNull() = tryOrNull { asJsonObject }

fun JsonElement.asJsonArrayOrNull() = tryOrNull { asJsonArray }

fun JsonObject.getOrNull(key: String): JsonElement? = get(key)

fun JsonObject.add(pair: Pair<String, Any>) {
    when (val value = pair.second) {
        is String -> this.addProperty(pair.first, value)
        is Number -> this.addProperty(pair.first, value)
        is Boolean -> this.addProperty(pair.first, value)
        is JsonElement -> this.add(pair.first, value)
        else -> throw UnsupportedOperationException("type of ${value::class.java.name} cannot be placed in json")
    }
}

fun jsonOf(vararg pairs: Pair<String, Any>) = JsonObject().apply { pairs.forEach { add(it) } }

fun jsonArrayOf(list: List<JsonObject>) = JsonArray().apply { list.forEach { add(it) } }

fun Map<String, JsonElement>.toJson(): JsonObject {
    val json = JsonObject()
    this.forEach { entry ->
        json.add(entry.key, entry.value)
    }
    return json
}