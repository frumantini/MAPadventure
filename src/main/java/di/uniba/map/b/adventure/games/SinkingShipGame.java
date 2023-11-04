package di.uniba.map.b.adventure.games;

import di.uniba.map.b.adventure.Engine;
import di.uniba.map.b.adventure.GameDescription;
import di.uniba.map.b.adventure.RoomDescription;
import di.uniba.map.b.adventure.parser.ParserOutput;
import di.uniba.map.b.adventure.type.AdvObject;
import di.uniba.map.b.adventure.type.AdvObjectContainer;
import di.uniba.map.b.adventure.type.Command;
import di.uniba.map.b.adventure.type.CommandType;
import di.uniba.map.b.adventure.type.Room;

import java.io.File; //file audio
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe GameDescription. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende GameDescription, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (nextMove) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author fra
 */
public class SinkingShipGame extends GameDescription {

    /**
     * Oggetto di tipo Engine.
     */
    private Engine engine;

    /**
     * Costruttore della classe SinkingShipGame.
     * 
     * @throws IOException
     */
    public SinkingShipGame() throws IOException {
        super();
    }

    /**
     * Metodo che inizializza i comandi.
     */
    private void setCommands() throws Exception {

        Command inventory = new Command(CommandType.INVENTORY, "inventario");
        inventory.setAlias(new String[] { "inventary", "catalogo", "repertorio", "zaino", "borsa", "inv" });
        getCommands().add(inventory);

        Command nord = new Command(CommandType.NORD, "nord");
        nord.setAlias(new String[] { "n", "N", "Nord", "NORD" });
        getCommands().add(nord);
        Command sud = new Command(CommandType.SOUTH, "sud");
        sud.setAlias(new String[] { "s", "S", "Sud", "SUD" });
        getCommands().add(sud);
        Command est = new Command(CommandType.EAST, "est");
        est.setAlias(new String[] { "e", "E", "Est", "EST" });
        getCommands().add(est);
        Command ovest = new Command(CommandType.WEST, "ovest");
        ovest.setAlias(new String[] { "o", "O", "Ovest", "OVEST" });
        getCommands().add(ovest);

        Command end = new Command(CommandType.END, "end");
        end.setAlias(new String[] { "end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit" });
        getCommands().add(end);
        Command sink = new Command(CommandType.SINK, "sink");
        sink.setAlias(new String[] { "fuga" });
        getCommands().add(sink);
        Command stab = new Command(CommandType.STAB, "io");
        stab.setAlias(new String[] { "io", "me" });
        getCommands().add(stab);
        Command win = new Command(CommandType.WIN, "phi");
        win.setAlias(new String[] { "Phi", "phi", "lei" });
        getCommands().add(win);
        Command help = new Command(CommandType.HELP, "help");
        help.setAlias(new String[] { "HELP", "aiuto", "comandi", "help", "istruzioni" });
        getCommands().add(help);
        Command look = new Command(CommandType.LOOK_AT, "osserva");
        look.setAlias(new String[] { "guarda", "vedi", "trova", "cerca", "descrivi" });
        getCommands().add(look);
        Command map = new Command(CommandType.MAP, "mappa");
        map.setAlias(new String[] { "MAPPA", "mappa", "usa mappa" });

        Command pickup = new Command(CommandType.PICK_UP, "raccogli");
        pickup.setAlias(new String[] { "prendi" });
        getCommands().add(pickup);
        Command use = new Command(CommandType.USE, "usa");
        use.setAlias(new String[] { "utilizza", "attiva" });
        getCommands().add(use);
        Command push = new Command(CommandType.PUSH, "premi");
        push.setAlias(new String[] { "spingi", "attiva" });
        getCommands().add(push);
        Command inspect = new Command(CommandType.INSPECT, "ispeziona");
        inspect.setAlias(new String[] { "ispeziona", "esamina" });
        getCommands().add(inspect);
        Command move = new Command(CommandType.MOVE, "sposta");
        move.setAlias(new String[] { "sposta", "muovi" });
        getCommands().add(move);
        Command unlock = new Command(CommandType.UNLOCK, "sblocca");
        unlock.setAlias(new String[] { "sblocca" });
        getCommands().add(unlock);

        Command saveGame = new Command(CommandType.SAVE, "SAVE");
        saveGame.setAlias(new String[] { "SAVE", "save" });
        getCommands().add(saveGame);
        Command incrementPbValue = new Command(CommandType.INCREMENT_PB_VALUE, "INCREMENTPBVALUE");
        incrementPbValue.setAlias(new String[] { "INCREMENTPBVALUE", "incrementpbvalue" });
        getCommands().add(incrementPbValue);
        Command getSaves = new Command(CommandType.GET_SAVES, "GETSAVES");
        getSaves.setAlias(new String[] { "GETSAVES", "getsaves" });
        getCommands().add(getSaves);
        Command startTimer = new Command(CommandType.START_TIMER, "STARTTIMER");
        startTimer.setAlias(new String[] { "STARTTIMER", "starttimer" });
        getCommands().add(startTimer);
        Command stopTimer = new Command(CommandType.STOP_TIMER, "STOPTIMER");
        stopTimer.setAlias(new String[] { "STOPTIMER", "stoptimer" });
        getCommands().add(stopTimer);
        
    }

