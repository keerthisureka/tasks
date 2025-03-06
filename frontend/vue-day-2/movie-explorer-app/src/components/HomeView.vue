<template>
  <div class="home-container">
    <h1>Movie Explorer App</h1>
    <SearchBar @search="searchMovies" />
    <router-link to="/favorites" class="favorites-button" tag="button">
      View Favorites
    </router-link>
    <MovieList :movies="movies" />
  </div>
</template>

<script>
import SearchBar from './SearchBar.vue';
import MovieList from './MovieList.vue';

export default {
  components: { SearchBar, MovieList },
  data() {
    return {
      movies: []
    };
  },
  methods: {
    async searchMovies(query) {
      try {
        const response = await fetch(`https://www.omdbapi.com/?apikey=2205c78f&s=${query}`);
        if (!response.ok) throw new Error(response.status);
        const data = await response.json();
        this.movies = data.Search || [];
      } catch (error) {
        console.error(error);
      }
    }
  }
};
</script>

<style scoped>
.home-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 20px;
  padding: 20px;
}

.favorites-button {
  margin-bottom: 20px;
  padding: 10px 20px;
  background-color: #ff9800;
  color: #fff;
  text-decoration: none;
  border-radius: 20px;
  transition: background-color 0.3s ease;
}

.favorites-button:hover {
  background-color: #e68900;
}
</style>
