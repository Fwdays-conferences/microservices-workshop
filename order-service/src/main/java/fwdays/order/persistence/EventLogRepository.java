package fwdays.order.persistence;

import fwdays.order.domain.EventLog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface EventLogRepository extends MongoRepository<EventLog, String> {

    List<EventLog> findByEntityIdOrderByCreatedAsc(int entityId);
}
