let obj = {
    name: "Alice",
    age: 20
}
const stringify = JSON.stringify(obj); // converts to string
console.log(stringify);
const parsed = JSON.parse(stringify); // converts to json
console.log(parsed);

// fetch
async function getData(meal) {
    // fetch(`https://www.themealdb.com/api/json/v1/1/search.php?s=${meal}`)
    // .then((data) => console.log(data))
    // .catch((err) => console.log(err))
    // .finally(() => console.log("Done!"));
    const url = `https://www.themealdb.com/api/json/v1/1/search.php?s=${meal}`;
    try {
    const response = await fetch(url);
    if (!response.ok) {
        throw new Error(`Response status: ${response.status}`);
    }
    const json = await response.json();
    console.log(json);
    } catch (error) {
        console.error(error.message);
    }
}
getData("Sushi");