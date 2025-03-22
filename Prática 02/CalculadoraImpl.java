public class CalculadoraImpl implements Calculadora {

    @Override
    public Numero soma(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() + b.getValor());
    };

    @Override
    public Numero subtrai(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() - b.getValor());
    };

    @Override
    public Numero multiplica(Numero a, Numero b) {
        return new NumeroImpl(a.getValor() * b.getValor());
    };

    @Override
    public Numero divide(Numero a, Numero b) throws DivisaoPorZeroException {
        if (b.getValor() == 0) {
            throw new DivisaoPorZeroException();
        }
        return new NumeroImpl(a.getValor() / b.getValor());
    };

    @Override
    public Numero potencia(Numero a, Numero b) {
        return new NumeroImpl(Math.pow(a.getValor(), b.getValor()));
    };

    @Override
    public Numero raiz(Numero a) throws DivisaoPorZeroException {
        if (a.getValor() < 0) {
            throw new DivisaoPorZeroException();
        }
        return new NumeroImpl(Math.sqrt(a.getValor()));
    };

    @Override
    public Numero log(Numero a) throws DivisaoPorZeroException {
        if (a.getValor() <= 0) {
            throw new DivisaoPorZeroException();
        }
        return new NumeroImpl(Math.log(a.getValor()));
    };

    @Override
    public Numero seno(Numero a) {
        return new NumeroImpl(Math.sin(a.getValor()));
    };

    @Override
    public Numero cosseno(Numero a) {
        return new NumeroImpl(Math.cos(a.getValor()));
    };

    @Override
    public Numero tangente(Numero a) {
        return new NumeroImpl(Math.tan(a.getValor()));
    };

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

}
