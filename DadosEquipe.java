import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DadosEquipe {
    private String equipe;
    private String piloto;
    private String volta1;
    private String volta2;
    private String total;

    public DadosEquipe(String equipe, String piloto, String volta1, String volta2, String total) {
        this.equipe = equipe;
        this.piloto = piloto;
        this.volta1 = volta1;
        this.volta2 = volta2;
        this.total = total;
    }

    // Retorna em tempo os dados do banco de dados que era Strings
    public long getTotalMillis() {
        // Instancia a classe SimpleDateFormat especificando o formato que deve ser esperado por ela
        SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS");
        try {
            // Converte para date a String de tempo
            Date tempoTotalDate = sdf.parse(total);
            return tempoTotalDate.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    public String getEquipe() {
        return equipe;
    }

    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }

    public String getPiloto() {
        return piloto;
    }

    public void setPiloto(String piloto) {
        this.piloto = piloto;
    }

    public String getVolta1() {
        return volta1;
    }

    public void setVolta1(String volta1) {
        this.volta1 = volta1;
    }

    public String getVolta2() {
        return volta2;
    }

    public void setVolta2(String volta2) {
        this.volta2 = volta2;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }
}
