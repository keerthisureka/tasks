import { createWebHistory, createRouter } from 'vue-router'

import HomeView from './HomeView.vue'
import AboutView from './AboutView.vue'
import User from './User.vue'
import Profile from './Profile.vue'
import Post from './Post.vue'

const routes = [
  { path: '/', component: HomeView },
  { path: '/about', component: AboutView },
  { path: '/dynamic/:id', component: AboutView},
  { 
    path: '/user/:id',
    component: User,
    children: [
      {
        path: "profile",
        component: Profile
      },
      {
        path: "posts",
        component: Post
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router;