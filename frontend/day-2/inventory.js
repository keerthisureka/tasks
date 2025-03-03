const book1 = {
    "title": "The Great Expectations",
    "author": "ABC",
    "price": 200,
    "isAvailable": true
};
console.log("Book 1:", book1, "\n\n");

const book2 = {
    "title": "DSA",
    "author": "Mr. Xyz",
    "price": 500,
    "isAvailable": false
};
console.log("Book 2:", book2, "\n\n");

const inventoryList = [];
// Add Book
function addBook(item) {
    inventoryList.push(item);
}
addBook(book1);
addBook(book2);
console.log("After adding books, Inventory List:", inventoryList, "\n\n");

// Delete Book based on title
function deleteBook(title) {
    let index = inventoryList.findIndex(book => book.title === title);
    inventoryList.splice(index, 1);
}
deleteBook("DSA");
console.log("After deleting book by title 'DSA', Inventory List:", inventoryList, "\n\n");

// Find Book by title
addBook(book2);
function findBook(title) {
    return inventoryList.filter(book => book.title === title);
}
console.log("The book with title 'The Great Expectations':", findBook("The Great Expectations"), "\n\n");

// List available books
function listAvailableBooks() {
    return inventoryList.filter(book => book.isAvailable === true);
}
console.log("Available Books:", listAvailableBooks(), "\n\n");