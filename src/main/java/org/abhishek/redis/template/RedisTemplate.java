/* Copyright 2016 Roychoudhury, Abhishek */

package org.abhishek.redis.template;

import java.util.Set;

import org.abhishek.redis.connect.RedisConnectionFactory;
import org.abhishek.redis.internal.AbstractJedisExecuter;
import org.abhishek.redis.internal.JedisExecuter;

import redis.clients.jedis.Jedis;

/**
 * @author abhishek
 * @since 1.0
 */
public class RedisTemplate {
    public String get(final String key) {
        return this.executer(new AbstractJedisExecuter<String>() {
            public String doInExecute(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public byte[] get(final byte[] key) {
        return this.executer(new AbstractJedisExecuter<byte[]>() {
            public byte[] doInExecute(Jedis jedis) {
                return jedis.get(key);
            }
        });
    }

    public String set(final String key, final String value) {
        return this.executer(new AbstractJedisExecuter<String>() {
            public String doInExecute(Jedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    public String set(final byte[] key, final byte[] value) {
        return this.executer(new AbstractJedisExecuter<String>() {
            public String doInExecute(Jedis jedis) {
                return jedis.set(key, value);
            }
        });
    }

    public String getSet(final String key, final String value) {
        return this.executer(new AbstractJedisExecuter<String>() {
            public String doInExecute(Jedis jedis) {
                return jedis.getSet(key, value);
            }
        });
    }

    public byte[] getSet(final byte[] key, final byte[] value) {
        return this.executer(new AbstractJedisExecuter<byte[]>() {
            public byte[] doInExecute(Jedis jedis) {
                return jedis.getSet(key, value);
            }
        });
    }

    public String setex(final byte[] key, final int seconds, final byte[] value) {
        return this.executer(new AbstractJedisExecuter<String>() {
            public String doInExecute(Jedis jedis) {
                return jedis.setex(key, seconds, value);
            }
        });
    }

    public String setex(final String key, final int seconds, final String value) {
        return this.executer(new AbstractJedisExecuter<String>() {
            public String doInExecute(Jedis jedis) {
                return jedis.setex(key, seconds, value);
            }
        });
    }

    public Long del(final String... keys) {
        return this.executer(new AbstractJedisExecuter<Long>() {
            public Long doInExecute(Jedis jedis) {
                return jedis.del(keys);
            }
        });
    }

    public Long del(final byte[]... keys) {
        return this.executer(new AbstractJedisExecuter<Long>() {
            public Long doInExecute(Jedis jedis) {
                return jedis.del(keys);
            }
        });
    }

    public Long exists(final String... keys) {
        return this.executer(new AbstractJedisExecuter<Long>() {
            public Long doInExecute(Jedis jedis) {
                return jedis.exists(keys);
            }
        });
    }

    public Long exists(final byte[]... keys) {
        return this.executer(new AbstractJedisExecuter<Long>() {
            public Long doInExecute(Jedis jedis) {
                return jedis.exists(keys);
            }
        });
    }

    public Set<String> keys(final String match) {
        return this.executer(new AbstractJedisExecuter<Set<String>>() {
            public Set<String> doInExecute(Jedis jedis) {
                return jedis.keys(match + "*");
            }
        });
    }

    public Set<byte[]> keys(final byte[] match) {
        return this.executer(new AbstractJedisExecuter<Set<byte[]>>() {
            public Set<byte[]> doInExecute(Jedis jedis) {
                return jedis.keys(match);
            }
        });
    }

    public void destroy() {
        RedisConnectionFactory.factory().closePool();
    }

    private <K> K executer(JedisExecuter<K> executer) {
        return executer.execute();
    }
}
