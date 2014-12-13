import java.util.List;

public class WireModel extends AbstractModel {

	public WireModel(int windowSize, int gridSize) {
		super(windowSize, gridSize);

	}

	@Override
	public void tick() {
		int[][] t = new int[rows][rows];

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < rows; j++) {

				List<Integer> n = getNeighbors(i, j);

				int next = 0;
				switch (_life[i][j]) {
				case 0: // empty
					break;
				case 1: // conductor
					int n1c = (int) n.stream().filter(x -> x == 2).count();
					if ((int) n.stream().filter(x -> x == 4).count() > 0) {
						next = 1;
					} else if (n1c == 1 || n1c == 2) {
						next = 2;
					} else {
						next = 1;
					}
					break;
				case 2: // head
					if ((int) n.stream().filter(x -> x == 4).count() > 0) {
						next = 1;
					} else {
						next = 3;
					}
					break;
				case 3:
					next = 1;
					break;
				case 4:
					next = 4;
					break;
				}

				t[i][j] = next;
			}
		}
		_life = t.clone();
	}

}
