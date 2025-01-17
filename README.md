# Arduino Communication Center
Il seguente progetto ha come obbiettivo raccogliere, elaborare e visualizzare dati da un dispositivo Arduino.


## Funzionalità:

•	Acquisizione: ricezione, tramite Wi-Fi, di dati acquisiti da sensori collegati ad Arduino.

•	Elaborazione: gestione e elaborazione dei dati ricevuti, incluso il caso di valori mancanti.

•	Visualizzazione: dashboard web per visualizzare i dati elaborati in tempo reale.

• Archiviazione: database relazionale per la persistenza dei dati

• Notifica: invio di un messaggio contentente l'ultima temperatura rilevata a un bot telegram

• Riepilogo: generazione e invio di una mail ogni lunedì mattina alle ore 9 con i dati raccolti negli ultimi sette giorni. 



## Architettura e tecnologie:

•	Client: Arduino

•	Server: Java Spring Boot & Thymeleaf. 

• Database: MySQL

• OpenAPI

• Docker

## Arduino dashboard

![dashboard arduino](https://github.com/user-attachments/assets/71a14376-e041-403a-8c76-da2d1903c3e8)

## Mail riepilogativa

![image](https://github.com/user-attachments/assets/4799f4ca-4c32-4773-84c8-610ab5000285)



