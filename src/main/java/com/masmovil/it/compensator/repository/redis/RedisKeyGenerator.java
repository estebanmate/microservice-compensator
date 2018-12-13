package com.masmovil.it.compensator.repository.redis;

import io.micronaut.cache.interceptor.CacheKeyGenerator;
import io.micronaut.context.annotation.Value;
import io.micronaut.core.annotation.AnnotationMetadata;

import java.util.Arrays;

public class RedisKeyGenerator implements CacheKeyGenerator {

  @Value("${redis-keys-prefix}")
  private String redisKeyPrefix;

  /**
   * Generate environment key.
   * @param annotationMetadata annotationMetadata
   * @param params params to generate the key
   * @return environment key
   */
  public Object generateKey(AnnotationMetadata annotationMetadata, Object... params) {
    return redisKeyPrefix
      + Arrays.stream(params)
        .map(param -> param.toString())
        .reduce((acc, param) -> acc + "-" + param)
        .orElse("");
  }

}