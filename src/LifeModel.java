import java.util.ArrayList;
import java.util.List;

public class LifeModel {
	private int[][] _life;
	private int rows;

	public LifeModel(int windowSize, int gridSize) {
		this.rows = (int) Math.floor(windowSize / gridSize);

		this._life = new int[rows][rows];
	}
	
	public void generateRandom(double percentCoverage){
		for(int i = 0; i < rows; i++){
			for (int j = 0; j < rows; j++){
				_life[i][j] = Math.random() < percentCoverage ? 1: 0;
			}
		}
	}

	public int getLifeAt(int i, int j) {
		return _life[i][j];
	}
	
	public void setLifeAt(int i, int j, int life){
		_life[i][j] = life;
	}

	public int getSize() {
		return rows;
	}

	public void tick() {
		int[][] temp = new int[rows][rows];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.rows; j++) {
				// apply rules
				List<Integer> n = getNeighbors(i, j);
				int nc = (int) n.stream().filter(x -> x != 0).count();
				if (_life[i][j] == 1) {
					if (nc == 2 || nc == 3) {
						temp[i][j] = (n.stream().filter(x-> x == 2).count() > 0) ? 2 : 1;
					}
				} else if (_life[i][j] == 0){
					if (nc == 3) {
						temp[i][j] = (n.stream().filter(x-> x == 2).count() > 0) ? 2 : 1;
					}
				} else if (_life[i][j] == 2){
					if (nc == 2 || nc == 3) {
						temp[i][j] = 2;
					}
				}
			}
		}
		_life = temp.clone();
	}
	
	public int getNeighborsCount(int i, int j){
		return getNeighbors(i,j).size();
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

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {
				sb.append(_life[i][j] != 0 ? "1" : "0" );
			}
			sb.append("\n");
		}
		return sb.toString();
	}
}
