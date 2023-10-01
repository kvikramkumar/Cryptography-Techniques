import crypto.*;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Cryptography extends JFrame implements ActionListener,ItemListener
{
	JLabel title,l1,l2,l3,l4,l5,l6,l7,l8;
	JComboBox cb1,cb2;
	JTextField t1,t2,t3,t4,t5,t6;
	JButton b1;
	Caesar caesar;
	
	Cryptography()
	{
		setTitle("ENCRYPTION ALGORITHM");
		setLayout(null);
		
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
        
		title = new JLabel("CRYPTOGRAPHY");
		title.setBounds(600,50,200,40);
		title.setFont(new Font("Dialog", Font.BOLD, 22));
		add(title);
		
		l1=new JLabel("SELECT ENCRYPTION OR DECRYPTION TECHNIQUE");
		l1.setBounds(100,150,400,30);
		l1.setFont(new Font("Dialog", Font.BOLD, 14));
		add(l1);
		
		String alg[] = {"Caesar cipher","Monoalphabetic cipher","Playfair cipher","Hill cipher","Vignere cipher","Vernam cipher","One Time Pad","Rail Fence Technique","Single Column Transposition","Double Column Transposition"};
		cb1 = new JComboBox(alg);
		cb1.setBounds(500,150,200,30);
		cb1.addItemListener(this);
		add(cb1);
		
		l2=new JLabel("SELECT OPERATION");
		l2.setBounds(800,150,150,30);
		l2.setFont(new Font("Dialog", Font.BOLD, 14));
		add(l2);
		
		String op[]= {"Encryption","Decryption","Encryption & Decryption"};
		cb2 = new JComboBox(op);
		cb2.setBounds(970,150,200,30);
		add(cb2);
		cb2.addItemListener(this);
		
		l3 = new JLabel("Enter the Message ");
		l3.setBounds(100,300,150,30);
		l3.setFont(new Font("Dialog", Font.BOLD, 14));
		add(l3);
		
		t1 = new JTextField();
		t1.setBounds(250,300,200,30);
		add(t1);
		
		l4 = new JLabel("Enter Key ");
		l4.setBounds(500,300,150,30);
		l4.setFont(new Font("Dialog", Font.BOLD, 14));
		add(l4);
		
		t2 = new JTextField();
		t2.setBounds(650,300,200,30);
		add(t2);
		
		l5 = new JLabel("Enter Second Key ");
		l5.setBounds(900,300,150,30);
		l5.setFont(new Font("Dialog", Font.BOLD, 14));
		add(l5);
		l5.setVisible(false);
		
		t3 = new JTextField();
		t3.setBounds(1050,300,200,30);
		add(t3);
		t3.setVisible(false);
		
		b1 = new JButton("SUBMIT");
		b1.setBounds(600,400,100,30);
		b1.setBorder(BorderFactory.createLineBorder(Color.BLUE, 2));
		b1.addActionListener(this);
		add(b1);
		
		l6 = new JLabel("PLAIN TEXT");
		l6.setBounds(400,500,150,30);
		l6.setFont(new Font("Dialog", Font.BOLD, 14));
		add(l6);
		
		
		t4 = new JTextField();
		t4.setBounds(550,500,400,30);
		t4.setFont(new Font("Dialog", Font.BOLD, 14));
		add(t4);
		t4.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		t4.setEditable(false);
		
		
		
		l7 = new JLabel("ENCRYPTED TEXT");
		l7.setBounds(400,550,150,30);
		l7.setFont(new Font("Dialog", Font.BOLD, 14));
		add(l7);
		
		t5 = new JTextField();
		t5.setBounds(550,550,400,30);
		t5.setFont(new Font("Dialog", Font.BOLD, 14));
		add(t5);
		t5.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		t5.setEditable(false);
		
		
		l8 = new JLabel("DECRYPTED TEXT");
		l8.setBounds(400,600,150,30);
		l8.setFont(new Font("Dialog", Font.BOLD, 14));
		add(l8);
		l8.setVisible(false);
		
		t6 = new JTextField();
		t6.setBounds(550,600,400,30);
		t6.setFont(new Font("Dialog", Font.BOLD, 14));
		add(t6);
		t6.setEditable(false);
		t6.setVisible(false);
		t6.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLUE));
		
		setVisible(true);
		
	}
	public void actionPerformed(ActionEvent ae)
	{
		String msg = t1.getText();
		
		String ch1 = (String) cb1.getSelectedItem();
		String ch2 = (String) cb2.getSelectedItem();
		switch(ch1)
		{
			case "Caesar cipher":
								{
									Caesar obj = new Caesar();
									int key;
									try{
									key = Integer.parseInt(t2.getText());
									}
									catch(NumberFormatException e)
									{
										JOptionPane.showMessageDialog(this,"Key Should only contains number");
										break;
									}
									if(ch2.equals("Encryption"))
									{
										String e = obj.encrypt(msg,key);
										t4.setText(msg);
										t5.setText(e);
								
									}
									else if(ch2.equals("Decryption"))
									{
										String d = obj.decrypt(msg,key);
										t5.setText(msg);
										t6.setText(d);
									}
									else
									{
										String e = obj.encrypt(msg,key);
										String d = obj.decrypt(e,key);
								
										t4.setText(msg);
										t5.setText(e);
										t6.setText(d);
									}
								}
								break;
			case "Monoalphabetic cipher":
								{
										Monoalphabetic obj = new Monoalphabetic();
										String key = t2.getText();
										if(ch2.equals("Encryption"))
										{
											String e = obj.encrypt(msg,key);
											t4.setText(msg);
											t5.setText(e);

										}
										else if(ch2.equals("Decryption"))
										{
											String d = obj.decrypt(msg,key);
											t5.setText(msg);
											t6.setText(d);
										}
										else
										{
											String e = obj.encrypt(msg,key);
											String d = obj.decrypt(e,key);

											t4.setText(msg);
											t5.setText(e);
											t6.setText(d);
										}
								}
								break;

			case "Playfair cipher":
								{
										Playfair obj = new Playfair();
										String key = t2.getText();
										if(ch2.equals("Encryption"))
										{
												String e = obj.encrypt(msg,key);
												t4.setText(msg);
												t5.setText(e);
					
										}
										else if(ch2.equals("Decryption"))
										{
											String d = obj.decrypt(msg,key);
											t5.setText(msg);
											t6.setText(d);
										}
										else
										{
											String e = obj.encrypt(msg,key);
											String d = obj.decrypt(e,key);
					
											t4.setText(msg);
											t5.setText(e);
											t6.setText(d);
										}
								}
								break;
			case "Hill cipher":
								{
										HillCipher obj = new HillCipher();
										String key = t2.getText();
										if(ch2.equals("Encryption"))
										{
											String e="";
											try
											{
												e = obj.encrypt(msg,key);
											}
											catch(Exception ex)
											{
												JOptionPane.showMessageDialog(this,"INVALID KEY");
												break;
											}
											t4.setText(msg);
											t5.setText(e);

										}
										else if(ch2.equals("Decryption"))
										{
											String d="";
											try
											{
												d = obj.decrypt(msg,key);
											}
											catch(Exception ex)
											{
												JOptionPane.showMessageDialog(this,"INVALID KEY");
												break;
											}
											t5.setText(msg);
											t6.setText(d);
										}
										else
										{
											String e="",d="";
											try
											{
												e = obj.encrypt(msg,key);
												d = obj.decrypt(e,key);
											}
											catch(Exception ex)
											{
												JOptionPane.showMessageDialog(this,"INVALID KEY");
												break;
											}
											
											t4.setText(msg);
											t5.setText(e);
											t6.setText(d);
										}
								}
								break;
				case "Rail Fence Technique":
								{
										RailFence obj = new RailFence();
										int key = Integer.parseInt(t2.getText());
										if(ch2.equals("Encryption"))
										{
											String e = obj.encrypt(msg,key);
											t4.setText(msg);
											t5.setText(e);

										}
										else if(ch2.equals("Decryption"))
										{
											String d = obj.decrypt(msg,key);
											t5.setText(msg);
											t6.setText(d);
										}
										else
										{
											String e = obj.encrypt(msg,key);
											String d = obj.decrypt(e,key);

											t4.setText(msg);
											t5.setText(e);
											t6.setText(d);
										}
								}
								break;
				case "Vignere cipher":
									{
											Vignere obj = new Vignere();
											String key = t2.getText();
											if(ch2.equals("Encryption"))
											{
												String e = obj.encrypt(msg,key);
												t4.setText(msg);
												t5.setText(e);

											}
											else if(ch2.equals("Decryption"))
											{
												String d = obj.decrypt(msg,key);
												t5.setText(msg);
												t6.setText(d);
											}
											else
											{
												String e = obj.encrypt(msg,key);
												String d = obj.decrypt(e,key);

												t4.setText(msg);
												t5.setText(e);
												t6.setText(d);
											}
									}
									break;
				case "Vernam cipher":
								{
										Vernam obj = new Vernam();
										String key = t2.getText();
										if(ch2.equals("Encryption"))
										{
											String e = obj.encrypt(msg,key);
											t4.setText(msg);
											t5.setText(e);

										}
										else if(ch2.equals("Decryption"))
										{
											String d = obj.decrypt(msg,key);
											t5.setText(msg);
											t6.setText(d);
										}
										else
										{
											String e = obj.encrypt(msg,key);
											String d = obj.decrypt(e,key);

											t4.setText(msg);
											t5.setText(e);
											t6.setText(d);
										}
								}
								break;

				case "One Time Pad":
				{
						OneTimePad obj = new OneTimePad();
						
						if(ch2.equals("Encryption"))
						{
							String e = obj.encrypt(msg);
							t2.setText(obj.key);
							
							t4.setText(msg);
							t5.setText(e);

						}
						else if(ch2.equals("Decryption"))
						{
							String key = t2.getText();
							String d = obj.decrypt(msg,key);
							
							t5.setText(msg);
							t6.setText(d);
						}
						else
						{
							String e = obj.encrypt(msg);
							t2.setText(obj.key);
							
							String key = t2.getText();
							String d = obj.decrypt(e,key);
							
							t4.setText(msg);
							t5.setText(e);
							t6.setText(d);
						}
				}
				break;
				case "Single Column Transposition":
									{
											SingleColumnTransposition obj = new SingleColumnTransposition();
											String key = t2.getText();
											if(ch2.equals("Encryption"))
											{
												String e = obj.encrypt(msg,key);
												t4.setText(msg);
												t5.setText(e);

											}
											else if(ch2.equals("Decryption"))
											{
												String d = obj.decrypt(msg,key);
												t5.setText(msg);
												t6.setText(d);
											}
											else
											{
												String e = obj.encrypt(msg,key);
												String d = obj.decrypt(e,key);
						
												t4.setText(msg);
												t5.setText(e);
												t6.setText(d);
											}
									}
									break;
									
				case "Double Column Transposition":
									{
											DoubleColumnTransposition obj = new DoubleColumnTransposition();
											String key1 = t2.getText();
											String key2 = t3.getText();
											if(ch2.equals("Encryption"))
											{
												String e = obj.encrypt(msg,key1,key2);
												t4.setText(msg);
												t5.setText(e);

											}
											else if(ch2.equals("Decryption"))
											{
												String d = obj.decrypt(msg,key1,key2);
												t5.setText(msg);
												t6.setText(d);
											}
											else
											{
												String e = obj.encrypt(msg,key1,key2);
												String d = obj.decrypt(e,key1,key2);
	
												t4.setText(msg);
												t5.setText(e);
												t6.setText(d);
											}
									}
									break;



			}
	
	}
	public void itemStateChanged(ItemEvent ie)
	{
		if(ie.getSource() == cb1)
		{
			if (ie.getStateChange() == ItemEvent.SELECTED) 
			{
				String item = (String) cb1.getSelectedItem();
	
				if(item.equals("Rail Fence Technique"))
				{
					l4.setText("Enter the Depth");
				}
				else if(item.equals("Double Column Transposition"))
				{
					l4.setText("Enter First Key");
					l5.setVisible(true);
					t3.setVisible(true);
				}
				else
				{
					l4.setText("Enter Key");
					l5.setVisible(false);
					t3.setVisible(false);
					t2.setEditable(true);
				}
				t1.setText("");
				t2.setText("");
			}
		}
		else
		{
			if(ie.getStateChange() == ItemEvent.SELECTED)
			{
				String item = (String) cb2.getSelectedItem();
				if(item.equals("Encryption"))
				{
					l6.setVisible(true);
					l7.setVisible(true);
					t4.setVisible(true);
					t5.setVisible(true);
					
					t4.setText("");
					t5.setText("");
					
					l8.setVisible(false);
					t6.setVisible(false);
					
					if((String) cb1.getSelectedItem() == "One Time Pad")
					{
						t2.setEditable(false);
					}
				}
				else if(item.equals("Decryption"))
				{
					l7.setVisible(true);
					l8.setVisible(true);
					t5.setVisible(true);
					t6.setVisible(true);
					l6.setVisible(false);
					t4.setVisible(false);
					
					t5.setText("");
					t6.setText("");
					
					if((String) cb1.getSelectedItem() == "One Time Pad")
					{
						t2.setEditable(true);
					}
				}
				else
				{
					l6.setVisible(true);
					l7.setVisible(true);
					l8.setVisible(true);
					t4.setVisible(true);
					t5.setVisible(true);
					t6.setVisible(true);
					
					t4.setText("");
					t5.setText("");
					t6.setText("");
					
					if((String) cb1.getSelectedItem() == "One Time Pad")
					{
						t2.setEditable(false);
					}
				}
			}
		}
		
	}
	public static void main(String[] args) 
	{
		new Cryptography();
	}
}
