package com.bitter.PingSweep.pingSweepCode;

import com.bitter.PingSweep.model.PingReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PingRepository extends JpaRepository<PingReturn, Long> {
    @Query(value = "SELECT DISTINCT address FROM ping_return", nativeQuery = true)
        //nativeQuery flag to not validate query for JPA specs
    List<String> findUniqueAddress();

    @Query(value = "SELECT COUNT(*) FROM ping_return WHERE DAYOFWEEK(date) = :dayNum AND address = :address", nativeQuery = true)
    String findCountByDay(@Param("dayNum") int dayNum, @Param("address") String address);

    @Query(value = "SELECT COUNT(*) FROM ping_return WHERE HOUR(time) = :hourNum AND address = :address", nativeQuery = true)
    String findCountByHour(@Param("hourNum") int hourNum, @Param("address") String address);

    @Query(value = "SELECT COUNT(*) FROM ping_return WHERE DAYOFWEEK(date) = :dayNum AND HOUR(time) = :hourNum AND address = :address", nativeQuery = true)
    String findCountByHourAndDay(@Param("dayNum") int dayNum, @Param("hourNum") int hourNum, @Param("address") String address);

    @Query(value = "SELECT * FROM ping_return WHERE address = :address", nativeQuery = true)
    List<PingReturn> getPingReturnByAddress(@Param("address") String address);
}
