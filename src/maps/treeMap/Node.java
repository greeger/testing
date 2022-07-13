package maps.treeMap;

class Node<K extends Comparable<? super K>,V> {
    public K key;
    public V value;
    public Node parent;
    public Node lc;
    public Node rc;
}
