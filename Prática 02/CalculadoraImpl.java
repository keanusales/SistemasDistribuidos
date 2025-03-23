import java.rmi.RemoteException;

public class CalculadoraImpl implements Calculadora {

    @Override
    public Numero soma(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() + b.getValor());
    }

    @Override
    public Numero subtrai(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() - b.getValor());
    }

    @Override
    public Numero multiplica(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() * b.getValor());
    }

    @Override
    public Numero divide(Numero a, Numero b) throws RemoteException {
        if (b.getValor() == 0) {
            throw new RemoteException("Divisão por zero não é permitida.");
        }
        return new NumeroImpl(a.getValor() / b.getValor());
    }

    @Override
    public Numero potencia(Numero a, Numero b) {
        return new NumeroImpl(Math.pow(a.getValor(), b.getValor()));
    }

    @Override
    public Numero raiz(Numero a) throws RemoteException {
        if (a.getValor() < 0) {
            throw new RemoteException("Raiz de número negativo não é permitida.");
        }
        return new NumeroImpl(Math.sqrt(a.getValor()));
    }

    @Override
    public Numero log(Numero a) throws RemoteException {
        if (a.getValor() <= 0) {
            throw new RemoteException("Logaritmo de número não positivo não é permitido.");
        }
        return new NumeroImpl(Math.log(a.getValor()));
    }

    @Override
    public Numero seno(Numero a) {
        return new NumeroImpl(Math.sin(a.getValor()));
    }

    @Override
    public Numero cosseno(Numero a) {
        return new NumeroImpl(Math.cos(a.getValor()));
    }

    @Override
    public Numero tangente(Numero a) {
        return new NumeroImpl(Math.tan(a.getValor()));
    }

    @Override
    public Numero valorAbsoluto(Numero a) {
        return new NumeroImpl(Math.abs(a.getValor()));
    }

    @Override
    public Numero teto(Numero a) {
        return new NumeroImpl(Math.ceil(a.getValor()));
    }

    @Override
    public Numero piso(Numero a) {
        return new NumeroImpl(Math.floor(a.getValor()));
    }

    @Override
    public Numero arredondar(Numero a) {
        return new NumeroImpl(Math.round(a.getValor()));
    }

    @Override
    public Numero maximo(Numero a, Numero b) {
        return new NumeroImpl(Math.max(a.getValor(), b.getValor()));
    }

    @Override
    public Numero minimo(Numero a, Numero b) {
        return new NumeroImpl(Math.min(a.getValor(), b.getValor()));
    }

    @Override
    public Numero raizCubica(Numero a) {
        return new NumeroImpl(Math.cbrt(a.getValor()));
    }

    @Override
    public Numero logNatural(Numero a) {
        return new NumeroImpl(Math.log1p(a.getValor()));
    }

    @Override
    public Numero hipotenusa(Numero a, Numero b) {
        return new NumeroImpl(Math.hypot(a.getValor(), b.getValor()));
    }

    @Override
    public Numero fatorial(Numero a) throws RemoteException {
        if (a.getValor() < 0) {
            throw new RemoteException("Fatorial não é definido para números negativos.");
        }
        double resultado = 1;
        for (int i = 1; i <= a.getValor(); i++) {
            resultado *= i;
        }
        return new NumeroImpl(resultado);
    }
}
