import { createRouter, createWebHistory } from 'vue-router';
import HomeView from '@/components/HomeView.vue';
import MovieDetailsView from '@/components/MovieDetailsView.vue';
import FavoritesView from '@/components/FavoritesView.vue';

const routes = [
  { path: '/', component: HomeView },
  { path: '/movie/:id', component: MovieDetailsView },
  { path: '/favorites', component: FavoritesView }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;
