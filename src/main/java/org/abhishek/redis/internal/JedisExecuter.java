/* Copyright 2016 Roychoudhury, Abhishek */

package org.abhishek.redis.internal;

import redis.clients.jedis.Jedis;

/**
 * @author abhishek
 * @since 1.0
 */
public interface JedisExecuter<K> {
    K doInExecute(Jedis jedis);

    K execute();
}
