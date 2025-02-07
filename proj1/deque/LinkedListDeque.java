package deque;

import jh61b.junit.In;
import net.sf.saxon.expr.instruct.Block;

public class LinkedListDeque<T> {
    public class Node {
        public T item;
        public Node next;
        public Node first;

        public Node(Node c, T i, Node n) {
            item = i;
            next = n;
            first = c;
        }
    }


    private Node sentinel;
    private int size = 0;

    public LinkedListDeque(T x) {
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.next = new Node(sentinel, x, sentinel.next);
        sentinel.first = sentinel.next;
        size = 1;
    }


    public LinkedListDeque() {
        sentinel = new Node(sentinel, null, sentinel);
        sentinel.first = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node mid = sentinel.next;
        sentinel.next = new Node(sentinel, item, sentinel.next);
        mid.first = sentinel.next;

        size++;
    }

    public void addLast(T item) {
        Node mid = sentinel.first;
        sentinel.first = new Node(sentinel.first, item, sentinel);
        mid.next = sentinel.first;

        size++;
    }

    public boolean isEmpty() {
        return sentinel.first.item == null && sentinel.next.item == null;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node mid = sentinel.next;
        while (mid.next.item != null) {
            System.out.print(sentinel.item + " ");
            mid = mid.next;
        }
        System.out.println(mid.item);

    }

    public T removeFirst() {
        if (sentinel.next.item == null) {
            return null;
        }
        T miditem = sentinel.next.item;
        Node midnode = sentinel.next.next;
        sentinel.next = midnode;
        midnode.first = sentinel;
        size--;
        return miditem;
    }

    public T removeLast() {
        if (sentinel.first.item == null) {
            return null;
        }
        T mid = sentinel.first.item;
        Node midnode = sentinel.first.first;
        sentinel.first = midnode;
        midnode.next = sentinel;
        size--;
        return mid;
    }

    public T get(int index){
        if (index>=size || index < 0){
            return null;
        }
        Node mid = sentinel.next;
        while (index!=0){
            index--;
            mid = mid.next;
        }
        return mid.item;
    }

    // not finished
    public boolean equals(Object o){
        if(o instanceof LinkedListDeque){
            if(((LinkedListDeque<?>) o).size() == this.size()){
                int mid = 0;
                while(mid <=  size-1){
                    if(((LinkedListDeque<?>) o).get(mid)!=this.get(mid)){
                        return false;
                    }
                    mid++;
                }
                return true;
            }
        }
        return false;
    }
}