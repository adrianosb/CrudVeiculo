package br.com.adrianosb.veiculo.repository;

import br.com.adrianosb.veiculo.model.Cor;
import br.com.adrianosb.veiculo.model.Veiculo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author adriano
 */
@Repository
public class VeiculoRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Mapeia o ResultSet para classe Veiculo
     */
    class VeiculoRowMapper implements RowMapper<Veiculo> {

        @Override
        public Veiculo mapRow(ResultSet rs, int rowNum) throws SQLException {
            final Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(rs.getString("placa"));
            veiculo.setAnoModelo(rs.getInt("ano_modelo"));
            veiculo.setAnoFabricacao(rs.getInt("ano_fabricacao"));
            veiculo.setAtivo(rs.getBoolean("ativo"));

            final Cor cor = new Cor();
            cor.setIdCor(rs.getInt("id_cor"));
            cor.setDescricao(rs.getString("descricao"));
            veiculo.setCor(cor);
            return veiculo;
        }
    }

    /**
     * Busca todos ativos
     *
     * @return lista de veiculos
     */
    public List<Veiculo> findAll() {
        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT v.*, c.descricao ")
                .append("FROM cad_veiculo v ")
                .append("INNER JOIN cad_cor c ON c.id_cor = v.id_cor ")
                .append("WHERE v.ativo = ?");

        return jdbcTemplate.query(sql.toString(),
                new Object[]{true},
                new VeiculoRowMapper());
    }

    /**
     * Busca um veiculo com base na placa
     *
     * @param placa
     * @param ativo
     * @return Veiculo
     */
    public Veiculo findByPlaca(final String placa, boolean ativo) {

        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT v.*, c.descricao ")
                .append("FROM cad_veiculo v ")
                .append("INNER JOIN cad_cor c ON c.id_cor = v.id_cor ")
                .append("WHERE v.placa = ? and v.ativo = ?");
        try {
            return jdbcTemplate.queryForObject(sql.toString(),
                    new Object[]{placa, ativo},
                    new VeiculoRowMapper());
        } catch (Exception e) {
            return null;
        }
    }
    
    /**
     * Busca um veiculo com base na placa
     *
     * @param placa
     * @return Veiculo
     */
    public Veiculo findByPlaca(final String placa) {

        final StringBuilder sql = new StringBuilder();
        sql.append("SELECT v.*, c.descricao ")
                .append("FROM cad_veiculo v ")
                .append("INNER JOIN cad_cor c ON c.id_cor = v.id_cor ")
                .append("WHERE v.placa = ?");
        try {
            return jdbcTemplate.queryForObject(sql.toString(),
                    new Object[]{placa},
                    new VeiculoRowMapper());
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Remove fisicamente
     *
     * @param placa
     * @return numero de linhas afetadas
     */
    public int deleteByPlaca(final String placa) {
        return jdbcTemplate.update("delete from cad_veiculo where placa = ?",
                new Object[]{placa});
    }

    /**
     * Inativa veiculo, ativo = false (logicamente)
     *
     * @param placa
     * @return numero de linhas afetadas
     */
    public int disableByPlaca(final String placa) {
        return jdbcTemplate.update("update cad_veiculo set ativo = ? where placa = ?",
                new Object[]{false, placa});
    }

    /**
     * Inclui um novo veiculo
     *
     * @param veiculo
     * @return numero de linhas afetadas
     */
    public int insert(final Veiculo veiculo) {
        return jdbcTemplate.update("insert into cad_veiculo (placa, ano_modelo, ano_fabricacao, id_cor, ativo) values(?, ?, ?, ?, ?)",
                new Object[]{veiculo.getPlaca(), veiculo.getAnoModelo(), veiculo.getAnoFabricacao(), veiculo.getCor().getIdCor(), true});
    }

    /**
     * Alterar os campos: ano_modelo, ano_fabricacao, id_cor Com base na placa
     * do veiculo
     *
     * @param veiculo
     * @return numero de linhas afetadas
     */
    public int update(final Veiculo veiculo) {
        return jdbcTemplate.update("update cad_veiculo set ano_modelo = ?, ano_fabricacao = ?, id_cor = ?, ativo = ? where placa = ?",
                new Object[]{veiculo.getAnoModelo(), veiculo.getAnoFabricacao(), veiculo.getCor().getIdCor(), true, veiculo.getPlaca()});
    }

}
