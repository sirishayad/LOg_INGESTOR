package com.assignment;

import java.time.Instant;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mysql.cj.log.Log;



@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
    // Existing methods

    @Query("SELECT l FROM Log l WHERE " +
            "l.level LIKE %:filter% OR " +
            "l.message LIKE %:filter% OR " +
            "l.resourceId LIKE %:filter% OR " +
            "l.traceId LIKE %:filter% OR " +
            "l.spanId LIKE %:filter% OR " +
            "l.commit LIKE %:filter% OR " +
            "l.metadata.parentResourceId LIKE %:filter%")
    List<Log> findByFilter(@Param("filter") String filter);

	List<Log> findByMessageContainingIgnoreCase(String message);

	List<Log> findByLevelAndResourceIdAndTimestampBetween(String level, String resourceId, Instant start, Instant end);

	List<Log> findByTimestampBetween(Instant start, Instant end);

	void save(LogModel log);
}
