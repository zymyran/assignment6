abstract class Pizza {
	protected double price;

	public Pizza(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}
}

class PlainPizza extends Pizza {
	public PlainPizza(double price) {
		super(price);
	}
}

abstract class PizzaDecorator extends Pizza {
	protected Pizza pizza;

	public PizzaDecorator(Pizza pizza) {
		super(pizza.getPrice());
		this.pizza = pizza;
	}

	public double getPrice() {
		return pizza.getPrice();
	}
}

class PepperoniTopping extends PizzaDecorator {
	public PepperoniTopping(Pizza pizza) {
		super(pizza);
	}

	public double getPrice() {
		return super.getPrice() + 1.50;
	}
}

class MushroomTopping extends PizzaDecorator {
	public MushroomTopping(Pizza pizza) {
		super(pizza);
	}

	public double getPrice() {
		return super.getPrice() + 1.00;
	}
}

public class PizzaDecoratorPattern {
	public static void main(String[] args) {
		Pizza pizza = new PlainPizza(8.99);

		pizza = new PepperoniTopping(pizza);

		pizza = new MushroomTopping(pizza);

		System.out.println("Final price of the pizza: $" + pizza.getPrice());
	}
}
