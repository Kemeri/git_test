import java.lang.annotation.*;
import java.lang.Class;
import java.lang.reflect.*;
import java.util.ArrayList;

// ���������-������
@Retention(RetentionPolicy.RUNTIME)
@interface MyMarker {}
@interface MyMarkerNew {}

class Marker2 {

	@MyMarker
	@MyMarkerNew
	public void newMethod1(){}
	
	@MyMarker
	public void newMethod2(){}
	
	@MyMarkerNew
	public void newMethod3(){}
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
				System.out.println("���������-������ MyMarker ������������.");
			else
				System.out.println("���������-������ MyMarker �����������.");
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
			ArrayList<Method> t =  new ArrayList<>();
			Class c = m2.getClass();
			t.add(c.getMethod("newMethod1"));
			t.add(c.getMethod("newMethod2"));
			t.add(c.getMethod("newMethod3"));
			
			// ���������� ������� ���������
			for(Method m : t){
				name = m.getName();
				if(m.isAnnotationPresent(MyMarker.class))
					System.out.println("\n���������-������ MyMarker � ������ " + name + " ������������.");
				else
					System.out.println("\n���������-������ MyMarker � ������ " + name + " �����������.");
					
				if(m.isAnnotationPresent(MyMarkerNew.class))
					System.out.println("\n���������-������ MyMarkerNew � ������ " + name + " ������������.");
				else
					System.out.println("\n���������-������ MyMarkerNew � ������ " + name + " �����������.");
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