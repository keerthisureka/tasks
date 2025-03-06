<script>
export default {
    methods: {
        navigate() {
            this.$router.push("/about");
            // console.log(this.$router);
            // this.$router.push({ path: '/dynamic', params: { id: "5" } });
        }
    }
}
</script>

<template>
    <h1>Home</h1>
    <button @click="navigate">Go to About</button>
</template>