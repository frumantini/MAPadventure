# MAPadventure

**Indice**
1. [Descrizione del caso di studio](#1--Descrizione-del-caso-di-studio)
2. [Diagramma delle classi](#2--Diagramma-delle-classi)
3. [Specifica algebrica](#3--Specifica-algebrica)
4. [Applicazione argomenti](#4--Applicazione-argomenti)


<br/><br/>

## **1 	Descrizione del caso di studio**

“Desert the sinking ship” è un'applicazione Java che simula un'avventura testuale all'interno di una nave in procinto di affondare.
Scopo del gioco è riuscire a uscire dalla nave in tempo, prima che la stessa affondi completamente. Il giocatore deve essere dunque in grado di ispezionare con attenzione l'ambiente circostante, cercando di non tralasciare nulla durante il percorso e di compiere le scelte giuste.
Per progredire nel gioco, infatti, il giocatore dovrà utilizzare il proprio ingegno e la capacità di analizzare gli indizi presenti nelle descrizioni delle stanze e degli oggetti, al fine di sbloccare le stanze e di risolvere enigmi, cercando di non cascare nei tranelli.

**<ins>ISTRUZIONI</ins>**

Ti svegli all'interno di quella che sembra essere una cabina di una nave. Non hai idea di come ci sei arrivato ma una brutta sensazione ti pervade. Provi ad aprire la porta, ma ti accorgi che è chiusa a chiave. Cerca di uscirne vivo!


Per avviare il programma, è necessario eseguire due operazioni:
1. avviare il server eseguendo Engine, che crea e gestisce il server per la comunicazione client-server. (Questo ti permetterà di stabilire una connessione e scambiare dati tra il client e il server)
2. avviare il client eseguendo SinkingShipGameGui, che rappresenta l'interfaccia grafica del gioco. (Il client si connetterà al server precedentemente avviato e consentirà all'utente di giocare all'avventura testuale.)


<br/><br/>

## **2  Diagramma delle classi**

![ModelloDiDominio](./img/ModelloDiDominio.png)

<br/><br/>

## **3  Specifica algebrica**
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

<br/><br/>

## **4  Applicazione argomenti**
Il System Desing è uno stile di progettazione ad alto livello ed è strettamente legato al concetto di architettura software. Essa definisce il modo in cui un sistema è strutturato e come comunicano fra di loro i suoi principali componenti.

