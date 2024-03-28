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
      :filter="filter"
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
          <div class="col-6">
          <q-input borderless dense debounce="300" v-model="filter" placeholder="Search">
            <template v-slot:append>
              <q-icon name="search" />
            </template>
          </q-input>
          </div>
       
        </div>
    </template>
    
    <!-- troubleShoot: @save @delete not emit -->
      <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="id" :props="props">
              {{ props.row.id }}
            </q-td>
            <q-td key="first_name" :props="props">
              {{ props.row.first_name }}
              <!-- <q-popup-edit v-model="props.row.frist_name" title="Update firstName" buttons @input="(val, initialValue) => updateFirstName(val, initialValue, props.row)" @cancel="console.log('@@@cancel')">
                <q-input v-model="props.row.first_name" dense autofocus counter ></q-input>
              </q-popup-edit> -->
            </q-td>
            <q-td key="last_name" :props="props">
              {{ props.row.last_name }}
              <!-- <q-popup-edit v-model="props.row.last_name" title="Update lastName" buttons @input="(val, initialValue) => updateLastName(val, initialValue, props.row)" @cancel="console.log('@@cancel')">
                <q-input v-model="props.row.last_name" dense autofocus ></q-input>
              </q-popup-edit> -->
            </q-td>
            <q-td key="actions" :props="props">
              <q-btn color="blue" dense flat round icon="edit" @click="toggleEdit" size=sm></q-btn>
              <q-btn color="red" dense flat round icon="delete"  @click="toggleDelete" size=sm></q-btn>
            </q-td>
          </q-tr>
        </template>
   </q-table>
   <!-- <div class="q-pa-md">
    <div class="cursor-pointer">
      {{ label }}
      <q-popup-edit v-model="label" v-slot="scope" buttons @save="onSave">
        <q-input v-model="scope.value" dense autofocus counter @keyup.enter="scope.set" />
      </q-popup-edit>
    </div>
  </div> -->
  </q-page>

</template>

<script setup>
import { addActorApi } from 'src/api/actor';
import { fetchActorApi , editActorApi, deleteActorApi } from 'src/api/actor';
import { useActorStore } from 'src/stores/actor-store'
import { onMounted , ref } from 'vue';

const show_dialog = ref(false)
const label = ref("click me")
const filter = ref("")
const onSave = (param) => {
  console.log('cheer!!! toggleonSave')
  console.log(param)
}
const newActor = ref({
  first_name: '', 
  last_name: ''
})
const updateFirstName = (val, initialValue, row) => {
  console.log("cheer!!!Toggle it")
}
const updateLastName = (val, initialValue, row) => {
  console.log("cheer!!!Toggle it")
}
const toggleAdd = async () =>{
  console.log('@TODO: toggleAdd')
  const actor = {
    first_name: newActor.value.first_name,
    last_name: newActor.value.last_name
  }
  console.log(actor)
  const response = await addActorApi(actor)
  console.log(response)
}
const toggleEdit = () => {
  console.log('@TODO: toggleEdit, 先做弹窗')
}
const toggleEditSave = async (actor = {
  id: 0,
  first_name: "",
  last_name: ""
}) => {
  console.log('@TODO: 发送API')
  const response = await editActorApi(actor)
  console.log(response)
}
const toggleDelete = async () => {
  console.log('@TODO: toggleDelete, 一个弹窗提示')
  const response = await deleteActorApi(0) 
  console.log(response)
}

const columns = [
  {
    name: 'id', label: 'id', field: 'id', sortable: true
  },
  {
    name: 'first_name', label: '名', field: 'first_name', sortable: true
  },
  {
    name: 'last_name', label: '姓', field: 'last_name', sortable: true
  },
  {
    name: 'actions', label: '操作', field: 'actions', sortable: false
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
