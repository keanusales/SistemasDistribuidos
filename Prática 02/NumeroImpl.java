public class NumeroImpl implements Numero {

    final private double num;

    public NumeroImpl (double val) {
        num = val;
    }

    @Override
    public double getValor() {
        return num;
    }
}
