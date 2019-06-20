import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DirectoryGUI extends JFrame {

	// implementation used for phone directories
	private ListDirectory array = new ListDirectory();
	private String lookUpNumberString = "";

	public DirectoryGUI() {
		directoryInterface();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(660, 150);
		setMinimumSize(new Dimension(660, 150));
		setLocationRelativeTo(null);
		setResizable(true);
	}

	private void directoryInterface() {
		// Container Panel to contain all layouts
		CardLayout card = new CardLayout();
		JPanel panelCont = new JPanel(card);
		getContentPane().add(panelCont);

		// Main menu panel
		JPanel mainMenu = new JPanel();
		JButton addEntries = new JButton("Add Entries");
		JButton deleteEntries = new JButton("Delete Entries");
		JButton lookUp = new JButton("Look Up An Entry");
		JButton changeNumber = new JButton("Change Number");
		JButton printDirectory = new JButton("Print Directory");
		mainMenu.add(addEntries);
		mainMenu.add(deleteEntries);
		mainMenu.add(lookUp);
		mainMenu.add(changeNumber);
		mainMenu.add(printDirectory);

		/*
		 * 
		 * 
		 * BEGGINING OF ADD ENTRIES SECTION
		 * 
		 * 
		 */

		// Main Panel using the border layout
		JPanel addPanel = new JPanel();
		addPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		addPanel.setLayout(new BorderLayout());

		// Center
		JPanel addPanelCenter = new JPanel();
		JLabel surname = new JLabel("Surname: ");
		addPanelCenter.add(surname);
		JTextField surnameBox = new JTextField(20);
		addPanelCenter.add(surnameBox);
		JLabel initials = new JLabel("Initials: ");
		addPanelCenter.add(initials);
		JTextField initialsBox = new JTextField(3);
		addPanelCenter.add(initialsBox);
		JLabel number = new JLabel("Number: ");
		addPanelCenter.add(number);
		JTextField numberBox = new JTextField(5);
		addPanelCenter.add(numberBox);
		addPanel.add(addPanelCenter);

		JButton addButtonReset = new JButton("Reset");
		JPanel addPanelWest = new JPanel();
		addButtonReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				surnameBox.setText("");
				initialsBox.setText("");
				numberBox.setText("");
			}

		});
		addPanelWest.add(addButtonReset);
		addPanel.add(addPanelWest, BorderLayout.WEST);

		JButton buttonAdd = new JButton("Add");
		JPanel addPanelEast = new JPanel();
		buttonAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String surname = surnameBox.getText();
				String initials = initialsBox.getText();
				String number = numberBox.getText();
				if (surname.isEmpty() || initials.isEmpty() || number.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Fill In All The Boxes");
				} else {
					array.addEntry(new Entry(surname, initials, number));
					surnameBox.setText("");
					initialsBox.setText("");
					numberBox.setText("");
				}

			}

		});
		addPanelEast.add(buttonAdd);
		addPanel.add(addPanelEast, BorderLayout.EAST);

		// Bottom
		JPanel addPanelSouth = new JPanel();
		JButton addButtonBack = new JButton("Back");
		addPanelSouth.add(addButtonBack);
		addPanel.add(addPanelSouth, BorderLayout.SOUTH);
		/*
		 * 
		 * 
		 * END OF ADD ENTRIES SECTION
		 * 
		 * 
		 */

		/*
		 * 
		 * 
		 * BEGGINING TO DELETE ENTRIES SECTION
		 * 
		 * 
		 */
		// Main Panel using the border layout
		JPanel deletePanel = new JPanel();
		deletePanel.setBorder(new EmptyBorder(10, 10, 10, 10));
		deletePanel.setLayout(new BorderLayout());

		// North
		JPanel deletePanelNorth = new JPanel();
		deletePanelNorth.setBorder(new EmptyBorder(0, 0, 10, 0));
		deletePanelNorth.setLayout(new BorderLayout());

		// North Center
		JPanel deletePanelNorthCenter = new JPanel();
		JLabel deleteNumber = new JLabel("Number: ");
		deletePanelNorthCenter.add(deleteNumber);
		JTextField deleteNumberBox = new JTextField(5);
		deletePanelNorthCenter.add(deleteNumberBox);
		deletePanelNorth.add(deletePanelNorthCenter);

		JButton deleteButtonReset = new JButton("Reset");
		deleteButtonReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteNumberBox.setText("");
			}

		});

		deletePanelNorth.add(deleteButtonReset, BorderLayout.WEST);
		JButton buttonDelete = new JButton("Delete");
		buttonDelete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String number = deleteNumberBox.getText();
				if (number.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Fill In The Box");
				} else {
					array.deleteEntry(number);
					deleteNumberBox.setText("");
				}

			}

		});
		deletePanelNorth.add(buttonDelete, BorderLayout.EAST);
		deletePanel.add(deletePanelNorth, BorderLayout.NORTH);

		// Bottom
		JPanel deletePanelSouth = new JPanel();
		JButton deleteButtonBack = new JButton("Back");
		deletePanelSouth.add(deleteButtonBack);
		deletePanel.add(deletePanelSouth, BorderLayout.SOUTH);
		/*
		 * 
		 * 
		 * END OF DELETE ENTRIES SECTION
		 * 
		 * 
		 */
		
		
		
		
		
		/*
		 * 
		 * BEGGINING OF LOOKUP ENTRIES SECTION
		 * 
		 */
		JPanel lookUpPanel = new JPanel(new BorderLayout());
		
		//Center
		JPanel lookUpCenter = new JPanel(new BorderLayout());
		JPanel lookUpCenterNorth = new JPanel();
		JLabel lookUpName = new JLabel("Name: ");
		lookUpCenterNorth.add(lookUpName);
		JTextField lookUpNameBox = new JTextField(20);
		lookUpCenterNorth.add(lookUpNameBox);
		JLabel lookUpInitials = new JLabel("Initials: ");
		lookUpCenterNorth.add(lookUpInitials);
		JTextField lookUpInitialsBox = new JTextField(2);
		lookUpCenterNorth.add(lookUpInitialsBox);
		lookUpCenter.add(lookUpCenterNorth, BorderLayout.NORTH);
		JLabel lookUpNumber = new JLabel();
		lookUpNumber.setText("Number: " + lookUpNumberString);
		lookUpCenter.add(lookUpNumber);
		lookUpPanel.add(lookUpCenter);
		
		//Left
		JButton lookUpButtonReset = new JButton("Reset");
		JPanel lookUpPanelWest = new JPanel();
		lookUpButtonReset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lookUpNameBox.setText("");
				lookUpInitialsBox.setText("");
			}

		});
		lookUpPanelWest.add(lookUpButtonReset);
		lookUpPanel.add(lookUpPanelWest, BorderLayout.WEST);

		//Right
		JButton lookUpButton = new JButton("Look Up");
		JPanel lookUpPanelEast = new JPanel();
		lookUpButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String name = lookUpNameBox.getText();
				String initials = lookUpInitialsBox.getText();
				if(name.isEmpty()||initials.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Please Fill In The Box");
				}
				else {
					lookUpNumberString = array.lookUp(name, initials);
					if(lookUpNumberString.equals(null)) {
						lookUpNumber.setText("Phone Number Not Found");
					}
					else {
						lookUpNumber.setText("Number: " + lookUpNumberString);
					}
				}
			}

		});
		lookUpPanelEast.add(lookUpButton);
		lookUpPanel.add(lookUpPanelEast, BorderLayout.EAST);
		
		//Bottom
		JPanel lookUpPanelSouth = new JPanel();
		JButton lookUpButtonBack = new JButton("Back");
		lookUpPanelSouth.add(lookUpButtonBack);
		lookUpPanel.add(lookUpPanelSouth, BorderLayout.SOUTH);
		/*
		 * 
		 * 
		 * END OF LOOKUP SECTION
		 * 
		 * 
		 */
		
		/*
		 * 
		 * 
		 * BEGGINING OF CHANGE NUMBER SECTION
		 * 
		 * 
		 */
		
		// Main Panel using the border layout
				JPanel changeNumberPanel = new JPanel();
				changeNumberPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
				changeNumberPanel.setLayout(new BorderLayout());

				// Center
				JPanel changeNumberPanelCenter = new JPanel();
				JLabel changeSurname = new JLabel("Surname: ");
				changeNumberPanelCenter.add(changeSurname);
				JTextField changeSurnameBox = new JTextField(20);
				changeNumberPanelCenter.add(changeSurnameBox);
				JLabel changeInitials = new JLabel("Initials: ");
				changeNumberPanelCenter.add(changeInitials);
				JTextField changeInitialsBox = new JTextField(3);
				changeNumberPanelCenter.add(changeInitialsBox);
				JLabel newNumber = new JLabel("New Number: ");
				changeNumberPanelCenter.add(newNumber);
				JTextField newNumberBox = new JTextField(5);
				changeNumberPanelCenter.add(newNumberBox);
				changeNumberPanel.add(changeNumberPanelCenter);

				JButton changeButtonReset = new JButton("Reset");
				JPanel changeNumberPanelWest = new JPanel();
				changeButtonReset.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						changeSurnameBox.setText("");
						changeInitialsBox.setText("");
						newNumberBox.setText("");
					}

				});
				changeNumberPanelWest.add(changeButtonReset);
				changeNumberPanel.add(changeNumberPanelWest, BorderLayout.WEST);

				JButton buttonChange = new JButton("Change Number");
				JPanel changeNumberPanelEast = new JPanel();
				buttonChange.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						String surname = changeSurnameBox.getText();
						String initials = changeInitialsBox.getText();
						String number = newNumberBox.getText();
						if (surname.isEmpty() || initials.isEmpty() || number.isEmpty()) {
							JOptionPane.showMessageDialog(null, "Please Fill In All The Boxes");
						} else {
							array.changeNumber(surname, initials, number);
							changeSurnameBox.setText("");
							changeInitialsBox.setText("");
							newNumberBox.setText("");
						}

					}

				});
				changeNumberPanelEast.add(buttonChange);
				changeNumberPanel.add(changeNumberPanelEast, BorderLayout.EAST);

				// Bottom
				JPanel changeNumberPanelSouth = new JPanel();
				JButton changeButtonBack = new JButton("Back");
				changeNumberPanelSouth.add(changeButtonBack);
				changeNumberPanel.add(changeNumberPanelSouth, BorderLayout.SOUTH);
		
		/*
		 * 
		 * 
		 * END OF CHANGE NUMBER SECTION
		 * 
		 * 
		 */
		
		/*
		 * 
		 * 
		 * PRINT DIRECTORY SECTION
		 * 
		 * 
		 */
		JPanel printDirectoryPane = new JPanel(new BorderLayout());
		JTextArea directoryText = new JTextArea();
		directoryText.setEditable(false);
		JScrollPane directoryTextScroll = new JScrollPane(directoryText);
		printDirectoryPane.add(directoryTextScroll);
		JButton printDirectoryBack = new JButton("Back");
		JPanel printDirectorySouth = new JPanel();
		printDirectorySouth.add(printDirectoryBack);
		printDirectoryPane.add(printDirectorySouth, BorderLayout.SOUTH);
		/*
		 * 
		 * 
		 * END OF PRINT DIRECTORY SECTION
		 * 
		 * 
		 */

		
		
		
		
		
		// Add all panels to the main menu...
		panelCont.add(mainMenu, "MainMenu");
		panelCont.add(addPanel, "AddSection");
		panelCont.add(deletePanel, "DeleteSection");
		panelCont.add(lookUpPanel, "LookUpSection");
		panelCont.add(changeNumberPanel, "ChangeNumberSection");
		panelCont.add(printDirectoryPane, "PrintDirectorySection");

		card.show(panelCont, "MainMenu");

		
		
		
		
		
		
		
		//Action Listeners for accessing the sections
		addEntries.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "AddSection");

			}

		});

		deleteEntries.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "DeleteSection");

			}

		});

		lookUp.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "LookUpSection");
				
			}
			
		});
		
		changeNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "ChangeNumberSection");
				
			}
			
		});
		
		printDirectory.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "PrintDirectorySection");
				directoryText.setText(array.printDirectory());
			}
			
		});
		
		
		
		
		
		
		
		
		
		
		
		
		//Action Listeners for going back to the main menu
		addButtonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "MainMenu");

			}

		});

		deleteButtonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "MainMenu");

			}

		});
		
		lookUpButtonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "MainMenu");
				
			}
			
		});
		
		changeButtonBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "MainMenu");
				
			}
			
		});
		
		printDirectoryBack.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				card.show(panelCont, "MainMenu");
				
			}
			
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new DirectoryGUI().setVisible(true);

			}

		});

	}

}