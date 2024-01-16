import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.table.DefaultTableModel;

public class student_records {
    public static void studentInfo() {
        JFrame f = new JFrame("Student Record");
        JLabel l1, l2, l3, l4, l5, l6;
        JTextField t1, t2, t3, t4, t5;
        JComboBox<String> c1;
        JButton b1, b2, b3;

        l1 = new JLabel("Name:");
        l1.setBounds(50, 70, 100, 30);
        l1.setFont(new Font("Algerian", Font.PLAIN, 20));

        l2 = new JLabel("Class:");
        l2.setBounds(50, 140, 120, 30);
        l2.setFont(new Font("Algerian", Font.PLAIN, 20));

        l3 = new JLabel("Rollno:");
        l3.setBounds(50, 210, 120, 30);
        l3.setFont(new Font("Algerian", Font.PLAIN, 20));

        l4 = new JLabel("Date of Birth:");
        l4.setBounds(50, 280, 150, 30);
        l4.setFont(new Font("Algerian", Font.PLAIN, 20));

        l5 = new JLabel("Mobile No:");
        l5.setBounds(50, 350, 120, 30);
        l5.setFont(new Font("Algerian", Font.PLAIN, 20));

        l6 = new JLabel("Course");
        l6.setBounds(50, 420, 150, 30);
        l6.setFont(new Font("Algerian", Font.PLAIN, 20));

        t1 = new JTextField();
        t1.setBounds(300, 70, 150, 30);

        t2 = new JTextField();
        t2.setBounds(300, 140, 150, 30);

        t3 = new JTextField();
        t3.setBounds(300, 210, 150, 30);

        t4 = new JTextField();
        t4.setBounds(300, 280, 150, 30);

        t5 = new JTextField();
        t5.setBounds(300, 350, 150, 30);

        String[] s1 = { "BCA", "MCA", "BA", "MA", "Bcom", "Mcom", "Bsc", "Msc" };

        c1 = new JComboBox<>(s1);
        c1.setBounds(300, 420, 150, 30);

        b1 = new JButton("Save");
        b1.setBounds(100, 550, 70, 30);

        b2 = new JButton("Exit");
        b2.setBounds(750, 550, 70, 30);

        b3 = new JButton("Display");
        b3.setBounds(400, 550, 100, 30);

        String[] columns = { "Name", "class", "Roll No", "Date of Birth", "Mobile No", "Course" };
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == b1) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("student.txt", true))) {
                        FileWriter w = new FileWriter("student.txt", true);
                        String s1 = t1.getText();
                        String s2 = t2.getText();
                        String s3 = t3.getText();
                        String s4 = t4.getText();
                        String s5 = t5.getText();
                        String s6 = c1.getSelectedItem() + "";
                        writer.write(String.format("%-20s%-20s%-20s%-20s%-20s%-20s%n", s1, s2, s3, s4, s5, s6));
                        w.close();
                        tableModel.addRow(new Object[] { s1, s2, s3, s4, s5, s6 });

                        t1.setText("");
                        t2.setText("");
                        t3.setText("");
                        t4.setText("");
                        t5.setText("");
                        c1.setSelectedIndex(0);

                        JOptionPane.showMessageDialog(f, "Successfully Saved The Details");
                    } catch (Exception ae) {
                        System.out.println(ae);
                    }
                }
            }
        });

        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                f.dispose();
            }
        });

        b3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try (BufferedReader reader = new BufferedReader(new FileReader("student.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        // Split the line into individual fields
                        String[] fields = line.split("\\s+");

                        // Add row to the table model
                        tableModel.addRow(fields);
                    }

                    

                    JFrame displayFrame = new JFrame("Student Records");
                    JTable table = new JTable(tableModel);

                    // Add the text area to the frame
                    JScrollPane scrollPane = new JScrollPane(table);
                    displayFrame.add(scrollPane);

                    // Set frame properties
                    displayFrame.setSize(400, 300);
                    displayFrame.setLocationRelativeTo(null); // Center the frame
                    displayFrame.setVisible(true);

                } catch (Exception ae) {
                    System.out.println(ae);
                }
            }
        });

        f.add(l1);
        f.add(l2);
        f.add(l3);
        f.add(l4);
        f.add(l5);
        f.add(l6);
        f.add(t1);
        f.add(t2);
        f.add(t3);
        f.add(t4);
        f.add(t5);
        f.add(c1);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.setLayout(null);
        f.setSize(900, 700);
        f.setVisible(true);
    }

    public static void main(String args[]) {
        studentInfo();
    }
}