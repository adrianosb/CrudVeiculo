package br.com.adrianosb.veiculo.model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade Cor, tabela cad_cor 
 * @author adriano
 */
@Data
@EqualsAndHashCode(of = "idCor")
public class Cor implements Serializable {
    
    private int idCor;
    private String descricao;
    
}
