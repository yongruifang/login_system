<template>
  <q-page padding>
    Welcom to my Dashboard
    <!-- content -->
    <div class="q-pa-md q-gutter-sm">
      <q-btn @click="logout" :ripple="false" color="secondary" label="Logout" no-caps />
  </div>
  </q-page>
</template>

<script setup>
import { useTokenStore } from 'src/stores/token-store';
import { useRouter } from 'vue-router';
import { useQuasar } from 'quasar';

const $q = useQuasar()

const tokenStore = useTokenStore()
const router = useRouter()

const logout = () => {
  //console.log('@TODO: clear Token')
  tokenStore.clearToken()
  //console.log('@TODO: redirect')
  router.push("/")
  // @TODO: Notify
  $q.notify({
    message: "log out successfully! ",
    color: 'primary'
  })
  
}

</script>
