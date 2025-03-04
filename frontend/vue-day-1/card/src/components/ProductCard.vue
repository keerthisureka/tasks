<script>
export default {
    props: {
        name: {
            type: String,
            required: true
        },
        price: {
            type: Number,
            required: true
        },
        image: {
            type: String,
            required: true
        }
    },
    data() {
        return {
            add: true,
            quantity: 1
        }
    },
    methods: {
        toggle() {
            this.add = !this.add;
        }
    },
    watch: {
        quantity(newValue, oldValue) {
            console.log(`Quantity changed from ${oldValue} to ${newValue}`);
        }
    }
}
</script>

<template>
    <div class="card">
        <h1>{{ name }}</h1>
        <img :src="image" class="img" />
        <div>Price: $ {{ price.toFixed(2) }}</div>
        <div>Dicounted price: $ {{ (price - (price * 0.1)).toFixed(2) }}</div>

        <!-- Quantity Selector -->
        <div>
            <label>Quantity: </label>
            <input type="number" v-model="quantity" min="1" class="qty" />
        </div>

        <div class="btn">
            <button v-if="add" @click="toggle">Add to cart</button>
            <button v-else @click="toggle">Remove from cart</button>
        </div>
    </div>
</template>

<style scoped>
.card {
    color: white;
    padding: 20px;
    margin: 30px;
    border: 3px solid white;
    border-radius: 10px;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.img {
    height: 200px;
    width: 200px;
}

.qty {
    width: 100%;
    padding: 3px;
    box-sizing: border-box;
}

.btn {
    margin: 10px;
    border-radius: 20%;
}
</style>