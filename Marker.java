import java.lang.annotation.*;
import java.lang.reflect.*;

// ���������-������
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
		myMeth();
		Marker2 m2 = new Marker2();
	}
}
/*
�������� ���������-������ @MyMarker, ������ �� ����������� ��������� ������
����� � �����. ��� ��������, ��� ���������-������ @MyMarker ����������� ������ �� � 
�����. � ���� �������� ������ ����� ����� ���������-������� �� ��������� �������, ������ ���
������ �� �����������.
*/