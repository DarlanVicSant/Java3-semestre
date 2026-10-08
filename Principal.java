import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;

public class Principal {

    public static void main(String[] args) {
        ArrayList<Carro> listaCarros = new ArrayList<>();
        int opcao = 0;

        do {
            String menu = "--- SISTEMA DE CADASTRO DE CARROS ---\n\n"
                    + "1 - Cadastrar Carro\n"
                    + "2 - Listar Carros\n"
                    + "3 - Detalhar Carro\n"
                    + "4 - Alterar Carro\n"
                    + "5 - Remover Carro\n"
                    + "6 - Gravar Informações em Arquivo\n"
                    + "7 - Sair\n\n"
                    + "Escolha uma opção:";

            String entrada = JOptionPane.showInputDialog(menu);

            if (entrada == null) {
                break;
            }

            try {
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opção inválida! Digite um número.");
                continue;
            }

            switch (opcao) {
                case 1:
                    String marca = JOptionPane.showInputDialog("Digite a marca do carro:");
                    if (marca == null) break;

                    String modelo = JOptionPane.showInputDialog("Digite o modelo do carro:");
                    if (modelo == null) break;

                    String strAno = JOptionPane.showInputDialog("Digite o ano do carro:");
                    if (strAno == null) break;

                    try {
                        int ano = Integer.parseInt(strAno);
                        Carro c = new Carro(marca, modelo, ano);
                        listaCarros.add(c);
                        JOptionPane.showMessageDialog(null, "Carro cadastrado com sucesso!");
                    } catch (NumberFormatException e) {
                        JOptionPane.showMessageDialog(null, "Ano inválido! Operação cancelada.");
                    }
                    break;

                case 2:
                    if (listaCarros.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum carro cadastrado.");
                    } else {
                        String relatorio = "=== LISTA DE CARROS ===\n";
                        for (int i = 0; i < listaCarros.size(); i++) {
                            Carro car = listaCarros.get(i);
                            relatorio += (i + 1) + " - Marca: " + car.getMarca() + " | Modelo: " + car.getModelo() + "\n";
                        }
                        JOptionPane.showMessageDialog(null, relatorio);
                    }
                    break;

                case 3:
                    if (listaCarros.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum carro cadastrado.");
                    } else {
                        String strIndice = JOptionPane.showInputDialog("Digite o número do carro que deseja detalhar:");
                        if (strIndice == null) break;

                        try {
                            int pos = Integer.parseInt(strIndice) - 1;
                            if (pos >= 0 && pos < listaCarros.size()) {
                                Carro car = listaCarros.get(pos);
                                JOptionPane.showMessageDialog(null, car.exibirDetalhes());
                            } else {
                                JOptionPane.showMessageDialog(null, "Número do carro inválido!");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Valor digitado é inválido!");
                        }
                    }
                    break;

                case 4:
                    if (listaCarros.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum carro cadastrado.");
                    } else {
                        String strIndice = JOptionPane.showInputDialog("Digite o número do carro que deseja alterar:");
                        if (strIndice == null) break;

                        try {
                            int pos = Integer.parseInt(strIndice) - 1;
                            if (pos >= 0 && pos < listaCarros.size()) {
                                String novaMarca = JOptionPane.showInputDialog("Digite a nova marca:");
                                String novoModelo = JOptionPane.showInputDialog("Digite o novo modelo:");
                                String strNovoAno = JOptionPane.showInputDialog("Digite o novo ano:");

                                int novoAno = Integer.parseInt(strNovoAno);

                                Carro car = listaCarros.get(pos);
                                car.setMarca(novaMarca);
                                car.setModelo(novoModelo);
                                car.setAno(novoAno);

                                JOptionPane.showMessageDialog(null, "Dados do carro alterados com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Número do carro inválido!");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Entrada de dados inválida!");
                        }
                    }
                    break;

                case 5:
                    if (listaCarros.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Nenhum carro cadastrado.");
                    } else {
                        String strIndice = JOptionPane.showInputDialog("Digite o número do carro que deseja remover:");
                        if (strIndice == null) break;

                        try {
                            int pos = Integer.parseInt(strIndice) - 1;
                            if (pos >= 0 && pos < listaCarros.size()) {
                                listaCarros.remove(pos);
                                JOptionPane.showMessageDialog(null, "Carro removido com sucesso!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Número do carro inválido!");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Número inválido!");
                        }
                    }
                    break;

                case 6:
                    if (listaCarros.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Não há carros cadastrados para gravar.");
                    } else {
                        try {
                            FileWriter arq = new FileWriter("carros.txt");
                            PrintWriter gravarArq = new PrintWriter(arq);

                            for (int i = 0; i < listaCarros.size(); i++) {
                                Carro c = listaCarros.get(i);
                                gravarArq.println("Marca: " + c.getMarca());
                                gravarArq.println("Modelo: " + c.getModelo());
                                gravarArq.println("Ano: " + c.getAno());
                                gravarArq.println("----------------------------------");
                            }

                            arq.close();
                            JOptionPane.showMessageDialog(null, "Informações gravadas em 'carros.txt' com sucesso!");
                        } catch (IOException e) {
                            JOptionPane.showMessageDialog(null, "Erro ao gravar no arquivo: " + e.getMessage());
                        }
                    }
                    break;

                case 7:
                    JOptionPane.showMessageDialog(null, "Saindo do sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida! Escolha de 1 a 7.");
                    break;
            }

        } while (opcao != 7);
    }
}