import java.lang.annotation.*;
import java.lang.Class;
import java.lang.reflect.*;
import java.util.ArrayList;

// ���������-������
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
	// ������������ ����� � ������� �������
	// �������� �������� �� �������������� ������ {}
	@MyMarker
	public static void myMeth(){
		Marker ob = new Marker();
		
		try {
			Method m = ob.getClass().getMethod("myMeth");
			
			// ���������� ������� ���������
			if(m.isAnnotationPresent(MyMarker.class))
				System.out.println("\n���������-������ MyMarker ������������.");
			else
				System.out.println("\n���������-������ MyMarker �����������.");
		} catch (NoSuchMethodException e){
			System.out.println("����� �� ������.");
		}
	}
	
	public static void main(String[] args){
		System.out.println("� ������ Marker: ");
		myMeth();
		
		System.out.println("\n� ������ Marker2: ");
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
			
			// ���������� ������� ���������
			for(Method m : t){
				name = m.getName();
				for(Class s : annotations)
					if(m.isAnnotationPresent(s))
						System.out.println("\n���������-������" + s.getName() + " � ������ " + name + " ������������.");
					else
						System.out.println("\n���������-������" + s.getName() + " � ������ " + name + " �����������.");
				}
		} catch (Exception e){
			System.out.println("��������� ����������: " + e);
			e.printStackTrace();
		}
	}
}
/*
�������� ���������-������ @MyMarker, ������ �� ����������� ��������� ������
����� � �����. ��� ��������, ��� ���������-������ @MyMarker ����������� ������ �� � 
�����. � ���� �������� ������ ����� ����� ���������-������� �� ��������� �������, ������ ���
������ �� �����������.
*/