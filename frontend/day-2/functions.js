/*
    This function will be called without any errors since the function has global access
*/
console.log(findMax(20, 30, 10, 40, 50));

// 1. Function Declaration: Create a function named findMax that takes five numbers as a parameter and returns the largest number
function findMax(num1, num2, num3, num4, num5) {
    return Math.max(num1, num2, num3, num4, num5);
}

/*
    This will return undefined if "var sum" is declared, since var has global access:
    console.log('sum:', sum)
*/
/*
    This will return an error, since the function is not hoisted in this case:
    console.log('sum fun:', sum())
*/
// 2. Function Expression: Define a function expression named sum that takes five numbers and returns the sum of all the numbers
const sum = function (num1, num2, num3, num4, num5) {
    return num1 + num2 + num3 + num4 + num5;
}
console.log(sum(2, 3, 4, 5, 6));

// 3. Arrow Function: Create a function to multiply 2 numbers
const multiply = (num1, num2) => num1 * num2;
console.log(multiply(3, 2));