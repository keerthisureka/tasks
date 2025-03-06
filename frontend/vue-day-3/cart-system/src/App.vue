<script>
import Cart from './components/Cart.vue';
import ProductList from './components/ProductList.vue';

export default {
  components: {
    ProductList,
    Cart
  }
}
</script>

<template>
  <h1 class="name">TheProductStore</h1>
  <div>
    <ProductList />
  </div>
  <div>
    <Cart />
  </div>
</template>

<style scoped>
.name {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 10px;
  border-bottom: 3px solid rgb(255, 179, 0);
  border-top: 3px solid rgb(255, 179, 0);
}

div {
  padding: 20px;
  margin: 10px;
}
</style>
