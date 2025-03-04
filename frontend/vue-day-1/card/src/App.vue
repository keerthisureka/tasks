<script>
import ProductCard from './components/ProductCard.vue'

export default {
  components: { ProductCard },
  data() {
    return {
      productList: [
        {
          name: "Tea",
          price: 20,
          image: "https://picsum.photos/200/300?random=1"
        },
        {
          name: "Cold drink",
          price: 50,
          image: "https://picsum.photos/200/300?random=2"
        },
        {
          name: "Coffee",
          price: 18,
          image: "https://picsum.photos/200/300?random=3"
        },
        {
          name: "Icecream",
          price: 40,
          image: "https://picsum.photos/200/300?random=4"
        },
        {
          name: "Shake",
          price: 80,
          image: "https://picsum.photos/200/300?random=5"
        }
      ]
    }
  }
}
</script>

<template>
  <div class="content">
    <ProductCard v-for="product in productList" :name="product.name" :price="product.price" :image="product.image" />
  </div>
</template>

<style>
#app {
  max-width: 100%;
  margin: 0% 40%;
}

.content {
  display: flex;
  flex-direction: column;
  align-items: center;
}
</style>