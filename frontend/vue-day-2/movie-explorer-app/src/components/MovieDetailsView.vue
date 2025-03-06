<template>
  <div class="movie-details" v-if="movie">
    <div class="movie-header">
      <img :src="movie.Poster" alt="Movie Poster" class="movie-poster" />
      <div class="movie-info">
        <h1>{{ movie.Title }} ({{ movie.Year }})</h1>
        <p><strong>Genre:</strong> {{ movie.Genre }}</p>
        <p><strong>Director:</strong> {{ movie.Director }}</p>
        <p><strong>Cast:</strong> {{ movie.Actors }}</p>
        <p><strong>Language:</strong> {{ movie.Language }}</p>
        <p><strong>Country:</strong> {{ movie.Country }}</p>
        <p><strong>Awards:</strong> {{ movie.Awards }}</p>
        <p><strong>Runtime:</strong> {{ movie.Runtime }}</p>
        <p><strong>Released:</strong> {{ movie.Released }}</p>
      </div>
    </div>
    <div class="movie-plot">
      <h2>Plot Summary</h2>
      <p>{{ movie.Plot }}</p>
    </div>
    <div class="movie-ratings">
      <h2>Ratings</h2>
      <ul>
        <li v-for="rating in movie.Ratings" :key="rating.Source">
          <strong>{{ rating.Source }}:</strong> {{ rating.Value }}
        </li>
      </ul>
    </div>
  </div>
  <div v-else class="loading">
    <p>Loading...</p>
  </div>
</template>

<script>
export default {
  data() {
    return {
      movie: null,
    };
  },
  async created() {
    const id = this.$route.params.id;
    try {
      const response = await fetch(`https://www.omdbapi.com/?apikey=2205c78f&i=${id}`);
      if (!response.ok) {
        throw new Error(`HTTP error! Status: ${response.status}`);
      }
      const data = await response.json();
      if (data.Response === 'True') {
        this.movie = data;
      } else {
        console.error(data.Error);
      }
    } catch (error) {
      console.error('Fetch error:', error);
    }
  },
};
</script>

<style scoped>
.movie-details {
  max-width: 1000px;
  margin: 20px auto;
  padding: 20px;
  background-color: #f8f9fa;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.movie-header {
  display: flex;
  align-items: flex-start;
}

.movie-poster {
  width: 250px;
  border-radius: 10px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
}

.movie-info {
  margin-left: 40px;
}

.movie-info h1 {
  margin-bottom: 20px;
  color: #343a40;
}

.movie-info p {
  margin: 10px 0;
  color: #495057;
}

.movie-plot, .movie-ratings {
  margin-top: 20px;
}

.movie-plot h2, .movie-ratings h2 {
  color: #343a40;
  border-bottom: 2px solid #ff9800;
  display: inline-block;
  padding-bottom: 5px;
}

.movie-plot p, .movie-ratings ul {
  color: #495057;
}

.movie-ratings ul {
  list-style-type: none;
  padding: 0;
}

.movie-ratings li {
  margin: 5px 0;
}

.loading {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
