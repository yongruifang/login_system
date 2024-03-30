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
                <q-input v-model="newActor.firstName" label="First Name"></q-input>
                <q-input v-model="newActor.lastName" label="Last Name"></q-input>
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
              <!-- {{ props.row }} -->
              <!-- {{ props.row.firstName }} -->
            </q-td>
            <q-td key="firstName" :props="props">
              {{ props.row.firstName }}
              <!-- <q-popup-edit v-model="props.row.frist_name" title="Update firstName" buttons @input="(val, initialValue) => updateFirstName(val, initialValue, props.row)" @cancel="console.log('@@@cancel')">
                <q-input v-model="props.row.firstName" dense autofocus counter ></q-input>
              </q-popup-edit> -->
            </q-td>
            <q-td key="lastName" :props="props">
              {{ props.row.lastName }}
              <!-- <q-popup-edit v-model="props.row.lastName" title="Update lastName" buttons @input="(val, initialValue) => updateLastName(val, initialValue, props.row)" @cancel="console.log('@@cancel')">
                <q-input v-model="props.row.lastName" dense autofocus ></q-input>
              </q-popup-edit> -->
            </q-td>
            <q-td key="lastUpdate" :props="props">
              <!-- {{ date.formatDate(props.row.lastUpdate, 'YYYY-MM-DDTHH:mm:ss')  }} -->
              {{ date.formatDate(props.row.lastUpdate, 'YYYY-MM-DD HH:mm')  }}
            </q-td>
            <q-td key="actions" :props="props">
              <q-btn color="blue" dense flat round icon="edit" @click="toggleEdit(props.row)" size=sm></q-btn>
              <q-btn color="red" dense flat round icon="delete"  @click="toggleDelete(props.row)" size=sm></q-btn>
            </q-td>
          </q-tr>
        </template>
   </q-table>
      <q-card>
            <q-card-section>
              <div class="text-h6">时间区间过滤器</div>
            </q-card-section>
            <q-card-section>
              <div class="row">
               <div class="q-pa-md">
                  <div class="q-mb-sm">
                    <q-badge color="teal">
                      起: {{ start_date }}
                    </q-badge>
                  </div>
                  <q-btn icon="event" round color="primary">
                    <q-popup-proxy @before-show="updateStartProxy" cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="proxyStartDate">
                        <div class="row items-center justify-end q-gutter-sm">
                          <q-btn label="Cancel" color="primary" flat v-close-popup />
                          <q-btn label="OK" color="primary" flat @click="saveStart" v-close-popup />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-btn>
              </div>
               <div class="q-pa-md">
                  <div class="q-mb-sm">
                    <q-badge color="teal">
                      止: {{ end_date }}
                    </q-badge>
                  </div>
                  <q-btn icon="event" round color="primary">
                    <q-popup-proxy @before-show="updateEndProxy" cover transition-show="scale" transition-hide="scale">
                      <q-date v-model="proxyEndDate">
                        <div class="row items-center justify-end q-gutter-sm">
                          <q-btn label="Cancel" color="primary" flat v-close-popup />
                          <q-btn label="OK" color="primary" flat @click="saveEnd" v-close-popup />
                        </div>
                      </q-date>
                    </q-popup-proxy>
                  </q-btn>
              </div>
              </div>
            <q-card-section align="left">
                <q-btn flat label="OK" color="primary" v-close-popup @click="toggleTimeFilter" ></q-btn>
            </q-card-section>
            </q-card-section>
      </q-card>
   <q-table
      flat bordered
      title="Actors"
      :rows="actors"
      :columns="columns.slice(0, -1)"
      :filter="filter"
      row-key="id"
      virtual-scroll
   >
      <template v-slot:body="props">
          <q-tr :props="props">
            <q-td key="id" :props="props">
              {{ props.row.id }}
            </q-td>
            <q-td key="firstName" :props="props">
              {{ props.row.firstName }}
            </q-td>
            <q-td key="lastName" :props="props">
              {{ props.row.lastName }}
            </q-td>
            <q-td key="lastName" :props="props">
              {{ date.formatDate(props.row.lastUpdate, 'YYYY-MM-DD HH:mm')  }}
            </q-td>
            <!-- <q-td key="actions" :props="props">
              <q-btn color="blue" dense flat round icon="edit" @click="toggleEdit(props.row)" size=sm></q-btn>
              <q-btn color="red" dense flat round icon="delete"  @click="toggleDelete(props.row)" size=sm></q-btn>
            </q-td> -->
          </q-tr>
        </template>
   </q-table>
    <div class="q-pa-sm q-gutter-sm">
          <q-dialog v-model="show_edit_dialog">
          <q-card>
            <q-card-section>
              <div class="text-h6">修改演员</div>
            </q-card-section>
            <q-card-section>
             <div class="col">
                <q-input v-model="editActor.firstName" label="First Name"></q-input>
                <q-input v-model="editActor.lastName" label="Last Name"></q-input>
             </div>
            </q-card-section>
            <q-card-section align="right">
                <q-btn flat label="OK" color="primary" v-close-popup @click="toggleEditSave" ></q-btn>
            </q-card-section>
          </q-card>
          </q-dialog>
        </div>
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
import { fetchActorApi , editActorApi, deleteActorApi, filterActorByTimeApi } from 'src/api/actor';
import { useActorStore } from 'src/stores/actor-store'
import { onMounted , ref } from 'vue';
import { useQuasar , date } from 'quasar';

