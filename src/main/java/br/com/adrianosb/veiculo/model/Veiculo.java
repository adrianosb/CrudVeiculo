package br.com.adrianosb.veiculo.model;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Entidade Veiculo, tabela cad_veiculo
 *
 * @author adriano
 */
@Data
@EqualsAndHashCode(of = "placa")
public class Veiculo implements Serializable {

    private String placa;

    private int anoModelo;

    private int anoFabricacao;

    private boolean ativo;

    private Cor cor;

}
