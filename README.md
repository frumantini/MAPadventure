# MAPadventure

**Indice**
1. [Introduzione](#1--Introduzione)
2. [Modello di dominio](#2--Modello-di-dominio)
3. [Requisiti specifici](#3--Requisiti-specifici)</br>
3.1 [Requisiti funzionali](#3-1--Requisiti-funzionali)</br>
3.2 [Requisiti non funzionali](#3-2--Requisiti-non-funzionali)
4. [System Design](#4--System-Design)<br/>
4.1 [Stile architetturale adottato](#4-1--Stile-architetturale-adottato)<br/>
4.2 [Diagramma dei package e diagramma dei componenti](#4-2--Diagramma-dei-package-e-diagramma-dei-componenti)</br>
4.3 [Commento delle decisioni prese](#4-3--Commento-delle-decisioni-prese)
5. [O.O. Design](#5--OO-Design)</br>
5.1 [Diagrammi delle classi e diagrammi di sequenza](#5-1--Diagrammi-delle-classi-e-diagrammi-di-sequenza)</br>
5.2 [Design pattern utilizzati](#5-2--Design-pattern-utilizzati)</br>
5.3 [Commento delle decisioni prese](#5-3--Commento-delle-decisioni-prese)
6. [Riepilogo del test](#6--Riepilogo-del-test)
7. [Manuale utente](#7--Manuale-utente)
8. [Processo di sviluppo e organizzazione del lavoro](#8--Processo-di-sviluppo-e-organizzazione-del-lavoro)


<br/><br/>

## **1  Introduzione**

La battaglia navale è tratta dall'omonimo gioco originariamente di carta e matita per due giocatori, estremamente popolare e diffuso in tutto il mondo. <br/>
Lo *scopo del gioco* è localizzare tutte le unità della flotta navale nemica, composta da: quattro cacciatorpediniere, tre incrociatori, due corazzate e un portaerei.

**<ins>ISTRUZIONI</ins>**

Il giocatore deve selezionare la casella (inserendo le coordinate) e "sparare un colpo"; se la casella sparata corrisponde a una nave viene mostrato a schermo il messaggio *"Colpito!"*, in caso negativo *"Mancato!"*. <br/>
Quando un colpo centra l'ultima casella di una nave viene mostrato a schermo il messaggio *"Colpito e affondato!"*. <br/>
Ci sono differenti livelli di difficoltà con un diverso numero massimo di tentativi falliti. <br/>
Il giocatore **vince** se riesce ad affondare tutte le navi prima di esaurire tutti i tentativi.

<br/><br/>

## **2  Modello di dominio**

![ModelloDiDominio](./img/ModelloDiDominio.png)

<br/><br/>

## **3  Requisiti Specifici**
Si riporta di seguito l'analisi dei requisiti formali e non formali.
<br/><br/>
### **3-1  Requisiti funzionali**
- **<ins>RF1</ins>: Come giocatore voglio mostrare l'help con elenco comandi e regole di gioco**
  - **Codice caso d’uso:** CdS01
  - **Nome:** Help
  - **Attore:** Giocatore
  - **Obiettivo:** Mostrare i comandi da utilizzare 
  - **Post-Condizioni:** Vengono mostrati all’utente tutti i comandi che si possono utilizzare durante la partita e le regole di gioco
 
 
- **<ins>RF2</ins>: Come giocatore voglio chiudere il gioco**
  - **Codice caso d’uso:** CdS02
  - **Nome:** Esci
  - **Attore:** Giocatore
  - **Obiettivo:** Uscire dalla partita
  - **Pre-Condizioni:** Il giocatore ha già avviato una partita
  - **Post-Condizioni:** Il giocatore esce dalla partita dopo aver confermato
 
 
- **<ins>RF3</ins></ins>: Come giocatore voglio impostare il livello di gioco per variare il numero massimo di tentativi falliti**
  - **Codice caso d’uso:** CdS03
  - **Nome:** Difficoltà
  - **Attore:** Giocatore
  - **Obiettivo:** Impostare il livello di gioco
  - **Pre-Condizioni:** Non esiste una partita già in corso
  - **Post-Condizioni:** Il giocatore selezione il livello di difficoltà (facile, medio,difficile)
 
 
- **<ins>RF4</ins>: Come giocatore voglio mostrare il livello di gioco e il numero di massimo di tentativi falliti**
  - **Codice caso d’uso:** CdS04
  - **Nome:** Mostra livello
  - **Attore:** Giocatore
  - **Obiettivo:** Mostrare il livello di gioco e il numero di tentativi falliti
  - **Post-Condizioni:** Al giocatore vengono mostrati i tentativi falliti e il livello di gioco attuale
 

- **<ins>RF5</ins>: Come giocatore voglio mostrare i tipi di nave e il numero**
  - **Codice caso d’uso:** CdS05
  - **Nome:** Mostra navi
  - **Attore:** Giocatore
  - **Obiettivo:** Mostrare le navi
  - **Pre-Condizioni:** Il giocatore ha già avviato una partita
  - **Post-Condizioni:** Al giocatore vengono mostrate le navi rimaste all’interno del campo con relativa lunghezza
 
 
- **<ins>RF6</ins>: Come giocatore voglio iniziare una nuova partita**
  - **Codice caso d’uso:** CdS06
  - **Nome:** Gioca
  - **Attore:** Giocatore
  - **Obiettivo:** Iniziare una partita
  - **Pre-Condizioni:** Non esiste una partita già in corso
  - **Post-Condizioni:** Vengono posizionate casualmente le navi e viene mostrata una griglia vuota
 
 
- **<ins>RF7</ins>: Come giocatore voglio svelare la griglia con le navi posizionate**
  - **Codice caso d’uso:** CdS07
  - **Nome:** Svela Griglia
  - **Attore:** Giocatore
  - **Obiettivo:** Visualizzare la griglia di gioco
  - **Pre-Condizioni:** Il giocatore ha già avviato una partita
  - **Post-Condizioni:** Viene mostrata una griglia 10x10 numerata, con al suo interno le navi posizionate


- **<ins>RF8</ins>: Come giocatore voglio impostare il numero massimo di tentativi falliti per livello di gioco**
  - **Codice caso d’uso:** CdS08
  - **Nome:** Difficoltà ``numero``
  - **Attore:** Giocatore
  - **Obiettivo:** Modificare il numero massimo di tentativi 
  - **Pre-Condizioni:** Non esiste una partita già in corso
  - **Post-Condizioni:** Viene modificato il numero di tentativi relativi al livello


- **<ins>RF9</ins>: Come giocatore voglio impostare direttamente il numero massimo di tentativi che si possono fallire**
  - **Codice caso d’uso:** CdS09
  - **Nome:** Tentativi ``numero``
  - **Attore:** Giocatore
  - **Obiettivo:** Modificare il numero massimo di tentativi 
  - **Pre-Condizioni:** Non esiste una partita già in corso
  - **Post-Condizioni:** Viene modificato il numero di tentativi


- **<ins>RF10</ins>: Come giocatore voglio impostare la taglia della griglia**
  - **Codice caso d’uso:** CdS10
  - **Nome:** Dimensione
  - **Attore:** Giocatore
  - **Obiettivo:** Modificare la dimensione della griglia
  - **Pre-Condizioni:** Non esiste una partita già in corso
  - **Post-Condizioni:** Viene impostata la taglia della griglia


- **<ins>RF11</ins>: Come giocatore voglio impostare il tempo ti gioco**
  - **Codice caso d’uso:** CdS11
  - **Nome:** Tempo ``numero``
  - **Attore:** Giocatore
  - **Obiettivo:** Impostare i minuti a disposizione 
  - **Pre-Condizioni:** Non esiste una partita già in corso
  - **Post-Condizioni:** Viene impostato il tempo di gioco


- **<ins>RF12</ins>: Come giocatore voglio mostrare il tempo di gioco**
  - **Codice caso d’uso:** CdS12
  - **Nome:** Mostra tempo
  - **Attore:** Giocatore
  - **Obiettivo:** Mostrare il tempo di gioco
  - **Post-Condizioni:** Vengono mostrati i minuti trascorsi e il numero di minuti ancora disponibili


- **<ins>RF13</ins>: Come giocatore voglio effettuare un tentativo per colpire una nave**
  - **Codice caso d’uso:** CdS13
  - **Nome:** Spara
  - **Attore:** Giocatore
  - **Obiettivo:** Sparare un colpo
  - **Pre-Condizioni:** Il giocatore ha già avviato una partita
  - **Post-Condizioni:** Viene mostrata la griglia con il colpo sparato e le navi colpite parzialmente o affondate, il numero di tentativi effettuati e il tempo trascorso


- **<ins>RF14</ins>: Come giocatore voglio mostrare la griglia con le navi colpite e affondate**
  - **Codice caso d’uso:** CdS14
  - **Nome:** Mostra griglia
  - **Attore:** Giocatore
  - **Obiettivo:** Mostrare la griglia aggiornata
  - **Pre-Condizioni:** Il giocatore ha già avviato una partita
  - **Post-Condizioni:** Viene mostrata la griglia con le navi affondate e le sole parti già colpite delle navi non affondate


- **<ins>RF15</ins>: Come giocatore voglio mostrare il numero di tentativi già effettuati e il numero di tentativi falliti**
  - **Codice caso d’uso:** CdS15
  - **Nome:** Mostra tentativi
  - **Attore:** Giocatore
  - **Obiettivo:** Mostrare i tentativi
  - **Post-Condizioni:** Vengono visualizzati il numero di tentativi già effettuati, falliti e il numero massimo di tentativi


- **<ins>RF16</ins>: Come giocatore voglio abbandonare la partita**
  - **Codice caso d’uso:** CdS16
  - **Nome:** Abbandona
  - **Attore:** Giocatore
  - **Obiettivo:** Abbandonare la partita
  - **Pre-Condizioni:** Il giocatore ha già avviato una partita
  - **Post-Condizioni:** Il giocatore abbandona la partita dopo aver confermato
<br/><br/>
### **3-2  Requisiti non funzionali**

- **<ins>RNF1</ins>: il container docker dell’app deve essere eseguito da terminali che supportano Unicode con encoding UTF-8 o UTF-16. Elenco di terminali supportati**</br>

  Linux:</br>
    - terminal</br>

  Windows:</br>
    - Powershell
    - Git Bash (in questo caso il comando Docker ha come prefisso winpty; es: winpty docker -it ....)</br>

  **Comando per l’esecuzione del container**</br>
Dopo aver eseguito il comando docker pull copiandolo da GitHub Packages, Il comando Docker da usare per eseguire il container contenente l’applicazione è:

    docker run --rm -it ghcr.io/softeng2223-inf-uniba/battleship-sifakis:latest

- **<ins>RNF2</ins>: Il sistema deve essere disponibile 7 giorni su 7, 24 ore su 24**

- **<ins>RNF3</ins>: Il sistema deve rispondere a ogni comando dell’utente entro 4 sec**

<br/><br/>

## **4  System Design**
Il System Desing è uno stile di progettazione ad alto livello ed è strettamente legato al concetto di architettura software. Essa definisce il modo in cui un sistema è strutturato e come comunicano fra di loro i suoi principali componenti.

### **4-1  Stile architetturale adottato**
Gli stili architetturali utilizzati sono i seguenti:
 - *Layered*, per osservare la relazione fra i package.
 - *Model-View-Presenter*, per visionare l'interazione fra l'utente e il sistema considerato.

#### **Layered**
Nello stile architetturale layered ogni strato espone un’interfaccia (API) che è utilizzata da un componente dello strato superiore e che permette di apportare modifiche senza intaccare i livelli inferiori.

I package presenti nella nostra realtà sono:
 - App, ovvero il package dove l'esecuzione del gioco ha inizio ed è contenuto il messaggio di benvenuto.
 - Control, dove sono presenti tutte le classi che contengono la logica del gioco e garantiscono la comunicazione tra le diverse tipologie.
 - Entity, cioè le classi che consentono gestione dei dati.

Abbiamo lasciato App nel package originale, quindi tutti i package elencati (ovvero Control e Entity) sono package interni a quello di App.

#### **Model-View-Presenter**
Lo stile architetturale Model-View-Presenter ha tre sottosistemi:
- Model: include oggetti che rappresentano la conoscenza del dominio e metodi per l’accesso ai dati, non notifica eventi alle viste;
- View: include la rappresentazione visuale dei dati, sono possibili viste multiple ed è separata dal Model; 
- Presenter: prende i dati dal Model, li elabora e li restituisce alla View, gestisce la sequenza di interazioni con l’utente, trasforma l'input ricevuto per il modello o la vista.

Abbiamo preso questa decisione in modo da rispettare il principio di presentazione separata (spiegato nella sezione di OO Design), infatti è possibile isolare le logiche di interazione, facilitandone la manutenzione e la leggibilità. Inoltre quest’ultime risultano facilmente testabili.

<br/>

### **4-2  Diagramma dei package e diagramma dei componenti**

#### **Diagramma dei package:**
![Diagramma_Dei_Package](./img/Diagramma_Dei_Package.png) <br/>

#### **Diagramma delle componenti:**
![Diagramma_Delle_Componenti](./img/Diagramma_Delle_Componenti.png)

<br/>

### **4-3  Commento delle decisioni prese**
Ci siamo assicurati che i requisiti non funzionali venissero rispettati prendendo le seguenti decisioni. 
Per garantire il corretto funzionamento del programma sia su Windows che su Linux abbiamo utilizzato solamente stringhe che supportano Unicode con encoding UTF-8.  
Inoltre, avendo inserito i colori per una scelta stilistica, ci siamo accertati che fossero compatibli con i sistemi opertivi sopra elencati utilizzando i codici di escape ANSI. 

<br/>

## **5  OO Design**
L’OO design è un approccio alla progettazione di dettaglio di sistemi software, fondata sul paradigma ad oggetti.  
Esso deve rispettare i seguenti principi:

 - Principio di information hiding
 - Obiettivo di alta coesione
 - Obiettivo di basso accoppiamento
 - Presentazione separata

#### **Principio di information hiding**
Ogni componente deve custodire dei segreti al proprio
interno, ovvero i dettagli della implementazione: 

- #### *Sottosistemi*
    Per i sottosistemi solo l’interfaccia delle operazioni è pubblica. L'implementazione invece risulta privata.

- #### *Package*
    Per i package solo le classi strettamente necessarie sono pubbliche, tutte le altre risultano private.

- #### *Classi*
    Per le classi solo le operazioni necessarie sono pubbliche, tutte le altre sono private.

#### **Obiettivo di alta coesione**
La *coesione* misura il grado di dipendenza tra elementi di uno stesso componente, i componenti possono avere coesione differente:
 - un componente ad alta coesione ha una responsabilità ben definita;
 - un componente a bassa coesione è indice di una responsabilità non ben definita ed è difficile da comprendere, riusare e modificare.

**Obiettivo: assegna le responsabilità in modo tale da
ottenere componenti con responsabilità ben definite**

``
Una bassa coesione si risolve delegando le 
responsabilità ad altri componenti.
``

#### **Obiettivo di basso accoppiamento**
L'*accoppiamento* misura il grado di dipendenza fra componenti diversi, anche in questo caso abbiamo una distinzione: 
 - alto accoppiamento, un cambiamento a un
componente si propaga facilmente a tutti i
componenti che dipendono da esso;
- basso accoppiamento, un cambiamento a un
componente non si propaga ad altri componenti.

**Obiettivo: assegna le responsabilità ai componenti in modo tale
da limitare l’impatto dei cambiamenti**

``
Un alto accoppiamento si risolve applicando il principio di information hiding.
``

#### **Presentazione separata**
La logica di presentazione e la logica di dominio sono
più facili da capire se tenute separate.

È possibile esporre la logica di dominio come
API/servizio ed è anche possibile supportare presentazioni differenti senza
duplicare il codice.
Senza parti grafiche è possibile scrivere i casi di test con asserzioni testuali.

<br/>

### **5-1  Diagrammi delle classi e diagrammi di sequenza**

Il diagramma delle classi a prospettiva software è utilizzato per descrivere i legami statici che sussistono tra i vari oggetti presenti all'interno del sistema di riferimento.

#### **Diagramma delle classi a prospettiva software:**
![Diagramma_Delle_Classi](./img/Diagramma_Delle_Classi.png)
![Diagramma_Delle_Classi2](./img/Diagramma_Delle_Classi2.png)
<br/>

#### **Diagrammi di sequenza per le user story più importanti**

- Diagramma di sequenza per il comando "/gioca" <br/>
![Sequenza_Gioca](./img/Sequenza_Gioca.png)
- Diagramma di sequenza per il comando "/esci" <br/>
![Sequenza_Esci](./img/Sequenza_Esci.png)
- Diagramma di sequenza per il comando "/abbandona" <br/>
![Sequenza_Abbandona](./img/Sequenza_Abbandona.png)
- Diagramma di sequenza per la gestione di un tentativo per colpire una nave <br/>
![Sequenza_Spara](./img/Sequenza_Spara.png)
<br/>

### **5-2  Design pattern utilizzati**
 - Abbiamo deciso di utilizzare il GoF Pattern: **Factory Method**.  
 
Il Factory Method ha lo scopo di fornire un'interfaccia comune per creare oggetti, delegando la responsabilità di creazione delle istanze a sottoclassi o a una classe specializzata (la Factory).
La classe Factory si occupa della creazione dell'istanza effettiva, solitamente tramite un metodo factory dedicato.
Inoltre, il Factory Method consente di creare oggetti di diverse classi che condividono un'interfaccia comune. Le sottoclassi o la Factory possono determinare quale classe concreta istanziare.
Risulta utile quando si desidera delegare la responsabilità di creazione degli oggetti a una classe specializzata o quando si ha la necessità di creare oggetti di diverse classi concretizzate che soddisfano una stessa interfaccia.

<br/>


#### **5-3  Commento delle decisioni prese**
I principi sopra citati vengono rispettati nel seguente modo:
 - l'information hiding tramite l'utilizzo dell'incapsulamento, difatti tutte le variabili di istanza sono private;

 - la presentazione separata grazie al MVP (Model-View-Presenter) esplicitato nella sezione "Model-View-Presenter" citata in System Design.

<br/>

## **6  Riepilogo del test**
I test effettuati sul codice si dividono in 3 categorie:
- Check Style
- Spotbugs
- JUnit

### **Check Style**
Categoria dei test per verificare che lo stile di formattazione del progetto sia consono con quello richiesto. I dati all'ultima versione:

![Checkstyle_Main](./img/Checkstyle_Main.png) <br/>
![Checkstyle_Test](./img/Checkstyle_Test.png) <br/>


### **Spotbugs**
Categorie dei test per verificare gli errori più banali commessi durante la stesura del codice come: variabili non inzializzate e mai usate.

### **JUnit**
Categoria dei test eseguiti sulla correttezza del codice scritto: ci si assicura che l'output prodotto da determinate porzioni di codice, con determinati input, sia quello aspettato. Verificato attraverso la tecnologia junit.

![Flotta_Test](./img/Flotta_Test.png) <br/>
![Nave_Test](./img/Nave_Test.png) <br/>
![Parser_Test](./img/Parser_Test.png) </br>
![Cella_Test](./img/Cella_Test.png) <br/>
![Comandi_Test](./img/Comandi_Test.png) <br/>
![App_test](./img/App_test.png) </br>
![Package_Test](./img/Package_Test.png) <br/>




## **7  Manuale utente**
**<ins>Per poter avviare il gioco è necessario:</ins>**</br>


• scaricare ed avviare Docker sul proprio pc;</br>
• effettuare il login su Docker;</br>
• avviare il terminale;</br>
• dopo aver eseguito il comando docker pull copiandolo da GitHub Packages, inserire il comando

    docker run --rm -it ghcr.io/softeng2223-inf-uniba/battleship-sifakis:latest
• avvio del gioco.
<br/><br/>
**<ins>Come giocare:</ins>**</br>

All’avvio del gioco all’utente viene mostrata a schermo una descrizione concisa seguita dall’elenco dei comandi disponibili.</br>
Output a schermo: </br>
![introduzione](./img/introduzione.png)


Di seguito si elencano i comandi con le loro specifiche: 

-	comando **/help**(o con invocazione dell'app con flag **--help** o **-h**), che mostra nuovamente a schermo una *descrizione concisa* seguita dall’*elenco dei comandi disponibili*, come da esempio successivo:</br>
•	gioca </br>
•	esci </br>
•	... </br>
Output a schermo: </br>
![comando_help](./img/comando_help.png) </br>
![comando_help_2](./img/comando_help_2.png)


-	comando **/esci**, per *chiudere il gioco*.</br>
Viene chiesta conferma all’utente, in caso *positivo* l'applicazione si chiude restituendo il controllo al sistema operativo, altrimenti, in caso *negativo*, si predispone a ricevere nuovi tentativi o comandi;</br>
Output a schermo: </br>
![comando_esci](./img/comando_esci.png)

- comando **/abbandona**, per *abbandonare la partita*.</br>
Viene chiesta conferma all'utente, in caso *positivo* l’applicazione risponde visualizzando sulla griglia la posizione di tutte le navi e si predispone a ricevere nuovi comandi, altrimenti, in caso *negativo*, si predispone a ricevere nuovi tentativi o comandi;</br>
Output a schermo: </br>
![comando_abbandona](./img/comando_abbandona.png)


-	comando **/gioca**, che, se <ins>nessuna partita è in corso</ins>, *imposta causalmente le navi*, in orizzontale o in verticale, *mostra la griglia vuota* e si predispone a ricevere il primo tentativo o altri comandi. Se la partita è già in corso e viene richiamato il comando, viene nuovamente stampata a video la stessa griglia con le navi nascoste. E' possibile controllare che la griglia sia effettivamente la stessa chiamando il comando /svelagriglia;</br>
Output a schermo: </br>
![comando_gioca](./img/comando_gioca.png)

-	comando **/mostranavi**, che mostra all’utente, per <ins>ogni</ins> tipo di nave, la *dimensione in quadrati* e il *numero di esemplari da **affondare***; </br>
Output a schermo: </br>
![comando_mostranavi](./img/comando_mostranavi.png) </br>

**<ins>COMANDI DI LIVELLO</ins>**</br>

- permettono all’utente di *impostare il numero massimo di tentativi **falliti*** con messaggio di OK.</br> Sono possibili 3 scelte: **/facile** (imposta a 50 il numero massimo di tentativi falliti, è il default), **/medio** (massimo 30 tentativi falliti) e **/difficile** (massimo 10 tentativi falliti). Per una scelta di programmazione non abbiamo permesso all'utente di cambiare il livello durante la partita, ma solo all'inizio di una nuova;</br>
Output a schermo: </br>
![comando_imposta_livello](./img/comando_imposta_livello.png) </br>

- comando **/facile** ``numero`` (analogo per gli altri livelli), permette di *reimpostare il numero massimo di tentativi **falliti** del livello selezionato* a ``numero`` con messaggio di OK; </br>
Output a schermo: </br> 
![comando_difficolta_numero](./img/comando_difficolta_numero.png)

-	comando **/mostralivello**, se utilizzato prima di selezionarlo verrà stampato un messaggio di avviso con il livello impostato a **facile di default**. L'applicazione risponde mostrando il *livello di gioco* e il *numero di massimo di tentativi falliti rimanenti*;</br>
Output a schermo: </br>
![comando_mostralivello](./img/comando_mostralivello.png)


- comando **/tentativi** ``numero``, l’applicazione risponde visualizzando un messaggio di OK e *imposta il numero massimo di tentativi **falliti***; </br>
Output a schermo: </br>
![comando_tentativi](./img/comando_tentativi.png)


- comando **/mostratentativi**, l’applicazione risponde visualizzando il numero di tentativi già effettuati, il numero di tentativi falliti e il numero massimo di tentativi falliti; </br>
Output a schermo: </br>
![comando_mostratentativi](./img/comando_mostratentativi.png)


**<ins>COMANDI DI GRIGLIA</ins>**</br>

- permettono all'utente di **impostare la taglia della griglia** con messaggio di OK. Sono possibili 3 scelte: **/standard** (imposta a 10x10 la dimensione della griglia, è il default), **/large** (imposta a 18x18 la dimensione della griglia) e **/extralarge** (imposta a 26x26 la dimensione della griglia); </br>
Output a schermo: </br>
![comando_dimensione_griglia](./img/comando_dimensione_griglia.png)


-	comando **/svelagriglia**, utilizzabile solo a seguito del comando /gioca, per *visualizzare la griglia impostata*, con le righe numerate e le colonne contrassegnate da lettera, e tutte le navi posizionate;</br>
Output a schermo: </br>
![comando_svelagriglia](./img/comando_svelagriglia.png) </br>
![error_svelagriglia](./img/error_svelagriglia.png)

- comando **/mostragriglia**, utilizzabile solo a seguito del comando /gioca, l’applicazione risponde visualizzando una grigliacon le righe numerate a partire da 1 e le colonne numerate a partire da A, conle navi affondate e le sole parti già colpite delle navi non affondate. </br>
Output a schermo: </br>
![comando_mostragriglia](./img/comando_mostragriglia.png)



**<ins>COMANDI DI TEMPO</ins>**</br>

- comando **/tempo** ``numero``, permette all'utente di impostare a ``numero`` minuti il tempo di gioco con messaggio di OK; </br>
Output a schermo: </br>
![comando_tempo](./img/comando_tempo.png)


- comando **/mostratempo**, se utilizzato prima di aver inserito un numero, verrà stampato un messaggio di avviso con il tempo impostato a **10 minuti di default**. L’applicazione risponde visualizzando il *numero di minuti trascorsi* nel gioco e il *numero di minuti ancora disponibili.* </br>
Output a schermo: </br>
![comando_mostratempo](./img/comando_mostratempo.png) </br>
![comando_mostratempo_2](./img/comando_mostratempo_2.png)

<br/><br/>

 ## **8  Processo di sviluppo e organizzazione del lavoro**

Il processo di sviluppo e l'organizzazione del lavoro è stato reso possibile grazie all'utilizzo del framework Scrum, che prevede la suddivisione di un dato progetto in una serie di interazioni dette Sprint. 

Queste non erano interrompibili, ogni timeboxing era di 14 giorni ed erano così suddivise: <br/>
**Sprint 0**, inizializzazione del progetto;  
**Sprint 1**, sviluppo del codice e scrittura della relazione tecnica (parziale);  
**Sprint 2**, completamento relazione tecnica e fix delle problematiche riscontrate.

**Battleship**


**Attori:** Giocatore


Il **Product Backlog** seguito per sviluppare il gioco è il seguente:
- mostrare l'help con elenco comandi e regole del gioco
- chiudere il gioco
- impostare il livello di gioco per variare il numero massimo di tentativi falliti
- mostrare il livello di gioco e il numero di massimo di tentativi falliti
- mostrare i tipi di nave e il numero	
- iniziare una nuova partita	
- svelare la griglia con le navi posizionate	
- mostrare la griglia con le navi colpite e affondate
- mostrare il numero di tentativi già effettuati e il numero di tentativi falliti	
- abbandonare una partita	
- effettuare un tentativo per colpire una nave
- impostare il tempo di gioco
- mostrare il tempo di gioco

I requisiti desiderati in ogni iterazione sono stati analizzati, progettati, realizzati e testati durante ogni sprint.

In seguito al lancio di ogni Sprint, il gruppo si riuniva per discutere il piano di lavoro e capire quali fossero i requisiti da realizzare. Successivamente avveniva la creazione e assegnazione di ogni issue secondo la divisione del lavoro decisa di comune accordo tra i membri.

In generale, la realizzazione del progetto è avvenuta per la maggior parte lavorando insieme di persona e sfruttando lo strumento Visual Studio con il plugin LiveShare. In questo modo è stato possibile confrontarsi e risolvere eventuali problemi sul momento, garantendo sempre aiuto reciproco.

Al completamento, da parte di un membro del team, della sua parte di codice o documentazione seguiva una revisione fatta dagli altri componenti per caricare il lavoro svolto oppure correggere possibili errori. 

Al termine di ogni Sprint veniva effettuato il testing e l’ultimo controllo generale di tutto il lavoro svolto. Se tutto funzionava correttamente veniva annunciato l'esito sul canale consegne di Teams.

<br/><br/>

### Comandi di Interazione con gli Oggetti
- `accendi [nome oggetto]` - Accende un oggetto come una lanterna.
- `apri [nome oggetto]` - Apre un oggetto come un baule o un libro.
- `ispeziona [nome oggetto]` - Fornisce informazioni su un oggetto.
- `premi [nome oggetto]` - Preme un pulsante.
- `sblocca [nome oggetto] "[password]"` - Sblocca un oggetto bloccato da password.
- `sposta [nome oggetto]` - Sposta un oggetto.
- `usa [nome oggetto]` - Utilizza un oggetto.

### Comandi di Interazione con le Stanze
- `osserva` - Osserva la stanza in cui ti trovi e mostra una descrizione degli oggetti al suo interno.
