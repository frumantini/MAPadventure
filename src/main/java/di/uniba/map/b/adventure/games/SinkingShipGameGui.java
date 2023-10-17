package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.db.GameStatus;
import di.uniba.map.b.adventure.socket.Client;
import di.uniba.map.b.adventure.socket.PluginableClient;
import di.uniba.map.b.adventure.type.CommandGUIOutput;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import di.uniba.map.b.adventure.type.CommandTypeGui;

public class SinkingShipGameGui extends JFrame {

    /**
     * Jpanel principale
     */
    private JPanel mainPanel = null;
    /**
     * Jpanel di avvio
     */
    private JPanel startPanel = null;
    /**
     * JTextArea per la stampa del testo
     */
    private JTextArea textArea = null;
    /**
     * JScrollPane per la gestione dello scroll dell'outpur
     */
    private JScrollPane scrollPane = null;
    /**
     * JTextField per l'inserimento del testo
     */
    private JTextField textField = null;
    /**
     * JPanel per contenere i loaded games
     */
    private JPanel contentPanel = null;
    /**
     * JPanel per il background dell'interfaccia
     */
    private JPanel backgroundPanel = null;
    /**
     * Image per il background dell'interfaccia
     */
    private Image backgroundImage = null;
    /**
     * Boolean per la gestione della chiusura del gioco
     */
    private boolean shouldCloseGame = false;
    /**
     * JProgressBar per la gestione del tempo
     */
    private JProgressBar liquidProgress;
    /**
     * Printer per la stampa del tempo
     */
    private Printer printer;
    /**
     * Boolean per la gestione della morte del giocatore
     */
    private boolean isDead = false;
    /**
     * Thread che rimane in ascolto per l'aggiornamento della liquidProgress
     */
    private ProgressBarListener progressBarListener = null;

    /**
     * Client per la gestione della connessione
     */
    private static PluginableClient client;

