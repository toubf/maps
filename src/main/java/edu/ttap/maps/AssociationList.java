package edu.ttap.maps;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.List;

/**
 * An association list is an implementation of a map via a list of key-value pairs.
 */
public class AssociationList<K, V> implements Map<K, V> {

    public class Pair<K, V> {
        public K key;
        public V value;
        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private ArrayList<Pair<K,V>> pairs;

    /**
     * Clears the association list, removing all key-value pairs.
     */
    @Override
    public void clear() {
        int size = pairs.size();
        for (int i = 0; i < size; i++) {
            pairs.remove(i);
        }
    }

    /**
     * @param key the key to check
     * @return true iff this map contains a mapping for the specified key
     */
    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i < pairs.size(); i++) {
            if ((pairs.get(i).key).equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param value the value to check
     * @return true iff this map maps one or more keys to the specified value
     */
    @Override
    public boolean containsValue(Object value) {
        for (int i = 0; i < pairs.size(); i++) {
            if ((pairs.get(i).value).equals(value)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a set view of the mappings contained in this map
     */
    @Override
    public Set<Entry<K, V>> entrySet() {
        
        // NOTE: you do not need to implement this method!
        throw new UnsupportedOperationException("Unimplemented method 'entrySet'");
    }

    /**
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key
     */
    @Override
    public V get(Object key) {
        for (int i = 0; i < pairs.size(); i++) {
            if ((pairs.get(i).key).equals(key)) {
                return pairs.get(i).value;
            }
        }
        return null;
    }

    /**
     * @return true iff this map contains no key-value mappings
     */
    @Override
    public boolean isEmpty() {
        return pairs.size() == 0;
    }

    /**
     * @return a set view of the keys contained in this map
     */
    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<K>();
        for (int i = 0; i < pairs.size(); i++) {
            set.add(pairs.get(i).key);
        }
        return set;
    }

    /**
     * If there is no entry for key in the map, updates the entry to associate key
     * with value. Otherwise, it updates the entry for key accordingly.
     * @param key the key with which the specified value is to be associated
     * @param value the value to be associated with the specified key
     * @return the previous value associated with key, or null if there was no
     *         mapping for key
     */
    @Override
    public V put(K key, V value) {
        V oldVal = null;
        if(containsKey(key)) {
            for (int i = 0; i < pairs.size(); i++) {
                if ((pairs.get(i).key).equals(key)) {
                    oldVal = pairs.get(i).value;
                    pairs.get(i).value = value;
                    break;
                }
            }
        } else {
            pairs.add(new Pair<K,V> (key, value));
        }
        return oldVal;
    }

    /**
     * Copies all of the mappings from the specified map to this map. The effect of this
     * operation is equivalent to applying the put(K, V) operation to each entry in the
     * specified map.
     * @param m the map whose mappings are to be copied to this map
     */
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for(K k : m.keySet()) {
            this.put(k, m.get(k));
        }
    }

    /**
     * Removes the mapping for a key from this map if it is present.
     * @param key the key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null if there was no mapping for
     *         key.
     */
    @Override
    public V remove(Object key) {
        int index = 0;
        V ret = null;
        for (int i = 0; i < pairs.size(); i++) {
            if ((pairs.get(i).key).equals(key)) {
                ret = pairs.get(i).value;
                pairs.remove(i);
                break;
            }
        }
        return ret;
    }

    /**
     * @return the number of key-value mappings in this map
     */
    @Override
    public int size() {
        return pairs.size();
    }

    /**
     * @return a collection vof the values contained in this map, e.g., a list
     */
    @Override
    public Collection<V> values() {
        List<V> c = new ArrayList<V>();
        for (int i = 0; i < pairs.size(); i++) {
            c.add(pairs.get(i).value);
        }
        return c;
    }
}
