import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.awt.datatransfer.*;

public class Main extends WindowAdapter implements ActionListener, KeyListener {
    private Frame janela;
    private Panel painelTempo, painelBotoes, painelVoltas, painelEquipe, painelVerRanking;
    private Label txtNumeroVolta, txtTempoVolta, txtTempoTotal, txtNumeroVolta2, txtTempoVolta2, txtTempoTotal2, txtEquipe, txtPiloto;
    private TextField telaTempo, telaNumeroVolta, telaTempoVolta, telaTempoTotal, telaNumeroVolta2, telaTempoVolta2, telaTempoTotal2, telaEquipe, telaPiloto;
    private Button iniciar, volta, parar, limpar, verRanking;

    private Cronometro cronometro;
    private Dados dados;
    private Ranking ranking;

    Toolkit toolkit = Toolkit.getDefaultToolkit();
    Clipboard clipboard = toolkit.getSystemClipboard();

    public Main() {
        janela = new Frame();
        janela.setTitle("Cronometro");
        janela.setSize(500, 385);
        janela.setBackground(new Color(250, 250, 250));
        janela.setLayout(null);
        janela.addWindowListener(this);

        painelEquipe = new Panel();
        painelEquipe.setSize(480, 80);
        painelEquipe.setLocation(10,20);
        painelEquipe.setBackground(new Color(20, 20, 20));
        painelEquipe.setLayout(null);

        painelTempo = new Panel();
        painelTempo.setSize(480, 80);
        painelTempo.setLocation(10,100);
        painelTempo.setBackground(new Color(0, 0, 0));
        painelTempo.setLayout(null);

        painelBotoes = new Panel();
        painelBotoes.setSize(480, 40);
        painelBotoes.setLocation(10,180);
        painelBotoes.setBackground(new Color(0, 0, 0));
        painelBotoes.setLayout(new FlowLayout());

        painelVoltas = new Panel();
        painelVoltas.setSize(480, 120);
        painelVoltas.setLocation(10,220);
        painelVoltas.setBackground(new Color(20, 20, 20));
        painelVoltas.setLayout(null);

        painelVerRanking = new Panel();
        painelVerRanking.setSize(480, 35);
        painelVerRanking.setLocation(10,340);
        painelVerRanking.setBackground(new Color(50, 50, 50));
        painelVerRanking.setLayout(new FlowLayout());

        verRanking = new Button("Ranking");
        verRanking.addActionListener(this);

        painelVerRanking.add(verRanking);

        txtEquipe = new Label("Nome da Equipe:");
        txtEquipe.setForeground(Color.WHITE);
        txtEquipe.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        txtPiloto = new Label("Nome do Piloto:");
        txtPiloto.setForeground(Color.WHITE);
        txtPiloto.setFont(new java.awt.Font("Arial", Font.ITALIC, 14));

        telaEquipe = new TextField();
        telaEquipe.setBounds(150, 20,160, 20);
        telaEquipe.setFont(new java.awt.Font("Arial", Font.PLAIN, 15));
        telaEquipe.addKeyListener(this);
        telaEquipe.getKeyListeners();

        telaPiloto = new TextField();
        telaPiloto.setBounds(150, 50,160, 20);
        telaPiloto.setFont(new java.awt.Font("Arial", Font.PLAIN, 15));
        telaPiloto.addKeyListener(this);
        telaPiloto.getKeyListeners();

        txtEquipe.setBounds(15, 15, 130, 30);
        txtPiloto.setBounds(15, 45, 130, 30);

        painelEquipe.add(txtEquipe);
        painelEquipe.add(txtPiloto);

        painelEquipe.add(telaEquipe);
        painelEquipe.add(telaPiloto);

        telaTempo = new TextField(10);
        telaTempo.setBackground(new Color(0, 0, 0));
        telaTempo.setBounds(162, 15,160, 50);
        telaTempo.setFont(new java.awt.Font("Arial", Font.PLAIN, 40));
        telaTempo.setText("00:00.00");
        telaTempo.setForeground(Color.RED);
        telaTempo.setEditable(false);

        painelTempo.add(telaTempo);

        parar = new Button("Parar");
        parar.addActionListener(this);

        volta = new Button("Volta");
        volta.addActionListener(this);

        iniciar = new Button("Iniciar");
        iniciar.addActionListener(this);

        limpar = new Button("Limpar");
        limpar.addActionListener(this);

        painelBotoes.add(limpar);
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

        telaNumeroVolta.setEditable(false);
        telaTempoVolta.setEditable(false);
        telaTempoTotal.setEditable(false);

        txtNumeroVolta.setBounds(40, 20, 120, 20);
        txtTempoVolta.setBounds(180, 20, 120, 20);
        txtTempoTotal.setBounds(320, 20, 120, 20);

        telaNumeroVolta.setBounds(40, 40, 120, 20);
        telaTempoVolta.setBounds(180, 40, 120, 20);
        telaTempoTotal.setBounds(320, 40, 120, 20);

        telaNumeroVolta2 = new TextField(10);
        telaTempoVolta2 = new TextField(10);
        telaTempoTotal2 = new TextField(10);

        telaNumeroVolta2.setEditable(false);
        telaTempoVolta2.setEditable(false);
        telaTempoTotal2.setEditable(false);

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

        janela.add(painelEquipe);
        janela.add(painelTempo);
        janela.add(painelBotoes);
        janela.add(painelVoltas);
        janela.add(painelVerRanking);

        janela.addKeyListener(this);
        janela.getKeyListeners();
        janela.requestFocusInWindow();

        addFocusListenerToTextFields();
    }

