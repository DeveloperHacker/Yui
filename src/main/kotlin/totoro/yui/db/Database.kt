package totoro.yui.db

import totoro.yui.Log
import java.sql.Connection
import java.sql.DriverManager


class Database(private val filename: String) {
    private var connection: Connection? = null

    var quotes: QuotesTable? = null

    fun connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:$filename")
            createTables(connection)
            Log.info("Succesfully connected to the database: $filename")
        } catch (e: Exception) {
            Log.error("Cannot establish connection to the database!")
            Log.debug(e)
        }
    }

    private fun createTables(connection: Connection?) {
        if (connection != null) {
            quotes = QuotesTable(connection)
            quotes?.create()
        } else {
            Log.error("WTF? Database connection appears to be broken...")
        }
    }

    fun close() {
        connection?.close()
    }
}
