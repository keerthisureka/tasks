<template>
    <div class="movie-list">
        <MovieCard v-for="movie in movies" :key="movie.imdbID" :movie="movie" @click="$router.push(`/movie/${movie.imdbID}`)">
            <template #poster>
                <img :src="movie.Poster" alt="Movie Poster" />
            </template>
            <template #title>
                <h3>
                    {{ movie.Title }}
                </h3>
            </template>
        </MovieCard>
    </div>
</template>

<script>
import MovieCard from './MovieCard.vue';

export default {
    props: ['movies'],
    components: { MovieCard }
};
</script>

<style>
.movie-list {
    display: flex;
    justify-content: center;
    gap: 20px;
    flex-wrap: wrap;
    margin: 0% 10%;
}
</style>
