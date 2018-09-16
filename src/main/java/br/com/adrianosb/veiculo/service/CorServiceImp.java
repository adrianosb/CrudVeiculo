package br.com.adrianosb.veiculo.service;

import br.com.adrianosb.veiculo.model.Cor;
import br.com.adrianosb.veiculo.repository.CorRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author adriano
 */
@Service
public class CorServiceImp implements CorService {
    
    @Autowired
    private CorRepository corRepository; 

    @Override
    public List<Cor> findAll() {
        return corRepository.findAll();
    }

    @Override
    public Cor findById(final int idCor) {
        return corRepository.findById(idCor);
    }
    
}
