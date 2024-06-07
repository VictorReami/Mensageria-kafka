package br.com.reami.api_boleto.Repository;

import br.com.reami.api_boleto.Entity.BoletoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoletoRepository extends JpaRepository<BoletoEntity, Long> {

    Optional<BoletoEntity> findByCodigoBarras(String codigoBarras);



}
