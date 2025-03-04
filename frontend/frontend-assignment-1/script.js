async function search(meal) {
    const url = `https://www.themealdb.com/api/json/v1/1/search.php?s=${meal}`;
    const response = await fetch(url);
    if (!response.ok) {
        throw new console.error();
        
    }
}