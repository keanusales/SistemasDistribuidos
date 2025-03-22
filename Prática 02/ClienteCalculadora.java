import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class ClienteCalculadora {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Uso: ClienteCalculadora <IP> <Porta>");
            System.exit(1);
        }
        String ip = args[0];
        int porta = Integer.parseInt(args[1]);

        try {
            // Localiza o registry usando o endereço IP e a porta fornecidos
            Registry registry = LocateRegistry.getRegistry(ip, porta);
            // Consulta o registry e obtém o stub para o objeto remoto
            Calculadora calc = (Calculadora) registry.lookup("calculadora");
            // A partir deste momento, chamadas à Calculadora podem ser
            // feitas como qualquer chamada a métodos

            try (Scanner input = new Scanner(System.in)) {

                int choice = -1;

                while (choice != 0) {
                    System.out.println("Calculator Main Menu\n");
                    System.out.print("1.) Addition \n");
                    System.out.print("2.) Subtraction.\n");
                    System.out.print("3.) Multiplication.\n");
                    System.out.print("4.) Division.\n");
                    System.err.print("5.) Exponentiation.\n");
                    System.out.print("6.) Square Root.\n");
                    System.out.print("7.) Logarithm.\n");
                    System.out.print("8.) Sine.\n");
                    System.out.print("9.) Cosine.\n");
                    System.out.print("10.) Tangent.\n");
                    System.out.print("11.) Absolute Value.\n");
                    System.out.print("12.) Ceiling.\n");
                    System.out.print("13.) Floor.\n");
                    System.out.print("14.) Round.\n");
                    System.out.print("15.) Maximum.\n");
                    System.out.print("16.) Minimum.\n");
                    System.out.print("17.) Cube Root.\n");
                    System.out.print("18.) Natural Logarithm.\n");
                    System.out.print("19.) Exit.\n");
                    System.out.print("\nEnter Your Menu Choice: ");

                    choice = input.nextInt();

                    switch (choice) {
                        case 1 -> {
                            Numero adNumf, adNuml, sum;
                            System.out.print("Please Enter The First Number: ");
                            adNumf = new NumeroImpl(input.nextInt());
                            System.out.print("\nPlease Enter The Second Number: ");
                            adNuml = new NumeroImpl(input.nextInt());
                            sum = calc.soma(adNumf, adNuml);
                            System.out.print("The Sum Of Those Numbers is: " + sum.getValor() + "\n");
                        }

                        case 2 -> {
                            Numero subNum1, subNum2, sub;
                            System.out.println("\nPlease Enter The First Number: ");
                            subNum1 = new NumeroImpl(input.nextInt());
                            System.out.println("Please Enter The Second Number: ");
                            subNum2 = new NumeroImpl(input.nextInt());
                            sub = calc.subtrai(subNum1, subNum2);
                            System.out.println("The Subtraction Leaves The Number: " + sub.getValor() + "\n");
                        }

                        case 3 -> {
                            Numero multNum1, multNum2, multTotal;
                            // Gather Input
                            System.out.println("Please Enter The First Number To Multiply: ");
                            multNum1 = new NumeroImpl(input.nextInt());
                            System.out.println("Please Enter The Second Number To Multiply: ");
                            multNum2 = new NumeroImpl(input.nextInt());
                            // This will Multiply the Numbers
                            multTotal = calc.multiplica(multNum1, multNum2);
                            //Display Final
                            System.out.println("The Multiplied Numbers Are: " + multTotal.getValor() + "\n");
                        }

                        case 4 -> {
                            Numero divNum1, divNum2, divTotal;
                            System.out.println("Enter Your Numerator ");
                            divNum1 = new NumeroImpl(input.nextInt());
                            System.out.println("Enter Your Denominator ");
                            divNum2 = new NumeroImpl(input.nextInt());
                            try {
                                divTotal = calc.divide(divNum1, divNum2);
                            } catch (DivisaoPorZeroException e) {
                                System.out.println("You Tried To Divide By Zero! This is a Remote Exception.");
                                break;
                            }
                            System.out.println("Your divisor is: " + divTotal.getValor() + "\n");
                        }

                        case 5 -> {
                            Numero potNum1, potNum2, potTotal;
                            System.out.println("Enter Your Base Number ");
                            potNum1 = new NumeroImpl(input.nextInt());
                            System.out.println("Enter Your Exponent ");
                            potNum2 = new NumeroImpl(input.nextInt());
                            potTotal = calc.potencia(potNum1, potNum2);
                            System.out.println("Your Exponential Value is: " + potTotal.getValor() + "\n");
                        }

                        case 6 -> {
                            Numero sqrtNum, sqrtTotal;
                            System.out.println("Enter Your Number ");
                            sqrtNum = new NumeroImpl(input.nextInt());
                            try {
                                sqrtTotal = calc.raiz(sqrtNum);
                            } catch (DivisaoPorZeroException e) {
                                System.out.println("You Tried To Divide By Zero! This is a Remote Exception.");
                                break;
                            }
                            System.out.println("The Square Root of Your Number is: " + sqrtTotal.getValor() + "\n");
                        }

                        case 7 -> {
                            Numero logNum, logTotal;
                            System.out.println("Enter Your Number ");
                            logNum = new NumeroImpl(input.nextInt());
                            try {
                                logTotal = calc.log(logNum);
                            } catch (DivisaoPorZeroException e) {
                                System.out.println("You Tried To Divide By Zero! This is a Remote Exception.");
                                break;
                            }
                            System.out.println("The Logarithm of Your Number is: " + logTotal.getValor() + "\n");
                        }

                        case 8 -> {
                            Numero sinNum, sinTotal;
                            System.out.println("Enter Your Number ");
                            sinNum = new NumeroImpl(input.nextInt());
                            sinTotal = calc.seno(sinNum);
                            System.out.println("The Sine of Your Number is: " + sinTotal.getValor() + "\n");
                        }

                        case 9 -> {
                            Numero cosNum, cosTotal;
                            System.out.println("Enter Your Number ");
                            cosNum = new NumeroImpl(input.nextInt());
                            cosTotal = calc.cosseno(cosNum);
                            System.out.println("The Cosine of Your Number is: " + cosTotal.getValor() + "\n");
                        }

                        case 10 -> {
                            Numero tanNum, tanTotal;
                            System.out.println("Enter Your Number ");
                            tanNum = new NumeroImpl(input.nextInt());
                            tanTotal = calc.tangente(tanNum);
                            System.out.println("The Tangent of Your Number is: " + tanTotal.getValor() + "\n");
                        }

                        case 11 -> {
                            Numero absNum, absTotal;
                            System.out.println("Enter Your Number ");
                            absNum = new NumeroImpl(input.nextInt());
                            absTotal = calc.valorAbsoluto(absNum);
                            System.out.println("The Absolute Value of Your Number is: " + absTotal.getValor() + "\n");
                        }

                        case 12 -> {
                            Numero ceilNum, ceilTotal;
                            System.out.println("Enter Your Number ");
                            ceilNum = new NumeroImpl(input.nextInt());
                            ceilTotal = calc.teto(ceilNum);
                            System.out.println("The Ceiling of Your Number is: " + ceilTotal.getValor() + "\n");
                        }

                        case 13 -> {
                            Numero floorNum, floorTotal;
                            System.out.println("Enter Your Number ");
                            floorNum = new NumeroImpl(input.nextInt());
                            floorTotal = calc.piso(floorNum);
                            System.out.println("The Floor of Your Number is: " + floorTotal.getValor() + "\n");
                        }

                        case 14 -> {
                            Numero roundNum, roundTotal;
                            System.out.println("Enter Your Number ");
                            roundNum = new NumeroImpl(input.nextInt());
                            roundTotal = calc.arredondar(roundNum);
                            System.out.println("The Rounded Value of Your Number is: " + roundTotal.getValor() + "\n");
                        }

                        case 15 -> {
                            Numero maxNum1, maxNum2, maxTotal;
                            System.out.println("Enter The First Number ");
                            maxNum1 = new NumeroImpl(input.nextInt());
                            System.out.println("Enter The Second Number ");
                            maxNum2 = new NumeroImpl(input.nextInt());
                            maxTotal = calc.maximo(maxNum1, maxNum2);
                            System.out.println("The Maximum Value is: " + maxTotal.getValor() + "\n");
                        }

                        case 16 -> {
                            Numero minNum1, minNum2, minTotal;
                            System.out.println("Enter The First Number ");
                            minNum1 = new NumeroImpl(input.nextInt());
                            System.out.println("Enter The Second Number ");
                            minNum2 = new NumeroImpl(input.nextInt());
                            minTotal = calc.minimo(minNum1, minNum2);
                            System.out.println("The Minimum Value is: " + minTotal.getValor() + "\n");
                        }

                        case 17 -> {
                            Numero cbrtNum, cbrtTotal;
                            System.out.println("Enter Your Number ");
                            cbrtNum = new NumeroImpl(input.nextInt());
                            cbrtTotal = calc.raizCubica(cbrtNum);
                            System.out.println("The Cube Root of Your Number is: " + cbrtTotal.getValor() + "\n");
                        }

                        case 18 -> {
                            Numero lnNum, lnTotal;
                            System.out.println("Enter Your Number ");
                            lnNum = new NumeroImpl(input.nextInt());
                            lnTotal = calc.logNatural(lnNum);
                            System.out.println("The Natural Logarithm of Your Number is: " + lnTotal.getValor() + "\n");
                        }

                        case 19 -> {
                            System.out.println("Exiting Program...");
                            System.exit(0);
                        }

                        default ->
                            System.out.println("This is not a valid Menu Option! Please Select Another");
                    }
                }

                try {
                    calc.divide(new NumeroImpl(1), new NumeroImpl(0));
                } catch (DivisaoPorZeroException e) {
                    System.out.println(
                            "Tentou dividir por zero! Esta é uma exceção remota.");
                }

            }

        } catch (NotBoundException | RemoteException e) {
            System.err.println("Ocorreu um erro no cliente: " + e.toString());
        }
    }
}