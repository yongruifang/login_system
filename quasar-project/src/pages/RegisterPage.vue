<template>
<q-layout view="lHh Lpr lFf">
    <q-page-container>
      <q-page class="flex flex-center bg-grey-2">
        <q-card class="q-pa-md shadow-2 my_card" bordered>
          <q-card-section class="text-center">
            <div class="text-grey-9 text-h5 text-weight-bold">Sign up</div>
            <div class="text-grey-8">Sign up below to create your account</div>
          </q-card-section>
          <q-card-section>
            <q-input dense outlined class="q-mb-md" v-model="regFirstName" label="First Name"></q-input>
            <q-input dense outlined class="q-mb-md" v-model="regLastName" label="Last Name"></q-input>
            <q-input dense outlined v-model="regEmail" label="Email Address"></q-input>
            <q-input dense outlined class="q-mt-md" v-model="regPassword" type="password" label="Password"></q-input>
          </q-card-section>
          <q-card-section>
            <q-btn style="
  border-radius: 8px;" color="dark" rounded size="md" label="Sign up" no-caps class="full-width" @click="register"></q-btn>
          </q-card-section>
          <q-card-section class="text-center q-pt-none">
            <div class="text-grey-8">Already have an account ?
              <router-link to="/" class="text-dark text-weight-bold" style="text-decoration: none">
                Sign in.
              </router-link>
            </div>
          </q-card-section>
        </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import {ref , onMounted} from 'vue'
import { useTokenStore } from 'src/stores/token-store'
import { registerApi } from 'src/api/auth'
import { useQuasar } from 'quasar';
// import route from 'src/router'
import { useRouter } from 'vue-router';

// const router = route()
const router = useRouter()
const $q = useQuasar()
const tokenStore = useTokenStore()
const regFirstName = ref("")
const regLastName = ref("")
const regEmail = ref("")
const regPassword = ref("")


const register = async () => {
  const response = await registerApi({
    firstName: regFirstName.value,
    lastName: regLastName.value,
    email: regEmail.value,
    password: regPassword.value,
  })
  console.log(response)
  const { message, status} = response.data
  if(status == "FAILURE"){
    $q.notify({
      message: response.data.message,
      color: "negative"
    })
    // Q: push后页面不刷新？
    // A: 不应该import 'src/route'，应该import from 'useRouter'
    // router.push({path: '/'})
  }else if(status == "SUCCESS") {
    $q.notify({
      message: response.data.message,
      color: "secondary"
    })
    router.push({path: '/'})
  }
}

const initFormData = () => {
  regFirstName.value = ""
  regLastName.value = ""
  regEmail.value = ""
  regPassword.value = ""
}
onMounted(()=>{
//  initFormData() 
})
</script>
