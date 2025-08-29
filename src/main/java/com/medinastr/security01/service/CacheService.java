package com.medinastr.security01.service;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CacheService {

  private final CacheManager cacheManager;

  public void clearCache(String cacheName) {
    Objects.requireNonNull(cacheManager.getCache(cacheName)).clear();
  }
}
