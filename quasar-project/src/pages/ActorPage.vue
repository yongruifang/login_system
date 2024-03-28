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
      flat bordered
      title="Actors"
      :rows="actorStore.getActors"
      :columns="columns"
      row-key="id"
      virtual-scroll
   >
    <template v-slot:top>
          <q-btn flat outline dense color="primary" label="Add row" @click="show_dialog = true" ></q-btn>
        <div class="q-pa-sm q-gutter-sm">
          <q-dialog v-model="show_dialog">
<q-card>
            <q-card-section>
              <div class="text-h6">添加一个新的演员</div>
            </q-card-section>
            <q-card-section>
             <div class="col">
                <q-input v-model="newActor.first_name" label="First Name"></q-input>
                <q-input v-model="newActor.last_name" label="Last Name"></q-input>
             </div> 
            </q-card-section>
            <q-card-section align="right">
                <q-btn flat label="OK" color="primary" v-close-popup @click="toggleAdd" ></q-btn>
            </q-card-section>
</q-card>
          </q-dialog>
        </div>
    </template>
   </q-table>


  </q-page>

</template>

<script setup>
import { fetchActorApi } from 'src/api/actor';
import { useActorStore } from 'src/stores/actor-store'
import { onMounted , ref } from 'vue';

const show_dialog = ref(false)
const newActor = ref({
  first_name: '', 
  last_name: ''
})
const toggleAdd = () =>{
  console.log('@TODO: toggleAdd')
}

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
  {
    name: 'actions', label: 'Actions', field: 'actions'
  }
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
