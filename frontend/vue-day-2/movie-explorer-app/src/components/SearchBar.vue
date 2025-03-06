<template>
  <div class="search-container">
    <input
      type="text"
      v-model="query"
      placeholder="Search for movies..."
      class="search-input"
    />
    <button class="search-button" @click="emitSearch">
      <svg
        xmlns="http://www.w3.org/2000/svg"
        width="24"
        height="24"
        viewBox="0 0 24 24"
        fill="none"
        stroke="currentColor"
        stroke-width="2"
        stroke-linecap="round"
        stroke-linejoin="round"
        class="feather feather-search"
      >
        <circle cx="11" cy="11" r="8"></circle>
        <line x1="21" y1="21" x2="16.65" y2="16.65"></line>
      </svg>
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return { query: '' };
  },
  methods: {
    emitSearch() {
      this.$emit('search', this.query);
    },
  },
  watch: {
    query(newVal) {
      this.$emit('search', newVal);
    },
  },
};
</script>

<style scoped>
.search-container {
  display: flex;
  justify-content: center;
  align-items: center;
  margin: 0px 0px 20px 0px;
  width: 50%;
}

.search-input {
  width: 100%;
  padding: 12px 20px;
  font-size: 16px;
  border: 2px solid #ddd;
  border-radius: 25px 0 0 25px;
  outline: none;
  transition: border-color 0.3s ease-in-out;
}

.search-input:focus {
  border-color: #ff9800;
}

.search-button {
  background-color: #ff9800;
  border: none;
  padding: 12px;
  border-radius: 0 25px 25px 0;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background-color 0.3s ease-in-out;
}

.search-button:hover {
  background-color: #e68900;
}

.search-button svg {
  color: #fff;
}
</style>
