import java.lang.annotation.*;
import java.lang.Class;
import java.lang.reflect.*;
import java.util.ArrayList;

// Аннотация-маркер
@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker {}

@Retention(RetentionPolicy.RUNTIME)
@interface MyMarkerNew {}

@Retention(RetentionPolicy.RUNTIME)
@interface BlaBlaMarker {}

class Marker2 {

	@MyMarker
	@MyMarkerNew
	@BlaBlaMarker
	public void newMethod1(){}
	
	@BlaBlaMarker
	@MyMarker
	public void newMethod2(){}
	
	@BlaBlaMarker
	@MyMarkerNew
	public void newMethod3(){}
	
	@BlaBlaMarker
	public void newMethod4(){}
	
	@MyMarker
	@MyMarkerNew
	public void newMethod4(){}
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
				System.out.println("\nАннотация-маркер MyMarker присутствует.");
			else
				System.out.println("\nАннотация-маркер MyMarker отсутствует.");
		} catch (NoSuchMethodException e){
			System.out.println("Метод не найден.");
		}
	}
	
	public static void main(String[] args){
		System.out.println("В классе Marker: ");
		myMeth();
		
		System.out.println("\nВ классе Marker2: ");
		Marker2 m2 = new Marker2();
		String name;
		
		try {
			ArrayList<Class> annotations = new ArrayList<>();
			annotations.add(MyMarker.class);
			annotations.add(MyMarkerNew.class);
			annotations.add(BlaBlaMarker.class);
			
			ArrayList<Method> t =  new ArrayList<>();
			Class c = m2.getClass();
			t.add(c.getMethod("newMethod1"));
			t.add(c.getMethod("newMethod2"));
			t.add(c.getMethod("newMethod3"));
			t.add(c.getMethod("newMethod4"));
			t.add(c.getMethod("newMethod5"));
			
			// определить наличие аннотаций
			for(Method m : t){
				name = m.getName();
				for(Class s : annotations)
					if(m.isAnnotationPresent(s))
						System.out.println("\nАннотация-маркер" + s.getName() + " в методе " + name + " присутствует.");
					else
						System.out.println("\nАннотация-маркер" + s.getName() + " в методе " + name + " отсутствует.");
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