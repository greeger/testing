package lists.linkedList;

import myExceptions.PositionOutOfBoundsException;
import lists.List;

import java.io.PrintStream;

public class LinkedList<T> implements List<T>{
    private Node<T> head;
    private int size;

    public LinkedList(){
        this.head=null;
        this.size=0;
    }

    public LinkedList(T[] values){
        this.size=values.length;
        if(this.size==0) head=null;
        else
            for(int i=0; i<this.size; i++){
                Node currNode = new Node<T>();
                currNode.value=values[this.size-i-1];
                currNode.next=head;
                head=currNode;
            }
    }

    @Override
    public int getSize(){
        return this.size;
    }

    @Override
    public boolean insert(T value, int position){
        if(position>this.size||position<0) return false;
        Node node=new Node<T>();
        node.value=value;
        if(position==0){
            node.next=this.head;
            this.head=node;
            this.size++;
            return true;
        }
        Node currNode=head;
        for(int i=0; i<position-1; i++)
            currNode=currNode.next;
        node.next=currNode.next;
        currNode.next=node;
        this.size++;
        return true;
    }

    @Override
    public boolean delete(int position){
        if(position>=this.size||position<0) return false;
        if(position==0){
            this.head=this.head.next;
            this.size--;
            return true;
        }
        Node currNode=head;
        for(int i=0; i<position-1; i++)
            currNode=currNode.next;
        currNode.next=currNode.next.next;
        this.size--;
        return true;
    }

    @Override
    public T getElem(int position) throws PositionOutOfBoundsException{
        if(position>=this.size||position<0)
            throw new PositionOutOfBoundsException(position);
        Node currNode=head;
        for(int i=0; i<position; i++)
            currNode=currNode.next;
        return (T)currNode.value;
    }

    @Override
    public void print(PrintStream out){
        Node currNode=head;
        while (currNode!=null){
            out.print(currNode.value+" ");
            currNode=currNode.next;
        }
        out.println();
        return;
    }
}
