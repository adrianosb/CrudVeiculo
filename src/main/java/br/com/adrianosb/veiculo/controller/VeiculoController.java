package br.com.adrianosb.veiculo.controller;

import br.com.adrianosb.veiculo.model.Cor;
import br.com.adrianosb.veiculo.model.Veiculo;
import br.com.adrianosb.veiculo.service.CorService;
import br.com.adrianosb.veiculo.service.VeiculoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author adriano
 */
@RestController
public class VeiculoController {

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private CorService corService;

    @GetMapping("/cores")
    public List<Cor> findAllCores() {
        return corService.findAll();
    }

    @GetMapping("/veiculos")
    public List<Veiculo> findAllVeiculos() {
        return veiculoService.findAll();
    }

    @GetMapping("/veiculos/{placa}")
    public Veiculo findByPlaca(@PathVariable String placa) {
        return veiculoService.findByPlaca(placa);
    }

    @PostMapping("/veiculos")
    public Veiculo insert(@RequestBody Veiculo veiculo) {
        veiculoService.insert(veiculo);
        return veiculo;
    }

    @PutMapping("/veiculos")
    public Veiculo update(@RequestBody Veiculo veiculo) {
        veiculoService.update(veiculo);
        return veiculo;
    }

    @DeleteMapping("/veiculos/{placa}")
    public void delete(@PathVariable String placa) {
        veiculoService.disableByPlaca(placa);
    }
}
