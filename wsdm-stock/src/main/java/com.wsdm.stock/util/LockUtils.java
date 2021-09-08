package com.wsdm.stock.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author 王瑞
 * @description
 * @date 2020年12月18日09:49:29
 */
@Component
public class LockUtils
{
    /**
     * 库存模块锁关键字
     */
    public static final String STOCK_MODULE_LOCK = "STOCK_MODULE_LOCK";

    /**
     * 可以支持一共模块一个读写锁
     */
    private Map<String, ReentrantReadWriteLock> lockMap = new HashMap<>();

    /**
     * 获取写锁
     * @param lockKey 模块关键字
     * @return 写锁
     */
    public Lock getWriteLock(String lockKey) {
        if (lockMap.get(lockKey) == null) {
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            lockMap.put(lockKey, lock);
        }
        return lockMap.get(lockKey).writeLock();
    }

    /**
     * 获取读锁
     * @param lockKey 模块关键字
     * @return 读锁
     */
    public Lock getReadLock(String lockKey) {
        if (lockMap.get(lockKey) == null) {
            ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
            lockMap.put(lockKey, lock);
        }
        return lockMap.get(lockKey).readLock();
    }
}
