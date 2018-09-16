package br.com.adrianosb.veiculo.repository;

import br.com.adrianosb.veiculo.model.Cor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 * Classe responsavel para acesso a tabela cad_cor
 *
 * @author adriano
 */
@Repository
public class CorRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Mapeia o ResultSet para classe Cor
     */
    class CorRowMapper implements RowMapper<Cor> {

        @Override
        public Cor mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Cor cor = new Cor();
            cor.setIdCor(rs.getInt("id_cor"));
            cor.setDescricao(rs.getString("descricao"));
            return cor;
        }
    }

    public List<Cor> findAll() {
        return jdbcTemplate.query("select * from cad_cor", new CorRowMapper());
    }

    public Cor findById(final int idCor) {
        try {
            return jdbcTemplate.queryForObject("select * from cad_cor where id_cor=?",
                    new Object[]{idCor},
                    new CorRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    public int deleteById(final int idCor) {
        return jdbcTemplate.update("delete from cad_cor where id_cor=?",
                new Object[]{idCor});
    }

    public int insert(final Cor cor) {
        return jdbcTemplate.update("insert into cad_cor (id_cor, descricao) values(?, ?)",
                new Object[]{cor.getIdCor(), cor.getDescricao()});
    }

    public int update(final Cor cor) {
        return jdbcTemplate.update("update cad_cor set descricao = ? where id_cor = ?",
                new Object[]{cor.getDescricao(), cor.getIdCor()});
    }

}
