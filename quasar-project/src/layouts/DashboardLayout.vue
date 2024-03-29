<template>
  <q-layout view="hHh Lpr fFf"> <!-- Be sure to play with the Layout demo on docs -->

    <!-- (Optional) The Header -->
    <q-header elevated>
      <q-toolbar>
        <q-btn flat round dense icon="menu" @click="toggleLeftDrawer" />

        <q-toolbar-title>
          Quasar App
        </q-toolbar-title>
      </q-toolbar>

    </q-header>

    <!-- (Optional) The Footer -->
    <!-- <q-footer>
      <q-tabs switch-indicator>
        <q-route-tab
          icon="map"
          to="/your/route"
          replace
          label="One Tab"
        />
        <q-route-tab
          icon="assignment"
          to="/some/other/route"
          replace
          label="Other Tab"
        />
      </q-tabs>

      <q-toolbar>
        <q-btn
          flat
          round
          dense
          icon="menu"
          @click="toggleLeftDrawer"
        />
        <q-toolbar-title>
          Footer
        </q-toolbar-title>
      </q-toolbar>
    </q-footer> -->

    <!-- (Optional) A Drawer; you can add one more with side="right" or change this one's side -->
    <q-drawer v-model="leftDrawerOpen" side="left" bordered class="bg-grey-2">
      <!-- QScrollArea is optional -->
      <q-scroll-area class="fit q-pa-sm">
        <!-- Content here -->
        <div class="q-pa-sm">
          <div v-for="item in menuList" :key="item">
            <EssentialLink
              :title = item.title 
              :caption = item.caption  
              :link = item.link
              :icon = item.icon
           />
          </div>
        </div>

      </q-scroll-area>
    </q-drawer>

    <q-page-container>
      <!-- This is where pages get injected -->
      <router-view />
    </q-page-container>

  </q-layout>
</template>

<script setup>
import { ref } from 'vue'

import EssentialLink from 'src/components/EssentialLink.vue';

const leftDrawerOpen = ref(true)

const toggleLeftDrawer = () => {
  leftDrawerOpen.value = !leftDrawerOpen.value
}
const menuList = ref([
  {
    title: "home",
    caption: "首页",
    link: "/dashboard",
    icon: "home"
  },
  {
    title: "actor",
    caption: "演员表",
    link: "/dashboard/actors",
    icon: "face"
  },
  {
    title: "film",
    caption: "影集库",
    link: "/dashboard/films",
    icon: "movie"
  },
  {
    title: "user",
    caption: "用户表",
    link: "/dashboard/users",
    icon: "person"
  },
])
</script>
