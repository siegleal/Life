import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


@SuppressWarnings("serial")
public class LifePanel extends JPanel {
	private int _windowSize;
	private int _gridSize;
	private AbstractModel _lifeModel;
	private int _currentSelection = 1;

	public LifePanel(int windowSize, int gridSize){
		super();
		this._windowSize = windowSize;
		this._gridSize = gridSize;
		this._lifeModel = new LifeModel(windowSize, gridSize);
//		this._lifeModel.generateRandom(.5);
		this.addMouseListener(new MouseListener(){

			@Override
			public void mouseClicked(MouseEvent arg0) {
				//find mouse location
				int i = (int)Math.floor(arg0.getX() / _gridSize);
				int j = (int)Math.floor(arg0.getY() / _gridSize);
				
				_lifeModel.setLifeAt(i, j, arg0.getButton() == MouseEvent.BUTTON1 ? _currentSelection : 0);
				repaint();
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
	}
	
	public void setSelection(int i){ _currentSelection = i;}
	
	public AbstractModel getLifeModel(){return _lifeModel;}
	
	public void tick(){
		_lifeModel.tick();
		this.repaint();
	}
	
	public void Reset(int modelType){
		//TODO make it configurable to which model is desired
		if (modelType == 0){
		this._lifeModel = new LifeModel(_windowSize, _gridSize);
		} else if (modelType == 1){
			this._lifeModel = new WireModel(_windowSize, _gridSize);
		}
		this.repaint();
	}
	
	public void GenerateRandom(double percentCoverage){
		this._lifeModel.generateRandom(percentCoverage);
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		for (int i = 0; i <= _windowSize; i += _gridSize) {
			g.drawLine(0, i, _windowSize, i);
			g.drawLine(i, 0, i, _windowSize);
		}
		
		//draw life
		int size = _lifeModel.getSize();
		for(int i = 0; i < size; i++){
			for (int j = 0; j < size; j++){
				g.setColor(Color.BLACK);
				int value = _lifeModel.getLifeAt(i, j);
				if (value != 0){
					if (value == 1)
						g.setColor(Color.BLACK);
					else if (value == 2)
						g.setColor(Color.BLUE);
					else if (value == 3)
						g.setColor(Color.RED);
					else if (value == 4)
						g.setColor(Color.GREEN);
					
					g.fillRect(i * _gridSize, j * _gridSize, _gridSize, _gridSize);
				}
			}
		}
	}
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(this._windowSize, this._windowSize);
	}
}
