<script>
export default {
  data() {
    return {
      content: null,
      price: 0
    }
  },
  async created() {
    try {
      const response = await fetch("https://jsonplaceholder.typicode.com/posts");
      console.log(response);
      if (!response.ok) {
        throw new Error(response.status);
      }
      this.content = await response.json();
    } catch (error) {
      console.log(error.message);
    }
  },
  computed: {
    convertToDollars() {
      return (this.price * 80);
    }
  }
}
</script>

<template>
  <div>
    <!-- Fetch Call -->

    <!-- <h1>Data from an API fetch call:</h1>
    <div v-for="c in content">
      <p>UserId: {{ c.userId }}</p>
      <p>Id: {{ c.id }}</p>
      <p>Title: {{ c.title }}</p>
      <p>Body: {{ c.body }}</p>
      <br />
    </div> -->


    <!-- Currency Converter -->

    <input type="number" v-model="price"/>
    <h2>The price in dollars is: $ {{ convertToDollars }}</h2>
  </div>
</template>

<style scoped>
#app {
  max-width: 100%;
}
</style>