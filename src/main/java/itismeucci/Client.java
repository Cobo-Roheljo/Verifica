import java.io.*;
import java.net.*;
import java.util.*;

public class Client{

    private int porta;
    private String indirizzoServer;
    private Socket server;
    private BufferedReader riceve;
    private DataOutputStream inviare;

    public Client(int porta, String indirizzoServer){

        this.porta =  porta;
        this.indirizzoServer = indirizzoServer;
    }

    public void connessione(){

        try {
            server = new Socket(indirizzoServer, porta);
            riceve = new BufferedReader(new InputStreamReader(server.getInputStream()));
            inviare = new DataOutputStream(server.getOutputStream());
        } catch (Exception e) {
            System.out.println("ERRORE DI CONNESSIONE\n\n"+e.getMessage());

        }
    }

    public void comunicazione(){

        System.out.println("INIZA LA COMUNICAZIONE");
        Scanner tastiera = new Scanner(System.in);
        String in;

        try {
            
            for(;;){

                System.out.println(riceve.readLine());
                System.out.println("\n\n");
                in.tastiera.nextLine();

                if(in.equals("FINE PROGRAMMA")){
                    System.out.println("\n ---la connessine si chiude....");
                    break;
                }
            }

            tastiera.close();
            riceve.close();
            inviare.close();
            server.close();

        } catch (Exception e) {
            System.out.println("ERRORE COMUNICAZIONE CON IL SERVER" + e.getMessage());
        }
    }
}