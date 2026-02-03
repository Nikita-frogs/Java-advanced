## Upcasting (висхідне перетворення)
Йдемо вгору по ієрархії до батьківського методу. Є цілком безпечним.
## Downcasting (низхідне перетворення)
Йдемо вниз по ієрархії до методу нащадку.
==Є небезпечним через ClassCastException й потребує перевірки, оскільки ми маємо перевірити чи справді змінна має цей клас нащадок.==

Перевірка при downcasting:
```
Animal a = new Dog();
Animal b = new Cat();

if(a instanceof Dog dog){
	dog.fetchstick();
}
else{
	System.out.println("This is not a dog");
}
```

