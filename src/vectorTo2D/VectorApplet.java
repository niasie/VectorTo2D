package vectorTo2D;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;

public class VectorApplet extends JFrame implements ActionListener, ChangeListener {
	Coordinatesystem canvas;
	JButton beenden, hinzufuegen, loeschen;
	JTextField tfX, tfY, tfZ;
	JSlider angleX3, angleX2, angleX1;
	List listVectors;
	
	LinkedList<Vector3D> vectors = new LinkedList<Vector3D>();
	
	public VectorApplet() {
		this.setSize(800, 600);
		this.setTitle("Vektoren in 2D Darstellung");
		this.setLayout(null);
		this.setResizable(false);
		
		canvas = new Coordinatesystem();
		canvas.setBounds(10, 10, 600, 550);
		canvas.setBackground(Color.BLACK);
		this.add(canvas);
		
		beenden = new JButton("Beenden");
		beenden.setBounds(650, 535, 100, 25);
		beenden.addActionListener(this);
		this.add(beenden);
		
		hinzufuegen = new JButton("Hinzufügen");
		hinzufuegen.setBounds(650, 90, 100, 25);
		hinzufuegen.addActionListener(this);
		this.add(hinzufuegen);
		
		loeschen = new JButton("Loeschen");
		loeschen.setBounds(650, 235, 100, 25);
		loeschen.addActionListener(this);
		this.add(loeschen);
		
		tfX = new JTextField();
		tfX.setBounds(650, 50, 25, 25);
		this.add(tfX);
		
		tfY = new JTextField();
		tfY.setBounds(688, 50, 25, 25);
		this.add(tfY);
		
		tfZ = new JTextField();
		tfZ.setBounds(725, 50, 25, 25);
		this.add(tfZ);
		
		listVectors = new List();
		listVectors.setBounds(650, 125, 100, 100);
		this.add(listVectors);
		
		angleX3 = new JSlider(-90, 90, 0);
		angleX3.setBounds(650, 300, 100, 75);
		angleX3.addChangeListener(this);
		angleX3.setMajorTickSpacing(45);
		angleX3.setPaintTicks(true);
		angleX3.setPaintLabels(true);
		this.add(angleX3);
		
		angleX2 = new JSlider(-90, 90, 0);
		angleX2.setBounds(650, 375, 100, 75);
		angleX2.addChangeListener(this);
		angleX2.setMajorTickSpacing(45);
		angleX2.setPaintTicks(true);
		angleX2.setPaintLabels(true);
		this.add(angleX2);
		
		angleX1 = new JSlider(-90, 90, 0);
		angleX1.setBounds(650, 450, 100, 75);
		angleX1.addChangeListener(this);
		angleX1.setMajorTickSpacing(45);
		angleX1.setPaintTicks(true);
		angleX1.setPaintLabels(true);
		this.add(angleX1);
		
		this.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == beenden) {
			System.exit(0);
		}
		
		if (ae.getSource() == hinzufuegen) {
			Vector3D v3D = new Vector3D(Integer.parseInt(tfX.getText()), Integer.parseInt(tfY.getText()), Integer.parseInt(tfZ.getText()));
			vectors.add(v3D);
			canvas.setVectors(vectors);
			listVectors.add(v3D.toString());
			canvas.repaint();
			repaint();
		}
		
		if (ae.getSource() == loeschen) {
			if (listVectors.getSelectedItem() != null) {
				vectors.remove(listVectors.getSelectedIndex());
				listVectors.remove(listVectors.getSelectedIndex());
				canvas.setVectors(vectors);
				canvas.repaint();
				repaint();
			}
			
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
	}
	
	public LinkedList<Vector3D> getVectors() {
		return vectors;
	}
	
	public static void main(String[] args) {
		VectorApplet va = new VectorApplet();
	}

}
