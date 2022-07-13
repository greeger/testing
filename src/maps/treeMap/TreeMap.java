package maps.treeMap;

import maps.Map;

import java.io.PrintStream;

public class TreeMap<K extends Comparable<? super K>,V> implements Map<K,V>{
    private Node<K,V> root;
    public TreeMap(){
        this.root=null;
    }
    @Override
    public boolean add(K key, V value) {
        Node node=new Node<K,V>();
        node.key=key;
        node.value=value;
        if(this.root==null){
            this.root=node;
            return true;
        }
        Node currNode=this.root;
        while(true){
            int cmp=node.key.compareTo(currNode.key);
            if(cmp<0){
                if(currNode.lc==null){
                    currNode.lc=node;
                    node.parent=currNode;
                    return true;
                }
                else
                    currNode=currNode.lc;
            } else if (cmp>0) {
                if(currNode.rc==null){
                    currNode.rc=node;
                    node.parent=currNode;
                    return true;
                }
                else
                    currNode=currNode.rc;
            } else
                return false;
        }
    }

    @Override
    public boolean delete(K key) {
        if(this.root==null) return false;
        Node currNode=this.root;
        boolean isLeft=true;
        while (true) {
            int cmp = key.compareTo((K) currNode.key);
            if(cmp<0)
                if(currNode.lc!=null){
                    isLeft=true;
                    currNode=currNode.lc;
                }
                else return false;
            else if(cmp>0)
                if(currNode.rc!=null){
                    isLeft=false;
                    currNode=currNode.rc;
                }
                else return false;
            else{
                if(currNode.lc==null&&currNode.rc==null){
                    if(currNode.parent==null){
                        this.root=null;
                        return true;
                    }
                    else{
                        if(isLeft)
                            currNode.parent.lc=null;
                        else
                            currNode.parent.rc=null;
                        return true;
                    }
                }
                else if(currNode.lc==null){
                    if(currNode.parent==null){
                        currNode.rc.parent=null;
                        this.root=currNode.rc;
                        return true;
                    }
                    else{
                        currNode.rc.parent=currNode.parent;
                        if(isLeft)
                            currNode.parent.lc=currNode.rc;
                        else
                            currNode.parent.rc=currNode.rc;
                        return true;
                    }
                }
                else if(currNode.rc==null){
                    if(currNode.parent==null){
                        currNode.lc.parent=null;
                        this.root=currNode.lc;
                        return true;
                    }
                    else{
                        currNode.lc.parent=currNode.parent;
                        if(isLeft)
                            currNode.parent.lc=currNode.lc;
                        else
                            currNode.parent.rc=currNode.lc;
                        return true;
                    }
                }
                else {
                    Node next = getNext(currNode);
                    if (currNode.parent == null)
                        this.root = next;
                    else {
                        if (isLeft)
                            currNode.parent.lc = next;
                        else
                            currNode.parent.rc = next;
                    }
                    currNode.lc.parent=next;
                    if(currNode.rc!=null)
                        currNode.rc.parent=next;
                    next.parent = currNode.parent;
                    next.lc=currNode.lc;
                    next.rc=currNode.rc;
                    return true;
                }
            }
        }
    }

    private Node getNext(Node node){
        if(node.rc.lc==null)
            if(node.rc.rc==null){
                Node bufNode=node.rc;
                node.rc=null;
                return bufNode;
            }
            else{
                Node bufNode=node.rc;
                node.rc=bufNode.rc;
                bufNode.rc.parent=node;
                return bufNode;
            }
        else{
            Node currNode=node.rc;
            while (currNode.lc!=null)
                currNode=currNode.lc;
            if(currNode.rc==null){
                currNode.parent.lc=null;
                return currNode;
            }
            else{
                currNode.parent.lc=currNode.rc;
                currNode.rc.parent=currNode.parent;
                return currNode;
            }
        }
    }

    @Override
    public V getValue(K key) {
        if(this.root==null) return null;
        Node currNode=this.root;
        while (true) {
            int cmp = key.compareTo((K) currNode.key);
            if(cmp<0)
                if(currNode.lc!=null)
                    currNode=currNode.lc;
                else return null;
            else if(cmp>0)
                if(currNode.rc!=null)
                    currNode=currNode.rc;
                else return null;
            else
                return (V)currNode.value;
        }
    }

    @Override
    public void print(PrintStream out) {
        if(this.root==null){
            out.println();
            return;
        }
        Node currNode=this.root;
        while(true){
            out.println("{"+currNode.key.toString()+": "+currNode.value.toString()+"}  parent: "+
                    (currNode.parent!=null?currNode.parent.key.toString():"none")+"  children: "+
                    (currNode.lc==null?"null":currNode.lc.key)+", "+(currNode.rc==null?"null":currNode.rc.key));
            if(currNode.lc!=null)
                currNode=currNode.lc;
            else if(currNode.rc!=null)
                currNode=currNode.rc;
            else{
                if(currNode.parent==null){
                    out.println("\n");
                    return;
                }
                while (true){
                    if(currNode.parent.key.compareTo(this.root.key)==0)
                            if(this.root.rc==null||currNode.key.compareTo(this.root.rc.key)==0){
                                out.println("\n");
                                return;
                            }
                    if(currNode.parent.rc!=null&&currNode.parent.rc.key.compareTo(currNode.key)!=0){
                        currNode=currNode.parent.rc;
                        break;
                    }
                    else currNode=currNode.parent;
                }
            }
        }
    }
}