    /**
     * getter per la liquidProgress
     * @return liquidProgress
     */
    public JProgressBar getProgressBar() {
        return liquidProgress;
    }
    /**
     * Costruttore
     */
    public SinkingShipGameGui() {
        try {
            client = new Client();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        setTitle("Desert the sinking ship");
        initMainPanel();
        initStartPanel();
        setVisible(true);
    }


    /**
     * Inizializza il pannello principale
     */
    private void initMainPanel() {
        // Ottieni le dimensioni dello schermo
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int) screenSize.getWidth();
        int screenHeight = (int) screenSize.getHeight();
        JOptionPane frame = new JOptionPane();

        // Impostazioni della finestra principale
        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (shouldCloseGame) {
                    try {
                        client.closeConnection();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    System.exit(0); // Chiude il gioco solo se shouldCloseGame è true
                }
            }
        });

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (textArea != null && !isDead) {
                    int scelta = JOptionPane.showConfirmDialog(frame, "Vuoi salvare la partita?", "Salvataggio", JOptionPane.YES_NO_OPTION);
                    if (scelta == JOptionPane.YES_OPTION) {
                        try {
                            openUsernameInputDialog(e);
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    } else {
                        shouldCloseGame = true; // Imposta la variabile shouldCloseGame a false se si seleziona "No" per la conferma
                        e.getWindow().dispose(); // Chiude solo la finestra
                        try {
                            client.executeCommand("STOPTIMER");
                            progressBarListener.stopListener();
                            textField.setEditable(false);
                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                } else if(isDead){
                    shouldCloseGame = true;
                    e.getWindow().dispose();
                } else {
                    shouldCloseGame = true;
                    e.getWindow().dispose();
                }
            }
        });

        setSize(screenWidth, screenHeight);
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // Pannello principale
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        add(mainPanel);
    }

    /**
     * Apre il pannello di conferma nella chiusura del gioco
     */
    private void openUsernameInputDialog(WindowEvent e)
            throws IOException, ClassNotFoundException {
        boolean validUsername = false;

        while (!validUsername) {
            JOptionPane input = new JOptionPane();
            JTextField usernameField = new JTextField();
            Object[] message = {
                    "Username:", usernameField
            };

            int option = JOptionPane.showOptionDialog(input, message, "Inserisci Username",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                    new Object[]{"OK", "Cancel"}, "OK");

            if (option == JOptionPane.OK_OPTION) {
                String username = usernameField.getText();
                if (username.isEmpty()) {
                    JOptionPane.showMessageDialog(input, "Il campo non può essere vuoto!", "Errore", JOptionPane.ERROR_MESSAGE);
                } else {
                    client.executeCommand("STOPTIMER");
                    progressBarListener.stopListener();
                    textField.setEditable(false);
                    client.sendResourcesToServer("username:" + username);
                    client.executeCommand("SAVE");
                    validUsername = true;
                    shouldCloseGame = true;
                    e.getWindow().dispose();
                }
            } else {
                validUsername = true;
                shouldCloseGame = false;
            }
        }
    }

    /**
     * Inizializza il pannello di avvio del gioco
     */
    private void initStartPanel(){
        startPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImageIcon = new ImageIcon("resources/start.png");
                Image backgroundImage = backgroundImageIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
            }
        };
        startPanel.setLayout(null); // Imposta il layout come null per utilizzare un layout personalizzato
        mainPanel.add(startPanel, BorderLayout.CENTER);

        // Calcola le dimensioni del pannello
        int panelWidth = getWidth();
        int panelHeight = getHeight();

        // Imposta le dimensioni e la posizione del pulsante
        int buttonWidth = 250;
        int buttonHeight = 100;
        int buttonX = (panelWidth - buttonWidth) / 2; // Posiziona il pulsante al centro orizzontalmente
        int buttonY = panelHeight - (2 * buttonHeight) - 170; // Posiziona il pulsante a 100 pixel dal fondo
        int buttonY2 = panelHeight - 150 - buttonHeight; // Posiziona il pulsante a 100 pixel dal fondo
        JButton startButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImageIcon =
                        new ImageIcon("resources/newgame.png");
                Image backgroundImage = backgroundImageIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),
                        this);
            }
        };
        startButton.setBounds(buttonX, buttonY, buttonWidth, buttonHeight);
        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        startButton.addActionListener(e -> {
            try {
                startGame(); // Carica il gioco
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        });
        JButton loadGameButton = new JButton() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImageIcon =
                        new ImageIcon("resources/loadgame.png");
                Image backgroundImage = backgroundImageIcon.getImage();
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(),
                        this);
            }
        };
        loadGameButton.setBounds(buttonX, buttonY2, buttonWidth, buttonHeight);
        loadGameButton.setFont(new Font("Arial", Font.BOLD, 16));
        loadGameButton.addActionListener(e -> {
            try {
                loadGame(); // Avvia il gioco
            } catch (SQLException | ClassNotFoundException | IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        startPanel.add(startButton); // Aggiungi il pulsante al pannello di avvio
        startPanel.add(loadGameButton); // Aggiungi il pulsante al pannello di avvio
    }

    /**
     * Inizializza il pannello laterale
     */
    private void initSidePanel() throws IOException, ClassNotFoundException {
        // Immagine laterale
        ImageIcon latImage = new ImageIcon("resources/side.png");
        Image lat = latImage.getImage().getScaledInstance(760, 1900, Image.SCALE_SMOOTH);

        // Pannello laterale per le statistiche
        // Carica l'immagine di sfondo
        JPanel sidePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica l'immagine di sfondo
                try {
                    g.drawImage(lat, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        sidePanel.setPreferredSize(new Dimension(250, 0));
        sidePanel.setLayout(new BorderLayout()); // Modifica il layout in BorderLayout
        initProgressBar(sidePanel); // Inizializza la barra di avanzamento
        mainPanel.add(sidePanel, BorderLayout.WEST);
    }

    private void initProgressBar(JPanel sidePanel) throws IOException, ClassNotFoundException {
        CommandGUIOutput response;
        liquidProgress = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
        liquidProgress.setStringPainted(true);
        liquidProgress.setPreferredSize(new Dimension(50, 100));
        liquidProgress.setForeground(new Color(173, 216, 230)); // Celeste chiaro
        sidePanel.add(liquidProgress, BorderLayout.WEST);
        
        // Avvio del timer
        client.executeCommand("STARTTIMER");
        progressBarListener = new ProgressBarListener(3000);
        progressBarListener.start();
    }
    
    public void changeProgressBarColor() {
        Color color = liquidProgress.getForeground();
        int red = color.getRed();
        int green = color.getGreen();
        int blue = color.getBlue();
    
        if (blue < 255) {
            // Aumenta gradualmente il valore del blu
            liquidProgress.setForeground(new Color(red, green, blue + 1));
        }
    }
    
    /**
     * Inizializza la barra di avanzamento
     *//*
     private void initProgressBar(JPanel sidePanel)
            throws IOException, ClassNotFoundException {
        CommandGUIOutput response;
        liquidProgress = new JProgressBar(JProgressBar.VERTICAL, 0, 100);
        liquidProgress.setStringPainted(true);
        liquidProgress.setPreferredSize(new Dimension(50, 100));
        liquidProgress.setForeground(new Color(0, 0, 255)); 
        sidePanel.add(liquidProgress, BorderLayout.WEST);
        // Avvio del timer
        client.executeCommand("STARTTIMER");
        progressBarListener = new ProgressBarListener(3000);
        progressBarListener.start();
    }
    
    /**
     * cambia il colore della progressBar (da verde a rosso)
     *//*
     public void changeProgressBarColor(){
        Color color = liquidProgress.getForeground();
        int red = color.getRed();
        int green = color.getGreen();
        if(red < 255)
            liquidProgress.setForeground(new Color(red + 5, 200, 0));
        else
            liquidProgress.setForeground(new Color(red, green - 3, 0));
    } */
    /**
 * Cambia il colore della progressBar da celeste chiaro a blu progressivamente.
 
public void changeProgressBarColor() {
    Color color = liquidProgress.getForeground();
    int red = color.getRed();
    int green = color.getGreen();
    int blue = color.getBlue();

    if (blue > 0) {
        // Incrementa il valore del canale blu per ottenere un colore più blu
        liquidProgress.setForeground(new Color(red, green, blue - 10));
    } else if (green < 255) {
        // Incrementa il valore del canale verde se il blu ha raggiunto il massimo
        liquidProgress.setForeground(new Color(red, green + 5, blue));
    } else if (red < 255) {
        // Incrementa il valore del canale rosso se il blu e il verde hanno raggiunto il massimo
        liquidProgress.setForeground(new Color(red + 5, green, blue));
    }
}*/
    
    /**
     * Aggiorna il valore della barra di avanzamento
     */
    public void incrementProgressBarValue(int progress)
            throws IOException, ClassNotFoundException {
        this.getProgressBar().setValue(progress);
        if (progress % 25 == 0 && progress != 100 && progress != 0) {
            this.appendAreaText("Il livello dell'acqua sta salendo!\n");
        } else if (progress == 100) {
            this.die("");
        }
        this.changeProgressBarColor();
    }

    /**
     * Inizializza il pannello di sfondo
     */
    private void initBackgroundPanel(int roomId){
        ImageIcon backgroundImageIcon = new ImageIcon("resources/" +roomId+".png");
        backgroundImage = backgroundImageIcon.getImage().getScaledInstance(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight(), Image.SCALE_SMOOTH);
        // Creazione del pannello per l'immagine sopra l'inputPanel e a sinistra del sidePanel
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica l'immagine di sfondo
                try {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        backgroundPanel.setPreferredSize(new Dimension(getWidth()-250, 0));
        backgroundPanel.setLayout(new BorderLayout());
        mainPanel.add(backgroundPanel, BorderLayout.EAST);
    }

    /**
     * Inizializza il pannello di sfondo nel caso del caricamento di una partita salvata
     */
    private void initLoadGameBackgroundPanel(){
        ImageIcon backgroundImageIcon = new ImageIcon("resources/start.png");
        backgroundImage = backgroundImageIcon.getImage().getScaledInstance(backgroundImageIcon.getIconWidth(), backgroundImageIcon.getIconHeight(), Image.SCALE_SMOOTH);
        // Creazione del pannello per l'immagine sopra l'inputPanel e a destra del sidePanel
        backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Carica l'immagine di sfondo
                try {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        mainPanel.add(backgroundPanel, BorderLayout.CENTER);
    }

    /**
     * Inizializza il pannello di output
     */
    private void initOutputArea() throws IOException, ClassNotFoundException {

        // Crea la JTextArea
        Color background = new Color(0, 20, 70, 150); // Colore di sfondo con opacità ridotta (valori RGB: 0, 0, 0, opacità)
        textArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(background);
                g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        printer= new Printer(textArea, 10);
        String firstDescription = "Ti svegli all'interno di quella che sembra essere una cabina di una nave. Non hai idea di come ci sei arrivato," 
        + " ma un brutta sensazione ti pervade. Provi ad aprire la porta, ma ti accorgi che è chiusa a chiave. Cerca di uscirne vivo!\n\n Digita HELP per visualizzare i comandi disponibili\n\n";
        performCommand(new CommandGUIOutput(CommandTypeGui.DISPLAY_TEXT,firstDescription));
        textArea.setFont(new Font("Consolas", Font.PLAIN, 18));
        textArea.setEditable(false); // Rendi la JTextArea non modificabile
        textArea.setOpaque(false); // Rendi lo sfondo trasparente
        textArea.setForeground(Color.WHITE); // Colore del testo
        textArea.setMaximumSize(new Dimension(Integer.MAX_VALUE, getHeight()/2)); // Imposta l'altezza massima della JTextArea
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true); // Rendi il testo a capo quando raggiunge il bordo della JTextArea

        // Crea la JScrollPane per avvolgere la JTextArea
        scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(scrollPane.getPreferredSize().width, getHeight()/2));
        scrollPane.setOpaque(false); // Rendi lo sfondo trasparente
        scrollPane.getViewport().setOpaque(false); // Rendi lo sfondo del viewport trasparente
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        backgroundPanel.add(scrollPane, BorderLayout.NORTH);
        /*backgroundPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                scrollPane.setVisible(!scrollPane.isVisible());
            }
        });*/
    }

    /**
     * Inizializza il pannello di output nel caso del caricamento di una partita salvata
     */
    private void initOutputLoadedGamesArea(){

        Color backgroundColor = new Color(0, 0, 0, 150); // Colore di sfondo con opacità ridotta (valori RGB: 0, 0, 0, opacità)

        contentPanel = new JPanel(); // Pannello principale che conterrà i pannelli delle righe
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS)); // Layout per allineare verticalmente gli elementi
        backgroundPanel.add(contentPanel, BorderLayout.CENTER);

        scrollPane = new JScrollPane(){
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(backgroundColor);
                g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                super.paintComponent(g2);
                g2.dispose();
            }
        };
        scrollPane.setPreferredSize(new Dimension(getWidth(),getHeight()));
        scrollPane.setOpaque(false); // Rendi lo sfondo trasparente
        scrollPane.getViewport().setOpaque(false); // Rendi lo sfondo del viewport trasparente
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        contentPanel.add(scrollPane, BorderLayout.NORTH);
    }

    /**
     * Inizializza il pannello di input
     */
    private void initInputArea(){
        // Aggiungi la JTextField al pannello di sfondo
        textField = new JTextField();
        textField.setOpaque(false); // Rendi lo sfondo trasparente
        textField.setPreferredSize(new Dimension(getWidth()-150, 40));
        textField.setForeground(Color.CYAN); // Colore del testo
        textField.setFont(new Font("Consolas", Font.BOLD, 16)); // Font del testo
        backgroundPanel.add(textField, BorderLayout.SOUTH);

        textField.addActionListener(e -> {
            CommandGUIOutput responseToGUI;
            Printer printer = new Printer(textArea, 10);
            printer.setDelay(10);
            String inputText = textField.getText(); // Ottieni il testo inserito nella JTextField
            try {
                responseToGUI=client.executeCommand(inputText); // Esegui il comando inserito nella JTextField
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            try {
                performCommand(responseToGUI); // Stampa la risposta carattere per carattere nella JTextArea
            } catch (IOException | ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
            textField.setText(""); // Resetta il contenuto della JTextField
            scrollPane.setVisible(true); // Mostra la JScrollPane
            textArea.setCaretPosition(textArea.getDocument().getLength()); // Scrolla la JTextArea fino alla fine del testo
        });

        // Imposta la JTextArea per lo scorrimento automatico
        DefaultCaret caret = (DefaultCaret) textArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
    }


    /**
     * setta il background
     * @param path path dell'immagine
     */
    public void setBackgroundImageFromPath(String path) {
        this.backgroundImage = new ImageIcon(path).getImage();
        backgroundPanel.repaint();
    }

    /**
     * esegue il comando
     * @param command Comando da eseguire
     */
    public void performCommand(CommandGUIOutput command)
            throws IOException, ClassNotFoundException {
        switch (command.getType())
        {
            case CHANGE_ROOM:
                this.setBackgroundImageFromPath(command.getResource());
                appendAreaText(command.getText());
                break;
            case DISPLAY_TEXT:
                appendAreaText(command.getText());
                break;
            case LOAD_GAME:
                startLoadedGame( Integer.parseInt(command.getResource()));
                break;
            case END:
                die(command.getText());
                break;
            case HELP:
                appendAreaText(command.getText());
                break;
            case INCREMENT_PB_VALUE:
                incrementProgressBarValue(Integer.parseInt(command.getResource()));
                break;
        }
    }

    /**
     * scrve il testo nella text area
     * @param text testo da scrivere
     */
    public void appendAreaText(String text) {
        printer.printText(text);
    }

    /**
     * stampa a video il messaggio di help
     * @return messaggio di help
     */
    public String printHelp(){
        return ("In questo goco devi cercare di scappare dalla nave in cui ti risvegli prima che essa affondi e ti porti giù con sè.\n" +
                "Per portare a termire la tua missione devi essere in grado di osservare attentamente i dintorni ed essere il più rapido possibile,"+
                "cercando di non tralasciare nulla indietro...\n" +
                "\n" +
                "Per muoverti usa:\n" +
                "\n" +
                "- NORD, SUD, EST, OVEST oppure \n- N, S, E, O\n" +
                "\n" +
                "Ti darò la descrizione completa di ogni stanza non appena vi entri" /*+ "la prima volta che vi entri,\n" +
                "poi darò solo una descrizione breve"*/ + ". Se vuoi rileggere la descrizione della stanza in cui ti trovi dimmi:\n" +
                "\n" +
                "- OSSERVA\n" +
                "\n" +
                "Comandi fondamentali per interagire con gli oggetti presenti:\n" +
                "\n" +
                "- PRENDI oggetto\n" +
                "- USA oggetto\n" +
                "- SPOSTA oggetto\n" +
                "- ISPEZIONA oggetto\n" +
                "- PREMI oggetto\n" +
                "- SBLOCCA oggetto \"password\"\n" +
                "\n" +
                "Altri comandi che potrebbero esserti d'aiuto:\n" +
                "\n" +
                "- INV elenca gli oggetti nel tuo inventario\n" +
                "- HELP presenta lo scopo del gioco e i comandi disponibili.\n");
    }

    /**
     * fa partire il gioco inizializzando tutte le componenti
     */
    private void startGame() throws IOException, ClassNotFoundException {
        mainPanel.remove(startPanel);
        initBackgroundPanel(0);
        initOutputArea();
        initInputArea();
        initSidePanel();
        revalidate();
    }

    /**
     * fa partire il gioco salvato inizializzando tutte le componenti
     */
    private void startLoadedGame(int id)
            throws IOException, ClassNotFoundException {
        initSidePanel();
        initBackgroundPanel(id);
        initOutputArea();
        initInputArea();
        revalidate();
    }

    /**
     * Carica le partite salvate
     */
    private void loadGame()
            throws SQLException, IOException, ClassNotFoundException {
        mainPanel.remove(startPanel);
        initLoadGameBackgroundPanel();
        initOutputLoadedGamesArea();
        showSavedGames();
        revalidate();
    }

    /**
     * mostra le partite salvate !!!!!!!!!!!!!!!!! METTI AL CENTRO E IN BASSO
     */
    private void showSavedGames()
            throws IOException, ClassNotFoundException {

        List<GameStatus> savedGames =
                (List<GameStatus>) client.getResourcesFromServer("resources:GETSAVES");

        Color backgroundColor = new Color(0, 20, 70, 150); // Colore di sfondo con opacità ridotta
        scrollPane.setMaximumSize(new Dimension(Integer.MAX_VALUE, savedGames.size() * 50));
        scrollPane.setViewportView(new SavedGame(savedGames, mainPanel, contentPanel) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(backgroundColor);
                g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                super.paintComponent(g2);
                g2.dispose();
            }
        });

        // Aggiorna la visualizzazione della scroll pane
        scrollPane.revalidate();
        scrollPane.repaint();
    }

    /**
     * implementazione fine gioco
     * @param command comando da eseguire
     */
    public void die(String command) throws IOException, ClassNotFoundException {
        textField.setEditable(false);
        progressBarListener.stopListener();
        appendAreaText(command + "Le onde ti sommergono, non c'è più nulla che tu possa fare. \n\nGAME OVER");
        isDead = true;
        client.executeCommand("STOPTIMER");
    }
    

    /**
     * Pannello che mostra le partite salvate
     */
    public class SavedGame extends JPanel {
        /**
         * Create the panel.
         */
        public SavedGame(List<GameStatus> savedGames, JPanel mainPanel, JPanel contentPanel) {
            setLayout(new GridLayout(savedGames.size(), 1)); // Imposta il layout con una riga per ogni partita salvata
            this.setOpaque(false);
            this.setPreferredSize(new Dimension(this.getPreferredSize().width, savedGames.size() * 50)); // Imposta la dimensione del pannello
            for (GameStatus game : savedGames) {
                Color background = new Color(0, 0, 0, 0);
                JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Imposta il layout con allineamento sinistro
                panel.setOpaque(false); // Imposta lo sfondo trasparente
                panel.setPreferredSize(new Dimension(panel.getPreferredSize().width, 50)); // Imposta la dimensione del pannello
                String rowString = game.getUsername() + " - " + game.getLastRoomId() + " - " + game.getTime().toString();
                JLabel rowLabel = new JLabel(rowString) {
                    @Override
                    protected void paintComponent(Graphics g) {
                        Graphics2D g2 = (Graphics2D) g.create();
                        g2.setColor(background);
                        g2.fillRect(0, 0, getWidth(), getHeight()); // Riempie l'area con il colore di sfondo
                        super.paintComponent(g2);
                        g2.dispose();
                    }
                };
                rowLabel.setOpaque(false);
                rowLabel.setFont(new Font("Consolas", Font.BOLD, 16));
                rowLabel.setForeground(Color.WHITE);
                panel.add(rowLabel);

                // Aggiunge un listener per il click del mouse e per il passaggio sopra con il mouse
                panel.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseEntered(MouseEvent e) { // Quando passi sopra con il mouse
                        panel.setOpaque(true);
                        Color hoverColor = new Color(70, 70, 70, 255);
                        panel.setBackground(hoverColor); // Imposta il colore di sfondo quando passi sopra con il mouse
                        panel.repaint(); // Forza l'aggiornamento grafico del pannello
                    }

                    @Override
                    public void mouseExited(MouseEvent e) { // Quando esci con il mouse
                        panel.setOpaque(false); // Ripristina l'opacità del pannello a false
                        panel.setBackground(new Color(0, 0, 0, 0)); // Ripristina il colore di sfondo trasparente
                        panel.repaint(); // Forza l'aggiornamento grafico del pannello
                    }

                    @Override
                    public void mouseClicked(MouseEvent e) { // Quando clicchi sul pannello
                        mainPanel.remove(contentPanel);
                        mainPanel.revalidate();
                        mainPanel.repaint();
                        try {
                            client.sendResourcesToServer("username:"+game.getUsername());
                            CommandGUIOutput response = client.executeCommand("LOADGAME"); // Carica la partita
                            performCommand(response); // Esegue il comando

                        } catch (IOException | ClassNotFoundException ex) {
                            throw new RuntimeException(ex);
                        }
                    }
                });
                add(panel); // Aggiunge il pannello alla lista
            }
        }
    }

    /**
     * Classe per la stampa del testo con un effetto di scrittura
     */
    public static class Printer {
        /**
         * textArea
         */
        private final JTextArea textArea;
        /**
         * delay
         */
        private int delay;
        /**
         * Costruttore della classe
         * @param textArea textArea
         * @param delay delay
         */
        public Printer(JTextArea textArea, int delay) {
            this.textArea = textArea;
            this.delay = delay;
        }

        /**
         * Metodo che imposta il delay
         * @param delay delay
         */
        public void setDelay(int delay) {
            this.delay = delay;
        }
        /**
         * Metodo che stampa il testo con un effetto di scrittura
         * @param inputText
         */
        public void printText(String inputText) {
            SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
                /**
                 * Metodo che separa il testo in caratteri e li stampa uno alla volta
                 * @return
                 * @throws Exception
                 */
                @Override
                protected Void doInBackground() throws Exception {
                    String[] chars = inputText.split("");
                    for (String c : chars) {
                        publish(c);
                        Thread.sleep(delay);
                    }
                    return null;
                }

                /**
                 * Metodo che aggiunge il testo alla JTextArea
                 * @param chunks intermediate results to process
                 *
                 */
                @Override
                protected void process(java.util.List<String> chunks) {
                    for (String c : chunks) {
                        textArea.append(c); // Aggiungi il testo alla JTextArea, aggiungendo un a capo
                    }
                }
            };

            worker.execute();
            textArea.append("\n"); // Aggiungi un a capo alla fine del testo
        }

    }

    /**
     * Classe che implementa un listener per la progress bar
     */
    public class ProgressBarListener extends Thread{

        /**
         * Delay tra un incremento e l'altro
         */
        private int delay;
        /**
         * Variabile per controllare se il listener è in esecuzione
         */
        private volatile boolean isRunning = true;

        /**
         * Costruttore del listener
         * @param delay delay
         */
        public ProgressBarListener(int delay){
            this.delay = delay;
        }

        /**
         * Metodo che viene eseguito quando il thread viene avviato
         */
        public void run(){
            while (isRunning){
                try {
                    Thread.sleep(delay);
                    CommandGUIOutput response = client.executeCommand("INCREMENTPBVALUE");
                    try{
                        setDelay(Integer.parseInt(response.getText()));
                    }catch (NumberFormatException ignored){}
                    performCommand(response);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        /**
         * Metodo per fermare il listener
         */
        public void stopListener(){
            isRunning = false;
        }

        /**
         * Metodo per impostare il delay
         * @param delay delay
         */
        public void setDelay(int delay){
            this.delay = delay;
        }
    }

    /**
     * Metodo main
     * @param args argomenti
     */
    public static void main(String[] args) {
        SinkingShipGameGui gui = new SinkingShipGameGui();
    }

}

