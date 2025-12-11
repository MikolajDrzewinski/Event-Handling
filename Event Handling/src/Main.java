import javax.swing.*;

class Klikanie extends JFrame {

    private int licznik = 0;

    public Klikanie() {
        setTitle("Liczenie Kliknięć");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel etykieta = new JLabel("Liczba kliknięć: 0");
        JButton przycisk = new JButton("Kliknij mnie");

        przycisk.addActionListener(e -> {
            licznik++;
            etykieta.setText("Liczba kliknięć: " + licznik);
        });

        setLayout(new java.awt.FlowLayout());
        add(etykieta);
        add(przycisk);

        setVisible(true);
    }



    public static void main(String[] args) {
        new Klikanie();
    }
}
