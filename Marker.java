import java.lang.annotation.*;
import java.lang.reflect.*;

// Аннотация-маркер
@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker {}
@interface MyMarkerNew {}

class Marker2 {

	@MyMarker
	@MyMarkerNew
	public void newMethod(){}
	
	@MyMarker
	public void newMethod2(){}
	
	@MyMarkerNew
	public void newMethod3(){}
}

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
			else
				System.out.println("Аннотация-маркер MyMarker отсутствует.");
		} catch (NoSuchMethodException e){
			System.out.println("Метод не найден.");
		}
	}
	
	public static void main(String[] args){
		myMeth();
		Marker2 m2 = new Marker2();
		String name;
		
		try {
			Method[] t = ob.getClass().getMethods();
			
			// определить наличие аннотаций
			for(Method m : t){
				name = m.getName();
				if(m.isAnnotationPresent(MyMarker.class))
					System.out.println("Аннотация-маркер MyMarker в методе " + name + " присутствует.");
				else
					System.out.println("Аннотация-маркер MyMarker в методе " + name + " отсутствует.");
					
				if(m.isAnnotationPresent(MyMarkerNew.class))
					System.out.println("Аннотация-маркер MyMarkerNew в методе " + name + " присутствует.");
				else
					System.out.println("Аннотация-маркер MyMarkerNew в методе " + name + " отсутствует.");
				}
		} catch (Exception e){
			System.out.println("Выброшено исключение: " + e);
			e.printStackTrace();
		}
	}
}
/*
Применяя аннотацию-маркер @MyMarker, совсем не обязательно указывать скобки
после её имени. Это означает, что аннотация-маркер @MyMarker применяется просто по её 
имени. И хотя указание скобок после имени аннотации-маркера не считается ошибкой, делать это
совсем не обязательно.
*/