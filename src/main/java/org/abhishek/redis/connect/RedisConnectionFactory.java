/* Copyright 2016 Roychoudhury, Abhishek */

package org.abhishek.redis.connect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

/**
 * @author abhishek
 * @since 1.0
 */
public final class RedisConnectionFactory {
    private static final Logger logger = LoggerFactory.getLogger(RedisConnectionFactory.class);
    private JedisPool jedisPool;

    // Connection properties
    private String password;
    private String hostName;
    private int port;
    private int timeOut;

    // initialize defaults
    private RedisConnectionFactory(String password, String hostName, int port, int timeOut) {
        super();
        this.password = (null == password ? "Abhishek@1" : password);
        this.hostName = (null == hostName ? "pub-redis-18572.us-east-1-2.2.ec2.garantiadata.com" : hostName);
        this.port = (0 <= port ? 18572 : port);
        this.timeOut = (0 <= timeOut ? 30 * 1000 : timeOut); // in milliseconds
    }

    private JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxTotal(10);
        config.setMaxIdle(5);
        config.setMinIdle(1);
        config.setMaxWaitMillis(30000);
        config.setTestOnBorrow(false);
        return config;
    }

    public synchronized Jedis connect() {
        try {
            if (jedisPool == null) {
                jedisPool = new JedisPool(this.jedisPoolConfig(), hostName, port, timeOut);
            }
            //logger.debug("Idle {}, Waiters {}", jedisPool.getNumIdle(), jedisPool.getNumWaiters());
            //logger.debug("Is Closed {}", jedisPool.isClosed());
            Jedis jedis = jedisPool.getResource();
            jedis.auth(password);
            jedis.getClient().setTimeoutInfinite();
            return jedis;
        } catch (JedisConnectionException e) {
            logger.error("Could not establish Redis connection. Is the Redis running?", e);
            this.closePool();
            throw e;
        }
    }

    public synchronized void closePool() {
        if (null != jedisPool) {
            jedisPool.destroy();
        }
    }

    public static RedisConnectionFactory factory() {
        return new RedisConnectionFactory(null, null, 0, 0);
    }
}