const $q = useQuasar()

const start_date = ref('2019/03/01')
const end_date = ref('2019/03/01')
const proxyStartDate = ref('2019/03/01')
const proxyEndDate = ref('2019/03/01')
const show_dialog = ref(false)
const show_edit_dialog = ref(false)
const label = ref("click me")
const filter = ref("")
const actors = ref()
const toggleTimeFilter = async () => {
  console.log('@TODO: 时间区间选择器 ')
  const from = start_date.value
  const to = end_date.value
  console.log(`from: ${from} , to : ${to}`)
  const response = await filterActorByTimeApi(from, to)
  console.log(response)
  actors.value = response.data.actorList
  console.log(actors.value)
}
const updateStartProxy = () => {
  proxyStartDate.value = start_date.value
}
const updateEndProxy = () => {
  proxyEndDate.value = end_date.value
}
const saveStart = () =>{
  start_date.value = proxyStartDate.value
}
const saveEnd = () =>{
  end_date.value = proxyEndDate.value
}
const onSave = (param) => {
  console.log('cheer!!! toggleonSave')
  console.log(param)
}
const newActor = ref({
  firstName: '',
  lastName: ''
})
const editActor = ref({
  id: 0,
  firstName: "",
  lastName : ""
})

const toggleAdd = async () =>{
  console.log('@TODO: toggleAdd')
  const actor = {
    firstName: newActor.value.firstName,
    lastName: newActor.value.lastName
  }
  const response = await addActorApi(actor)
  $q.notify({
    message: "add actor successfully!",
    color: 'primary'
  })
  await toggleFetch()
  console.log(response)
}
const toggleEdit = (row) => {
  show_edit_dialog.value = true
  editActor.value.id = row.id
  editActor.value.firstName = row.firstName
  editActor.value.lastName = row.lastName
}
const toggleEditSave = async () => {
  const response = await editActorApi(editActor.value)
  console.log(response)
  toggleFetch()
  $q.notify({
    message: "update successfully",
    color: 'secondary'
  })
}
const toggleDelete = async (row) => {
  $q.dialog({
    title: 'Confirm',
    message: 'Would you like to delete it?',
    cancel: true,
    persistent: true
  }).onOk( async () =>{
  console.log('@TODO: toggleDelete, 删除用户')
    console.log(row.id)
    const response = await deleteActorApi(row.id)
    console.log(response)
    $q.notify({
      message: "delete success",
      color: "secondary"
    })
    toggleFetch()
  })
}

const columns = [
  {
    name: 'id', label: 'id', field: 'id', sortable: true
  },
  {
    name: 'firstName', label: '名', field: 'firstName', sortable: true
  },
  {
    name: 'lastName', label: '姓', field: 'lastName', sortable: true
  },
  {
    name: 'lastUpdate', label: '更新时间', field: 'lastUpdate', sortable: true
  },
  {
    name: 'actions', label: '操作', field: 'actions', sortable: false
  }
]
const actorStore = useActorStore()

const toggleFetch = async () => {
  try{
  const response = await fetchActorApi();
  let actors = response.data.actorList
  console.log('发现时间戳')
  console.log(actors[0])
  let isArr = actors instanceof Array
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
