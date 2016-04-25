/* Copyright 2016 Roychoudhury, Abhishek */

package org.abhishek.redis.test;

import java.util.Set;

import org.abhishek.redis.template.RedisTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author abhishek
 * @since 1.0
 */
public class RedisJavaTester {
    private static final Logger logger = LoggerFactory.getLogger(RedisJavaTester.class);

    public static void main(String[] args) {
        RedisTemplate execute = new RedisTemplate();
        logger.info("{}", execute.set("india:captain", "Sourav Ganguly"));
        logger.info("{}", execute.set("india:batsman1", "Sachin Tendulkar"));
        logger.info("{}", execute.set("india:batsman2", "Sourav Ganguly"));
        logger.info("{}", execute.set("india:batsman3", "Rahul Dravid"));
        logger.info("{}", execute.set("india:batsman4", "Yuvraj Singh"));
        logger.info("{}", execute.set("india:batsman5", "M. S. Dhoni"));
        logger.info("{}", execute.set("india:batsman6", "V. V. S. Laxman"));
        print(execute.keys("india:"));
        logger.info("{}", execute.getSet("india:captain", "M. S. Dhoni"));
        logger.info("{}", execute.get("india:captain"));
        logger.info("{}", execute.del("india:batsman6"));
        print(execute.keys("india:"));
        logger.info("{}", execute.set("india:batsman6", "Virat Kohli"));

        logger.info("------------------------ Now with Bytes -----------------------");
        logger.info("{}", execute.set("india:captain".getBytes(), "Sourav Ganguly".getBytes()));
        logger.info("{}", execute.set("india:batsman1".getBytes(), "Sachin Tendulkar".getBytes()));
        logger.info("{}", execute.set("india:batsman2".getBytes(), "Sourav Ganguly".getBytes()));
        logger.info("{}", execute.set("india:batsman3".getBytes(), "Rahul Dravid".getBytes()));
        logger.info("{}", execute.set("india:batsman4".getBytes(), "Yuvraj Singh".getBytes()));
        logger.info("{}", execute.set("india:batsman5".getBytes(), "M. S. Dhoni".getBytes()));
        logger.info("{}", execute.set("india:batsman6".getBytes(), "V. V. S. Laxman".getBytes()));
        printBytes(execute.keys("india:*".getBytes()));
        logger.info("{}", execute.getSet("india:captain".getBytes(), "M. S. Dhoni".getBytes()));
        logger.info("{}", execute.get("india:captain".getBytes()));
        logger.info("{}", execute.del("india:batsman6".getBytes()));
        print(execute.keys("india:"));
        logger.info("{}", execute.set("india:batsman6".getBytes(), "Virat Kohli".getBytes()));
        execute.destroy();
    }

    public static void print(Set<String> keys) {
        int counter = 0;
        for (String key : keys) {
            logger.info("{}. {}", ++counter, key);
        }
    }

    public static void printBytes(Set<byte[]> keys) {
        int counter = 0;
        for (byte[] key : keys) {
            logger.info("{}. {}", ++counter, new String(key));
        }
    }
}
