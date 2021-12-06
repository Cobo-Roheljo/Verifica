package itismeucci;

public class Smain{

    public static void main(String[] args){

        Server server = new Server(6789);
        server.comunicazione();
        server.connessine();
    }
}