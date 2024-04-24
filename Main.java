import java.awt.*;
import java.awt.event.*;

public class Main extends WindowAdapter implements ActionListener {
    private Frame janela;
    private Panel painelTempo, painelBotoes, painelVoltas;
    private Label txtMinutos, txtSegundos, txtMilesimos, txtNumeroVolta, txtTempoVolta, txtTempoTotal;
    private TextField telaMinutos, telaSegundos, telaMilesimos, telaNumeroVolta, telaTempoVolta, telaTempoTotal;
    private Button iniciar, volta, parar;

    private Cronometro cronometro;

    public Main() {
        janela = new Frame();
        janela.setTitle("Cronometro");
        janela.setSize(500, 300);
        janela.setBackground(new Color(250, 250, 250));
        janela.setLayout(null);

        painelTempo = new Panel();
        painelTempo.setSize(480, 100);
        painelTempo.setLocation(10,20);
        painelTempo.setBackground(new Color(0, 0, 0));
        painelTempo.setLayout(null);

        painelBotoes = new Panel();
        painelBotoes.setSize(480, 40);
        painelBotoes.setLocation(10,120);
        painelBotoes.setBackground(new Color(0, 0, 0));
        painelBotoes.setLayout(new FlowLayout());

        painelVoltas = new Panel();
        painelVoltas.setSize(440, 120);
        painelVoltas.setLocation(30,165);
        painelVoltas.setBackground(new Color(20, 20, 20));
        painelVoltas.setLayout(null);

        txtMinutos = new Label("Minutos:");
        txtSegundos = new Label("Segundos:");
        txtMilesimos = new Label("Milesimos:");

        txtMinutos.setBounds(45, 35, 120, 20);
        txtMinutos.setForeground(Color.WHITE);
        txtMinutos.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtSegundos.setBounds(185, 35, 120, 20);
        txtSegundos.setForeground(Color.WHITE);
        txtSegundos.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtMilesimos.setBounds(325, 35, 120, 20);
        txtMilesimos.setForeground(Color.WHITE);
        txtMilesimos.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        telaMinutos = new TextField(10);
        telaSegundos = new TextField(10);
        telaMilesimos = new TextField(10);

        telaMinutos.setBounds(45, 55, 120, 20);
        telaSegundos.setBounds(185, 55, 120, 20);
        telaMilesimos.setBounds(325, 55, 120, 20);

        painelTempo.add(txtMinutos);
        painelTempo.add(txtMilesimos);
        painelTempo.add(txtSegundos);

        painelTempo.add(telaMinutos);
        painelTempo.add(telaSegundos);
        painelTempo.add(telaMilesimos);

        parar = new Button("Parar");
        parar.addActionListener(this);

        volta = new Button("Volta");
        volta.addActionListener(this);

        iniciar = new Button("Iniciar");
        iniciar.addActionListener(this);

        painelBotoes.add(parar);
        painelBotoes.add(volta);
        painelBotoes.add(iniciar);

        txtNumeroVolta = new Label("Número da Volta:");
        txtNumeroVolta.setForeground(Color.WHITE);
        txtNumeroVolta.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtTempoVolta = new Label("Tempo da Volta:");
        txtTempoVolta.setForeground(Color.WHITE);
        txtTempoVolta.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtTempoTotal = new Label("Tempo Total:");
        txtTempoTotal.setForeground(Color.WHITE);
        txtTempoTotal.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        telaNumeroVolta = new TextField(10);
        telaTempoVolta = new TextField(10);
        telaTempoTotal = new TextField(10);

        txtNumeroVolta.setBounds(20, 20, 120, 20);
        txtTempoVolta.setBounds(160, 20, 120, 20);
        txtTempoTotal.setBounds(300, 20, 120, 20);

        telaNumeroVolta.setBounds(20, 40, 120, 20);
        telaTempoVolta.setBounds(160, 40, 120, 20);
        telaTempoTotal.setBounds(300, 40, 120, 20);

        painelVoltas.add(txtNumeroVolta);
        painelVoltas.add(txtTempoVolta);
        painelVoltas.add(txtTempoTotal);

        painelVoltas.add(telaNumeroVolta);
        painelVoltas.add(telaTempoVolta);
        painelVoltas.add(telaTempoTotal);

        janela.add(painelTempo);
        janela.add(painelBotoes);
        janela.add(painelVoltas);
    }

    public TextField getTelaMinutos() {
        return telaMinutos;
    }

    public TextField getTelaSegundos() {
        return telaSegundos;
    }

    public TextField getTelaMilesimos() {
        return telaMilesimos;
    }

    public TextField getTelaNumeroVolta() {
        return telaNumeroVolta;
    }
    
    public TextField getTelaTempoVolta() {
        return telaTempoVolta;
    }
    
    public TextField getTelaTempoTotal() {
        return telaTempoTotal;
    }

    public void mostraAgenda() {
        janela.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)	{
        if (e.getSource() == iniciar) {
            if (cronometro == null || !cronometro.isRunning()) {
                cronometro = new Cronometro();
                cronometro.setMain(this);
                cronometro.start();
            }
        } else if (e.getSource() == parar) {
            if (cronometro != null && cronometro.isRunning()) {
                cronometro.stopCronometro();
            }
        } else if (e.getSource() == volta) {
            if (cronometro != null && cronometro.isRunning()) {
                cronometro.reset();
            }
        }
	}

    public static void main(String[] args) {
        Main agenda = new Main();
        agenda.mostraAgenda();
    }
}