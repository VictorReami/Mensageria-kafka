package br.com.reami.validador_boleto.Repository;


import br.com.reami.validador_boleto.Entity.BoletoEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoletoRepository extends CrudRepository<BoletoEntity, Long> {

}
