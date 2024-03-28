<template>
<q-layout view="lHh Lpr lFf">
    <q-page-container>
      <q-page class="flex flex-center bg-grey-2">
        <q-card class="q-pa-md shadow-2 my_card" bordered>
          <q-card-section class="text-center">
            <div class="text-grey-9 text-h5 text-weight-bold">Sign in</div>
            <div class="text-grey-8">Sign in below to access your account</div>
          </q-card-section>
          <q-card-section>
            <q-input dense outlined v-model="email" label="Email Address"></q-input>
            <q-input dense outlined class="q-mt-md" v-model="password" type="password" label="Password"></q-input>
          </q-card-section>
          <q-card-section>
            <q-btn style="
  border-radius: 8px;" color="dark" rounded size="md" label="Sign in" no-caps class="full-width" @click="login"></q-btn>
          </q-card-section>
          <q-card-section class="text-center q-pt-none">
            <div class="text-grey-8">Don't have an account yet?
              <a href="#" class="text-dark text-weight-bold" style="text-decoration: none">Sign
                up.</a></div>
          </q-card-section>
        </q-card>
      </q-page>
    </q-page-container>
  </q-layout>
</template>

<script setup>
import {ref} from 'vue'
import {loginApi} from 'src/api/auth'
import { useTokenStore } from 'src/stores/token-store'

const tokenStore = useTokenStore()
const email = ref("")
const password = ref("")
const login = async () => {
  console.log(`email: ${email.value}`)
  console.log(`password: ${password.value}`)
  try{
    const response = await loginApi({
      email: email.value,
      password: password.value
    });
    console.log(response)
    // 保存 Token 
    const accessToken = response.data.accessToken 
    // 保存到 Pinia中 ，还是在LocalStorage中呢？
    tokenStore.setToken(accessToken)
  }catch(error) {
    console.log(error)
  }
}

</script>
