package com.thoughtworks.codename.config

import org.apache.http.client.methods.HttpPost
import org.apache.http.impl.client.HttpClients
import org.apache.http.util.EntityUtils

object TranslatorToken {
    operator fun get(token: String?): String {
        val request = HttpPost("https://api.cognitive.microsoft.com/sts/v1.0/issueToken")
        request.setHeader("Ocp-Apim-Subscription-Key", token)
        return EntityUtils.toString(HttpClients.createDefault().execute(request).entity)
    }
}