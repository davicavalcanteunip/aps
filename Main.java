import java.awt.*;
import java.awt.event.*;

public class Main extends WindowAdapter implements ActionListener {
    private Frame janela;
    private Panel painelTempo, painelBotoes, painelVoltas;
    private Label txtNumeroVolta, txtTempoVolta, txtTempoTotal, txtNumeroVolta2, txtTempoVolta2, txtTempoTotal2;
    private TextField telaTempo, telaNumeroVolta, telaTempoVolta, telaTempoTotal, telaNumeroVolta2, telaTempoVolta2, telaTempoTotal2;
    private Button iniciar, volta, parar;

    private Cronometro cronometro;
    private Dados dados;

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
        painelVoltas.setSize(480, 120);
        painelVoltas.setLocation(10,165);
        painelVoltas.setBackground(new Color(20, 20, 20));
        painelVoltas.setLayout(null);

        telaTempo = new TextField(10);
        telaTempo.setBackground(new Color(0, 0, 0));
        telaTempo.setBounds(188, 55, 105, 40);
        telaTempo.setFont(new java.awt.Font("Arial", Font.PLAIN, 26));
        telaTempo.setForeground(Color.RED);
        telaTempo.setText("00:00.00");

        painelTempo.add(telaTempo);

        parar = new Button("Parar");
        parar.addActionListener(this);

        volta = new Button("Volta");
        volta.addActionListener(this);

        iniciar = new Button("Iniciar");
        iniciar.addActionListener(this);

        painelBotoes.add(parar);
        painelBotoes.add(volta);
        painelBotoes.add(iniciar);

        txtNumeroVolta = new Label("Volta:");
        txtNumeroVolta.setForeground(Color.WHITE);
        txtNumeroVolta.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtTempoVolta = new Label("Tempo:");
        txtTempoVolta.setForeground(Color.WHITE);
        txtTempoVolta.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtTempoTotal = new Label("Tempo Total:");
        txtTempoTotal.setForeground(Color.WHITE);
        txtTempoTotal.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtNumeroVolta2 = new Label("Volta:");
        txtNumeroVolta2.setForeground(Color.WHITE);
        txtNumeroVolta2.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtTempoVolta2 = new Label("Tempo:");
        txtTempoVolta2.setForeground(Color.WHITE);
        txtTempoVolta2.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtTempoTotal2 = new Label("Tempo Total:");
        txtTempoTotal2.setForeground(Color.WHITE);
        txtTempoTotal2.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        telaNumeroVolta = new TextField(10);
        telaTempoVolta = new TextField(10);
        telaTempoTotal = new TextField(10);

        txtNumeroVolta.setBounds(40, 20, 120, 20);
        txtTempoVolta.setBounds(180, 20, 120, 20);
        txtTempoTotal.setBounds(320, 20, 120, 20);

        telaNumeroVolta.setBounds(40, 40, 120, 20);
        telaTempoVolta.setBounds(180, 40, 120, 20);
        telaTempoTotal.setBounds(320, 40, 120, 20);

        telaNumeroVolta2 = new TextField(10);
        telaTempoVolta2 = new TextField(10);
        telaTempoTotal2 = new TextField(10);

        txtNumeroVolta2.setBounds(40, 70, 120, 20);
        txtTempoVolta2.setBounds(180, 70, 120, 20);
        txtTempoTotal2.setBounds(320, 70, 120, 20);

        telaNumeroVolta2.setBounds(40, 90, 120, 20);
        telaTempoVolta2.setBounds(180, 90, 120, 20);
        telaTempoTotal2.setBounds(320, 90, 120, 20);

        painelVoltas.add(txtNumeroVolta);
        painelVoltas.add(txtTempoVolta);
        painelVoltas.add(txtTempoTotal);

        painelVoltas.add(telaNumeroVolta);
        painelVoltas.add(telaTempoVolta);
        painelVoltas.add(telaTempoTotal);
        
        painelVoltas.add(txtNumeroVolta2);
        painelVoltas.add(txtTempoVolta2);
        painelVoltas.add(txtTempoTotal2);

        painelVoltas.add(telaNumeroVolta2);
        painelVoltas.add(telaTempoVolta2);
        painelVoltas.add(telaTempoTotal2);

        janela.add(painelTempo);
        janela.add(painelBotoes);
        janela.add(painelVoltas);
    }

    public TextField getTelaTempo() {
        return telaTempo;
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

    public TextField getTelaNumeroVolta2() {
        return telaNumeroVolta2;
    }
    
    public TextField getTelaTempoVolta2() {
        return telaTempoVolta2;
    }
    
    public TextField getTelaTempoTotal2() {
        return telaTempoTotal2;
    }

    public void mostraAgenda() {
        janela.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)	{
        if (e.getSource() == iniciar) {
            if (cronometro == null || !cronometro.isRunning()) {
                cronometro = new Cronometro();
                dados = new Dados();

                cronometro.setMain(this);
                cronometro.setEnvia(dados);
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
        Main inicio = new Main();
        inicio.mostraAgenda();
    }
}