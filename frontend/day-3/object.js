const person = {
    "name": "John",
    "age": 20,
    "city": "Bangalore",
    greet: function() {
        console.log(`Hello, my name is ${this.name}`);
    },
    addTwoNums: (num1, num2) => num1 + num2,
    greet2: () => console.log(this)
}

console.log(person.name);
person.greet();
console.log(person.addTwoNums(4, 5));
console.log(`I am ${person.name}, ans I am ${person.age} years old.`);

person.greet2();