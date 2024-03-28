import { defineStore } from 'pinia'
import { LocalStorage } from 'quasar'

export const useTokenStore = defineStore('token', {
    state: () => ({
        token: LocalStorage.getItem('token') || '', 
    }), 
    getters: {
        getToken: (state) => state.token,
    },
    actions: {
        setToken(token) {
            this.token = token
            LocalStorage.set('token', token)
        },
        clearToken(token) {
            this.token = ""
            localStorage.removeItem('token')
        }
    }
})