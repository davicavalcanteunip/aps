public class Cronometro extends Thread {
    private int numeroVolta;
    private int tempoVolta;
    private int tempoTotal;

    private int minutos;
    private int segundos;
    private int milesimos;

    private boolean running;

    private Main main;

    public Cronometro() {
        minutos = 0;
        segundos = 0;
        milesimos = 0;
        running = true;
    }

    public void setMain(Main main) {
        this.main = main;
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
        while (running) {
            try {
                Thread.sleep(10);
                milesimos++;

                if (milesimos == 100) {
                    milesimos = 0;
                    segundos++;

                    if (segundos == 60) {
                        segundos = 0;
                        minutos++;
                    }
                }

                atualizarTela();

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
        // minutos = 0;
        // segundos = 0;
        // milesimos = 0;
        registrarVolta();
    }

    private void registrarVolta() {
        numeroVolta++;
        tempoVolta = (minutos * 60 * 100 + segundos * 100 + milesimos) - tempoTotal;
        tempoTotal += tempoVolta;
    }  

    private void atualizarTela() {
        if (main != null && main.getTelaMinutos() != null &&
            main.getTelaSegundos() != null && main.getTelaMilesimos() != null &&
            main.getTelaNumeroVolta() != null && main.getTelaTempoVolta() != null &&
            main.getTelaTempoTotal() != null) {
    
            main.getTelaMinutos().setText(String.format("%02d", minutos));
            main.getTelaSegundos().setText(String.format("%02d", segundos));
            main.getTelaMilesimos().setText(String.format("%02d", milesimos));
            main.getTelaNumeroVolta().setText(Integer.toString(numeroVolta));
            main.getTelaTempoVolta().setText(formatarTempo(tempoVolta));
            main.getTelaTempoTotal().setText(formatarTempo(tempoTotal));
        }
    }
    
    private String formatarTempo(int tempo) {
        int min = tempo / (60 * 100);
        int sec = (tempo / 100) % 60;
        int mil = tempo % 100;
        return String.format("%02d:%02d:%02d", min, sec, mil);
    }    
}    