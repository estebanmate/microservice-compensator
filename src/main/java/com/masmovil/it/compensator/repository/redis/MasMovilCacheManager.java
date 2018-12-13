package com.masmovil.it.compensator.repository.redis;

import io.micronaut.cache.CacheManager;
import io.micronaut.cache.DefaultCacheManager;
import io.micronaut.cache.DefaultSyncCache;
import io.micronaut.cache.SyncCache;
import io.micronaut.configuration.lettuce.cache.RedisCache;
import io.micronaut.context.annotation.Primary;
import io.micronaut.context.annotation.Replaces;
import io.micronaut.context.exceptions.ConfigurationException;
import io.micronaut.core.util.ArrayUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the {@link CacheManager} interface to replace DefaultSyncCache with RedisCache.
 *
 * @param <C> The native cache implementation
 */
@Singleton
@Primary
@Replaces(DefaultCacheManager.class)
public class MasMovilCacheManager<C> implements CacheManager<C> {
  private final Map<String, SyncCache<C>> cacheMap;

  private static final Logger LOG = LoggerFactory.getLogger(MasMovilCacheManager.class);

  /**
   * Create default cache manager for the given caches.
   *
   * @param caches List of synchronous cache implementations
   */
  @SuppressWarnings({"unused", "unchecked"})
  public MasMovilCacheManager(SyncCache<C>... caches) {
    if (ArrayUtils.isEmpty(caches)) {
      this.cacheMap = Collections.emptyMap();
    } else {
      this.cacheMap = new LinkedHashMap<>(caches.length);

      List<String> redisCacheNames = Arrays.stream(caches)
          .filter(cache -> {
            try {
              RedisCache redisCache = (RedisCache) cache;
              return true;
            } catch (ClassCastException e) {
              LOG.debug("Not a RedisCache: " + e.getMessage());
            }
            return false;
          }).map(redisCache -> ((RedisCache) redisCache).getName())
          .collect(Collectors.toList());

      List<SyncCache<C>> syncCaches = Arrays.stream(caches)
          .filter(cache -> {
            try {
              RedisCache redisCache = (RedisCache) cache;
              return true;
            } catch (ClassCastException e) {
              LOG.debug("Not a RedisCache: " + e.getMessage());    
            }
            DefaultSyncCache defaultCache = (DefaultSyncCache) cache;
            return !redisCacheNames.contains(defaultCache.getName());
          })
          .collect(Collectors.toList());

      for (SyncCache<C> cache : syncCaches) {
        this.cacheMap.put(cache.getName(), cache);
      }

    }
  }

  @Override
  public Set<String> getCacheNames() {
    return cacheMap.keySet();
  }

  @Override
  public SyncCache<C> getCache(String name) {
    SyncCache<C> cache = cacheMap.get(name);
    if (cache == null) {
      throw new ConfigurationException("No cache configured for name: " + name);
    }
    return (SyncCache<C>) cache;
  }
}
