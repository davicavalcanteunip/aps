public class Cronometro extends Thread {
    private int numeroVolta;
    private int tempoVolta;
    private int tempoTotal;

    private int minutos;
    private int segundos;
    private int centesimos;

    private boolean running;

    private Main main;
    private Dados envia;

    public Cronometro() {
        minutos = 0;
        segundos = 0;
        centesimos = 0;

        running = true;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setEnvia(Dados envia) {
        this.envia = envia;
    }

    public int getNumeroVolta() {
        return numeroVolta;
    }
    
    public int getTempoVolta() {
        return tempoVolta;
    }
    
    public int getTempoTotal() {
        return tempoTotal;
    }  

    @Override
    public void run() {
        zerarVolta();
        long tempoInicial = System.currentTimeMillis();
        while (running) {
            try {
                long tempoDecorrido = System.currentTimeMillis() - tempoInicial;
                centesimos = (int) (tempoDecorrido % 100);
                segundos = (int) ((tempoDecorrido / 1000) % 60);
                minutos = (int) (tempoDecorrido / (60 * 1000));
                atualizarTela();
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void stopCronometro() {
        running = false;
    }

    public void reset() {
        registrarVolta();
    }

    private void registrarVolta() {
        numeroVolta++;
        tempoVolta = (minutos * 60 * 100 + segundos * 100 + centesimos) - tempoTotal;
        tempoTotal += tempoVolta;
        atualizarVolta();
    }    

    private void atualizarTela() {
        if (main != null && main.getTelaTempo() != null &&
            main.getTelaNumeroVolta() != null && main.getTelaTempoVolta() != null &&
            main.getTelaTempoTotal() != null) {
            main.getTelaTempo().setText(String.format("%02d:%02d.%02d", minutos, segundos, centesimos));
        }
    }

    private void zerarVolta() {
        main.getTelaNumeroVolta().setText("");
        main.getTelaTempoVolta().setText("");
        main.getTelaTempoTotal().setText("");
        main.getTelaNumeroVolta2().setText("");
        main.getTelaTempoVolta2().setText("");
        main.getTelaTempoTotal2().setText("");
    }

    private void limparEquipe() {
        main.getTelaEquipe().setText("");
        main.getTelaPiloto().setText("");
    }

    private void limparTempo() {
        main.getTelaTempo().setText("00:00:00");
    }

    public String getEquipe() {
        return main.getTelaEquipe().getText();
    }

    public String getPiloto() {
        return main.getTelaPiloto().getText();
    }

    public String getVolta1() {
        return main.getTelaTempoVolta().getText();
    }

    public String getVolta2() {
        return main.getTelaTempoVolta2().getText();
    }

    public String getTotal() {
        return main.getTelaTempoTotal2().getText();
    }

    private void atualizarVolta() {
        if (numeroVolta == 1) {
            main.getTelaNumeroVolta().setText(Integer.toString(numeroVolta));
            main.getTelaTempoVolta().setText(formatarTempo(tempoVolta));
            main.getTelaTempoTotal().setText(formatarTempo(tempoTotal));
        } else if (numeroVolta == 2) {
            stopCronometro();
            main.getTelaNumeroVolta2().setText(Integer.toString(numeroVolta));
            main.getTelaTempoVolta2().setText(formatarTempo(tempoVolta));
            main.getTelaTempoTotal2().setText(formatarTempo(tempoTotal));
            envia.enviarDados(this);
        }
    }
    
    private String formatarTempo(long tempo) {
        long min = tempo / (60 * 100);
        long sec = (tempo / 100) % 60;
        long cent = tempo % 100;
        return String.format("%02d:%02d.%02d", min, sec, cent);
    }


    public void resetarCronometro() {
        zerarVolta();
        limparEquipe();
        limparTempo();
    }
}