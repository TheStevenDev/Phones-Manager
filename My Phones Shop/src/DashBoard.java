import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class DashBoard extends JFrame implements ActionListener {
    private Color myGreen = new Color(93,183,91);
    private Color myGray = new Color(37,41,46);
    private Font buttonsFont = new Font("Arial",Font.PLAIN,18);

    //--//
    private JLabel titleLabel = new JLabel("MY PHONES SHOP");
    private JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home") + "/Desktop");
    private  FileNameExtensionFilter filter = new FileNameExtensionFilter("File CSV", "csv");

    //--//

    private JButton addButton = new JButton("ADD PHONE");
    private JButton addButtonCSV = new JButton("ADD PHONES (CSV)");
    private JButton viewButton = new JButton("VIEW PHONES");
    private JButton removePhone = new JButton("REMOVE PHONE");

    //--//
    private MyArray magazine;
    private int magazineSize=1;

    public DashBoard(int magazineSize){
        if(magazineSize>0) this.magazineSize = magazineSize;
        magazine = new MyArray(magazineSize);

        this.setSize(1200,800);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("My Phones Shop - Phones: "+magazine.getNumPhones());
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(myGreen);


        titleLabel.setBounds(182, 80, 837, 92);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial",Font.BOLD,50));

        addButton.setBounds(344,254,240,64);
        addButton.setFont(buttonsFont);
        addButton.setBackground(myGray);
        addButton.setForeground(Color.WHITE);
        addButton.setOpaque(true);
        addButton.setFocusable(false);
        addButton.setBorder(null);
        addButton.addActionListener(this);

        addButtonCSV.setBounds(600,254,240,64);
        addButtonCSV.setFont(buttonsFont);
        addButtonCSV.setBackground(myGray);
        addButtonCSV.setForeground(Color.WHITE);
        addButtonCSV.setOpaque(true);
        addButtonCSV.setFocusable(false);
        addButtonCSV.setBorder(null);
        addButtonCSV.addActionListener(this);

        viewButton.setBounds(480,355,240,64);
        viewButton.setFont(buttonsFont);
        viewButton.setBackground(myGray);
        viewButton.setForeground(Color.WHITE);
        viewButton.setOpaque(true);
        viewButton.setFocusable(false);
        viewButton.setBorder(null);
        viewButton.addActionListener(this);

        removePhone.setBounds(480,456,240,64);
        removePhone.setFont(buttonsFont);
        removePhone.setBackground(myGray);
        removePhone.setForeground(Color.WHITE);
        removePhone.setOpaque(true);
        removePhone.setFocusable(false);
        removePhone.setBorder(null);
        removePhone.addActionListener(this);



        this.add(removePhone);
        this.add(viewButton);
        this.add(addButton);
        this.add(titleLabel);
        this.add(addButtonCSV);
    }

    private Phone askPhone(){
        String brand = JOptionPane.showInputDialog(this,"Brand: ", "My Phones Shop",JOptionPane.PLAIN_MESSAGE);
        String name = JOptionPane.showInputDialog(this,"Name: ", "My Phones Shop",JOptionPane.PLAIN_MESSAGE);
        double price = Double.parseDouble(JOptionPane.showInputDialog(null,"Price: ", "My Phones Shop",JOptionPane.PLAIN_MESSAGE));

        return new Phone(brand,name,price);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==addButton){
            magazine.add(askPhone());
            magazine.savePhones();
            this.setTitle("My Phones Shop - Phones: "+magazine.getNumPhones());
        }

        if(e.getSource()==removePhone){
            magazine.remove(askPhone());
            this.setTitle("My Phones Shop - Phones: "+magazine.getNumPhones());
        }

        if(e.getSource()==viewButton){
            JOptionPane.showMessageDialog(this, magazine.toString(), "My Phones", JOptionPane.PLAIN_MESSAGE);
        }

        if(e.getSource()==addButtonCSV){
            fileChooser.setFileFilter(filter);
            int returnValue = fileChooser.showOpenDialog(this);

            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFilePath = fileChooser.getSelectedFile().getPath();
                File selectedFile = new File(selectedFilePath);
                magazine.importPhones(selectedFile);
                magazine.savePhones();
            }
            this.setTitle("My Phones Shop - Phones: "+magazine.getNumPhones());

        }

    }


}
