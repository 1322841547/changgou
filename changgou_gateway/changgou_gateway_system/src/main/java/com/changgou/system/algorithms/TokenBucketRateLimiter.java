package com.changgou.system.algorithms;
public class TokenBucketRateLimiter{}

/**
 * 法二
 */

/**
 * 令牌桶
 * 局限：最多1毫秒生成一个令牌
 *
 * create time: 2020/7/15 9:42
 */
//public class TokenBucket {
//
//    private final double unitAddNum;    // 单位时间（1s）往桶中放令牌数量
//    private final long maxTokenNum;      // 桶中最大有多少令牌
//
//    private volatile long currentTokenCount = 0;  // 当前桶中有多少令牌
//    private volatile long nextRefreshTime = 0L;  // 下一次刷新桶中令牌数量的时间戳
//    private volatile long lastAcquireTime;       // 上一次从桶中获取令牌的时间戳（貌似用不到）
//
//    /**
//     *
//     * @param unitAddNum 1秒增加几个令牌
//     * @param maxToken 桶中最大令牌数
//     * @param isFullStart 一开始是否是满的
//     */
//    public TokenBucket(double unitAddNum, long maxToken, boolean isFullStart) {
//        if (unitAddNum <= 0 || maxToken <= 0) {
//            throw new RuntimeException("unitAddNum and maxToken can't less than 0");
//        }
//        if (unitAddNum > 1000) {
//            throw new RuntimeException("unitAddNum max is 1000");
//        }
//        this.unitAddNum = unitAddNum;
//        this.maxTokenNum = maxToken;
//        if (isFullStart) {
//            this.currentTokenCount = maxToken;
//        } else {
//            this.currentTokenCount = 0;
//        }
//        this.nextRefreshTime = calculateNextRefreshTime(System.currentTimeMillis());
//    }
//
//    public boolean acquire(long needTokenNum) {
//        if (needTokenNum > this.maxTokenNum) {
//            return false;
//        }
//        synchronized (this) {
//            long currentTimestamp = System.currentTimeMillis();
//            this.refreshCurrentTokenCount(currentTimestamp);
//            if (needTokenNum <= this.currentTokenCount) {
//                return this.doAquire(needTokenNum, currentTimestamp);
//            }
//            return false;
//        }
//    }
//
//    private boolean doAquire(long needTokenNum, long currentTimestamp) {
//        this.currentTokenCount -= needTokenNum;
//        this.lastAcquireTime = currentTimestamp;
//        return true;
//    }
//
//    /**
//     * 刷新桶中令牌数量
//     * @param currentTimestamp
//     */
//    private void refreshCurrentTokenCount(long currentTimestamp) {
//        if (this.nextRefreshTime > currentTimestamp) {
//            return;
//        }
//        this.currentTokenCount = Math.min(this.maxTokenNum, this.currentTokenCount + calculateNeedAddTokenNum(currentTimestamp));
//        this.nextRefreshTime = calculateNextRefreshTime(currentTimestamp);
//
//    }
//
//    /**
//     * 计算当前需要添加多少令牌
//     * @param currentTimestamp
//     * @return
//     */
//    private long calculateNeedAddTokenNum(long currentTimestamp) {
//        if (this.nextRefreshTime > currentTimestamp) {
//            return 0;
//        }
//        long addOneMs = Math.round(1.0D / this.unitAddNum * 1000D); // 这么久才能加1个令牌
//        return (currentTimestamp - this.nextRefreshTime) / addOneMs + 1;
//    }
//
//    private long calculateNextRefreshTime(long currentTimestamp) {
//        if (currentTimestamp < this.nextRefreshTime) {
//            return this.nextRefreshTime;
//        }
//        long addOneMs = Math.round(1.0D / this.unitAddNum * 1000D); // 这么久才能加1个令牌
//        long result = 0;
//        if (this.nextRefreshTime <= 0) {
//            result = currentTimestamp + addOneMs;
//        } else {
//            result = this.nextRefreshTime + ((currentTimestamp - this.nextRefreshTime) / addOneMs + 1) * addOneMs;
//        }
//        return result;
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//        TokenBucket tokenBucket = new TokenBucket(1D, 1, true);
//        for (int i=0; i<10; i++) {
//            System.out.println(tokenBucket.acquire(1));
//            Thread.sleep(500);
//        }
//    }
//}
//————————————————
//        版权声明：本文为CSDN博主「长沙刘德华」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
//        原文链接：https://blog.csdn.net/xxxxssss12/article/details/107359477



/**
 * 法二
 */
//public class TokenBucketRateLimiter extends MyRateLimiter {
//    /**
//     * 令牌桶的容量「限流器允许的最大突发流量」
//     */
//    private final long capacity;
//    /**
//     * 令牌发放速率
//     */
//    private final long generatedPerSeconds;
//    /**
//     * 最后一个令牌发放的时间
//     */
//    long lastTokenTime = System.currentTimeMillis();
//    /**
//     * 当前令牌数量
//     */
//    private long currentTokens;
//
//    public TokenBucketRateLimiter(long generatedPerSeconds, int capacity) {
//        this.generatedPerSeconds = generatedPerSeconds;
//        this.capacity = capacity;
//    }
//
//    /**
//     * 尝试获取令牌
//     *
//     * @return true表示获取到令牌，放行；否则为限流
//     */
//    @Override
//    public synchronized boolean tryAcquire() {
//        /**
//         * 计算令牌当前数量
//         * 请求时间在最后令牌是产生时间相差大于等于额1s（为啥时1s？因为生成令牌的最小时间单位时s），则
//         * 1. 重新计算令牌桶中的令牌数
//         * 2. 将最后一个令牌发放时间重置为当前时间
//         */
//        long now = System.currentTimeMillis();
//        if (now - lastTokenTime >= 1000) {
//            long newPermits = (now - lastTokenTime) / 1000 * generatedPerSeconds;
//            currentTokens = Math.min(currentTokens + newPermits, capacity);
//            lastTokenTime = now;
//        }
//        if (currentTokens > 0) {
//            currentTokens--;
//            return true;
//        }
//        return false;
//    }
//}
