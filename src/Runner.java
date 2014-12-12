public class Runner implements Runnable {
	private volatile boolean done;
	private int _sleepTime;
	private LifePanel _drawingPanel;

	public Runner(int sleepInt, LifePanel panel) {
		super();
		this._sleepTime = sleepInt;
		this._drawingPanel = panel;
		done = true;
	}

	public void suspend() {
		done = true;
	}

	public void resume() {
		done = false;
	}

	@Override
	public void run() {
		while (true) {
			if (!done) {
				try {
					Thread.sleep(_sleepTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_drawingPanel.tick();
			}
		}
	}

}
