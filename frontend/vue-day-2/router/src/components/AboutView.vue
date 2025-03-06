<script>
export default {
    data() {
        console.log(this);
        return {
            text: "Hi! This is about.",
            num: 0
        }
    },
    watch: {
        num(val) {
            if(val == 5) {
                console.log(this.text);
                this.text = "Hi there! This is about. The id is 5.";
            }
        }
    },
    beforeCreate() {
        console.log(this.$route.params.id);
    },
    mounted() {
        this.num = this.$route.params.id;
    }
}
</script>

<template>
    <h1>
        {{ text }}
        {{ $route.params.id }}
    </h1>
</template>
