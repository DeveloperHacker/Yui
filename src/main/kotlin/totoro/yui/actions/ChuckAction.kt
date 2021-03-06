package totoro.yui.actions

import totoro.yui.client.IRCClient
import totoro.yui.util.Chuck

class ChuckAction : SensitivityAction("chuck", "norris", "chucknorris") {
    override fun handle(client: IRCClient, command: Command): Boolean {
        Chuck.quote({ client.send(command.chan, it) }) {
            client.send(command.chan, "chuck is sleeping")
        }
        return true
    }
}
