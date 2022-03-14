package ee.himaster.platform.services.repository;

import ee.himaster.core.localization.model.Country;
import ee.himaster.platform.services.model.LanguageModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LanguageRepository extends JpaRepository<LanguageModel, Integer> {
    List<LanguageModel> findB(Country country);
}
