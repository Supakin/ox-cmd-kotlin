class Player (private val name: String) {
    private var win: Int = 0
    private var draw: Int = 0
    private var lose: Int = 0

    fun getName (): String = this.name
    fun getWin (): Int = this.win
    fun setWin (win: Int) { this.win = win }
    fun getDraw (): Int = this.draw
    fun setDraw (draw: Int) { this.draw = draw }
    fun getLose (): Int = this.lose
    fun setLose (lose: Int) { this.lose = lose }
}