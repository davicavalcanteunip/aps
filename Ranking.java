import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import java.awt.datatransfer.*;

public class Ranking extends WindowAdapter implements ActionListener, KeyListener {
    private JFrame janela;
    private JPanel painelRanking, painelBotao;
    private JButton verRanking;
    private JTable tabela;
    private JScrollPane scrollPane;

    private Main main;

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();

    public Ranking() {
        janela = new JFrame();
        janela.setTitle("Cronometro");
        janela.setSize(500, 700);
        janela.setBackground(new Color(250, 250, 250));
        janela.setLayout(new BorderLayout());
        janela.addWindowListener(this);

        painelBotao = new JPanel();
        painelBotao.setBackground(new Color(0, 0, 0));

        verRanking = new JButton("Ver Ranking");
        verRanking.addActionListener(this);
        painelBotao.add(verRanking);

        janela.add(painelBotao, BorderLayout.NORTH);

        painelRanking = new JPanel();
        painelRanking.setLayout(new BorderLayout());
        janela.add(painelRanking, BorderLayout.CENTER);

        janela.addKeyListener(this);
        janela.getKeyListeners();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verRanking) {
            // dadosRanking é uma instancia da classe DadosRanking
            DadosRanking dadosRanking = new DadosRanking();

            // List<DadosEquipe> resultados é uma lista retornada pelo método buscarDados da classe DadosRanking
            List<DadosEquipe> resultados = dadosRanking.buscarDados();

            // Colections trabalha para operar e retornar e Coleções =>  Nesse caso, retorna ordenadamente do menor tempo total para o maior
            Collections.sort(resultados, Comparator.comparingLong(DadosEquipe::getTotalMillis));

            // Array das opções que vão nas colunas da tabela
            String[] colunas = {"Equipe", "Piloto", "Volta 1", "Volta 2", "Total"};

            // Cria uma matriz que receberá os dados a serem inseridos na tabela
            String[][] dados = new String[resultados.size()][5];

            // Loop que percorre sobre as posições da matriz e insere os dados
            for (int i = 0; i < resultados.size(); i++) {
                DadosEquipe equipe = resultados.get(i);
                dados[i][0] = equipe.getEquipe();
                dados[i][1] = equipe.getPiloto();
                dados[i][2] = equipe.getVolta1();
                dados[i][3] = equipe.getVolta2();
                dados[i][4] = equipe.getTotal();
            }

            // Cria nova tabela
            tabela = new JTable(dados, colunas);

            // Insere a tabela dentro de um painel de scroll
            scrollPane = new JScrollPane(tabela);

            // Renderiza novamente o painel com os dados da tabela
            painelRanking.removeAll();
            painelRanking.add(scrollPane);
            painelRanking.revalidate();
            painelRanking.repaint();

            // Adiciona escutador de teclas a tabela, para impedir o print da tela mesmo com o foco na tabela
            tabela.addKeyListener(this);
            tabela.getKeyListeners();
            janela.requestFocusInWindow();
        }
    }

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {
        // Impede que vaze informações dos dados da tela
        if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {
            String textoSeguranca = "Não é permitido tirar print dessa tela!";
            StringSelection selection = new StringSelection(textoSeguranca);
            clipboard.setContents(selection, null);
            minimizarJanelaRanking();
        }
    }

    public void minimizarJanelaRanking() {
        janela.setState(Frame.ICONIFIED);
        this.main.minimizarJanelaMain();
    }

    public void keyTyped(KeyEvent e) {}

    public void setMain(Main main) {
        this.main = main;
    }

    public void mostraJanela() {
        janela.setVisible(true);
        janela.requestFocusInWindow();
    }

    public void windowClosed(WindowEvent e) {}
}