package com.medinastr.security01.job;

import com.medinastr.security01.service.CacheService;
import java.util.concurrent.TimeUnit;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Log4j2
@RequiredArgsConstructor
public class CacheJob {

  private final CacheService cacheService;

  @Scheduled(fixedDelay = 30, timeUnit = TimeUnit.SECONDS)
  @CacheEvict("products")
  public void cleanProductsCache() {
    log.info("Execute products cache clean");
    cacheService.clearCache("products");
  }
}
