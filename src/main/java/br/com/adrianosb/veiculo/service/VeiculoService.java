package br.com.adrianosb.veiculo.service;

import br.com.adrianosb.veiculo.model.Veiculo;
import java.util.List;

/**
 *
 * @author adriano
 */
public interface VeiculoService {
    
    public List<Veiculo> findAll();
    
    public Veiculo findByPlaca(final String placa);
    
    public void disableByPlaca(final String placa);
    
    public void insert(final Veiculo veiculo);
    
    public void update(final Veiculo veiculo);
    
}
