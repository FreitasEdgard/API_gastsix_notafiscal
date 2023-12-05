package com.api.gastapi.repositories;

import com.api.gastapi.models.NotaModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotaModelRepository extends JpaRepository<NotaModel, String> {

    NotaModel findByNumeronota(String numeronota);



}