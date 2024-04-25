import java.sql.Connection;
import java.sql.PreparedStatement;

public class Dados {
    Connection conn;
    PreparedStatement pstm;

    public void enviarDados(Cronometro dados) {
        String sql = "insert into cronometro (primeira_volta, segunda_volta, tempo_total) values (?, ?, ?)";

        conn = new ConectaDB().conectaDB();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, dados.getVolta1());
            pstm.setString(2, dados.getVolta2());
            pstm.setString(3, dados.getTotal());

            pstm.execute();
            pstm.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
