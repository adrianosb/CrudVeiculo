package br.com.adrianosb.veiculo.service;

import br.com.adrianosb.veiculo.model.Cor;
import java.util.List;

/**
 *
 * @author adriano
 */
public interface CorService {
    
    public List<Cor> findAll();
    
    public Cor findById(final int idCor);
    
}
