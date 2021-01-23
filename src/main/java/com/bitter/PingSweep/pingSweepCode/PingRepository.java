package com.bitter.PingSweep.pingSweepCode;

import com.bitter.PingSweep.model.PingReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PingRepository extends JpaRepository<PingReturn, Long> {
}
