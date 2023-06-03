package br.senac.go.resources;


import br.senac.go.domain.Cliente;
import br.senac.go.domain.Conta;
import br.senac.go.generics.GenericOperationsResource;
import br.senac.go.services.ClienteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController //Fala que o controlador vai trabalhar com REST
@RequestMapping(path = "/pessoa")
@Api(value="Operações para manipulação dos dados do pessoa",
        tags = "pessoa ")
public class ClienteResource implements
        GenericOperationsResource<Cliente, Integer> {

    @Autowired
    private ClienteService clienteService;

    //Outro exemplo de fazer injeção de dependência sem usar @Autowired
    /*public PessoaResource(PessoaService pessoaService){
        this.pessoaService = pessoaService;
    }*/

    private static final Logger LOGGER =
            Logger.getLogger(ClienteResource.class.getName());

    /**
     * Consumes é o que o serviço vai receber (json ou xml)
     * Produces é o que o serviço vai entregar (json ou xml)
     * @param entity
     * @return
     */
    @Override
    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE,
            "application/xml;charset=UTF-8"},
    produces = {MediaType.APPLICATION_JSON_VALUE,
    MediaType.APPLICATION_XML_VALUE})
    @ApiOperation(value="${resource.pessoa-post}",
            notes="Criar dados de pessoa.")
    @ApiResponses(value={
            @ApiResponse(code = 200, message = "Requisição feita com sucesso.", response = Cliente.class),
            @ApiResponse(code = 201, message = "Registro criado com sucesso.", response = Cliente.class),
            @ApiResponse(code = 204, message = "O servidor processou a solicitação com sucesso e não está retornando nenhum conteúdo."),
            @ApiResponse(code = 301, message = "Redirecionamento permanente.", response = Cliente.class),
            @ApiResponse(code = 401, message = "Não autorizado.", response = Cliente.class),
            @ApiResponse(code = 404, message = "Registro não encontrado.", response = Cliente.class),
            @ApiResponse(code = 500, message = "Erro na requisão, verifique configurações do servidor.", response = Cliente.class)
    })
    public Cliente post(@RequestBody @Validated Cliente entity) {
        LOGGER.log(Level.INFO,"PessoaResource.post inicado {} ", entity);

        Cliente clientePersitida = this.clienteService.create(entity.getConta());

        LOGGER.log(Level.INFO,"PessoaResource.post concluído {} ", clientePersitida);
        return clientePersitida;
    }

    @Override
    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE,
            MediaType.APPLICATION_XML_VALUE})
    public List<Conta> get() {

        LOGGER.log(Level.INFO,"Exemplo do GET:");
        return null;
    }

    @Override
    @PutMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void put(@RequestBody @Validated Cliente entity,
                    @PathVariable("id") Integer id) {
        LOGGER.log(Level.INFO,
                String.format("Exemplo do PUT: %s | %d", entity, id));

        this.clienteService.updatePut(entity, id);

    }

    @Override
    @PatchMapping(value = "/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void patch(@RequestBody @Validated Cliente entity,
                      @PathVariable("id") Integer id) throws Exception {
        LOGGER.log(Level.INFO,
                String.format("Exemplo do PATCH: %s | %d", entity, id));

        this.clienteService.updatePatch(entity, id);

    }

    @Override
    @DeleteMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public void delete(@RequestBody @Validated Cliente entity) {
        LOGGER.log(Level.INFO,
                String.format("Exemplo do DELETE: %s", entity));
    }

    @Override
    @DeleteMapping(value="/{id}")
    public void deleteById(@PathVariable("id") Integer id) {
        LOGGER.log(Level.INFO,
                String.format("Exemplo do DELETE: %d", id));
    }
}
