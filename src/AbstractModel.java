import java.util.ArrayList;
import java.util.List;

public abstract class AbstractModel {

	protected int[][] _life;
	protected int rows;

	public AbstractModel(int windowSize, int gridSize) {
		this.rows = (int) Math.floor(windowSize / gridSize);

		this._life = new int[rows][rows];
	}

	public void generateRandom(double percentCoverage) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				_life[i][j] = Math.random() < percentCoverage ? 1 : 0;
			}
		}
	}

	public int getLifeAt(int i, int j) {
		return _life[i][j];
	}

	public void setLifeAt(int i, int j, int life) {
		_life[i][j] = life;
	}

	public int getSize() {
		return rows;
	}

	public void tick() {

	}

	public int getNeighborsCount(int i, int j) {
		return getNeighbors(i, j).size();
	}

	public List<Integer> getNeighbors(int i, int j) {
		ArrayList<Integer> list = new ArrayList<Integer>();

		for (int x = -1; x < 2; x++) {
			for (int y = -1; y < 2; y++) {
				if (x == 0 && y == 0) {
					continue;
				}
				try {
					list.add(_life[i + x][j + y]);
				} catch (IndexOutOfBoundsException e) {
					// invalid index
				}
			}
		}
		return list;
	}
}
