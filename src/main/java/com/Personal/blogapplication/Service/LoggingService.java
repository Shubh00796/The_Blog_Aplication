package com.Personal.blogapplication.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoggingService {

    public void log(String message) {
        log.info(message);
    }

    public void logDebug(String message) {
        log.debug(message);
    }

    public void logWarn(String message) {
        log.warn(message);
    }

    public void logError(String message, Throwable e) {
        log.error(message, e);
    }
}