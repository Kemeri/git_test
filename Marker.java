import java.lang.annotation.*;
import java.lang.reflect.*;

// Аннотация-маркер
@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker {}

class Marker {
	// аннотировать метод с помощью маркера
	// Обратить внимание на обязательность скобок {}
	@MyMarker
	public static void myMeth(){
		Marker ob = new Marker();
		
		try {
			Method m = ob.getClass().getMethod("myMeth");
			
			// определить наличие аннотации
			if(m.isAnnotationPresent(MyMarker.class))
				System.out.println("Аннотация-маркер MyMarker присутствует.");
		} catch (NoSuchMethodException e){
			System.out.println("Метод не найден.");
		}
	}
	
	public static void main(String[] args){
		myMeth();
	}
}
/*
Применяя аннотацию-маркер @MyMarker, совсем не обязательно указывать скобки
после её имени. Это означает, что аннотация-маркер @MyMarker применяется просто по её 
имени. И хотя указание скобок после имени аннотации-маркера не считается ошибкой, делать это
совсем не обязательно.
*/