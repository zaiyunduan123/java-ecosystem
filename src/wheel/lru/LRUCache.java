package wheel.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 基于LinkedHashMap(inheritance)实现LRU缓存
 *
 * 实现了Map接口，在多线程环境使用时可以使用 Collections.synchronizedMap()方法实现线程安全操作
 */
public class LRUCache<K, V> extends LinkedHashMap<K,V> {

    private final int MAX_CACHE_SIZE;//缓存阈值

    public LRUCache(int cacheSize){
        //三个参数：1、初始容量,2、加载因子，3、true表明LinkedHashMap按照访问的次序来排序
        super((int)Math.ceil(cacheSize/0.75) + 1, 0.75f,true);
        MAX_CACHE_SIZE = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size()>MAX_CACHE_SIZE;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<K, V> entry : entrySet()) {
            sb.append(String.format("%s:%s ", entry.getKey(), entry.getValue()));
        }
        return sb.toString();
    }
}
