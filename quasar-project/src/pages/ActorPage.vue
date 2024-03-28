<template>
  <q-page padding>
    <!-- content -->
    Welcome to ActorPage
  <div class="q-pa-md q-gutter-sm">
    <q-btn round color="primary" icon="cached" @click="toggleFetch"/>
  </div>
  <!-- the 10th actor -->
  <!-- <div>
    {{ actorStore.getTenthActor }}
  </div>
  {{ typeof(actorStore.getActors)  }}
  {{actorStore.getActors.slice(0,9)  }}
  <div v-for="actor in actorStore.getTenActors" :key="actor.id">
   {{ actor }}
  </div> -->
 <!-- table -->
   <q-table 
      style="height: 400px"
      flat bordered
      title="Actors"
      :rows="actorStore.getActors"
      :columns="columns"
      row-key="id"
      virtual-scroll
   >
   </q-table>


  </q-page>

</template>

<script setup>
import { fetchActorApi } from 'src/api/actor';
import { useActorStore } from 'src/stores/actor-store'
import { onMounted } from 'vue';

const columns = [
  {
    name: '序号', label: 'id', field: 'id', sortable: true
  },
  {
    name: '名', label: 'firstname', field: 'first_name', sortable: true
  },
  {
    name: '姓', label: 'lastname', field: 'last_name', sortable: true
  },
]
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
onMounted(()=>{
  if(actorStore.getActors.length == 0) {
    toggleFetch()
  }
})
</script>
