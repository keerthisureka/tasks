<template>
  <div class="movie-card" v-highlight="isFavorite" @mouseover="hover = true" @mouseleave="hover = false">
    <div class="poster">
      <slot name="poster"></slot>
    </div>
    <div class="details">
      <slot name="title"></slot>
      <button @click.stop="toggleFavorite" class="favorite-button">
        {{ isFavorite ? 'Remove from Favorites' : 'Add to Favorites' }}
      </button>
    </div>
  </div>
</template>

<script>
export default {
  props: ['movie'],
  data() {
    return {
      isFavorite: false,
      hover: false,
    };
  },
  watch: {
    movie: {
      immediate: true,
      handler() {
        this.checkFavorite();
      },
    },
  },
  methods: {
    checkFavorite() {
      const favorites = JSON.parse(localStorage.getItem('favorites') || '[]');
      this.isFavorite = favorites.some(
        (fav) => fav.imdbID === this.movie.imdbID
      );
    },
    toggleFavorite() {
      let favorites = JSON.parse(localStorage.getItem('favorites') || '[]');
      if (this.isFavorite) {
        favorites = favorites.filter(
          (fav) => fav.imdbID !== this.movie.imdbID
        );
      } else {
        favorites.push(this.movie);
      }
      localStorage.setItem('favorites', JSON.stringify(favorites));
      this.isFavorite = !this.isFavorite;
    },
  },
};
</script>

<style scoped>
.movie-card {
  width: 250px;
  border-radius: 10px;
  overflow: hidden;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  transition: transform 0.3s ease, box-shadow 0.3s ease;
  cursor: pointer;
  background-color: #fff;
}

.movie-card:hover {
  transform: scale(1.05);
  box-shadow: 0 8px 16px rgba(0, 0, 0, 0.3);
  z-index: 1;
}

.poster {
  height: 300px;
  overflow: hidden;
}

.poster img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  transition: transform 0.3s ease;
}

.details {
  padding: 8px;
  text-align: center;
  font-size: 13px;
}

.favorite-button {
  padding: 8px 12px;
  border: none;
  border-radius: 20px;
  background-color: #ff9800;
  color: #fff;
  font-weight: bold;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.favorite-button:hover {
  background-color: #e68900;
}
</style>