    private void addFocusListenerToTextFields() {

        telaTempo.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                janela.requestFocusInWindow();
            }
        });

        telaNumeroVolta.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                janela.requestFocusInWindow();
            }
        });

        telaTempoVolta.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                janela.requestFocusInWindow();
            }
        });

        telaTempoTotal.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                janela.requestFocusInWindow();
            }
        });

        telaNumeroVolta2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                janela.requestFocusInWindow();
            }
        });

        telaTempoVolta2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                janela.requestFocusInWindow();
            }
        });

        telaTempoTotal2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                janela.requestFocusInWindow();
            }
        });
    }

    public void keyPressed(KeyEvent e) {}

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_PRINTSCREEN) {
            String textoSeguranca = "Não é permitido tirar print dessa tela!";
            StringSelection selection = new StringSelection(textoSeguranca);
            clipboard.setContents(selection, null);
        }
    }

    public void keyTyped(KeyEvent e) {}

    public TextField getTelaEquipe() {
        return telaEquipe;
    }

    public TextField getTelaPiloto() {
        return telaPiloto;
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

    public void mostraJanela() {
        janela.setVisible(true);
    }

    public void actionPerformed(ActionEvent e)	{
        if (e.getSource() == iniciar) {
            if (cronometro == null || !cronometro.isRunning()) {
                if (telaEquipe.getText().isEmpty() && telaPiloto.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha os dados da equipe para continuar");
                } else if (telaEquipe.getText().isEmpty() && !telaPiloto.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o nome da equipe!");
                } else if (!telaEquipe.getText().isEmpty() && telaPiloto.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o nome do piloto!");
                } else {
                    cronometro = new Cronometro();
                    dados = new Dados();
                    cronometro.setMain(this);
                    cronometro.setEnvia(dados);
                    cronometro.start();
                }
            }
        } else if (e.getSource() == parar) {
            if (cronometro != null && cronometro.isRunning()) {
                cronometro.stopCronometro();
            }
        } else if (e.getSource() == volta) {
            if (cronometro != null && cronometro.isRunning()) {
                cronometro.reset();
            }
        } else if (e.getSource() == limpar) {
            cronometro.resetarCronometro();
        } else if (e.getSource() == verRanking) {
            ranking = new Ranking();
        }

        janela.requestFocusInWindow();
	}

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public static void main(String[] args) {
        Main inicio = new Main();
        inicio.mostraJanela();
    }
}