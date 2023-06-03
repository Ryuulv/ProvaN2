package br.senac.go.services;

import br.senac.go.domain.Cliente;
import br.senac.go.domain.Conta;
import br.senac.go.generics.IService;
import br.senac.go.repositories.ClienteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Slf4j
public class ClienteService implements IService<Cliente, Integer> {

    /**
     * @Autowired fazer a injeção de dependênciada classe
     */
    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public Cliente create(Conta entity) {

        log.info("Método PessoaService.create invocado");
        log.debug("Valores informados PessoaService.create {}", entity);

        return this.clienteRepository.save(entity);
    }

    @Override
    public Cliente readById(Integer id) throws Exception {
        log.info("Método PessoaService.readById invocado");
        log.debug("Valores informados PessoaService.readById {}", id);

        Cliente cliente = this.clienteRepository
                .findById(id)
                .orElseThrow(() -> new Exception("Registro não encontrado."));

        log.debug("Valores recuperados em PessoaService.readById são {}", cliente);
        return cliente;
    }

    @Override
    public Cliente read(Cliente entity) throws Exception {

        log.info("Método PessoaService.read invocado");
        log.debug("Valores informados PessoaService.read {}", entity);

        Example<Cliente> pessoaAprocurar = Example.of(entity);
        Cliente cliente = this.clienteRepository
                .findOne(pessoaAprocurar)
                .orElseThrow(() -> new Exception("Registro não encontrado."));

        log.debug("Valores recuperados em PessoaService.read são {}", cliente);

        return cliente;
    }

    @Override
    @Transactional
    public Cliente updatePatch(Cliente entity, Integer id) throws Exception {
        log.info("Método PessoaService.updatePatch invocado");
        log.debug("Valores informados PessoaService.updatePatch {} {}", entity, id);

       boolean registroEncontrado = this.clienteRepository.findById(id).isPresent();
       Cliente clienteAtualizada;

       if(registroEncontrado) {
           entity.setId(id);
           clienteAtualizada = this.clienteRepository.save(entity);
       }
       else {
           log.error("Error: PessoaService.updatePatch ao atualizar registro: {} {}",
                   entity, id);
           throw new Exception("Erro ao atualizar regitro");
       }


        log.debug("Valores atualizados em PessoaService.updatePatch são {}", clienteAtualizada);

        return clienteAtualizada;
    }

    @Override
    @Transactional
    public Cliente updatePut(Cliente entity, Integer id) {
        log.info("Método PessoaService.updatePatch invocado");
        log.debug("Valores informados PessoaService.updatePatch {} {}", entity, id);

        log.debug("Valores recuperados em PessoaService.read são {}", entity);

        return null;
    }

    @Override
    @Transactional
    public Cliente deleteById(Integer id) {
        return null;
    }

    @Override
    @Transactional
    public Cliente delete(Cliente entity) {
        return null;
    }
}
