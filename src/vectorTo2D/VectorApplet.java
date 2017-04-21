package vectorTo2D;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;

public class VectorApplet extends JFrame implements ActionListener, ChangeListener, MouseWheelListener {
	Coordinatesystem canvas;
	JButton pointHinzufuegen, pointLoeschen, planeHinzufuegen, planeLoeschen, showGUI, lineHinzufuegen, lineLoeschen;
	JTextField tfPointX1, tfPointX2, tfPointX3, tfPlaneX1, tfPlaneX2, tfPlaneX3, tfPlaneD, tfLineV1, tfLineV2, tfLineV3, tfLineX1, tfLineX2, tfLineX3;
	JSlider angleX3, angleX2, angleX1, scale;
	JPanel uiPoint, uiControl, uiLine;
	JLabel labelSliderX1, labelSliderX2, labelSliderX3, labelPoints, labelPlanes, labelScale, labelLine, labelLineEquation;
	List listPoints, listPlanes, listLines;

	boolean boolShowGUI = true;

	LinkedList<Vector3Di> vectors = new LinkedList<Vector3Di>();
	LinkedList<Plane> planes = new LinkedList<Plane>();
	LinkedList<Line> lines = new LinkedList<Line>();

	public VectorApplet() {
		this.setSize(1200, 800);
		this.setMinimumSize(new Dimension(800, 800));
		this.setLayout(null);
		this.setTitle("Analytische Geometrie");
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				dispose();
			}
		});
		this.addMouseWheelListener(this);

		canvas = new Coordinatesystem();
		canvas.setBackground(Color.BLACK);
		this.add(canvas);

		uiPoint = new JPanel();
		uiPoint.setLayout(null);
		this.add(uiPoint);

		uiControl = new JPanel();
		uiControl.setLayout(null);
		this.add(uiControl);
		
		uiLine = new JPanel();
		uiLine.setLayout(null);
		this.add(uiLine);

		showGUI = new JButton("Vollbild an");
		showGUI.addActionListener(this);
		canvas.add(showGUI);

		pointHinzufuegen = new JButton("Hinzufügen");
		pointHinzufuegen.setBounds(0, 90, 100, 25);
		pointHinzufuegen.addActionListener(this);
		uiPoint.add(pointHinzufuegen);

		pointLoeschen = new JButton("Löschen");
		pointLoeschen.setBounds(0, 235, 100, 25);
		pointLoeschen.addActionListener(this);
		uiPoint.add(pointLoeschen);

		labelPoints = new JLabel("Punkte");
		labelPoints.setBounds(25, 15, 50, 25);
		uiPoint.add(labelPoints);

		tfPointX1 = new JTextField("x1");
		tfPointX1.setBounds(0, 50, 25, 25);
		uiPoint.add(tfPointX1);

		tfPointX2 = new JTextField("x2");
		tfPointX2.setBounds(38, 50, 25, 25);
		uiPoint.add(tfPointX2);

		tfPointX3 = new JTextField("x3");
		tfPointX3.setBounds(75, 50, 25, 25);
		uiPoint.add(tfPointX3);

		listPoints = new List();
		listPoints.setBounds(0, 125, 100, 100);
		uiPoint.add(listPoints);
		
		labelPlanes = new JLabel("<html>Ebenen (in<br>Koordinatenform)</html>");
		labelPlanes.setBounds(0, 300, 100, 50);
		uiPoint.add(labelPlanes);

		tfPlaneX1 = new JTextField("n1");
		tfPlaneX1.setBounds(0, 350, 20, 25);
		uiPoint.add(tfPlaneX1);
		
		tfPlaneX2 = new JTextField("n2");
		tfPlaneX2.setBounds(27, 350, 20, 25);
		uiPoint.add(tfPlaneX2);

		tfPlaneX3 = new JTextField("n3");
		tfPlaneX3.setBounds(55, 350, 20, 25);
		uiPoint.add(tfPlaneX3);
		
		tfPlaneD = new JTextField("d");
		tfPlaneD.setBounds(82, 350, 20, 25);
		uiPoint.add(tfPlaneD);

		planeHinzufuegen = new JButton("Hinzufügen");
		planeHinzufuegen.setBounds(0, 390, 100, 25);
		planeHinzufuegen.addActionListener(this);
		uiPoint.add(planeHinzufuegen);

		listPlanes = new List();
		listPlanes.setBounds(0, 425, 100, 100);
		uiPoint.add(listPlanes);

		planeLoeschen = new JButton("Löschen");
		planeLoeschen.setBounds(0, 535, 100, 25);
		planeLoeschen.addActionListener(this);
		uiPoint.add(planeLoeschen);

		angleX3 = new JSlider(-90, 90, -45);
		angleX3.setBounds(500, 50, 100, 75);
		angleX3.addChangeListener(this);
		angleX3.setMajorTickSpacing(45);
		angleX3.setPaintTicks(true);
		angleX3.setPaintLabels(true);
		uiControl.add(angleX3);

		angleX2 = new JSlider(-90, 90, -45);
		angleX2.setBounds(375, 50, 100, 75);
		angleX2.addChangeListener(this);
		angleX2.setMajorTickSpacing(45);
		angleX2.setPaintTicks(true);
		angleX2.setPaintLabels(true);
		uiControl.add(angleX2);

		angleX1 = new JSlider(-90, 90, 0);
		angleX1.setBounds(250, 50, 100, 75);
		angleX1.addChangeListener(this);
		angleX1.setMajorTickSpacing(45);
		angleX1.setPaintTicks(true);
		angleX1.setPaintLabels(true);
		uiControl.add(angleX1);

		labelSliderX1 = new JLabel("<html>Rotation um<br>x1-Achse</html>");
		labelSliderX1.setBounds(265, 0, 80, 50);
		uiControl.add(labelSliderX1);

		labelSliderX2 = new JLabel("<html>Rotation um<br>x2-Achse</html>");
		labelSliderX2.setBounds(390, 0, 80, 50);
		uiControl.add(labelSliderX2);

		labelSliderX3 = new JLabel("<html>Rotation um<br>x3-Achse</html>");
		labelSliderX3.setBounds(515, 0, 80, 50);
		uiControl.add(labelSliderX3);

		labelScale = new JLabel("Skalierung des Bildes");
		labelScale.setBounds(40, 0, 150, 50);
		uiControl.add(labelScale);

		Hashtable<Integer, JLabel> logarithmic = new Hashtable<Integer, JLabel>();
		logarithmic.put(new Integer(0), new JLabel("0.1x"));
		logarithmic.put(new Integer(100), new JLabel("1x"));
		logarithmic.put(new Integer(200), new JLabel("10x"));

		scale = new JSlider(0, 200, 100);
		scale.setBounds(0, 50, 200, 75);
		scale.setMajorTickSpacing(25);
		scale.setPaintTicks(true);
		scale.setPaintLabels(true);
		scale.setLabelTable(logarithmic);
		scale.addChangeListener(this);
		uiControl.add(scale);
		
		labelLine = new JLabel("Geraden");
		labelLine.setBounds(25, 15, 50, 25);
		uiLine.add(labelLine);
		
		tfLineX1 = new JTextField("x1");
		tfLineX1.setBounds(0, 50, 25, 25);
		uiLine.add(tfLineX1);
		
		tfLineX2 = new JTextField("x2");
		tfLineX2.setBounds(0, 90, 25, 25);
		uiLine.add(tfLineX2);
		
		tfLineX3 = new JTextField("x3");
		tfLineX3.setBounds(0, 130, 25, 25);
		uiLine.add(tfLineX3);
		
		labelLineEquation = new JLabel("+ s *");
		labelLineEquation.setBounds(35, 395, 25, 10);
		uiPoint.add(labelLineEquation);
		
		tfLineV1 = new JTextField("v1");
		tfLineV1.setBounds(75, 50, 25, 25);
		uiLine.add(tfLineV1);
		
		tfLineV2 = new JTextField("v2");
		tfLineV2.setBounds(75, 90, 25, 25);
		uiLine.add(tfLineV2);
		
		tfLineV3 = new JTextField("v3");
		tfLineV3.setBounds(75, 130, 25, 25);
		uiLine.add(tfLineV3);
		
		labelLineEquation = new JLabel("+ s *");
		labelLineEquation.setBounds(35, 90, 50, 25);
		uiLine.add(labelLineEquation);
		
		lineHinzufuegen = new JButton("Hinzufügen");
		lineHinzufuegen.setBounds(0, 170, 100, 25);
		lineHinzufuegen.addActionListener(this);
		uiLine.add(lineHinzufuegen);
		
		listLines = new List();
		listLines.setBounds(0, 205, 100, 100);
		uiLine.add(listLines);
		
		lineLoeschen = new JButton("Löschen");
		lineLoeschen.setBounds(0, 315, 100, 25);
		lineLoeschen.addActionListener(this);
		uiLine.add(lineLoeschen);


		this.addComponentListener(new ComponentListener() {
			public void componentMoved(ComponentEvent ce) {

			}

			public void componentShown(ComponentEvent ce) {

			}

			public void componentHidden(ComponentEvent ce) {

			}

			public void componentResized (ComponentEvent ce) {
				resize();
			}
		});

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == pointHinzufuegen) {
			if (!tfPointX1.getText().isEmpty() && !tfPointX2.getText().isEmpty() && !tfPointX3.getText().isEmpty()) {
				Vector3Di v3D = new Vector3Di(Integer.parseInt(tfPointX1.getText()) * 100, Integer.parseInt(tfPointX2.getText()) * 100, Integer.parseInt(tfPointX3.getText()) * 100);
				vectors.add(v3D);
				canvas.setVectors(vectors);
				listPoints.add(v3D.toString());
				canvas.repaint();
				repaint();
			}		
		}

		if (ae.getSource() == pointLoeschen) {
			if (listPoints.getSelectedItem() != null) {
				vectors.remove(listPoints.getSelectedIndex());
				listPoints.remove(listPoints.getSelectedIndex());
				canvas.setVectors(vectors);
				canvas.repaint();
				repaint();
			}	
		}
		
		if (ae.getSource() == planeHinzufuegen) {
			if (!tfPlaneX1.getText().isEmpty() && !tfPlaneX2.getText().isEmpty() && !tfPlaneX3.getText().isEmpty()) {
				Plane p = new Plane(new Vector3Di(Integer.parseInt(tfPlaneX1.getText()), Integer.parseInt(tfPlaneX2.getText()), Integer.parseInt(tfPlaneX3.getText())), Integer.parseInt(tfPlaneD.getText()) * 100);
				planes.add(p);
				canvas.setPlanes(planes);
				listPlanes.add(p.toString());
				canvas.repaint();
				repaint();
			}
		}
		
		if (ae.getSource() == planeLoeschen) {
			if (listPlanes.getSelectedItem() != null) {
				planes.remove(listPlanes.getSelectedIndex());
				listPlanes.remove(listPlanes.getSelectedIndex());
				canvas.setPlanes(planes);
				canvas.repaint();
				repaint();
			}	
		}
		
		if (ae.getSource() == lineHinzufuegen) {
			if (!tfLineX1.getText().isEmpty() && !tfLineX2.getText().isEmpty() && !tfLineX3.getText().isEmpty()) {
				Line l = new Line(new Vector3Di(Integer.parseInt(tfLineX1.getText()), Integer.parseInt(tfLineX2.getText()), Integer.parseInt(tfLineX3.getText())), new Vector3Di(Integer.parseInt(tfLineV1.getText()), Integer.parseInt(tfLineV2.getText()), Integer.parseInt(tfLineV3.getText())));
				lines.add(l);
				canvas.setLines(lines);
				listLines.add(l.toString());
				canvas.repaint();
				repaint();
			}
		}

		if (ae.getSource() == showGUI) {
			if (boolShowGUI) {
				uiControl.setVisible(false);
				uiPoint.setVisible(false);
				showGUI.setText("Vollbild aus");
			} else {
				uiControl.setVisible(true);
				uiPoint.setVisible(true);
				showGUI.setText("Vollbild an");
			}

			boolShowGUI = !boolShowGUI;
			resize();

		}	
	}

	public void stateChanged(ChangeEvent ce) {
		if (ce.getSource() == angleX3) {
			canvas.setAngleX3(angleX3.getValue());
			canvas.repaint();
			repaint();
		}

		if (ce.getSource() == angleX2) {
			canvas.setAngleX2(angleX2.getValue());
			canvas.repaint();
			repaint();
		}

		if (ce.getSource() == angleX1) {
			canvas.setAngleX1(angleX1.getValue());
			canvas.repaint();
			repaint();
		}

		if (ce.getSource() == scale) {
			canvas.setScale(Math.pow(10, scale.getValue() / 100.0 - 1));
			canvas.repaint();
			repaint();
		}


	}

	public void mouseWheelMoved(MouseWheelEvent e) {
		scale.setValue(scale.getValue() - e.getWheelRotation() * 5);
		canvas.setScale(Math.pow(10, scale.getValue() / 100.0 - 1));
		canvas.repaint();
		repaint();
	}

	public LinkedList<Vector3Di> getVectors() {
		return vectors;
	}

	public void resize() {
		if (boolShowGUI) {
			if (getHeight() > 980) {
				canvas.setBounds(0, 0, getWidth() - 175, getHeight() - 150);
				uiPoint.setBounds(getWidth() - 150, 0, 100, 590);
				uiLine.setBounds(getWidth() - 150, 590, 100, 340);
				uiControl.setBounds(25, getHeight() - 150, getWidth() - 175, 150);
			} else {
				canvas.setBounds(0, 0, getWidth() - 325, getHeight() - 150);
				uiPoint.setBounds(getWidth() - 300, 0, 100, 650);
				uiLine.setBounds(getWidth() - 150, 0, 100, 650);
				uiControl.setBounds(25, getHeight() - 150, getWidth() - 325, 150);
			}
			
		} else {
			canvas.setBounds(0, 0, getWidth(), getHeight()); 
		}

		showGUI.setBounds(15, 15, 100, 25);
	}

	public static void main(String[] args) {
		VectorApplet va = new VectorApplet();
	}

}
