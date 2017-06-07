import com.jereksel.ji3.I3Socket

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        val socket = I3Socket.getSocket()

        val workspaces = socket.workspaces
        val tree = socket.tree

        println("Workspaces: ${socket.workspaces}")
        println("Outputs: ${socket.outputs}")
        println("Tree: $tree")
        println("Version: ${socket.version}")
        println("Callback")

        socket.subscribeToWorkspace {
            println(it)
        }

    }

}