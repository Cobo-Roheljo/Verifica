package itismeucci;

public class Cmain {
    
    public static void main(String[] args) {

        Client client = new Client(6789,"localhost");
        client.comunicazione();
        client.connessione();
        
    }
}
