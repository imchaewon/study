package com.example.java_.serialization.t1;

import lombok.AllArgsConstructor;
import lombok.ToString;

import java.io.*;

@ToString
@AllArgsConstructor
public class Animal implements Serializable{
	String name;
	int age;

	String bark(){
		return "동물이 짖는다";
	}
}

class Run{
	public static void main(String[] args) throws IOException {

//		직렬화(Serialization)
		FileOutputStream fos = new FileOutputStream("objectfile.ser");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		oos.writeObject(new Animal("navi", 12));

//		역직렬화(Deserialization)
		Animal a1 = null;
		try (FileInputStream fis = new FileInputStream("objectfile.ser");
			ObjectInputStream ois = new ObjectInputStream(fis)) {
			a1 = (Animal) ois.readObject();
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		System.out.println(a1);
	}
}