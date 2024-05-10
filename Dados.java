import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.swing.JOptionPane;

public class Dados {
    Connection conn;
    PreparedStatement pstm;

    public void enviarDados(Cronometro dados) {
        String sql = "insert into cronometro (car_equipe, car_piloto, car_primeira_volta, car_segunda_volta, car_tempo_total) values (?, ?, ?, ?, ?)";

        conn = new ConectaDB().conectaDB();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, dados.getEquipe());
            pstm.setString(2, dados.getPiloto());
            pstm.setString(3, dados.getVolta1());
            pstm.setString(4, dados.getVolta2());
            pstm.setString(5, dados.getTotal());

            pstm.execute();
            pstm.close();
            JOptionPane.showMessageDialog(null, "Os dados da sua equipe foram salvos no Banco de Dados");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
