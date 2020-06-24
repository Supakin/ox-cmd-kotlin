class Board (private var x: Player,private var o: Player) {
    private var board = Array(9) {"-"}
    private var turn = 0
    private var winner: Player? = null

    fun getBoard () = this.board
    fun getX (): Player = x
    fun getO (): Player = o
    fun getWinner () = this.winner

    fun getCurrentPlayer (): Player {
        if (this.turn % 2 == 0) return this.x
        return this.o
    }
    fun checkBlank (index: Int): Boolean {
        if (this.board[index] == "-") return false
        return true
    }

    private fun checkResult (index: Int, diff: Int): Boolean {
        if (this.board[index] == this.board[index + diff] && this.board[index + diff] == this.board[index + (diff * 2)]) return true
        return false
    }

    private fun checkResultSlant (): Boolean {
        if (this.checkBlank(0) && this.checkResult(0, 4)) return true;
        if (this.checkBlank(2) && this.checkResult(2, 2)) return true;
        return false;
    }

    private fun checkResultVertical(): Boolean {
        if (this.checkBlank(0) && this.checkResult(0, 1)) return true
        if (this.checkBlank(3) && this.checkResult(3, 1)) return true
        if (this.checkBlank(6) && this.checkResult(6, 1)) return true
        return false
    }

    private fun checkResultHorizontal(): Boolean {
        if (this.checkBlank(0) && this.checkResult(0, 3)) return true
        if (this.checkBlank(1) && this.checkResult(1, 3)) return true
        if (this.checkBlank(2) && this.checkResult(2, 3)) return true
        return false
    }

    private fun checkWin(): Boolean {
        if (this.checkResultVertical()) return true
        if (this.checkResultHorizontal()) return true
        if (this.checkResultSlant()) return true
        return false
    }

    private fun checkDraw(): Boolean {
        if (turn == 8) return true
        return false
    }

    fun setBoard(index: Int, item: String) {
        this.board[index] = item
    }

    private fun switchTurn() { turn++ }

    fun isFinal(): Boolean {
        if (checkWin()) {
            this.setWinner()
            this.setResultsPlayer()
            return true
        }
        if (checkDraw()) {
            this.setResultsPlayer()
            return true
        }
        this.switchTurn()
        return false
    }

    private fun setWinner() {
        this.winner = this.getCurrentPlayer()
    }

    private fun setResultsPlayer() {
        if (this.winner!!.getName() === this.x.getName()) {
            this.x.setDraw(this.x.getDraw() + 1)
            this.o.setDraw(this.o.getDraw() + 1)
        } else if (this.winner == null) {
            this.x.setWin(this.x.getWin() + 1)
            this.o.setLose(this.o.getLose() + 1)
        } else {
            this.o.setWin(this.o.getWin() + 1)
            this.x.setLose(this.x.getLose() + 1)
        }
    }


}