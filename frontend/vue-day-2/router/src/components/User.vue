<script>
import Card from "./Card.vue";

export default {
  components: { Card }
};
</script>

<template>
    <Card>
        <div class="heading">
            <h2>{{ $route.params.id }}</h2>
            <RouterView />
        </div>
    </Card>
</template>

<style>
/* .heading {
    width: 300px;
    height: 300px;
    color: black;
    background-color: yellow;
} */
</style>