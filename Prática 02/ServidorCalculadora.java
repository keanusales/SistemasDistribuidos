import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServidorCalculadora {

    public static void main(String args[]) {
        if (args.length < 2) {
            System.err.println("Uso: ServidorCalculadora <IP> <Porta>");
            System.exit(1);
        }
        String ip = args[0];
        int porta = Integer.parseInt(args[1]);

        try {
            System.setProperty("java.rmi.server.hostname", ip); // Define o IP do servidor
            CalculadoraImpl calc = new CalculadoraImpl();
            Calculadora stub = (Calculadora) UnicastRemoteObject.exportObject(calc, 0);
            Registry registry = LocateRegistry.createRegistry(porta); // Cria o registry na porta especificada
            registry.bind("calculadora", stub);
            System.out.println("Servidor iniciado em " + ip + ":" + porta);
        } catch (AlreadyBoundException | RemoteException e) {
            System.err.println("Ocorreu um erro no servidor: " + e.toString());
        }
    }
}