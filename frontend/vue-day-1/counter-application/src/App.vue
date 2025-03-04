<script>
export default {
  data() {
    return {
      counter: 0,
      ifEven: true
    }
  },
  methods: {
    increment() {
      this.counter = this.counter + 1;
      if (this.counter % 2 === 0) this.ifEven = true;
      else this.ifEven = false;
    },
    decrement() {
      this.counter = this.counter - 1;
      if (this.counter % 2 === 0) this.ifEven = true;
      else this.ifEven = false;
    },
    checkPrime() {
      if (this.counter <= 1) return false;
      for (let i = 2; i < this.counter; i++) {
        if (this.counter % i === 0)
          return false;
      }
      return true;
    }
  }
}
</script>

<template>
  <div>
    <h1>Counter Application</h1>
    <div class="btns">
      <button @click="decrement">Decrement</button>
      <h2 :style="{ color: counter % 2 === 0 ? 'green' : 'red'}">{{ counter }}</h2>
      <button @click="increment">Increment</button>
    </div>

    <div class="output">
      <div v-if="checkPrime()">PRIME number</div>
      <div v-else-if="ifEven">EVEN number</div>
      <div v-else="!ifEven">ODD number</div>
    </div>
  </div>
</template>

<style scoped>
header {
  line-height: 1.5;
}

.logo {
  display: block;
  margin: 0 auto 2rem;
}

@media (min-width: 1024px) {
  header {
    display: flex;
    place-items: center;
    padding-right: calc(var(--section-gap) / 2);
  }

  .logo {
    margin: 0 2rem 0 0;
  }

  header .wrapper {
    display: flex;
    place-items: flex-start;
    flex-wrap: wrap;
  }
}

.btns {
  margin: 10px;
  display: flex;
  justify-content: space-between;
}

.output {
  margin: 20px;
  display: flex;
  justify-content: center;
}
</style>
