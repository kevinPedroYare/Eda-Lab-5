public class Node<T>{

    private T dato;
    private Node<T> left;
    private Node<T> right;
    private int factorE;
    
    public Node(){	
        dato = null;
        left = null;
        right = null;
        factorE = 0;
    }    
    public Node(T dato){	
        this.dato = dato;
        left = null;
        right = null;
        factorE = 0;
    }    
    
    public Node<T> getleft(){
        return left;
    }

    public Node<T> getDerecha(){
        return right;
    }
    
    public T getDato(){
        return dato;
    }
    
    public void setDerecha(Node<T> right){
        this.right = right;
    }
    
    public void setleft(Node<T> left){
        this.left = left;
    }
    
    public void setDato(T dato){
        this.dato = dato;
    }
    
    public int getFactorE(){
        int altDer = 0;
        int altIzq = 0;
        if(this.getDerecha()!=null){
            altDer = this.getDerecha().getAltura();
           }
           if(this.getleft()!=null){		    
               altIzq = this.getleft().getAltura();
           }
        return (altDer - altIzq);
    }
    
    public void setFactorE(int fe){
        this.factorE = fe;
    }
    
    public int getAltura(){
        int hIzq = 0;
        int hDer = 0;
        
        if(this.getDato()==null){
          return 0;
        }


        if(this.getleft()!=null){	
            hIzq = this.getleft().getAltura();
        }else{
            hIzq = 0;
        }
        
        if(this.getDerecha()!=null){   
            hDer = this.getDerecha().getAltura();
        }else{
            hDer = 0;
        }
        return Math.max(hIzq, hDer) + 1;
    }
}   