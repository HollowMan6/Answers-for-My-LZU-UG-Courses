class ArraySizeException extends NegativeArraySizeException{
   ArraySizeException() {
      super("您传递的是非法的数组大小");
  }
}

class UserExceptionDemo {
int size, array[];
UserExceptionDemo(int s) {
size = s;
try {	checkSize();	}
catch(ArraySizeException e) {System.out.println(e);}
}
void checkSize() throws ArraySizeException {
if(size < 0) 	throw new ArraySizeException();
array = new int[size];
for(int i = 0; i < size; i++) {
  array[i] = i+1;
     System.out.print(array[i]+" ");
       }
}
public static void main(String arg[]) { 
   new UserExceptionDemo(Integer.parseInt(arg[0])); }
} 
