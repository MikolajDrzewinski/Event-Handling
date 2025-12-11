import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

class LoggerZdarzen extends JFrame {

    private JTextArea logArea;
    private Map<Character, Integer> licznikZnakow = new HashMap<>();

    public LoggerZdarzen() {
        setTitle("Aplikacja Diagnostyczna Zdarzeń");
        setSize(500, 400);
        setLocationRelativeTo(null);

        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(logArea);

        JButton clearBtn = new JButton("Wyczyść logi");

        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.setText("");
                licznikZnakow.clear();
            }
        });

        JPanel panel = new JPanel();
        panel.add(clearBtn);

        add(scroll, BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        logArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                log("MYSZ: Kliknięcie w: " + e.getX() + ", " + e.getY());
            }
        });

        logArea.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char znak = e.getKeyChar();
                licznikZnakow.put(znak, licznikZnakow.getOrDefault(znak, 0) + 1);
                log("KLAWISZ: Wpisano: '" + znak + "' | Liczba wpisów: " + licznikZnakow.get(znak));
            }

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {}
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int decyzja = JOptionPane.showConfirmDialog(
                        LoggerZdarzen.this,
                        "Czy na pewno chcesz zamknąć aplikację?",
                        "Potwierdzenie",
                        JOptionPane.YES_NO_OPTION
                );

                if (decyzja == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        setVisible(true);
    }

    private void log(String txt) {
        logArea.append(txt + "\n");
    }

    public static void main(String[] args) {
        new LoggerZdarzen();
    }
}
