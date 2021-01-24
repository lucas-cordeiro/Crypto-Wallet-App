package br.com.lucas.cordeiro.cryptowalletapp.domain.utils

import br.com.lucas.cordeiro.cryptowalletapp.domain.error.ErrorHandler
import br.com.lucas.cordeiro.cryptowalletapp.domain.helper.Result
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.text.NumberFormat
import java.util.*
import kotlin.coroutines.CoroutineContext


fun <T> Flow<T>.handleResult(errorHandler: ErrorHandler, coroutineExceptionHandler: CoroutineExceptionHandler? = null, coroutineContext: CoroutineContext? = null): Flow<Result<T>> {
    return this
        .map {
            val result: Result<T> = Result.Success(it)
            result
        }
        .catch {
            if(coroutineExceptionHandler!=null && coroutineContext!=null){
                coroutineExceptionHandler.handleException(coroutineContext, it)
            }else{
                emit(Result.Error(errorHandler.getError(it)))
            }
        }
}

fun Double?.currency(): String {
    val ptBR = Locale("pt", "BR")
    val formatter = NumberFormat.getCurrencyInstance(ptBR)
    return formatter.format(this ?: 0.0)
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)