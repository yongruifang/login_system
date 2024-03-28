<template>
  <router-view />
</template>

<script setup>
import { useRouter } from 'vue-router';
import { globalRouter } from 'src/router/globalRouter'
const router = useRouter()
globalRouter.router = router;
defineOptions({
  name: 'App'
});
</script>
