<template>
  <q-page padding>
    <!-- content -->
    Welcome to user page
  <div class="q-pa-md q-gutter-sm">
    <q-btn round color="primary" icon="cached" @click="toggleFetch"/>
  </div>

  <q-table
    flat bordered
    title="用户角色表"
    :rows = "userStore.getUsers"
    :columns = "columns"
    row-key="email"
    virtual-scroll
  >
  <template v-slot:body="props">
    <q-tr :props = "props">
      <q-td key="email" :props="props">
        {{ props.row.email }}
      </q-td>
      <q-td key="admin" :props="props">
        {{ props.row.roles.some((item)=>item.id==1) ? "1" : "0" }}
      </q-td>
      <q-td key="user" :props="props">
        {{ props.row.roles.some((item)=>item.id==2) ? "1" : "0" }}
      </q-td>
      <q-td key="actions" :props="props">
        <q-btn color="blue" dense flat round icon="edit" @click="toggleEdit(props.row)" size=sm></q-btn>
      </q-td>
    </q-tr>
  </template>

  </q-table>

    <!-- <div>
     {{ users }}
    </div> -->
  </q-page>
</template>

<script setup>
import { ref } from 'vue';

import {fetchUserApi } from 'src/api/user'

import {useUserStore} from 'src/stores/user-store'

const userStore = useUserStore()


const columns = [
  {
    name: 'email', label: '邮箱', field: "email", sortable: true
  },
  {
    name: 'admin', label: '管理员', field: "admin"
  },
  {
    name: 'user', label: '用户' , field: "user"
  },
  {
    name: 'actions', label: '操作', field: 'actions'
  }
]

const users = ref()

const toggleFetch = async() => {
  const response = await fetchUserApi();
  console.log(response);
  users.value = response.data.userList
  userStore.setUsers(response.data.userList)
}

const toggleEdit = () => {
  console.log("@TODO: toggleEdit")
}

</script>

