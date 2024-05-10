import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Ranking extends WindowAdapter implements ActionListener {
    private JFrame janela;
    private JPanel painelRanking, painelBotao;
    private JButton verRanking;
    private JTable tabela;
    private JScrollPane scrollPane;

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
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == verRanking) {
            DadosRanking dadosRanking = new DadosRanking();
            List<DadosEquipe> resultados = dadosRanking.buscarDados();

            Collections.sort(resultados, Comparator.comparingLong(DadosEquipe::getTotalMillis));

            String[] colunas = {"Equipe", "Piloto", "Volta 1", "Volta 2", "Total"};
            String[][] dados = new String[resultados.size()][5];

            for (int i = 0; i < resultados.size(); i++) {
                DadosEquipe equipe = resultados.get(i);
                dados[i][0] = equipe.getEquipe();
                dados[i][1] = equipe.getPiloto();
                dados[i][2] = equipe.getVolta1();
                dados[i][3] = equipe.getVolta2();
                dados[i][4] = equipe.getTotal();
            }

            tabela = new JTable(dados, colunas);
            scrollPane = new JScrollPane(tabela);
            painelRanking.removeAll();
            painelRanking.add(scrollPane);
            painelRanking.revalidate();
            painelRanking.repaint();
        }
    }

    public void mostraJanela() {
        janela.setVisible(true);
    }

    public void windowClosed(WindowEvent e) {}
}

