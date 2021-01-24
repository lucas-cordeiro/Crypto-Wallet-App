package br.com.lucas.cordeiro.cryptowalletapp.domain.error

sealed class ErrorEntity(var code: Int) {
    sealed class ApiError(code: Int = 400) : ErrorEntity(code) {
        data class Loc(var message: String) : ApiError(400)
        object Network : ApiError(401)
        object NotFound : ApiError(402)
        object Timeout : ApiError(403)
        object Unknown : ApiError(404)
        object MissingParameters : ApiError(405)
        object Unauthorized : ApiError(406)
        object InvalidVerification: ApiError(407)
    }
    sealed class File(code: Int = 200): ErrorEntity(code){
        object FileNotFound: File(201)
        object PermissionsDenied: File(202)
    }
    sealed class App(code: Int = 100) : ErrorEntity(code) {
        object JobCancelled: App(101)
        object JsonParseError: App(102)
        object NullException: App(103)
    }
    sealed class Security(code: Int = 300): ErrorEntity(code){
        object Parse256: ErrorEntity(301)
    }
    sealed class Validate(code: Int = 500): ErrorEntity(code){
        object FederalIdInvalid: Validate(501)
        object FederalIdAlreadyExist: Validate(502)

        object FullNameInvalid: Validate(510)
        object FullNameTooSmall: Validate(511)
        object FullNameWithNumber: Validate(512)
        object FullNameWithSpecialCharacter: Validate(513)

        object EmailInvalid: Validate(520)
        object EmailAlreadyExist: Validate(521)

        object MobilePhoneInvalid: Validate(530)
        object MobilePhoneAlreadyExist: Validate(531)

        object CNPJInvalid: Validate(540)

        object ZipcodeInvalid: Validate(550)
    }
    sealed class UI(code: Int = 800): ErrorEntity(code){
        object BottomSheetEmpty: UI(801)
    }

    sealed class Permissions(code: Int = 900): ErrorEntity(code){
        object Location: Permissions(901)
    }

    object Unknown : ErrorEntity(1)
}