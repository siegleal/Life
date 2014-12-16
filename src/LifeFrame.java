import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class LifeFrame extends JFrame implements ActionListener {
	LifePanel _drawingPanel;
	int _sleepTime = 100;
	int _selected = 1;

	public LifeFrame(int windowSize, int gridSize) {
		super("Life");

		_drawingPanel = new LifePanel(windowSize, gridSize);
		JButton tickButton = new JButton("Tick");
		tickButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				_drawingPanel.tick();
			}
		});

		final Runner r = new Runner(_sleepTime,_drawingPanel);
		Thread t = new Thread(r);
		t.start();
		final JButton startButton = new JButton("Start");
		final JButton stopButton = new JButton("Stop");
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
		
		JButton resetButton = new JButton("Reset to Life");
		resetButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				_drawingPanel.Reset(0);
			}
			
		});
		
		JButton resetWireButton = new JButton("Reset to Wire");
		resetWireButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				_drawingPanel.Reset(1);
			}
			
		});
		
		JButton randomButton = new JButton("Random");
		randomButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				_drawingPanel.GenerateRandom(.5);
			}
			
		});
		
		JPanel resetPanel = new JPanel();
		resetPanel.setLayout(new FlowLayout());
		resetPanel.add(resetButton);
		resetPanel.add(resetWireButton);
		
		JButton setToOneButton = new JButton("Set all to 1");
		setToOneButton.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {
				_drawingPanel.SetAllTo(1);
			}
			
		});
		

		
		//radio Buttons
		JRadioButton btn0 = new JRadioButton("0");
		btn0.setActionCommand("0");
		JRadioButton btn1 = new JRadioButton("1");
		btn1.setActionCommand("1");
		btn1.setSelected(true);
		JRadioButton btn2 = new JRadioButton("2");
		btn2.setActionCommand("2");
		JRadioButton btn3 = new JRadioButton("3");
		btn3.setActionCommand("3");
		JRadioButton btn4 = new JRadioButton("4");
		btn4.setActionCommand("4");
		
		ButtonGroup group = new ButtonGroup();
		group.add(btn0);
		group.add(btn1);
		group.add(btn2);
		group.add(btn3);
		group.add(btn4);
		
		btn0.addActionListener(this);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		btn4.addActionListener(this);
		
		JPanel rPanel = new JPanel();
		rPanel.setLayout(new FlowLayout());
		rPanel.add(btn0);
		rPanel.add(btn1);
		rPanel.add(btn2);
		rPanel.add(btn3);
		rPanel.add(btn4);
		
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1));
		buttonPanel.add(tickButton, 0, 0);
		buttonPanel.add(stopButton, 2, 0);
		buttonPanel.add(startButton, 1, 0);
		buttonPanel.add(resetPanel, 3,0);
		buttonPanel.add(randomButton,4,0);
		buttonPanel.add(rPanel, 5,0);
		buttonPanel.add(setToOneButton, 6, 0);
		

		this.setLayout(new FlowLayout());
		this.add(_drawingPanel);
		this.add(buttonPanel);
		this.pack();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		_selected = Integer.parseInt(e.getActionCommand());
		_drawingPanel.setSelection(_selected);
	}
}
