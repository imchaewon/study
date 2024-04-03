package com.example.java_.gofDesignPatterns.behavioral.visitor.t1;

public class Run {
	public static void main(String[] args) {
		Animal[] animals = {new Cat(), new Dog(), new Bird()};
		SoundVisitor soundVisitor = new SoundVisitor();

		for (Animal animal : animals) {
			animal.accept(soundVisitor);
		}
	}
}

interface AnimalVisitor{
	void visit(Cat cat);
	void visit(Dog dog);
	void visit(Bird bird);
}

interface Animal{
	void accept(AnimalVisitor visitor);
}

class Cat implements Animal {
	@Override
	public void accept(AnimalVisitor visitor) {
		visitor.visit(this);
	}
}

class Dog implements Animal {
	@Override
	public void accept(AnimalVisitor visitor) {
		visitor.visit(this);
	}
}

class Bird implements Animal {
	@Override
	public void accept(AnimalVisitor visitor) {
		visitor.visit(this);
	}
}

class SoundVisitor implements AnimalVisitor{
	@Override
	public void visit(Cat cat) {
		System.out.println("야옹");
	}

	@Override
	public void visit(Dog dog) {
		System.out.println("멍멍");
	}

	@Override
	public void visit(Bird bird) {
		System.out.println("짹짹");
	}
}



















