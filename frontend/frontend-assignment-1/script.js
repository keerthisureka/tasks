document.addEventListener("DOMContentLoaded", () => {
    loadCategories();
    document.getElementById("searchBtn").addEventListener("click", searchMeal);
    document.getElementById("categoryDropdown").addEventListener("change", searchCategory);
});

// Run function when page loads
document.addEventListener("DOMContentLoaded", () => {
    if (document.getElementById("meals")) {
        loadMealsByCategory();
    }
});

function handle(e){
    if(e.keyCode === 13){
        searchMeal();
    }
}

// Fetch and Display All Categories
async function loadCategories() {
    const res = await fetch("https://www.themealdb.com/api/json/v1/1/categories.php");
    const data = await res.json();
    
    const container = document.getElementById("categories");
    const dropdown = document.getElementById("categoryDropdown");

    container.innerHTML = "";
    dropdown.innerHTML = `<option value="">Select a Category</option>`;

    data.categories.forEach(category => {
        // Populate Grid
        const card = document.createElement("div");
        card.classList.add("card");
        card.innerHTML = `
            <img src="${category.strCategoryThumb}" alt="${category.strCategory}">
            <h3>${category.strCategory}</h3>
            <p>${category.strCategoryDescription.substring(0, 100)}...</p>
        `;
        card.addEventListener("click", () => {
            window.location.href = `category.html?category=${category.strCategory}`;
        });
        container.appendChild(card);

        // Populate Dropdown
        const option = document.createElement("option");
        option.value = category.strCategory;
        option.innerText = category.strCategory;
        dropdown.appendChild(option);
    });
}

// Select Category and Show Description
async function searchCategory() {
    const searchText = document.getElementById("searchBar").value.toLowerCase();
    const dropdownText = document.getElementById("categoryDropdown").value;

    const res = await fetch("https://www.themealdb.com/api/json/v1/1/categories.php");
    const data = await res.json();

    if (!searchText) return;

    const container = document.getElementById("categories");
    container.innerHTML = ""; 

    let foundCategory = null;

    // Prioritize dropdown selection
    if (dropdownText) {
        foundCategory = data.categories.find(cat => cat.strCategory === dropdownText);
    }

    // If no dropdown selection, use search input
    if (!foundCategory && searchText) {
        foundCategory = data.categories.find(cat => cat.strCategory.toLowerCase().startsWith(searchText));
    }

    if (foundCategory) {
        // Display Only Selected/Searched Category
        const card = document.createElement("div");
        card.classList.add("card");
        card.classList.add("grid-container");
        card.innerHTML = `
            <img src="${foundCategory.strCategoryThumb}" alt="${foundCategory.strCategory}">
            <h3>${foundCategory.strCategory}</h3>
            <p>${foundCategory.strCategoryDescription}</p>
        `;
        card.addEventListener("click", () => {
            window.location.href = `category.html?category=${foundCategory.strCategory}`;
            closeMealModal();
        });
        container.appendChild(card);
    } else {
        container.innerHTML = "<p>No category found!</p>";
    }
}

// Fetch and Display Meals in a Category
async function loadMealsByCategory() {
    const params = new URLSearchParams(window.location.search);
    const category = params.get("category");

    console.log("Selected Category:", category);

    if (!category) {
        document.getElementById("meals").innerHTML = "<p>No category selected.</p>";
        console.log("No category found in URL!");
        return;
    }

    // Update category title
    document.getElementById("categoryTitle").innerText = `Meals in ${category}`;

    // Fetch meals under the category
    const res = await fetch(`https://www.themealdb.com/api/json/v1/1/filter.php?c=${category}`);
    const data = await res.json();

    console.log("Meals API Response:", data);

    const container = document.getElementById("meals");
    container.innerHTML = "";

    if (!data.meals) {
        container.innerHTML = "<p>No meals found for this category.</p>";
        console.log("No meals found!");
        return;
    }

    // Display all meals under the selected category
    data.meals.forEach(meal => {
        console.log("Meal Loaded:", meal.strMeal);

        const card = document.createElement("div");
        card.classList.add("card");
        card.innerHTML = `
            <img src="${meal.strMealThumb}" alt="${meal.strMeal}">
            <h3>${meal.strMeal}</h3>
        `;

        // Clicking a meal should show details
        card.addEventListener("click", () => showMealDetails(meal.strMeal));

        container.appendChild(card);
    });
}

// Fetch and Show Meal Details in Modal
async function showMealDetails(mealName) {
    const res = await fetch(`https://www.themealdb.com/api/json/v1/1/search.php?s=${mealName}`);
    const data = await res.json();
    
    if (!data.meals) {
        alert("Meal details not found!");
        return;
    }

    const meal = data.meals[0];

    // Update Modal Content
    document.getElementById("mealTitle").innerText = meal.strMeal;
    document.getElementById("mealImage").src = meal.strMealThumb;
    document.getElementById("mealInstructions").innerText = meal.strInstructions.substring(0, 300) + "...";
    document.getElementById("mealVideo").href = meal.strYoutube;
    
    // Show Modal
    document.getElementById("mealModal").style.display = "block";
}

// Close Modal Function
function closeMealModal() {
    document.getElementById("mealModal").style.display = "none";
}

// Go Back to Home Page
function goBack() {
    window.location.href = "home.html";
}

// Function to handle search for a meal by name
async function searchMeal() {
    const searchText = document.getElementById("searchBar").value.toLowerCase();

    // Check if the search input is empty
    if (!searchText) {
        return;
    }

    const res = await fetch(`https://www.themealdb.com/api/json/v1/1/search.php?s=${searchText}`);
    const data = await res.json();

    const container = document.getElementById("categories");
    container.innerHTML = "";

    // If there are no results
    if (data.meals === null) {
        container.innerHTML = "<p>No meals found!</p>";
        return;
    }

    // Display results
    data.meals.forEach(meal => {
        console.log("Meal Loaded:", meal.strMeal);

        const card = document.createElement("div");
        card.classList.add("card");
        card.innerHTML = `
            <img src="${meal.strMealThumb}" alt="${meal.strMeal}">
            <h3>${meal.strMeal}</h3>
        `;

        // Clicking a meal should show details
        card.addEventListener("click", () => showMealDetails(meal.strMeal));

        container.appendChild(card);
    });
}