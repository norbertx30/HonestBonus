package com.github.groupproject.repository;

import com.github.groupproject.entities.Bonus;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BonusRepository extends CrudRepository<Bonus,Long> {

    Bonus findOneByUuid(String bonusUuid);

    Set<Bonus> findAllBy();

    Set<Bonus> findAllByUserUuid(String userUuid);
}
