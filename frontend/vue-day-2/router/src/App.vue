<script>
import Card from "./components/Card.vue";

export default {
  components: { Card }
};
</script>

<template>
  <h1>Hello App!</h1>
  <p>
    <strong>Current route path:</strong> {{ $route.fullPath }}
  </p>
  <nav>
    <RouterLink to="/">Go to Home</RouterLink>
    <br />
    <RouterLink to="/about">Go to About</RouterLink>
  </nav>
  <main>
    <RouterView />
  </main>

  <div>
    <Card>
      <template #header>
        <h1>Hello</h1>
      </template>
      <h3>This is the normal slot: (Slot 1)</h3>
      <template #footer>
        <h6>2025.</h6>
      </template>
    </Card>

    <Card>
      <h2>Slot 2</h2>
    </Card>
  </div>
</template>

<style scoped>
</style>
