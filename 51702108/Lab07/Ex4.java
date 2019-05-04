import java.io.*;  

class Ex4 {  
  
    static int Color(int vertices)  
    {  
        int result = 0;  
        if (vertices % 2 == 0) {
            result = 2;  
        }
        else{
            result = 3;  
        }
        
        return result;  
    }   
  
    public static void main (String[] args)  
    {  
        int vertices = 3;  
          
        System.out.println("No. of colors require is: " + Color(vertices)); 
            
    }  
}  