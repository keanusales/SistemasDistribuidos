import java.awt.*;
import java.awt.event.ActionEvent;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import javax.swing.*;

public class ClienteCalculadoraGUI extends JFrame {

    private Calculadora calc;
    private final JTextField num1Field, num2Field, resultField;

    public ClienteCalculadoraGUI(String ip, int porta, String nomeServidor) {
        try {
            Registry registry = LocateRegistry.getRegistry(ip, porta);
            calc = (Calculadora) registry.lookup(nomeServidor);
        } catch (NotBoundException | RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao servidor: " + e.toString(),
                "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Calculadora RMI");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(7, 4)); // Ajustado para acomodar mais botões

        num1Field = new JTextField();
        num2Field = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        add(new JLabel("Número 1:"));
        add(num1Field);
        add(new JLabel("Número 2:"));
        add(num2Field);
        add(new JLabel("Resultado:"));
        add(resultField);

        String[] operations = {
            "Soma", "Subtração", "Multiplicação", "Divisão",
            "Potência", "Raiz", "Logaritmo", "Seno", "Cosseno",
            "Tangente", "Valor Absoluto", "Teto", "Piso",
            "Arredondar", "Máximo", "Mínimo", "Raiz Cúbica",
            "Log Natural", "Hipotenusa", "Fatorial"
        };

        for (String operation : operations) {
            JButton button = new JButton(operation);
            button.addActionListener((ActionEvent e) -> {
                calcular(operation);
            });
            add(button);
        }
    }

    private void calcular(String operation) {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            Numero a = new NumeroImpl(num1);
            Numero b = new NumeroImpl(num2);

            Numero result = null;

            switch (operation) {
                case "Soma" -> result = calc.soma(a, b);
                case "Subtração" -> result = calc.subtrai(a, b);
                case "Multiplicação" -> result = calc.multiplica(a, b);
                case "Divisão" -> result = calc.divide(a, b);
                case "Potência" -> result = calc.potencia(a, b);
                case "Raiz" -> result = calc.raiz(a);
                case "Logaritmo" -> result = calc.log(a);
                case "Seno" -> result = calc.seno(a);
                case "Cosseno" -> result = calc.cosseno(a);
                case "Tangente" -> result = calc.tangente(a);
                case "Valor Absoluto" -> result = calc.valorAbsoluto(a);
                case "Teto" -> result = calc.teto(a);
                case "Piso" -> result = calc.piso(a);
                case "Arredondar" -> result = calc.arredondar(a);
                case "Máximo" -> result = calc.maximo(a, b);
                case "Mínimo" -> result = calc.minimo(a, b);
                case "Raiz Cúbica" -> result = calc.raizCubica(a);
                case "Log Natural" -> result = calc.logNatural(a);
                case "Hipotenusa" -> result = calc.hipotenusa(a, b);
                case "Fatorial" -> result = calc.fatorial(a);
            }

            if (result != null) {
                resultField.setText(String.valueOf(result.getValor()));
            } else {
                resultField.setText("Erro");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira números válidos.",
                "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Erro no servidor: " + e.toString(),
                "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            JOptionPane.showMessageDialog(null, "Uso: ClienteCalculadoraGUI <IP> <Porta> <NomeServidor>",
                "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        String ip = args[0];
        int porta = Integer.parseInt(args[1]);
        String nomeServidor = args[2];

        SwingUtilities.invokeLater(() -> {
            ClienteCalculadoraGUI gui = new ClienteCalculadoraGUI(ip, porta, nomeServidor);
            gui.setVisible(true);
        });
    }
}