document.addEventListener('DOMContentLoaded', () => {
    const searchBar = document.getElementById('search-bar');
    const searchButton = document.getElementById('search-button');
    const categoryDropdown = document.getElementById('category-dropdown');
    const mealContainer = document.getElementById('meal-container');
    const mealModal = document.getElementById('meal-modal');
    const modalContent = document.querySelector('.modal-content');
    const modalClose = document.querySelector('.close');

    let allMeals = [];
    let allCategories = [];

    async function fetchCategories() {
        try {
            const response = await fetch('https://www.themealdb.com/api/json/v1/1/categories.php');
            const data = await response.json();
            allCategories = data.categories;
            populateCategoryDropdown();
            displayMeals(allCategories);
        } catch (error) {
            console.error('Error fetching categories:', error);
        }
    }

    function populateCategoryDropdown() {
        allCategories.forEach(category => {
            const option = document.createElement('option');
            option.value = category.strCategory;
            option.textContent = category.strCategory;
            categoryDropdown.appendChild(option);
        });
    }

    async function fetchMealsByCategory(category) {
        try {
            const response = await fetch(`https://www.themealdb.com/api/json/v1/1/filter.php?c=${category}`);
            const data = await response.json();
            return data.meals;
        } catch (error) {
            console.error(`Error fetching meals for category ${category}:`, error);
            return [];
        }
    }

    async function fetchMealDetails(mealName) {
        try {
            const response = await fetch(`https://www.themealdb.com/api/json/v1/1/search.php?s=${mealName}`);
            const data = await response.json();
            return data.meals ? data.meals[0] : null;
        } catch (error) {
            console.error(`Error fetching details for meal ${mealName}:`, error);
            return null;
        }
    }

    async function displayMeals(categories) {
        mealContainer.innerHTML = '';
        for (const category of categories) {
            const meals = await fetchMealsByCategory(category.strCategory);
            meals.forEach(meal => {
                const mealCard = createMealCard(meal);
                mealContainer.appendChild(mealCard);
            });
        }
    }

    function createMealCard(meal) {
        const card = document.createElement('div');
        card.className = 'meal-card';
        card.innerHTML = `
            <img src="${meal.strMealThumb}" alt="${meal.strMeal}">
            <h3>${meal.strMeal}</h3>
        `;
        card.addEventListener('click', () => showMealDetails(meal.strMeal));
        return card;
    }

    async function showMealDetails(mealName) {
        const meal = await fetchMealDetails(mealName);
        if (meal) {
            modalContent.innerHTML = `
                <span class="close">&times;</span>
                <div class="modal-body">
                    <div class="modal-image">
                        <img src="${meal.strMealThumb}" alt="${meal.strMeal}">
                    </div>
                    <div class="modal-description">
                        <h2>${meal.strMeal}</h2>
                        <p><strong>Category:</strong> ${meal.strCategory}</p>
                        <p><strong>Area:</strong> ${meal.strArea}</p>
                        <p><strong>Instructions:</strong> ${meal.strInstructions}</p>
                        ${meal.strYoutube ? `<a href="${meal.strYoutube}" target="_blank">Watch Recipe Video</a>` : ''}
                    </div>
                </div>
            `;
            mealModal.style.display = 'block';

            const modalClose = modalContent.querySelector('.close');
            modalClose.addEventListener('click', () => {
                mealModal.style.display = 'none';
            });
        }
    }

    async function filterMeals() {
        const searchTerm = searchBar.value.toLowerCase();
        const selectedCategory = categoryDropdown.value;
        mealContainer.innerHTML = '';

        for (const category of allCategories) {
            if (selectedCategory === 'all' || category.strCategory === selectedCategory) {
                const meals = await fetchMealsByCategory(category.strCategory);
                meals.forEach(meal => {
                    if (meal.strMeal.toLowerCase().includes(searchTerm)) {
                        const mealCard = createMealCard(meal);
                        mealContainer.appendChild(mealCard);
                    }
                });
            }
        }
    }

    searchBar.addEventListener('input', filterMeals);
    searchButton.addEventListener('click', filterMeals);
    categoryDropdown.addEventListener('change', filterMeals);

    function closeModal() {
        mealModal.style.display = 'none';
    }

    if (modalClose) {
        modalClose.addEventListener('click', closeModal);
    }

    window.addEventListener('click', (event) => {
        if (event.target === mealModal) {
            closeModal();
        }
    });
    
    fetchCategories();
});
