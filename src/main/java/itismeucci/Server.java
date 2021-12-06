package itismeucci;


import java.io.*;
import java.net.*;
import java.util.*;
import java.util.ArrayList;

public class Server {
    
    private int porta;
    private ServerSocket server;
    private Socket client;
    private DataOutputStream riceve;
    private DataOutputStream inviare;
    ArrayList<String> dati = new  ArrayList<>();
    private int conta = 1;

    public Server(int porta){

        this.porta = porta;

        try {
            server = new ServerSocket(porta);
        } catch (Exception e) {
           System.out.println("CI SONO ERRORI SULLA PORTA \n\n" + e.getMessage());
        }
    }

    public void connessine(){

        try {
            
            System.out.println("\n\n SERVER IN ATTESA .... ");
            client = server.accept();
            System.out.println("\n\n NUOVO CLIENT SI STA CONNETTENDO");
            riceve = new  DataOutputStream(client.getOutputStream());

        } catch (Exception e) {
            
            System.out.println("ERRORE NELLA CONNESSIONE DIRETTA VERSO IL CLIENT  \n\n" + e.getMessage());
        }
    }

    public void comunicazione(){

         boolean aperto = true;

         try {
             
            inviare.writeBytes("\n\n CONNESSIONE EFFETTUATA" + "\n\n PREMI INVIO PER INIZIARE " + "\n");

            while(aperto){
                String messaggio = "";

                messaggio = riceve.readLine();
                dati.add(messaggio);

                if(conta <= 2){
                    inviare.writeBytes("--- Dammi il " + conta + "numero" +  "\n");
                }
                else if(conta == 3){
                    inviare.writeBytes(" --- scegli l'operazioe (-,+,*,/)" + "\n");
                }else if(conta == 4){
                    if(dati.get(3).equals("-")){
                        int n1 = Integer.parseInt(dati.get(1));
                        int n2 = Integer.parseInt(dati.get(2));

                        int risulatato = n1 - n2;
                        inviare.writeBytes("--- il risulato e': " + risulatato + "\n");
                    }else if (dati.get(3).equals("+")){

                        int n1 = Integer.parseInt(dati.get(1));
                        int n2 = Integer.parseInt(dati.get(2));

                        int risulatato = n1 + n2;
                        inviare.writeBytes("--- il risulato e': " + risulatato + "\n");
                    }else if(dati.get(3).equals("*")){
                        int n1 = Integer.parseInt(dati.get(1));
                        int n2 = Integer.parseInt(dati.get(2));

                        int risulatato = n1 * n2;
                        inviare.writeBytes("--- il risulato e': " + risulatato + "\n");
                    }else if(dati.get(3).equals("/")){

                        int n1 = Integer.parseInt(dati.get(1));
                        int n2 = Integer.parseInt(dati.get(2));

                        int risulatato = n1 / n2;
                        inviare.writeBytes("--- il risulato e': " + risulatato + "\n");
                    }
                    else if(conta == 5){
                        inviare.writeBytes("--- Vuoi fare un nuovo calcolo(Y/N)" + "\n");
                        String  mess = riceve.readLine();
                        System.out.println(mess);
                        if(mess.equals("N"))
                        {
                            System.out.println("--- SI STA CHIUDENDO");
                            close();
                        }else if (mess.equals("Y")){
                            conta = 0;

                            dati.clear();
                            inviare.writeBytes("FINE PROGRAMMA" + "\n");
                        }

                    }
                }
                System.out.println("-- MESSAGGIO CLIENT: " + messaggio);
                conta ++;
            }
         } catch (Exception e) {
             System.out.println("ERRORE COMUNICAZIOE CON CLIENT \n" + e.getMessage());
         }
    }

    public void close(){

        try {
            
            client.close();
            inviare.close();
            riceve.close();
            server.close();
        } catch (Exception e) {


            System.out.println(" ERRORE DI CHIUSURA \n" + e.getMessage());

        }
    }
}
