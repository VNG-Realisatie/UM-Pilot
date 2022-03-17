package com.cap.pocvng.scheduler;

import com.cap.pocvng.service.GemeenteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ScheduledTasks {

    private final GemeenteService service;

    public ScheduledTasks(GemeenteService service) {
        this.service = service;
    }

    /**
     * Remove all expired aanvragen on a daily basis.
     */
    @Scheduled(fixedRate = 86400000) // one day in ms
    public void removeAllExpiredAanvraag() {
        log.info("Removing all expired aanvragen");
        service.removeAllExpiredAanvraag();
    }
}
