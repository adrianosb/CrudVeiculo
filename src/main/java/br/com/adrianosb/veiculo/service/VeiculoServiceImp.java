package br.com.adrianosb.veiculo.service;

import br.com.adrianosb.veiculo.model.Cor;
import br.com.adrianosb.veiculo.model.Veiculo;
import br.com.adrianosb.veiculo.repository.CorRepository;
import br.com.adrianosb.veiculo.repository.VeiculoRepository;
import br.com.adrianosb.veiculo.util.VeiculoException;
import java.time.LocalDate;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author adriano
 */
@Service
public class VeiculoServiceImp implements VeiculoService {

    @Autowired
    private CorRepository corRepository;
    @Autowired
    private VeiculoRepository veiculoRepository;

    @Override
    public List<Veiculo> findAll() {
        return veiculoRepository.findAll();
    }

    @Override
    public Veiculo findByPlaca(final String placa) {
        return veiculoRepository.findByPlaca(placa, true);
    }

    @Override
    public void insert(final Veiculo veiculo) {
        validaVeiculo(veiculo);

        Veiculo veiculoJahExiste = veiculoRepository.findByPlaca(veiculo.getPlaca(), true);
        if (veiculoJahExiste != null) {
            throw new VeiculoException("Placa já cadastrada.");
        }

        //se tudo certo grava
        Veiculo veiculoJahExisteMasInativo = veiculoRepository.findByPlaca(veiculo.getPlaca(), false);
        if (veiculoJahExisteMasInativo == null) {
            veiculoRepository.insert(veiculo);
        } else {
            veiculoRepository.update(veiculo);
        }
    }

    @Override
    public void update(final Veiculo veiculo) {
        validaVeiculo(veiculo);

        Veiculo veiculoJahExiste = veiculoRepository.findByPlaca(veiculo.getPlaca());
        if (veiculoJahExiste == null) {
            throw new VeiculoException("Placa não localizada.");
        }

        //se tudo certo grava
        veiculoRepository.update(veiculo);
    }

    @Override
    public void disableByPlaca(final String placa) {
        if (StringUtils.isBlank(placa) || placa.length() != 7) {
            throw new VeiculoException("Placa fora do padrão (AAAA-1111).");
        }

        Veiculo veiculoJahExiste = veiculoRepository.findByPlaca(placa, true);
        if (veiculoJahExiste == null) {
            throw new VeiculoException("Placa não localizada.");
        }
        veiculoRepository.disableByPlaca(placa);
    }

    /**
     * valida os campos do veiculo
     *
     * @param veiculo
     * @throws VeiculoException
     */
    private void validaVeiculo(final Veiculo veiculo) throws VeiculoException {
        final LocalDate now = LocalDate.now();
        //validar se ja existe placa e campos preenchidos
        if (veiculo == null) {
            throw new VeiculoException("Veículo nulo");
        }

        if (StringUtils.isBlank(veiculo.getPlaca()) || veiculo.getPlaca().length() != 7) {
            throw new VeiculoException("Placa fora do padrão (AAA1111).");
        }

        if (veiculo.getAnoFabricacao() < 1900 || veiculo.getAnoFabricacao() > now.getYear()) {
            throw new VeiculoException("Ano Fabricação fora do padrão, ano deve ser maior ou igual 1900 e menor igual " + LocalDate.now().getYear());
        }

        if (veiculo.getAnoModelo() < 1900 || veiculo.getAnoModelo() > now.getYear() + 1) {
            throw new VeiculoException("Ano Modelo fora do padrão, ano deve ser maior ou igual 1900 e menor igual " + (LocalDate.now().getYear() + 1));
        }

        final int diffAnoModeloFabricacao = veiculo.getAnoModelo() - veiculo.getAnoFabricacao();
        if (diffAnoModeloFabricacao != 0 && diffAnoModeloFabricacao != 1) {
            throw new VeiculoException("Diferença entre ano modelo e ano fabricação maior que 1.");
        }

        if (veiculo.getCor() == null && veiculo.getCor().getIdCor() <= 0) {
            throw new VeiculoException("Cor não selecionada");
        }

        Cor corExiste = corRepository.findById(veiculo.getCor().getIdCor());
        if (corExiste == null) {
            throw new VeiculoException("Cor não localizada");
        }
    }

}
