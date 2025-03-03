// Sorting
let arr = [2, 10, 3, 70, 50];
console.log(arr.sort((a, b) => a - b)); // sort() otherwise sorts as string, so we add a comparator

// Student
const students = [
    {
        name: "Alice",
        age: 20,
        grade: 85
    },
    {
        name: "Bob",
        age: 22,
        grade: 90
    },
    {
        name: "Charlie",
        age: 19,
        grade: 78
    },
    {
        name: "John",
        age: 21,
        grade: 82
    }
]

// Add 2 students
function addStudent(item) {
    students.push(item);
}
addStudent({name: "ABC", age: 21, grade: 88});
addStudent({name: "XYZ", age: 20, grade: 98});
console.log("Students:", students);

// Last student removed
function removeLastStudent() {
    students.pop();
}
removeLastStudent();
console.log("After Last student removed:", students);

// Older than 18
function olderThan18() {
    return students.filter(student => {return student.age > 18});
}
console.log("Students older than 18: ", olderThan18());

// Array of grades
const arrOfGrades = () => students.map(student => student.grade);
console.log(arrOfGrades());

// Average grade of all students
const avgOfGrades = () => {
    let sum = arrOfGrades().reduce((accu, curr) => accu + curr, 0);
    return sum / arrOfGrades().length;
}
console.log(avgOfGrades());