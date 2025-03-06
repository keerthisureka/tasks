<template>
  <div class="cart">
    <h1 class="cart-title">Shopping Cart</h1>
    <div v-if="cartItems.length > 0" class="cart-items">
      <div v-for="item in cartItems" :key="item.id" class="cart-item">
        <img :src="item.image" alt="item" class="cart-item-image" />
        <div class="item-details">
          <p class="item-name">{{ item.name }}</p>
          <p class="item-price">Price: ${{ item.price.toFixed(2) }}</p>
          <div class="quantity-controls">
            <button @click="decrease(item.id)" class="quantity-btn">-</button>
            <span class="quantity">{{ item.quantity }}</span>
            <button @click="increase(item.id)" class="quantity-btn">+</button>
          </div>
          <button @click="remove(item.id)" class="remove-btn">Remove</button>
        </div>
      </div>
      <div class="cart-summary">
        <p>Total Items: {{ totalItems }}</p>
        <p>Total Price: ${{ totalPrice.toFixed(2) }}</p>
      </div>
    </div>
    <div v-else>
      <p class="empty-cart-message">Your cart is empty.</p>
    </div>
  </div>
</template>

<script>
import { useCartStore } from '@/stores/CartStore'
import { mapActions, mapState } from 'pinia'

export default {
  computed: {
    ...mapState(useCartStore, ["cartItems", "totalPrice", "totalItems"])
  },
  methods: {
    ...mapActions(useCartStore, ["removeItem", "increaseQuantity", "decreaseQuantity"]),
    remove(id) {
      this.removeItem(id);
    },
    increase(id) {
      this.increaseQuantity(id);
    },
    decrease(id) {
      this.decreaseQuantity(id);
    }
  }
}
</script>

<style scoped>
.cart {
  font-family: 'Arial', sans-serif;
  background-color: #e8ddbf;
  padding: 20px;
  border-radius: 8px;
  max-width: 800px;
  margin: 0 auto;
}

.cart-title {
  text-align: center;
  font-size: 2rem;
  margin-bottom: 20px;
}

.cart-items {
  display: flex;
  flex-direction: column;
  gap: 20px;
}

.cart-item {
  display: flex;
  gap: 20px;
  align-items: center;
  background-color: #f9f9f9;
  padding: 15px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.cart-item-image {
  width: 100px;
  height: 100px;
  border-radius: 8px;
  object-fit: cover;
}

.item-details {
  flex: 1;
}

.item-name {
  font-size: 1.2rem;
  color: #333;
  margin-bottom: 5px;
}

.item-price {
  font-size: 1rem;
  color: #555;
  margin-bottom: 10px;
}

.quantity-controls {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 10px;
}

.quantity-btn {
  background-color: rgb(255, 179, 0);
  color: black;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.quantity-btn:hover {
  background-color: rgb(230, 194, 110);
}

.quantity {
  font-size: 1rem;
  color: #333;
}

.remove-btn {
  background-color: #dc3545;
  color: #fff;
  border: none;
  padding: 5px 10px;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.remove-btn:hover {
  background-color: #c82333;
}

.cart-summary {
  margin-top: 20px;
  font-weight: bold;
  color: #333;
}

.empty-cart-message {
  text-align: center;
  color: #777;
  font-size: 1.2rem;
}
</style>
