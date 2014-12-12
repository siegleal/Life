import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class LifeFrame extends JFrame {
	LifePanel _drawingPanel;
	int _sleepTime = 500;

	public LifeFrame(int windowSize, int gridSize) {
		super("Life");

		_drawingPanel = new LifePanel(windowSize, gridSize);
		JButton tickButton = new JButton("Tick");
		tickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_drawingPanel.tick();
			}
		});

		Runner r = new Runner(100,_drawingPanel);
		Thread t = new Thread(r);
		t.start();
		JButton startButton = new JButton("Start");
		JButton stopButton = new JButton("Stop");
		stopButton.setEnabled(false);
		
		startButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				r.resume();
				stopButton.setEnabled(true);
				startButton.setEnabled(false);
			}
		});
		
		stopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				r.suspend();
				startButton.setEnabled(true);
				stopButton.setEnabled(false);
			}
		});

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 1));
		buttonPanel.add(tickButton, 0, 0);
		buttonPanel.add(startButton, 1, 0);
		buttonPanel.add(stopButton, 2, 0);

		this.setLayout(new FlowLayout());
		this.add(_drawingPanel);
		this.add(buttonPanel);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
