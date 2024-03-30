import {defineStore} from 'pinia'

export const useUserStore = defineStore('user', {
  state: () => ({
    users: {},
  }),
  getters: {
    getUsers: (state) => Array.prototype.slice.call(state.users)

  },
  actions: {
    setUsers(users) {
      this.users = users
    },

  }
})
