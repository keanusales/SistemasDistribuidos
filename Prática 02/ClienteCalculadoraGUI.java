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
    private final JComboBox<String> operationComboBox;

    public ClienteCalculadoraGUI(String ip, int porta) {
        try {
            Registry registry = LocateRegistry.getRegistry(ip, porta);
            calc = (Calculadora) registry.lookup("calculadora");
        } catch (NotBoundException | RemoteException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao servidor: " + e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }

        setTitle("Calculadora RMI");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        num1Field = new JTextField();
        num2Field = new JTextField();
        resultField = new JTextField();
        resultField.setEditable(false);

        String[] operations = {"Soma", "Subtração", "Multiplicação", "Divisão", "Potência", "Raiz", "Logaritmo", "Seno", "Cosseno", "Tangente"};
        operationComboBox = new JComboBox<>(operations);

        JButton calculateButton = new JButton("Calcular");
        calculateButton.addActionListener((ActionEvent e) -> {
            calcular();
        });

        add(new JLabel("Número 1:"));
        add(num1Field);
        add(new JLabel("Número 2:"));
        add(num2Field);
        add(new JLabel("Operação:"));
        add(operationComboBox);
        add(new JLabel("Resultado:"));
        add(resultField);
        add(new JLabel());
        add(calculateButton);
    }

    private void calcular() {
        try {
            double num1 = Double.parseDouble(num1Field.getText());
            double num2 = Double.parseDouble(num2Field.getText());
            Numero a = new NumeroImpl(num1);
            Numero b = new NumeroImpl(num2);

            String operation = (String) operationComboBox.getSelectedItem();
            Numero result = null;

            switch (operation) {
                case "Soma" -> result = calc.soma(a, b);
                case "Subtração" -> result = calc.subtrai(a, b);
                case "Multiplicação" -> result = calc.multiplica(a, b);
                case "Divisão" -> {
                    try {
                        result = calc.divide(a, b);
                    } catch (DivisaoPorZeroException e) {
                        JOptionPane.showMessageDialog(this, "Divisão por zero não é permitida.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Potência" -> result = calc.potencia(a, b);
                case "Raiz" -> {
                    try {
                        result = calc.raiz(a);
                    } catch (DivisaoPorZeroException e) {
                        JOptionPane.showMessageDialog(this, "Raiz de número negativo não é permitida.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Logaritmo" -> {
                    try {
                        result = calc.log(a);
                    } catch (DivisaoPorZeroException e) {
                        JOptionPane.showMessageDialog(this, "Logaritmo de número não positivo não é permitido.", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                case "Seno" -> result = calc.seno(a);
                case "Cosseno" -> result = calc.cosseno(a);
                case "Tangente" -> result = calc.tangente(a);
            }

            if (result != null) {
                resultField.setText(String.valueOf(result.getValor()));
            } else {
                resultField.setText("Erro");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, insira números válidos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (RemoteException e) {
            JOptionPane.showMessageDialog(this, "Erro ao comunicar com o servidor: " + e.toString(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            JOptionPane.showMessageDialog(null, "Uso: ClienteCalculadoraGUI <IP> <Porta>", "Erro", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }
        String ip = args[0];
        int porta = Integer.parseInt(args[1]);

        SwingUtilities.invokeLater(() -> {
            ClienteCalculadoraGUI gui = new ClienteCalculadoraGUI(ip, porta);
            gui.setVisible(true);
        });
    }
}