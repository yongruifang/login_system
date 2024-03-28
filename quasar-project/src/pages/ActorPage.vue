<template>
  <q-page padding>
    <!-- content -->
    Welcome to ActorPage
  <div class="q-pa-md q-gutter-sm">
    <q-btn round color="primary" icon="cached" @click="toggleFetch"/>
  </div>
  <!-- the 10th actor -->
  <div>
    {{ actorStore.getTenthActor }}
  </div>
  <div v-for="actor in actorStore.getTenActors" :key="actor.id">
   {{ actor }}
  </div>
  </q-page>

</template>

<script setup>
import { fetchActorApi } from 'src/api/actor';
import { useActorStore } from 'src/stores/actor-store'
const actorStore = useActorStore()
const toggleFetch = async () => {
  console.log('@TODO: to fetch actor list')
  try{
  const response = await fetchActorApi();
  console.log(response)
  const { data: actors} = response;
  console.log(actors, typeof(actors))
  console.log('@TODO: object to array')
  let isArr = actors instanceof Array 
  console.log('isArr: ', isArr)
  console.log('now actorStore is: ', actorStore.getActors)
  actorStore.setActors(actors)
  
  }catch(err) {
    console.log('need redirect', err)
  }
}
</script>
