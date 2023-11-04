# MAPadventure

**Indice**
1. [Introduzione](#1--Introduzione)
2. [Diagramma delle classi](#2--Diagramma-delle-classi)
3. [Specifica algebrica](#3--Specifica-algebrica)
4. [Applicazione argomenti](#4--Applicazione-argomenti)


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

