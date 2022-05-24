/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gcsouradnice.data;

import gcsouradnice.data.Cache;
import java.util.Comparator;

/**
 *
 * @author tomch
 */
public class ComparatorCachesByCode implements Comparator<Cache>{
    @Override
    public int compare(Cache o1, Cache o2) {
        return o1.getCode().compareTo(o2.getCode());
    }
}
