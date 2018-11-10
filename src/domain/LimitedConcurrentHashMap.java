package domain;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LimitedConcurrentHashMap extends ConcurrentHashMap<Customer,Integer>{
    private final int maxSize = 20;

    @Override
    public Integer put(Customer key, Integer value) {
        if(size()>=maxSize)
        {
           Entry<Customer,Integer> entry= entrySet().stream().max(Map.Entry.comparingByValue(Comparator.comparingInt(value1 -> value))).get();
           if(value>entry.getValue())
               remove(entry.getKey());
           else
               return entry.getValue();
        }
        return super.put(key, value);
    }


}
