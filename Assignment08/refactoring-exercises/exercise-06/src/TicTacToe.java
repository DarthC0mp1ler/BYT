/**
 * Bad smells:
 *  - Comments - Extract Method
 *  - Data class - Encapsulate Fields
 */
public class TicTacToe {
	private StringBuffer board;

	public TicTacToe(String s) {
		setBoard(new StringBuffer(s));
	}

	public TicTacToe(String s, int position, char player) {
		setBoard(new StringBuffer(s));
		getBoard().setCharAt(position, player);
	}

	public int suggestMove(char player) {
		for (int i = 0; i < 9; i++) {
			if (getCharAt(i) == '-') {
				TicTacToe game = makeMove(i, player);
				if (game.winner() == player)
					return i;
			}
		}

		for (int i = 0; i < 9; i++) {
			if (getCharAt(i) == '-')
				return i;
		}

		return -1;
	}

	public TicTacToe makeMove(int i, char player) {
		return new TicTacToe(getBoard().toString(), i, player);
	}

	public char winner() {
		Character i1 = checkHorizontalWinner();
		if (i1 != null) return i1;

		Character i = checkVerticalWinner();
		if (i != null) return i;

		Character x = checkDiagonalWinner();
		if (x != null) return x;

		return '-';
	}

	private Character checkDiagonalWinner() {
		if (getCharAt(0) != '-' && getCharAt(4) == getCharAt(0)
				&& getCharAt(8) == getCharAt(0))
			return getCharAt(0);
		if (getCharAt(2) != '-' && getCharAt(4) == getCharAt(2)
				&& getCharAt(6) == getCharAt(2))
			return getCharAt(2);
		return null;
	}

	private char getCharAt(int i) {
		return getBoard().charAt(i);
	}

	private Character checkVerticalWinner() {
		for (int i = 0; i < 3; ++i) {
			if (getCharAt(i) != '-'
					&& getCharAt(i + 3) == getCharAt(i)
					&& getCharAt(i + 6) == getCharAt(i))
				return getCharAt(i);
		}
		return null;
	}

	private Character checkHorizontalWinner() {
		for (int i = 0; i < 9; i += 3) {
			if (getCharAt(i) != '-'
					&& getCharAt(i + 1) == getCharAt(i)
					&& getCharAt(i + 2) == getCharAt(i))
				return getCharAt(i);
		}
		return null;
	}

	public StringBuffer getBoard() {
		return board;
	}

	public void setBoard(StringBuffer board) {
		this.board = board;
	}
}
