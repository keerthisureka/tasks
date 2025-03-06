<template>
  <div class="todo">
      <h1>ToDo App</h1>
      <div v-for="item in items" class="list">
          <div>
            {{ item.name }} &emsp;
            <button @click="remove(item.id)">Remove</button>
          </div>
      </div>
      <button @click="add" class="addBtn">Add Item</button>
  </div>
</template>

<script>
import { mapActions, mapState } from 'pinia'
import { useCartStore } from './stores/cartStore'

export default {
  computed: {
      ...mapState(useCartStore, ["items"])
  },
  methods: {
      ...mapActions(useCartStore, ["addItem", "removeItem"]),
      add() {
        this.addItem({ 'id': Date.now(), 'name': 'New Item'});
      },
      remove(id) {
        this.removeItem(id);
      }
  }
}
</script>

<style>
#app {
  margin: 0px;
  width: 100%;
  display: flex;
  flex-direction: column;
}

.todo {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.list {
  margin: 10px;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.addBtn {
  margin: 20px 0px;
}
</style>