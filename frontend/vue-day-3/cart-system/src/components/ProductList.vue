<template>
    <div class="products">
        <h1 class="product-title">Products</h1>
        <div class="product-list">
            <div v-for="product in productList" :key="product.id" class="product-card">
                <h3>{{ product.name }}</h3>
                <img :src="product.image" alt="product" class="product-image" />
                <p>Price: ${{ product.price.toFixed(2) }}</p>
                <button @click="add(product)" class="addToCartBtn">Add to Cart</button>
            </div>
        </div>
    </div>
</template>

<script>
import { useCartStore } from '@/stores/CartStore';
import { mapActions, mapState } from 'pinia';

export default {
    computed: {
        ...mapState(useCartStore, ["productList"])
    },
    methods: {
        ...mapActions(useCartStore, ["addItem"]),
        add(item) {
            this.addItem(item);
        }
    }
}
</script>

<style scoped>
.products {
  font-family: 'Arial', sans-serif;
  background-color: #e8ddbf;
  padding: 20px;
  border-radius: 8px;
  max-width: 100%;
  margin: 0 auto;
}

.product-title {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
}

.product-list {
  padding: 20px;
  border-radius: 8px;
  display: flex;
  flex-wrap: wrap;
  gap: 20px;
}

.product-card {
  background-color: #f9f9f9;
  padding: 10px 20px;
  border-radius: 10px;
  width: 200px;
  text-align: center;
  transition: transform 0.3s ease-in-out;
}

.product-card:hover {
  transform: scale(1.05);
}

.product-image {
  width: 100%;
  height: 250px;
  border-radius: 20px;
}

.addToCartBtn {
    padding: 5px 10px;
    background-color: rgb(255, 179, 0);
    border-radius: 20px;
}

.addToCartBtn:hover {
    background-color: rgb(230, 194, 110);
}
</style>