import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {

    public Numero soma(Numero a, Numero b) throws RemoteException;

    public Numero subtrai(Numero a, Numero b) throws RemoteException;

    public Numero multiplica(Numero a, Numero b) throws RemoteException;

    public Numero divide(Numero a, Numero b) throws RemoteException;

    public Numero potencia(Numero a, Numero b) throws RemoteException;

    public Numero raiz(Numero a) throws RemoteException;

    public Numero log(Numero a) throws RemoteException;

    public Numero seno(Numero a) throws RemoteException;

    public Numero cosseno(Numero a) throws RemoteException;

    public Numero tangente(Numero a) throws RemoteException;

    public Numero valorAbsoluto(Numero a) throws RemoteException;

    public Numero teto(Numero a) throws RemoteException;

    public Numero piso(Numero a) throws RemoteException;

    public Numero arredondar(Numero a) throws RemoteException;

    public Numero maximo(Numero a, Numero b) throws RemoteException;

    public Numero minimo(Numero a, Numero b) throws RemoteException;

    public Numero raizCubica(Numero a) throws RemoteException;

    public Numero logNatural(Numero a) throws RemoteException;

    public Numero hipotenusa(Numero a, Numero b) throws RemoteException;

    public Numero fatorial(Numero a) throws RemoteException;
}
