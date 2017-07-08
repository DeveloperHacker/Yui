package totoro.yui

import com.sun.xml.internal.fastinfoset.util.StringArray
import java.io.FileInputStream
import java.util.*

class Config(val filepath: String) {
    val prop = Properties()

    lateinit var host: String
    lateinit var chan: List<String>
    var pass: String? = null

    fun load() {
        try {
            FileInputStream(filepath).use {
                prop.load(it)
                host = prop.getProperty("host")
                chan = prop.getProperty("chan").split(",")
                pass = prop.getProperty("pass")
            }
        } catch (e: Exception) {
            Log.warn("Cannot load properties file, seting default values.")
            host = "irc.esper.net"
            chan = listOf("#cc.ru")
        }
    }
}
