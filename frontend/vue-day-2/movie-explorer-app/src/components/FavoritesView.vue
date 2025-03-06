<template>
  <div class="favorites-container">
    <h1>Your Favorite Movies</h1>
    <MovieList :movies="favorites" />
  </div>
</template>

<script>
import MovieList from './MovieList.vue';

export default {
  components: { MovieList },
  data() {
    return {
      favorites: [],
    };
  },
  mounted() {
    this.loadFavorites();
  },
  methods: {
    loadFavorites() {
      const storedFavorites = localStorage.getItem('favorites');
      if (storedFavorites) {
        this.favorites = JSON.parse(storedFavorites);
      }
    },
  }
};
</script>

<style scoped>
.favorites-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  padding: 20px;
}
</style>
