package com.bitter.PingSweep.pingSweepCode;

import com.bitter.PingSweep.model.NameList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface NameListRepository extends JpaRepository<NameList, Long> {

    Optional<NameList> findNameListByAddress(String address);

    @Query(value = "SELECT name FROM namelist WHERE address = :address", nativeQuery = true)
    String getNameByAddress(@Param("address") String address);
}
