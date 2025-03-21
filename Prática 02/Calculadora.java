import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculadora extends Remote {

    public Numero soma(Numero a, Numero b)
        throws RemoteException;

    public Numero subtrai(Numero a, Numero b)
        throws RemoteException;

    public Numero multiplica(Numero a, Numero b)
        throws RemoteException;

    public Numero divide(Numero a, Numero b)
        throws RemoteException, DivisaoPorZeroException;

    public Numero potencia(Numero a, Numero b)
        throws RemoteException;

    public Numero raiz(Numero a)
        throws RemoteException, DivisaoPorZeroException;
    
    public Numero log(Numero a)
        throws RemoteException, DivisaoPorZeroException;
    
    public Numero seno(Numero a)
        throws RemoteException, DivisaoPorZeroException;

    public Numero cosseno(Numero a)
        throws RemoteException, DivisaoPorZeroException;
    
    public Numero tangente(Numero a)
        throws RemoteException, DivisaoPorZeroException;
}
