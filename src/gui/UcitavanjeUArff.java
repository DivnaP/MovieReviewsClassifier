package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.awt.event.ActionEvent;

public class UcitavanjeUArff extends JFrame {
	final JFileChooser fc = new JFileChooser();
	private JPanel contentPane;
File fileChosen;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UcitavanjeUArff frame = new UcitavanjeUArff();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UcitavanjeUArff() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		final JButton btNeg = new JButton("Negativna");
		btNeg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				File folder = new File("C:/Users/Divna/Desktop/txt_sentoken/neg");
				File[] listOfFiles = folder.listFiles();

				for (File file : listOfFiles) {
				    if (file.isFile()) {
				    	String name= file.getPath();
						String text=load(name);
						writeP(text);
				    }
				}
			/*	if (arg0.getSource() == btNeg) {
					int returnVal = fc.showOpenDialog(UcitavanjeUArff.this);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						fileChosen = fc.getSelectedFile();
						String name= fileChosen.getPath();
						String text=load(name);
						write(text);
						
					} else {

					}
			}
			*/
				}}
		);
		contentPane.add(btNeg);
		
		final JButton btPoz = new JButton("Pozitivna");
		btPoz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				File folder = new File("C:/Users/Divna/Desktop/txt_sentoken/pos");
				File[] listOfFiles = folder.listFiles();

				for (File file : listOfFiles) {
				    if (file.isFile()) {
				    	String name= file.getPath();
						String text=load(name);
						writeP(text);
				    }
				}
				
				/*
				if (arg0.getSource() == btPoz) {
					int returnVal = fc.showOpenDialog(UcitavanjeUArff.this);

					if (returnVal == JFileChooser.APPROVE_OPTION) {
						fileChosen = fc.getSelectedFile();
						String name= fileChosen.getPath();
						String text=load(name);
						writeP(text);
						
					} else {

					}
			}
				*/
				}}
		);
		contentPane.add(btPoz);
	}
	public String load(String fileName) {
		String text="";
		try {
			BufferedReader reader = new BufferedReader(new FileReader(fileName));
			String line;
	
			while ((line = reader.readLine()) != null) {
                text = text + " " + line;
                
            }
			// System.out.println("===== Loaded text data: " + fileName + " =====");
			reader.close();
			// System.out.println(text);
			
		}
		catch (IOException e) {
			System.out.println("Problem found when reading: " + fileName);
		}
		for (int i = 0; i < text.length(); i++) {
			text=text.replace("'", " ").replace("\n", " ");
			
		}
		
		return text;
	}
	public void write(String text){
		
		FileWriter fw;
		BufferedWriter bufferedWriter;
		try {
			bufferedWriter = new BufferedWriter(new FileWriter("kritikeSve3.arff",true));
			//fw = new FileWriter("kritikeSve2.arff", true);
			
			bufferedWriter.write("\n negativeC,'");
			bufferedWriter.write(text+"'");
			bufferedWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}
public void writeP(String text){
		
	FileWriter fw;
	BufferedWriter bufferedWriter;
	try {
		bufferedWriter = new BufferedWriter(new FileWriter("kritikeSve3.arff",true));
		//fw = new FileWriter("kritikeSve2.arff", true);
		
		bufferedWriter.write("\n positiveC,'");
		bufferedWriter.write(text+"'");
		bufferedWriter.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();

	}
}}
