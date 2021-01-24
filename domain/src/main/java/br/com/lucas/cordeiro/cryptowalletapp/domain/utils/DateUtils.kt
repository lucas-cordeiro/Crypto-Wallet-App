package br.com.lucas.cordeiro.cryptowalletapp.domain.utils

import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.getDateFormatted
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.getDayFormatted
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.getHourFormatted
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.isOfWeek
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.isToday
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.isTomorrow
import br.com.lucas.cordeiro.cryptowalletapp.domain.utils.DateUtils.Companion.isYesterday
import java.text.SimpleDateFormat
import java.time.Year
import java.util.*

class DateUtils {
    companion object {
        fun Long?.getDateFormatted(): String {
            return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this.fromUnixDate())
        }

        fun Long?.getHourFormatted(): String{
            return SimpleDateFormat("HH:mm", Locale.getDefault()).format(this.fromUnixDate())
        }

        fun Long?.getDayOfWeek(): String{
            return SimpleDateFormat("EEEE", Locale.getDefault()).format(this.fromUnixDate())
        }

        fun Long?.getDayOfWeekShort(): String{
            return SimpleDateFormat("EE", Locale.getDefault()).format(this.fromUnixDate())
        }

        fun Long?.getDay(): String {
            return SimpleDateFormat("dd", Locale.getDefault()).format(this.fromUnixDate())
        }

        fun Long?.getMonth(): String {
            return SimpleDateFormat("MMMM", Locale.getDefault()).format(this.fromUnixDate())
        }

        fun Long?.getYear(): String {
            return SimpleDateFormat("yyyy", Locale.getDefault()).format(this.fromUnixDate())
        }

        fun Long?.getDayFormatted(): String {
            val date = this.fromUnixDate()
            return when(true){
                date.isYesterday() -> "Ontem, ${getHourFormatted()}"
                date.isToday() -> "Hoje, ${getHourFormatted()}"
                date.isTomorrow() -> "Amanhã, ${getHourFormatted()}"
                date.isOfWeek() -> "${getDayOfWeek()}, ${getHourFormatted()}"
                date.isOfYear() -> "${getDay()} de ${getMonth()}"
                else -> "${getDay()} de ${getMonth()} de ${getYear()}"
            }
        }

        fun Date?.isToday(): Boolean {
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.resetHour()

            val calDiff = Calendar.getInstance()
            calDiff.timeInMillis = this?.time?:0L
            calDiff.resetHour()

            return cal.get(Calendar.DAY_OF_MONTH) == calDiff.get(Calendar.DAY_OF_MONTH) &&
                    cal.get(Calendar.MONTH) == calDiff.get(Calendar.MONTH) &&
                    cal.get(Calendar.YEAR) == calDiff.get(Calendar.YEAR)
        }

        fun Date?.isYesterday(): Boolean {
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.add(Calendar.DAY_OF_MONTH, -1)
            cal.resetHour()

            val calDiff = Calendar.getInstance()
            calDiff.timeInMillis = this?.time?:0L
            calDiff.resetHour()

            return cal.get(Calendar.DAY_OF_MONTH) == calDiff.get(Calendar.DAY_OF_MONTH) &&
                    cal.get(Calendar.MONTH) == calDiff.get(Calendar.MONTH) &&
                    cal.get(Calendar.YEAR) == calDiff.get(Calendar.YEAR)
        }

        fun Date?.isTomorrow(): Boolean {
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.add(Calendar.DAY_OF_MONTH, +1)
            cal.resetHour()

            val calDiff = Calendar.getInstance()
            calDiff.timeInMillis = this?.time?:0L
            calDiff.resetHour()

            return cal.get(Calendar.DAY_OF_MONTH) == calDiff.get(Calendar.DAY_OF_MONTH) &&
                    cal.get(Calendar.MONTH) == calDiff.get(Calendar.MONTH) &&
                    cal.get(Calendar.YEAR) == calDiff.get(Calendar.YEAR)
        }

        fun Date?.isOfWeek(): Boolean {
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.resetHour()

            val calDiff = Calendar.getInstance()
            calDiff.timeInMillis = this?.time?:0L
            calDiff.resetHour()
            return cal.get(Calendar.WEEK_OF_MONTH) == calDiff.get(Calendar.WEEK_OF_MONTH)
        }

        fun Date?.isOfMonth(): Boolean {
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.resetHour()

            val calDiff = Calendar.getInstance()
            calDiff.timeInMillis = this?.time?:0L
            calDiff.resetHour()
            return cal.get(Calendar.MONTH) == calDiff.get(Calendar.MONTH)
        }

        fun Date?.isOfYear(): Boolean {
            val cal = Calendar.getInstance()
            cal.timeInMillis = System.currentTimeMillis()
            cal.resetHour()

            val calDiff = Calendar.getInstance()
            calDiff.timeInMillis = this?.time?:0L
            calDiff.resetHour()
            return cal.get(Calendar.YEAR) == calDiff.get(Calendar.YEAR)
        }

        fun Long?.fromUnixDate(): Date {
            return if(this!=null)Date(this*1000L) else Date()
        }

        fun Calendar.resetHour() {
            this.set(Calendar.HOUR_OF_DAY, 0)
            this.set(Calendar.MINUTE, 0)
            this.set(Calendar.SECOND, 0)
        }
    }
}