<template>
<div class="card">
    <slot name="header"></slot>
    <slot></slot>
    <slot name="footer"></slot>
</div>
</template>

<style>
.card {
  max-width: 300px;
  max-height: 300px;
  border: 2px solid white;
  margin: 40px;
  padding: 20px;
  border-radius: 10px;
  justify-content: center;
  align-items: center;
}
</style>