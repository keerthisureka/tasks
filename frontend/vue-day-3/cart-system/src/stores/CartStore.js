import { defineStore } from 'pinia'

export const useCartStore = defineStore('cart', {
  state: () => ({
    productList: [
      {
        id: 1,
        name: "Tea",
        price: 20,
        image: "https://picsum.photos/200/300?random=1"
      },
      {
        id: 2,
        name: "Cold drink",
        price: 50,
        image: "https://picsum.photos/200/300?random=2"
      },
      {
        id: 3,
        name: "Coffee",
        price: 18,
        image: "https://picsum.photos/200/300?random=3"
      },
      {
        id: 4,
        name: "Icecream",
        price: 40,
        image: "https://picsum.photos/200/300?random=4"
      },
      {
        id: 5,
        name: "Shake",
        price: 80,
        image: "https://picsum.photos/200/300?random=5"
      },
      {
        id: 6,
        name: "Potato",
        price: 88,
        image: "https://picsum.photos/200/300?random=6"
      },
      {
        id: 7,
        name: "Pot",
        price: 100,
        image: "https://picsum.photos/200/300?random=7"
      },
      {
        id: 8,
        name: "Mat",
        price: 200,
        image: "https://picsum.photos/200/300?random=28"
      },
      {
        id: 9,
        name: "Hair Dryer",
        price: 550,
        image: "https://picsum.photos/200/300?random=9"
      },
    ],
    cartItems: []
  }),

  actions: {
    addItem(item) {
      const foundItem = this.cartItems.find(cartItem => cartItem.id === item.id);
      if (foundItem) {
        foundItem.quantity++;
      } else {
        this.cartItems.push({ ...item, quantity: 1 });
      }
    },

    removeItem(id) {
      this.cartItems = this.cartItems.filter(item => item.id !== id);
    },

    increaseQuantity(id) {
      const item = this.cartItems.find(item => item.id === id);
      if (item) {
        item.quantity++;
      }
    },

    decreaseQuantity(id) {
      const item = this.cartItems.find(item => item.id === id);
      if (item && item.quantity > 1) {
        item.quantity--;
      }
    }
  },

  getters: {
    totalPrice() {
      return this.cartItems.reduce((total, item) => total + item.price * item.quantity, 0);
    },

    totalItems() {
      return this.cartItems.reduce((total, item) => total + item.quantity, 0);
    }
  }
})
