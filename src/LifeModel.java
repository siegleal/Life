import java.util.List;

public class LifeModel extends AbstractModel{
	
	public LifeModel(int windowSize, int gridSize) {
		super(windowSize, gridSize);
		
	}

	public void tick() {
		int[][] temp = new int[rows][rows];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.rows; j++) {
				// apply rules
				List<Integer> n = getNeighbors(i, j);
				int n1c = (int) n.stream().filter(x -> x == 1).count();
				int n2c = (int) n.stream().filter(x -> x == 2).count();
				int n3c = (int) n.stream().filter(x -> x == 3).count();
				int nc = (int) n.stream().filter(x -> x != 0).count();
				
				int next = 0;

				switch (_life[i][j]) {
				case 0: // EMPTY
					if (n1c == 3) {
						next = 1;
					}
					break;
				case 1: // BLACK
					if (nc == 2 || nc == 3) {
						next = 1;
					}
					break;
				case 2: // BLUE
					if (nc == 2 || nc == 3) {
						next = 2;
					}
					break;
				case 3: // RED
					if (n2c > 2) {
						next = 2;
					} else if (n3c > 4 || n3c < 2){
						next = 0;
					}else {
						next = 3;
					}
					break;
				}
				temp[i][j] = next;
			}
		}
		_life = temp.clone();
	}
}
