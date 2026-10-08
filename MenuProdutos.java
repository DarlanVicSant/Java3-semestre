import javax.swing.JOptionPane;

public class MenuProdutos {

    public static void main(String[] args) {
        String menu = """
                === GERENCIADOR DE PRODUTOS ===
                1. Cadastrar Produto
                2. Listar Produtos
                3. Buscar Produto
                4. Sair
                
                Escolha uma opção:
                """;

        String listaProdutos = "";
        int quantidadeProdutos = 0;
        boolean executando = true;

        while (executando) {
            String opcaoStr = JOptionPane.showInputDialog(null, menu, "Menu Principal", JOptionPane.QUESTION_MESSAGE);

            // Caso o usuário clique em 'Cancelar' ou feche a janela
            if (opcaoStr == null) {
                break;
            }

            switch (opcaoStr) {
                case "1" -> {
                    String nome = JOptionPane.showInputDialog(null, "Digite o nome do produto:", "Cadastrar", JOptionPane.QUESTION_MESSAGE);
                    
                    if (nome != null && !nome.trim().isEmpty()) {
                        String precoStr = JOptionPane.showInputDialog(null, "Digite o preço do produto (ex: 49.90):", "Cadastrar", JOptionPane.QUESTION_MESSAGE);
                        
                        try {
                            double preco = Double.parseDouble(precoStr.replace(",", "."));
                            listaProdutos += String.format("- %s : R$ %.2f\n", nome.trim(), preco);
                            quantidadeProdutos++;
                            
                            JOptionPane.showMessageDialog(null, "Produto \"" + nome + "\" cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Preço inválido! O produto não foi cadastrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }

                case "2" -> {
                    if (quantidadeProdutos == 0) {
                        JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado até o momento.", "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Produtos Cadastrados:\n\n" + listaProdutos, "Lista de Produtos", JOptionPane.INFORMATION_MESSAGE);
                    }
                }

                case "3" -> {
                    if (quantidadeProdutos == 0) {
                        JOptionPane.showMessageDialog(null, "Nenhum produto cadastrado para buscar.", "Busca", JOptionPane.WARNING_MESSAGE);
                    } else {
                        String busca = JOptionPane.showInputDialog(null, "Digite o nome do produto que deseja buscar:", "Buscar Produto", JOptionPane.QUESTION_MESSAGE);
                        
                        if (busca != null && !busca.trim().isEmpty()) {
                            if (listaProdutos.toLowerCase().contains(busca.trim().toLowerCase())) {
                                JOptionPane.showMessageDialog(null, "Produto encontrado na lista!", "Resultado da Busca", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Produto não encontrado.", "Resultado da Busca", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                    }
                }

                case "4" -> executando = false;

                default -> JOptionPane.showMessageDialog(null, "Opção inválida! Escolha um número de 1 a 4.", "Atenção", JOptionPane.WARNING_MESSAGE);
            }
        }

        JOptionPane.showMessageDialog(null, "Programa encerrado. Até logo!", "Sair", JOptionPane.INFORMATION_MESSAGE);
    }
}