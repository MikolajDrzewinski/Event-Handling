import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

class EventLogger extends JFrame {

    private JTextArea logArea;
    private JButton clearBtn;

    public EventLogger() {
        super("Aplikacja Diagnostyczna Zdarzeń (INF.03)");
        inicjalizacjaUI();
        obslugaZdarzen();
    }

    private void inicjalizacjaUI() {
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        logArea = new JTextArea();
        logArea.setEditable(false);
        logArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(logArea);

        clearBtn = new JButton("Wyczyść logi");

        add(new JLabel("  Klikaj w pole poniżej lub pisz na klawiaturze:"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(clearBtn, BorderLayout.SOUTH);
    }

    private void obslugaZdarzen() {
        clearBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logArea.setText("");
                dodajLog("SYSTEM: Wyczyszczono logi.");
                logArea.requestFocusInWindow();
            }
        });

        logArea.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String info = String.format("MYSZ: Kliknięcie w punkcie x=%d, y=%d", e.getX(), e.getY());
                dodajLog(info);
            }
        });

        logArea.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char znak = e.getKeyChar();
                if (Character.isLetterOrDigit(znak) || Character.isWhitespace(znak)) {
                    dodajLog("KLAWISZ: Wpisano znak: '" + znak + "'");
                }
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int odpowiedz = JOptionPane.showConfirmDialog(
                        EventLogger.this,
                        "Czy na pewno chcesz zamknąć aplikację?",
                        "Potwierdzenie",
                        JOptionPane.YES_NO_OPTION
                );

                if (odpowiedz == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private void dodajLog(String komunikat) {
        String czas = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        logArea.append("[" + czas + "] " + komunikat + "\n");
        logArea.setCaretPosition(logArea.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new EventLogger().setVisible(true);
        });
    }
}