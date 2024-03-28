<template>
  <q-page padding>
    <!-- content -->
    Welcome to ActorPage
  <div class="q-pa-md q-gutter-sm">
    <q-btn round color="primary" icon="cached" @click="toggleFetch"/>
  </div>
  </q-page>

</template>

<script setup>
import { fetchActorApi } from 'src/api/actor';
const toggleFetch = async () => {
  console.log('@TODO: to fetch actor list')
  try{
  const response = await fetchActorApi();
  console.log(response)
  }catch(err) {
    console.log('need redirect', err)
  }
}
</script>
