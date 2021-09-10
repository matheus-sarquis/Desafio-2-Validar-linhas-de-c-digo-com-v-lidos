import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class App {
    public static Scanner scan;

    public static void main(String[] args) throws IOException {
        ArrayList<String> entrada = new ArrayList<String>();
        try {
            scan = new Scanner(new FileReader("prog.txt"));

            while (scan.hasNextLine()) {
                entrada.add(scan.nextLine());
            }
        } catch (IOException e) {
            System.err.printf("Erro no arquivo: %s.\n", e.getMessage());
        }

        FileWriter saida = new FileWriter("prog-check.txt");
        PrintWriter gravaArq = new PrintWriter(saida);
        int linhas = entrada.size();
        Stack<Character> listaCaract = new Stack<Character>();

        for (int u = 0; u < linhas; u++) {
            boolean invalid = false;
            String result = entrada.get(u).toString();
            System.out.println(result);

            for (int i = 0; i < result.length(); i++) {
                if (result.charAt(i) == '[')
                    listaCaract.push('[');
                else if (result.charAt(i) == '{')
                    listaCaract.push('{');
                else if (result.charAt(i) == '<')
                    listaCaract.push('<');
                else if (result.charAt(i) == '(')
                    listaCaract.push('(');
                else if (result.charAt(i) == ']') {
                    if (listaCaract.isEmpty())
                        invalid = true;
                    else if (listaCaract.pop() != '[')
                        invalid = true;
                } else if (result.charAt(i) == '}') {
                    if (listaCaract.isEmpty())
                        invalid = true;
                    else if (listaCaract.pop() != '{')
                        invalid = true;
                } else if (result.charAt(i) == ')') {
                    if (listaCaract.isEmpty())
                        invalid = true;
                    else if (listaCaract.pop() != '(')
                        invalid = true;

                } else if (result.charAt(i) == '>') {
                    if (listaCaract.isEmpty())
                        invalid = true;

                    else if (listaCaract.pop() != '<')
                        invalid = true;
                }
            }

            listaCaract.clear();

            if (invalid == true) {
                result = result + " - Inválido";
            } else {
                result = result + " - OK";
            }
            try {
                gravaArq.println(result);
            } catch (Exception e) {
                System.err.printf("Erro no salvamento: %s.\n", e.getMessage());
            }
        }
        saida.close();
    }
}