/* Copyright 2016 Roychoudhury, Abhishek */

package org.abhishek.redis.internal;

import org.abhishek.redis.connect.RedisConnectionFactory;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import redis.clients.jedis.Jedis;

/**
 * @author abhishek
 * @since 1.0
 */
public abstract class AbstractJedisExecuter<K> implements JedisExecuter<K> {
    private static final byte[] REMOVE_KEYS_BY_PATTERN_LUA = new StringRedisSerializer().serialize("local keys = redis.call('KEYS', ARGV[1]); local keysCount = table.getn(keys); if(keysCount > 0) then for _, key in ipairs(keys) do redis.call('del', key); end; end; return keysCount;");
    private static final byte[] WILD_CARD = new StringRedisSerializer().serialize("*");

    /**
     * @author abhishek
     * @since 1.0
     * @see org.abhishek.redis.internal.JedisExecuter#execute()
     */
    public K execute() {
        Jedis jedis = RedisConnectionFactory.factory().connect();
        try {
            return this.doInExecute(jedis);
        } finally {
            jedis.close();
        }
    }

}
