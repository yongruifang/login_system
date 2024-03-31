<template>
  <q-page padding>
    <!-- content -->
    <q-banner class="bg-red text-white q-my-md">
      Note: Only admin can access user page. <b>(权限路由)</b>
    </q-banner>

  <q-table
    flat bordered
    title="用户角色表"
    :rows = "userStore.getUsers"
    :columns = "columns"
    row-key="email"
    virtual-scroll
  >
  <template v-slot:top-right>
    <q-btn round color="primary" icon="cached" @click="toggleFetch">
        <q-tooltip>
          refresh
        </q-tooltip>
    </q-btn>
  </template>
  <template v-slot:body="props">
    <q-tr :props = "props">
      <q-td key="email" :props="props">
        {{ props.row.email }}
      </q-td>
      <q-td key="admin" :props="props">
        {{ props.row.roles.some((item)=>item.id==1) ? "✔️" : "❌" }}
      </q-td>
      <q-td key="user" :props="props">
        {{ props.row.roles.some((item)=>item.id==2) ? "✔️" : "❌" }}
      </q-td>
      <q-td key="actions" :props="props">
        <q-btn color="blue" dense flat round icon="edit" @click="toggleEdit(props.row)" size=sm></q-btn>
      </q-td>
    </q-tr>
  </template>

  </q-table>
    <div class="q-pa-sm q-gutter-sm">
          <q-dialog v-model="show_edit_dialog">
          <q-card>
            <q-card-section>
              <div class="text-h6">修改用户权限</div>
            </q-card-section>
            <q-card-section>
             <div class="text-h6">{{ edit_user_email }}</div>
             <div class="col">
                <q-checkbox v-model="edit_user_admin" label="管理员"></q-checkbox>
                <q-checkbox v-model="edit_user_user" label="用户"></q-checkbox>
             </div>
            </q-card-section>
            <q-card-section align="right">
                <q-btn flat label="OK" color="primary" v-close-popup @click="toggleEditSave" ></q-btn>
            </q-card-section>
          </q-card>
          </q-dialog>
        </div>
    <!-- <div>
     {{ users }}
    </div> -->
  </q-page>
</template>

<script setup>
import { ref , onMounted} from 'vue';

import {fetchUserApi, setUserRoleApi } from 'src/api/user'

import {useUserStore} from 'src/stores/user-store'

import { useQuasar } from 'quasar';

const $q = useQuasar()
const userStore = useUserStore()
const show_edit_dialog = ref(false)

const edit_user_email = ref()
const edit_user_admin = ref()
const edit_user_user = ref()


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

const toggleEdit = (row) => {
  show_edit_dialog.value = true
  edit_user_email.value  = row.email
  edit_user_admin.value = row.roles.some((item)=>item.id==1)
  edit_user_user.value = row.roles.some((item)=>item.id==2)
}
const toggleEditSave = async () => {
  console.log("@TODO: edit save", edit_user_admin.value, edit_user_user.value)
  const response = await setUserRoleApi({
    email: edit_user_email.value ,
    is_admin: edit_user_admin.value,
    is_user: edit_user_user.value
  })
  console.log(response)
  $q.notify({
    message: "修改成功",
    color: "primary"
  })
  toggleFetch()
}

onMounted(()=>{
  toggleFetch()
})

</script>

