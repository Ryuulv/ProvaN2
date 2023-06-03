package br.senac.go.repositories;

import br.senac.go.domain.Cliente;
import br.senac.go.domain.Conta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContaRepository extends JpaRepository<Cliente, Integer> {

    //List<Conta> findContaByNomeAndAndDataInicioOrDataFimAndContatos();
    List<Conta> findContaByNumeroLikeIgnoreCase(String nome);

}
