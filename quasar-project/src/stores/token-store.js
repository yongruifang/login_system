import { defineStore } from 'pinia'
import { LocalStorage } from 'quasar'

export const useTokenStore = defineStore('token', {
    state: () => ({
        token: LocalStorage.getItem('token') || '', 
        roles: LocalStorage.getItem('roles') || null,
    }), 
    getters: {
        getToken: (state) => state.token,
    },
    actions: {
        setToken(token) {
            this.token = token
            LocalStorage.set('token', token)
        },
        setRoles(roles){
            this.roles = roles 
            LocalStorage.set('roles',  roles)
        },
        clearToken(token) {
            this.token = ""
            localStorage.removeItem('token')
        }
    }
})