package com.uga.forwords.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.uga.forwords.model.Config;

@Repository
public interface ConfigRepository extends CrudRepository<Config, String> {

}
