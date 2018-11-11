package domain;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * This is a customized ConcurrentHashMap. It limits the maximum size of the ConcurrentHashMap
 */
public class LimitedConcurrentHashMap extends ConcurrentHashMap<Customer,Integer>{
    /**
     * This is the max size of the ConcurrentHashMap
     */
    private final int maxSize = 20;

    @Override
    public Integer put(Customer key, Integer value) {
        if(size()>=maxSize)
        {
            /**
             * Because we want only the top 20 values, we delete the least one and add the higher offer
             */
           Entry<Customer,Integer> entry= entrySet().stream().min(Map.Entry.comparingByValue(Comparator.comparingInt(value1 -> value))).get();
           if(value>entry.getValue())
               remove(entry.getKey());
           else
               return entry.getValue();
        }
        return super.put(key, value);
    }


}
