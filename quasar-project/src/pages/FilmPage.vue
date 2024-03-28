<template>
  <q-page padding>
    <!-- content -->
    Welcome to Film Page
   <div class="q-pa-md q-gutter-sm">
    <q-btn round color="primary" icon="cached" @click="toggleFetch"/>
  </div>
  </q-page>
</template>

<script setup>
const toggleFetch = () => {
  console.log("@TODO: to fetch film list")
}
</script>