    /**
     * Inizializza le stanze del gioco e definisce la mappa.
     */
    private void setRooms() {
        String namesFilePath = "resources/names.txt";
        String descriptionsFilePath = "resources/descriptions.txt";

        try {
            RoomDescription info = new RoomDescription(namesFilePath, descriptionsFilePath);

            Room cabinA = new Room(0, info.getName(0), info.getDescription(0));
            Room cabinBed = new Room(19, info.getName(25), info.getDescription(25));
            Room floodedCabin = new Room(1, info.getName(24), info.getDescription(24));
            floodedCabin.setLocked(true);
            Room hallway3 = new Room(2, info.getName(1), info.getDescription(1));
            Room cabinB = new Room(2, info.getName(2), info.getDescription(2));
            Room lobby3 = new Room(3, info.getName(3), info.getDescription(3));
            Room lockedLobby = new Room(41, info.getName(4), info.getDescription(4));
            Room bookshelf = new Room(41, info.getName(5), info.getDescription(5));
            bookshelf.setLocked(true);
            Room hallway2 = new Room(5, info.getName(6), info.getDescription(6));
            hallway2.setLocked(true);
            Room darkRoom = new Room(61, info.getName(7), info.getDescription(7));
            darkRoom.setDark(true); //non credo che serva.SI SERVE
            Room phi = new Room(18, info.getName(8), info.getDescription(8));
            phi.setLocked(true);
            Room kitchen = new Room(62, info.getName(9), info.getDescription(9));
            Room infirmary = new Room(7, info.getName(10), info.getDescription(10));
            Room table = new Room(7, info.getName(11), info.getDescription(11));
            Room tortureRoom = new Room(8, info.getName(12), info.getDescription(12));
            Room study = new Room(9, info.getName(13), info.getDescription(13));
            Room laboratory = new Room(10, info.getName(14), info.getDescription(14));
            laboratory.setLocked(true);
            Room lobby2 = new Room(42, info.getName(15), info.getDescription(15));
            Room goodsLift = new Room(11, info.getName(16), info.getDescription(16));
            goodsLift.setLocked(true);
            Room bar = new Room(12, info.getName(17), info.getDescription(17));
            Room controlRoom = new Room(13, info.getName(18), info.getDescription(18));
            Room rustyDoor = new Room(13, info.getName(19), info.getDescription(19));
            Room cabin1 = new Room(14, info.getName(20), info.getDescription(20));
            cabin1.setLocked(true);
            Room mainRoom = new Room(15, info.getName(21), info.getDescription(21));
            Room outside = new Room(16, info.getName(22), info.getDescription(22));
            Room helicopter = new Room(17, info.getName(23), info.getDescription(23));

            cabinA.setNorth(cabinBed);
            cabinBed.setSouth(floodedCabin);
            floodedCabin.setWest(hallway3);
            floodedCabin.setSouth(cabinBed);
            hallway3.setNorth(cabinB);
            hallway3.setEast(lobby3);
            cabinB.setSouth(hallway3);
            lobby3.setNorth(lockedLobby);
            lockedLobby.setNorth(hallway2);
            lockedLobby.setSouth(lobby3);
            lockedLobby.setEast(bookshelf);
            bookshelf.setSouth(lockedLobby);
            bookshelf.setNorth(laboratory);
            hallway2.setEast(tortureRoom);
            hallway2.setWest(darkRoom);
            hallway2.setSouth(lockedLobby);
            darkRoom.setSouth(phi);
            phi.setWest(kitchen);
            kitchen.setWest(infirmary);
            kitchen.setSouth(hallway2);
            infirmary.setNorth(table);
            infirmary.setSouth(kitchen);
            table.setSouth(infirmary);
            tortureRoom.setNorth(study);
            tortureRoom.setSouth(hallway2);
            study.setSouth(tortureRoom);
            laboratory.setSouth(lobby2);
            lobby2.setSouth(laboratory);
            lobby2.setEast(hallway2);
            lobby2.setNorth(goodsLift);
            goodsLift.setWest(bar);
            goodsLift.setEast(outside);
            goodsLift.setSouth(lobby2);
            bar.setSouth(goodsLift);
            bar.setNorth(controlRoom);
            controlRoom.setWest(rustyDoor);
            controlRoom.setSouth(bar);
            rustyDoor.setNorth(cabin1);
            rustyDoor.setSouth(controlRoom);
            cabin1.setSouth(controlRoom);
            cabin1.setEast(mainRoom);
            mainRoom.setSouth(cabin1);
            outside.setEast(helicopter);

            getRooms().add(cabinA);
            getRooms().add(cabinBed);
            getRooms().add(floodedCabin);
            getRooms().add(hallway3);
            getRooms().add(cabinB);
            getRooms().add(lobby3);
            getRooms().add(lockedLobby);
            getRooms().add(bookshelf);
            getRooms().add(hallway2);
            getRooms().add(darkRoom);
            getRooms().add(phi);
            getRooms().add(kitchen);
            getRooms().add(infirmary);
            getRooms().add(table);
            getRooms().add(tortureRoom);
            getRooms().add(study);
            getRooms().add(laboratory);
            getRooms().add(lobby2);
            getRooms().add(goodsLift);
            getRooms().add(bar);
            getRooms().add(controlRoom);
            getRooms().add(rustyDoor);
            getRooms().add(cabin1);
            getRooms().add(mainRoom);
            getRooms().add(outside);
            getRooms().add(helicopter);

            AdvObject lantern = new AdvObject(1, "Lanterna",
                    "Una lanterna spenta. Servirebbe qualcosa per accenderla.");
            lantern.setAlias(new String[] { "lucerna", "lampada", "lume", "lanterna" });
            cabinA.getObjects().add(lantern);

            AdvObject pillow = new AdvObject(2, "Cuscino", "Un cuscino rovinato.");
            pillow.setAlias(new String[] { "cuscino", "guanciale" });
            pillow.setPickupable(false);
            pillow.setMoveable(true);
            cabinBed.getObjects().add(pillow);

            AdvObject dresser = new AdvObject(4, "Cassettiera",
                    "Una cassettiera vuota. Forse puoi bloccare la porta spostandola");
            dresser.setAlias(new String[] { "cassettiera", "comodino", "credenza", "cassapanca" });
            dresser.setPickupable(false);
            dresser.setMoveable(true);
            lobby3.getObjects().add(dresser);

            AdvObject nipper = new AdvObject(5, "Tronchese", "Una tronchese.");
            nipper.setAlias(new String[] { "tronchese", "tenaglia", "pinza" });
            table.getObjects().add(nipper);

            AdvObject oil = new AdvObject(6, "Olio lubrificante", "Dell'olio per la ruggine.");
            oil.setAlias(new String[] { "olio lubrificante", "olio", "lubrificante" });
            tortureRoom.getObjects().add(oil);

            AdvObject map = new AdvObject(12, "Mappa", "Una mappa della nave."); // MOSTRABILE???
            map.setAlias(new String[] { "mappa", "carta geografica" });

            AdvObjectContainer book = new AdvObjectContainer(7, "Libro", "Un libro di nautica. Sembra esserci qualcosa dentro.");
            book.setAlias(new String[] { "libro", "volume", "libro di nautica", "volume di nautica"});
            book.setInspectable(true);
            book.add(map);
            study.getObjects().add(book);

            AdvObject button = new AdvObject(8, "Pulsante", "Un pulsante verde. Potrebbe sbloccare qualcosa.");
            button.setAlias(
                    new String[] { "pulsante", "tasto", "bottone", "pulsante verde", "tasto verde", "bottone verde" });
            button.setPickupable(false);
            button.setPushable(true);
            laboratory.getObjects().add(button);

            AdvObject telegraph = new AdvObject(9, "Telegrafo",
                    "Un telegrafo bloccato da una password di 4 lettere. 4 lettere... dove le avevi viste?");
            telegraph.setAlias(new String[] { "telegrafo", "telegrafo bloccato", "telegrafo con password" });
            telegraph.setPickupable(false);
            telegraph.setUnlockable(true);
            telegraph.setPassword("aguf");
            bar.getObjects().add(telegraph);

            AdvObject rug = new AdvObject(10, "Tappeto", "Un tappeto stranamente fuoriposto.");
            rug.setAlias(new String[] { "tappeto", "tappeto fuoriposto" });
            rug.setPickupable(false);
            rug.setMoveable(true);
            cabin1.getObjects().add(rug);

            AdvObject card = new AdvObject(11, "Tessera", "La tessera per sbloccare il montacarichi.");
            card.setAlias(new String[] { "carta", "carta magnetica", "carta per montacarichi", "tessera" });
            mainRoom.getObjects().add(card);

            getObjectsList().add(lantern);
            getObjectsList().add(pillow);
            getObjectsList().add(dresser);
            getObjectsList().add(nipper);
            getObjectsList().add(oil);
            getObjectsList().add(book);
            getObjectsList().add(button);
            getObjectsList().add(telegraph);
            getObjectsList().add(rug);
            getObjectsList().add(card);

            // set starting room
            setCurrentRoom(cabinA);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metodo che inizializza il gioco.
     */
    @Override
    public void init() throws Exception {
        setCommands();
        setRooms();
    }

    /**
     * Imposta l'engine del gioco.
     * @param engine l'engine del gioco
     */
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void playSound(String soundFilePath) {
        try {
            File soundFile = new File(soundFilePath);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(soundFile));
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
            e.printStackTrace();
        }
    }

    public String winCommand() { //non cambia il background
        String out = " ";
         if (getCurrentRoom().getId() == 16){
            setCurrentRoom(getCurrentRoom().getEast());
            out = " -------------------------------\n";
            out += "| " + getCurrentRoom().getName() + "\n";
            out += " -------------------------------\n";
            out += getCurrentRoom().getDescription();
        /*out += "Finalmente sei in salvo, la stanchezza comincia a farsi sentire. Mentre le palpebre calando, vedi in lontananza la nave che affonda."
        + "Che strano, pensi tra te e te... non ho ancora capito perchè ci trovavamo lì... il buio di un sonno meritato ti pervade.\n\n\n\n"
        + "CE L'HAI FATTA! HAI VINTO!";*/
            engine.endGame();
        }
        return out;
    }

    public String inventoryCommand() {
        String out = "Nel tuo inventario ci sono:\n";
        for (AdvObject o : getInventory()) {
            out += "\n" + o.getName() + ": " + o.getDescription();
        }
        return out;
    }

    /**
     * Metodo per gestire l'osservazione delle stanze.
     * 
     * @return
     */
    public String lookAtCommand() {
        String out = "";
        int nObjects = 0;
        if (getCurrentRoom().isDark()) {
            out = "La stanza è completamente buia, è impossibile orientarsi.";
        } else {
            out = getCurrentRoom().getDescription() + "\nNella stanza c'è:\n";
            if (getCurrentRoom().getObjects().size() > 0) {
                for (AdvObject o : getCurrentRoom().getObjects()) {
                    if (!getInventory().contains(o)) {
                        nObjects++;
                        out += o.getName() + ": " + o.getDescription() + "\n";
                    }
                }
                if (nObjects == 0) { //ma serve sto controllo?
                    out = "Non c'è niente di interessante qui.";
                }
            } else {
                out = "Non c'è niente di interessante qui.";
            }
            return out;
        }
        return out;
    }

    /**
     * Metodo per gestire la raccolta degli oggetti.
     * 
     * @param p
     * @return
     */
    public String pickUpCommand(ParserOutput p) {
        String out = "";
        if (p.getObject() != null) {
            if (p.getObject().isPickupable()) {
                if (p.getObject().getId() == 3) {
                    getInventory().add(p.getObject());
                    getCurrentRoom().getObjects().remove(p.getObject());
                    getTimer().setLiquidDelay(7500);
                    playSound("resources/porthole.wav");
                    out = "Hai raccolto: " + p.getObject().getDescription() + " All'improvviso, senti un rumore alle tue spalle, verso Sud";
                } else {
                    getInventory().add(p.getObject());
                    getCurrentRoom().getObjects().remove(p.getObject());
                    out = "Hai raccolto: " + p.getObject().getDescription();
                }
            } else {
                out = "Non c'è nulla da raccogliere qui.";
            }
        } else {
            out = "Non ho capito cosa hai detto!";
        }
        return out;
    }

    /**
     * Metodo per gestire l'uso degli oggetti.
     * 
     * @param p
     * @return
     */
    public String useCommand(ParserOutput p) {
        String out = "";
        if (p.getInvObject() != null) {
            if (p.getInvObject().isUsable()) {
                int idObject = p.getInvObject().getId();
                switch (idObject) {
                    case 1:
                        if (getCurrentRoom().getId() == 18) {
                            out = "Hai acceso la lanterna. Ora puoi entrare nella stanza buia a Ovest.";
                            getCurrentRoom().setLocked(false);
                            getInventory().remove(p.getObject());
                        } else {
                            out = "Non c'è niente con cui accedere la lanterna.";
                        }
                        break;
                    case 3:
                        if (getCurrentRoom().getId() == 1){
                            out = "Hai aperto la porta della cabina.";
                            getRooms().get(1).setLocked(false); //serve?
                            getRooms().get(2).setLocked(false);
                        } else if (getCurrentRoom().getId() == 19){
                            out = "Girati a Sud per aprire la porta della cabina.";
                        }
                        break;
                    case 5:
                        if (getCurrentRoom().getId() == 5) {
                            out = "Hai rotto il lucchetto. Ora puoi accedere alla stanza a Est";
                            getCurrentRoom().setLocked(false);
                            getInventory().remove(p.getObject());
                        }
                        break;
                    case 6:
                        if (getCurrentRoom().getId() == 13) {
                            out = "Hai lubrificato la maniglia. Ora puoi accedere alla stanza a Nord";
                            getRooms().get(14).setLocked(false);
                            getInventory().remove(p.getObject());
                        }
                        break;
                    case 7:
                        if ((p.getInvObject().isInspected()) && (getCurrentRoom().getId() == 41)) {
                            out = "Hai inserito il libro nella libreria. La libreria comincia a muoversi. Ora puoi accedere alla stanza a Nord";
                            getCurrentRoom().setLocked(false);
                            //getInventory().remove(p.getObject());
                            
                        } else if (!(p.getInvObject().isInspected()) && (getCurrentRoom().getId() == 41)) {
                            out = "Forse dovresti prima sfogliare il libro...";
                        }
                        break;
                    case 11:
                        if (getCurrentRoom().getId() == 11) {
                            out = "Hai inserito la carta nel montacarichi. Ora puoi usare anche il pulsante a Est";
                            getCurrentRoom().setLocked(false);
                            getInventory().remove(p.getObject());
                        }
                        break;
                    //aggiungi mappa da non rimuovere dopo l'uso
                    default:
                        out = "Non puoi usare questo oggetto in questa stanza.";
                        break;
                }
            } else {
                out = "Non hai nulla da usare. Devi prima raccogliere qualcosa.";
            }
        } else {
            out = "Non hai nulla da usare! Devi prima raccogliere qualcosa.";
        }
        return out;
    }

    /**
     * Metodo per gestire il comando push.
     * 
     * @param p
     * @return
     */
    public String pushCommand(ParserOutput p) {
        String out = "";
        if (p.getObject() != null) {
            if (p.getObject().isPushable()) {
                if (p.getObject().getId() == 8) {
                    out = "Hai premuto il pulsante. Senti Phi urlare dall'atrio: ''Le sbarre si sono aperte!''. Anche la libreria si sposta nuovamente, dandoti libero accesso";
                    getCurrentRoom().getObjects().remove(p.getObject());
                    getCurrentRoom().setLocked(false);
                }
            } else {
                out = "Non c'è niente da premere qui.";
            }
        } else {
            out = "Non ho capito cosa hai detto!";
        }
        return out;
    }

    public String inspectCommand(ParserOutput p) {
        String out = "";
        if (p.getInvObject() == null && p.getObject().getId() != 7) {
            out = "Non c'è niente da ispezionare qui.";
        } else {
            if (p.getInvObject().isInspectable() && !p.getInvObject().isInspected()) {
                if (p.getInvObject() instanceof AdvObjectContainer) {
                    AdvObjectContainer c = (AdvObjectContainer) p.getInvObject();
                    if (!c.getList().isEmpty()) {
                        out = "Sulla prima pagina sembra esserci scritto qualcosa... 'aguf'. ''E' 'fuga' al contrario, servirà a qualcosa?'' chiede Phi,"
                                + "aggiungendo ''Forse dovremmo inserire il libro da qualche parte''."
                                + "\nContiene:" + "\n"+ "Una mappa";
                        AdvObject map = c.getList().get(0);  
                        getInventory().add(map);
                        c.getList().clear();
                        p.getInvObject().setInspected(true);
                    }
                } else {
                    p.getInvObject().setInspected(true);
                    out = "Hai aperto nel tuo inventario: " + p.getInvObject().getName();
                }
            } else {
                out = "Non puoi ispezionare questo oggetto.";
            }
        }
        return out;
    }    

    /**
     * Metodo per gestire il comando move.
     * 
     * @param p
     * @return
     */
    public String moveCommand(ParserOutput p) {
        String out = "";
        if (p.getObject() != null) {
            if (p.getObject().isMoveable()) {
                if (p.getObject().getId() == 2) {
                    out = "Hai spostato il cuscino. Hai trovato una chiave!";
                    getCurrentRoom().getObjects().remove(p.getObject());
                    AdvObject key = new AdvObject(3, "Chiave", "Una chiave antica.");
                    key.setAlias(new String[] { "chiave" });
                    getCurrentRoom().getObjects().add(key);
                    getObjectsList().add(key);
                }
                if (p.getObject().getId() == 4) {
                    out = "Hai spostato la cassettiera e hai bloccato la porta. Hai rallentato il flusso dell'acqua!";
                    getCurrentRoom().getObjects().remove(p.getObject());
                    getTimer().setLiquidDelay(8500);
                }
                if (p.getObject().getId() == 10) {
                    out = "Hai spostato il tappeto. Hai trovato la botola che porta alla stanza murata del piano inferiore. A Est c'è la botola.";
                    getCurrentRoom().getObjects().remove(p.getObject());
                    getCurrentRoom().setLocked(false);
                    //getRooms().get(42).setLocked(true);
                }
            } else {
                out = "Non puoi spostare questo oggetto.";
            }
        } else {
            out = "Non c'è niente da spostare qui.";
        }
        return out;
    }

    /**
     * Metodo per gestire il comando unlock.
     * 
     * @param p
     * @return
     */
    public String unlockCommand(ParserOutput p) {
        String out = "";
        if (p.getObject() != null) {
            if (p.getObject().isUnlockable()) { //controllo su Id? Solo il telegrafo è sbloccabile
                    if (p.getObject().getPassword().equals(p.getPasswordInput())) {
                        out = "Hai sbloccato il telegrafo. E' stata mandata una richiesta di soccorso!";
                        getCurrentRoom().getObjects().remove(p.getObject());
                    /*} else if (p.getObject().getPassword().equals("fuga")) {
                        engine.sinkCommand();*/
                    } else {
                        out = "Password errata.";
                    }
            } else {
                out = "Non puoi sbloccare questo oggetto.";
            }
        } else {
            out = "Non c'è niente da sbloccare qui.";
        }
        return out;
    }
    
    /*forse con engine non si può
    public class GameOverException extends Exception {
        public GameOverException(String message) {
            super(message);
        }
    }
    
    public String unlockCommand(ParserOutput p) {
        String out = "";
        if (p.getObject() != null) {
            if (p.getObject().isUnlockable()) {
                if ("fuga".equals(p.getPasswordInput())) {
                    try {
                        throw new GameOverException("Hai inserito la password 'fuga'. Il gioco è finito!");
                        //String out = "All'improvviso, non si sa da dove si sente: ''Tentativo di fuga rilevato. Attivazione protocollo di autodistruzione, tutte le porte verranno aperte''. Prima di realizzare cosa sia successo, le onde ti travolgono, affondando completamente la barca.\n";
                        //return out;
                        //engine.endGame();
                    } catch (GameOverException e) {
                        out = e.getMessage();
                    }
                } else if (p.getObject().getPassword().equals(p.getPasswordInput())) {
                    out = "Hai sbloccato il telegrafo. E' stata mandata una richiesta di soccorso!";
                } else {
                    out = "Password errata.";
                    //out = sinkCommand(); ????
                }
            } else {
                out = "Non puoi sbloccare questo oggetto.";
            }
        } else {
            out = "Non c'è niente da sbloccare qui.";
        }
        return out;
    }*/
    /**
     * Metodo per gestire le mosse del giocatore.
     * 
     * @param p
     * @return
     */
    @Override
    public String nextMove(ParserOutput p) {
        boolean noroom = false;
        boolean newroom = false;
        boolean lockroom = false;
        String out = "";
        CommandType command = p.getCommand().getType();
        switch (command) {
            case NORD:
                if (getCurrentRoom().getNorth() != null) {
                    if (!getCurrentRoom().isLocked()) {
                        setCurrentRoom(getCurrentRoom().getNorth());
                        newroom = true;
                        if (getCurrentRoom().getId()== 2 )
                            getTimer().setLiquidDelay(5000);
                        else if (getCurrentRoom().getId()== 5 )
                            playSound("resources/pots.wav");
                    /*} else if (getCurrentRoom().getId() == 41){
                        getRooms().get(41).setLocked(false);
                        setCurrentRoom(getCurrentRoom().getNorth());
                        newroom = true; */
                    } else {
                        lockroom = true;
                    }
                } else {
                    noroom = true;
                }
                break;
            case EAST:
                if (getCurrentRoom().getEast() != null) {
                    if (!getCurrentRoom().isLocked()) {
                    setCurrentRoom(getCurrentRoom().getEast());
                    newroom = true;
                    } else {
                        /*if (getCurrentRoom().getId() == 16) {
                            setCurrentRoom(getCurrentRoom().getEast());
                            String out = "Finalmente sei in salvo, la stanchezza comincia a farsi sentire. Mentre le palpebre calando, vedi in lontananza la nave che affonda."
                            + "Che strano, pensi tra te e te... non ho ancora capito perchè ci trovavamo lì... il buio di un sonno meritato ti pervade.\n\n\n\n"
                            + "CE L'HAI FATTA! HAI VINTO!";
                             engine.endGame();
                        newroom = true;
                        } else {
                            //checkLock(command);
                            lockroom = true;
                        }*/ //come risolvo sta cosa boh, win non cambia stanza(tengo questa ora), east non stampa
                        lockroom = true;
                    }
                } else {
                    noroom = true;
                }
                break;
            case WEST:
                if (getCurrentRoom().getWest() != null) {
                    if (!getCurrentRoom().isLocked()) {
                        setCurrentRoom(getCurrentRoom().getWest());
                        newroom = true;
                    } else {
                        if (getCurrentRoom().getId() == 5) {
                            getRooms().get(5).setLocked(false);
                            setCurrentRoom(getCurrentRoom().getWest());
                            newroom = true;
                        } else if (getCurrentRoom().getId() == 11) {
                            getRooms().get(11).setLocked(false);
                            setCurrentRoom(getCurrentRoom().getWest());
                            newroom = true;
                        }
                        lockroom = true;
                    }
                } else {
                    noroom = true;
                }
                break;
            case SOUTH: //controlli sui piani allagati? NO perchè non si può tornare indietro per forza
                if (getCurrentRoom().getSouth() != null) {
                    if (!getCurrentRoom().isLocked()) {
                    setCurrentRoom(getCurrentRoom().getSouth());
                    newroom = true;
                    } else {
                        if (getCurrentRoom().getId() == 5 && getRooms().get(10).isLocked() == false) {
                            getRooms().get(3).setLocked(false);
                            getCurrentRoom().setSouth(getRooms().get(42));
                            newroom = true;
                        } else if (getCurrentRoom().getId() == 14) {
                            getRooms().get(14).setLocked(false);
                            setCurrentRoom(getCurrentRoom().getSouth());
                            newroom = true;
                        } else if (getCurrentRoom().getId() == 41) {
                            getCurrentRoom().setLocked(false);
                            setCurrentRoom(getCurrentRoom().getSouth());
                            newroom = true;
                        }
                        lockroom = true;
                    }
                } else {
                    noroom = true;
                }
                break;
            case WIN:
                out = winCommand();
                break;
            case INVENTORY:
                out = inventoryCommand();
                break;
            case LOOK_AT:
                out = lookAtCommand();
                break;
            case PICK_UP:
                out = pickUpCommand(p);
                break;
            case USE:
                out = useCommand(p);
                break;
            case PUSH:
                out = pushCommand(p);
                break;
            case INSPECT:
                out = inspectCommand(p);
                break;
            case MOVE:
                out = moveCommand(p);
                break;
            case UNLOCK:
                out = unlockCommand(p);
                break;
            default:
                out = "Non ho capito cosa devo fare! Prova con un altro comando.";
                break;
        }
        if (noroom) {
            out = "Non puoi andare in quella direzione... perchè non mi ascolti?";
        } else if (newroom) {
            out = " -------------------------------\n";
            out += "| " + getCurrentRoom().getName() + "\n";
            out += " -------------------------------\n";
            out += getCurrentRoom().getDescription();
        } else if (lockroom) {
            out = "La porta è bloccata. Devi sbloccarla prima di poterla aprire.";
        }
        return out;
    }
}
