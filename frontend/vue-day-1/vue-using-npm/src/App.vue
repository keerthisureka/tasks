<script>
export default {
  data() {
    return {
      message: "hi there",
      overrideCounter: 0,
      show: true,
      students: ["A", "B"]
    }
  },
  methods: {
    overrideIsCalled() {
      this.message = "overridden";
      this.overrideCounter = this.overrideCounter + 1;
    },
    update() {
      this.message = event.target.value;
    }
  }
}
</script>

<template>
  <div>
    <!-- Using v-bind and @input -->
    <!-- <input v-bind:value="message" @input="event => message = event.target.value" > -->
    <!-- <input v-bind:value="message" @input="update()" > -->

    <!-- Using v-model - It combines both v-bind and @input -->
    <input v-model="message" >
    <h1>{{ message }}</h1>
    <button @click="overrideIsCalled">Override</button>
    <div>Overridden by {{ overrideCounter }} times</div>

    <br />

    <button @click="show = !show">Toggle</button>
    <!-- Using v-show -->
    <!-- <h1 v-show="show">Vue is awesome!</h1> -->
    
    <!-- Using if else -->
    <h2 v-if="show">Using v-if</h2>
    <h2 v-else="show">Using v-else</h2>

    <br />

    <!-- Using v-for -->
     <h1>Using v-for below:</h1>
    <div v-for="student in students">{{ student }}</div>
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
</style>
