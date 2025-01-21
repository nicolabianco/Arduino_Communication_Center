# Arduino Communication Center
Il seguente progetto ha come obbiettivo raccogliere, elaborare e visualizzare dati da un dispositivo Arduino.


## Funzionalità:

•	Acquisizione: ricezione, tramite Wi-Fi, di dati acquisiti da sensori collegati ad Arduino.

•	Elaborazione: gestione e elaborazione dei dati ricevuti.

•	Visualizzazione: dashboard web per visualizzare i dati elaborati in tempo reale così come i dati registrati negli ultimi sette giorni.

• Archiviazione: database relazionale per la persistenza dei dati

• Notifica: invio di un messaggio contentente l'ultima temperatura rilevata a un bot telegram

• Riepilogo: generazione e invio di una mail ogni lunedì mattina alle ore 9 con i dati raccolti negli ultimi sette giorni. 

• Reportistica: generazione e download di un file pdf contenente un report delle temperature rilevate negli ultimi sette giorni.

## Architettura e tecnologie:

•	Client: Arduino

•	Server: Java Spring Boot & Thymeleaf. 

• Database: MySQL

• OpenAPI

• Docker

## Arduino dashboard

![image](https://github.com/user-attachments/assets/f54bebf3-a720-4b87-b9ec-60f6c41636ab)


## Mail riepilogativa

![image](https://github.com/user-attachments/assets/4799f4ca-4c32-4773-84c8-610ab5000285)



