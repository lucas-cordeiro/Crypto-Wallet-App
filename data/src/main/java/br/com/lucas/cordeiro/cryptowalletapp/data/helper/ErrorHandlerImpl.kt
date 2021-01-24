package br.com.lucas.cordeiro.cryptowalletapp.data.helper

import br.com.lucas.cordeiro.cryptowalletapp.domain.error.ErrorEntity
import br.com.lucas.cordeiro.cryptowalletapp.domain.error.ErrorHandler
import com.google.gson.Gson
import io.ktor.client.features.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.*
import kotlinx.coroutines.flow.flow
import okhttp3.RequestBody.Companion.toRequestBody
import timber.log.Timber
import java.net.UnknownHostException
import java.nio.ByteBuffer
import java.nio.channels.UnresolvedAddressException
import java.util.*
import javax.net.ssl.SSLException

class ErrorHandlerImpl() : ErrorHandler {
    override suspend fun getError(throwable: Throwable): ErrorEntity {
        val errorEntity = when(throwable){
            is UnresolvedAddressException -> ErrorEntity.ApiError.Network
            is SSLException -> ErrorEntity.ApiError.Network
            is HttpRequestTimeoutException -> ErrorEntity.ApiError.Timeout
            is ClientRequestException -> {
                val response = throwable.response
                when(response.status){
                    HttpStatusCode.Unauthorized -> ErrorEntity.ApiError.Unauthorized
                    HttpStatusCode.Forbidden -> ErrorEntity.ApiError.Unauthorized
                    HttpStatusCode.NotFound -> ErrorEntity.ApiError.NotFound
                    HttpStatusCode.RequestTimeout, HttpStatusCode.GatewayTimeout -> ErrorEntity.ApiError.Timeout
                    HttpStatusCode.GatewayTimeout -> ErrorEntity.ApiError.Timeout
                    else -> ErrorEntity.ApiError.Unknown
                }
            }
            is UnknownHostException -> ErrorEntity.ApiError.Network
            is CancellationException -> ErrorEntity.App.JobCancelled
            else -> ErrorEntity.Unknown
        }
        Timber.tag("ErrorHandler").d(throwable,"getError: $errorEntity")
        return errorEntity
    }
}