package com.policeschool.code.collections;

import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 抛出ConcurrentModificationException的条件：
 * 1.通过迭代器遍历集合的时候，集合的结构被修改了
 * 2.增强的 for 循环实际上是使用迭代器来遍历集合的，因此在循环中修改集合也会导致该异常
 * 3.在没有正确同步的多线程环境中，如果一个线程在迭代集合时，另一个线程修改了该集合的结构，也会抛出 ConcurrentModificationException
 *
 * 为了避免在迭代过程中出现 ConcurrentModificationException，可以采用以下几种方法：
 * 1.在迭代器的循环中使用迭代器的 remove() 方法
 * 2.使用并发集合，比如CopyOnWriteArrayList
 * 3.收集要删除的元素，在迭代结束后统一删除
 */
public class ConcurrentModificationExceptionDemo {

    private final static ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 2, 60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(), runnable -> {
        Thread t = new Thread(runnable);
        t.setName("ConcurrentModificationExceptionDemo");
        return t;
    }, new ThreadPoolExecutor.CallerRunsPolicy());

    static class ListModification {
        private final List<String> list = new ArrayList<>(Arrays.asList("a", "b", "c"));

        /**
         * 用迭代器迭代list元素时，
         * 如果用的是集合本身的remove方法会抛出 ConcurrentModificationException，
         * 如果用的是迭代器本身的remove方法则不会抛出 ConcurrentModificationException
         */
        public void removeDataFromIterator() {
            Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String item = iterator.next();
                if (item.equals("a")) {
                    list.remove(item); // 这里会抛出 ConcurrentModificationException
                    iterator.remove(); // 不会抛出 ConcurrentModificationException
                }
            }
            System.out.println(Arrays.toString(list.toArray()));
        }

        /**
         * 由于使用了增强的for循环，所以会抛出 ConcurrentModificationException，
         * 因为增强的for循环本质上是使用迭代器来遍历集合的
         */
        public void removeDataFromList() {
            for (String item : list) {
                if (item.equals("a")) {
                    list.remove(item); // 这里会抛出 ConcurrentModificationException
                }
            }
            System.out.println(Arrays.toString(list.toArray()));
        }

        /**
         * 这个loop方法要搭配下面的remove方法来使用，
         * 让线程一执行这个loop方法，让线程二执行remove方法，
         * 在没用正确同步的多线程环境中也会抛出  ConcurrentModificationException
         */
        public void loop() {
            for (String s : list) {
                System.out.println(s);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        public void remove() {
            list.remove(2);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ListModification listModification = new ListModification();
        executor.execute(listModification::loop);
        Thread.sleep(500);
        listModification.remove();
    }
}
