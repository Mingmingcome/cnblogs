package com.mingmingcome.designpattern.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * @who luhaoming
 * @when 2021/12/1 20:26
 * @what 家集合实现类
 */
// 具体集合类
public class FamilyAggregateImpl implements FamilyAggregate {

    private int generation;

    private List<String>[] generations;

    public FamilyAggregateImpl(int generation) {
        this.generation = generation;
        this.generations = initFamily(generation);
    }

    private List<String>[] initFamily(int generation) {
        List<String>[] name = new ArrayList[generation];
        for (int i = 0; i < generation; i++) {
            name[i] = new ArrayList<>();
        }
        return name;
    }

    public boolean addFamily(int generation, String name) {
        return generations[generation - 1].add(name);
    }

    @Override
    public NameIterator createIterator() {
        return new NameIteratorImpl();
    }

    // 具体迭代器
    private class NameIteratorImpl implements NameIterator {

        int currentGeneration;

        int cursor;

        NameIteratorImpl() {
            this.currentGeneration = FamilyAggregateImpl.this.generation;
        }

        @Override
        public String next() {
            int i = cursor;
            List<String>[] generations = FamilyAggregateImpl.this.generations;
            if (currentGeneration == 1 && cursor == generations.length) {
                System.out.println("无剩余可遍历");
            }
            String name = generations[currentGeneration - 1].get(i);
            cursor = i + 1;
            if (cursor == generations[currentGeneration - 1].size()) {
                currentGeneration--;
                cursor = 0;
            }
            return name;
        }

        @Override
        public boolean hastNext() {
            return currentGeneration != 0;
        }
    }

}
