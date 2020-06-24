import java.util.*

class Game {
    private var board = arrayListOf<Board>()
    private val x: Player = Player("x")
    private val o: Player = Player("o")
    var kb = Scanner(System.`in`)

    private fun printWelcome () = println("Welcome to OX game")

    private fun printBoard () {
        val temp = board.last().getBoard()
        for ((i, value) in temp.withIndex()) {
            print("$value ")
            if (i == 2 || i == 5 || i == 8) println()
        }
    }

    private fun printTurn () = println("Turn ${this.board.last().getCurrentPlayer().getName()}")

    private fun inputPosition() {
        while (true) {
            println("Please input Row, Col : ")
            try {
                val row = kb.nextInt()
                val col = kb.nextInt()
                val index: Int = this.getRequirePosition(row, col)
                if (row < 1 || row > 3 || col < 1 || col > 3) {
                    println("""
                                Min value of Row and Col is 1 
                                Max value of Row and Col is 3
                            """.trimIndent())
                    continue
                }
                if (board[board.size - 1].checkBlank(index)) {
                    println("Please change position!!")
                    continue
                }
                board.last().setBoard(index, board.last().getCurrentPlayer().getName())
                break
            } catch (e: Exception) {
                println("Position Incorrect")
                if (kb.hasNext()) {
                    kb.nextLine()
                }
            }
        }
    }

    private fun spacing () = println()

    private fun printWinner() {
        if (this.board.last().getWinner() == null) println("!!!-----Draw-----!!!")
        else println("!!!-----${board.last().getWinner()!!.getName()} is the Winner-----!!!")
        this.spacing()
    }

    private fun getRequirePosition(row: Int, col: Int): Int = (row - 1) * 3 + col - 1

    private fun play() {
        printWelcome()
        while (true) {
            this.printBoard()
            this.printTurn()
            this.inputPosition()
            if (this.board.last().isFinal()) break
        }
        this.printBoard()
       this. printWinner()
    }

    fun start() {
        while (true) {
            this.newGame()
            play()
            if (this.watchWinRate()) {
                this.printWinRate()
            }
            if (!this.tryAgainQuestion()) {
                this.printWinRate()
                break
            }
        }
    }

    private fun tryAgainQuestion(): Boolean {
        var input: Char
        while (true) {
            println("Try Again? (Y/N)")
            input = kb.next()[0]
            if (input == 'Y' || input == 'y') return true
            if (input == 'N' || input == 'n') return false
        }
    }

    private fun newGame()  = board.add(Board(x, o))


    private fun printWinRate() {
        println("Win Rate : ")
        println("Player ${x.getName()} : win [${x.getWin()} ] lose [ ${x.getLose()} ] draw [ ${x.getDraw()} ]")
        println("Player ${o.getName()} : win [${o.getWin()} ] lose [ ${o.getLose()} ] draw [ ${o.getDraw()} ]")
    }

    private fun watchWinRate(): Boolean {
        var input: Char
        while (true) {
            println("Do you want to watch Win Rate? (Y/N)")
            input = kb.next()[0]
            if (input == 'Y' || input == 'y') return true
            if (input == 'N' || input == 'n') return false
        }
    }


}