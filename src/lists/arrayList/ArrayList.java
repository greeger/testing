package lists.arrayList;

import myExceptions.PositionOutOfBoundsException;
import lists.List;

import java.io.PrintStream;

public class ArrayList<T> implements List<T>{
    private Object[] array;
    private int size;

    public ArrayList(){
        this.array=new Object[10];
        this.size=0;
    }

    public ArrayList(T[] values){
        this.size=values.length;
        array = new Object[this.size+10];
        for(int i=0; i<this.size; i++)
            array[i]=values[i];
    }

    @Override
    public int getSize(){
        return this.size;
    }

    @Override
    public boolean insert(T value, int position){
        if(position>size||position<0) return false;
        if(this.array.length>this.size){
            for(int i=this.size; i>position; i--)
                this.array[i]=this.array[i-1];
            this.array[position]=value;
            this.size++;
            return true;
        }
        else{
            Object[] newArray = new Object[this.size+11];
            for(int i=0;i<position;i++)
                newArray[i]=this.array[i];
            newArray[position]=value;
            for(int i=position+1;i<this.size+1;i++)
                newArray[i]=this.array[i-1];
            this.array=newArray;
            this.size++;
            return true;
        }
    }

    @Override
    public boolean delete(int position){
        if(position>=size||position<0) return false;
        for(int i=position; i<this.size-1; i++)
            this.array[i]=this.array[i+1];
        this.size--;
        return true;
    }

    @Override
    public T getElem(int position) throws PositionOutOfBoundsException {
        if(position>=size||position<0)
            throw new PositionOutOfBoundsException(position);
        return (T)this.array[position];
    }

    @Override
    public void print(PrintStream out){
        for(int i=0; i<this.size; i++)
            out.print(array[i]+" ");
        out.println();
        return;
    }
}
